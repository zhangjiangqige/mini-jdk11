package java.io;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import jdk.internal.misc.Unsafe;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import jdk.internal.reflect.ReflectionFactory;
import sun.reflect.misc.ReflectUtil;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.JavaSecurityAccess;
import static java.io.ObjectStreamField.*;

@AnnotatedFor({ "index", "lock", "nullness", "signature" })
public class ObjectStreamClass implements Serializable {

    public static final ObjectStreamField[] NO_FIELDS = new ObjectStreamField[0];

    private static final long serialVersionUID = -6120832682080437368L;

    private static final ObjectStreamField[] serialPersistentFields = NO_FIELDS;

    private static final ReflectionFactory reflFactory = AccessController.doPrivileged(new ReflectionFactory.GetReflectionFactoryAction());

    private static class Caches {

        static final ConcurrentMap<WeakClassKey, Reference<?>> localDescs = new ConcurrentHashMap<>();

        static final ConcurrentMap<FieldReflectorKey, Reference<?>> reflectors = new ConcurrentHashMap<>();

        private static final ReferenceQueue<Class<?>> localDescsQueue = new ReferenceQueue<>();

        private static final ReferenceQueue<Class<?>> reflectorsQueue = new ReferenceQueue<>();
    }

    private Class<?> cl;

    private String name;

    private volatile Long suid;

    private boolean isProxy;

    private boolean isEnum;

    private boolean serializable;

    private boolean externalizable;

    private boolean hasWriteObjectData;

    private boolean hasBlockExternalData = true;

    private static class ExceptionInfo {

        private final String className;

        private final String message;

        ExceptionInfo(String cn, String msg) {
            className = cn;
            message = msg;
        }

        InvalidClassException newInvalidClassException();
    }

    private ClassNotFoundException resolveEx;

    private ExceptionInfo deserializeEx;

    private ExceptionInfo serializeEx;

    private ExceptionInfo defaultSerializeEx;

    private ObjectStreamField[] fields;

    private int primDataSize;

    private int numObjFields;

    private FieldReflector fieldRefl;

    private volatile ClassDataSlot[] dataLayout;

    private Constructor<?> cons;

    private ProtectionDomain[] domains;

    private Method writeObjectMethod;

    private Method readObjectMethod;

    private Method readObjectNoDataMethod;

    private Method writeReplaceMethod;

    private Method readResolveMethod;

    private ObjectStreamClass localDesc;

    private ObjectStreamClass superDesc;

    private boolean initialized;

    private static native void initNative();

    static {
        initNative();
    }

    @Nullable
    public static ObjectStreamClass lookup(Class<?> cl);

    public static ObjectStreamClass lookupAny(Class<?> cl);

    @BinaryName
    public String getName();

    public long getSerialVersionUID();

    @CallerSensitive
    @Nullable
    public Class<?> forClass();

    public ObjectStreamField[] getFields();

    @Nullable
    public ObjectStreamField getField(String name);

    @SideEffectFree
    public String toString(@GuardSatisfied ObjectStreamClass this);

    static ObjectStreamClass lookup(Class<?> cl, boolean all);

    private static class EntryFuture {

        private static final Object unset = new Object();

        private final Thread owner = Thread.currentThread();

        private Object entry = unset;

        synchronized boolean set(Object entry);

        synchronized Object get();

        Thread getOwner();
    }

    private ObjectStreamClass(final Class<?> cl) {
        this.cl = cl;
        name = cl.getName();
        isProxy = Proxy.isProxyClass(cl);
        isEnum = Enum.class.isAssignableFrom(cl);
        serializable = Serializable.class.isAssignableFrom(cl);
        externalizable = Externalizable.class.isAssignableFrom(cl);
        Class<?> superCl = cl.getSuperclass();
        superDesc = (superCl != null) ? lookup(superCl, false) : null;
        localDesc = this;
        if (serializable) {
            AccessController.doPrivileged(new PrivilegedAction<>() {

                public Void run() {
                    if (isEnum) {
                        suid = Long.valueOf(0);
                        fields = NO_FIELDS;
                        return null;
                    }
                    if (cl.isArray()) {
                        fields = NO_FIELDS;
                        return null;
                    }
                    suid = getDeclaredSUID(cl);
                    try {
                        fields = getSerialFields(cl);
                        computeFieldOffsets();
                    } catch (InvalidClassException e) {
                        serializeEx = deserializeEx = new ExceptionInfo(e.classname, e.getMessage());
                        fields = NO_FIELDS;
                    }
                    if (externalizable) {
                        cons = getExternalizableConstructor(cl);
                    } else {
                        cons = getSerializableConstructor(cl);
                        writeObjectMethod = getPrivateMethod(cl, "writeObject", new Class<?>[] { ObjectOutputStream.class }, Void.TYPE);
                        readObjectMethod = getPrivateMethod(cl, "readObject", new Class<?>[] { ObjectInputStream.class }, Void.TYPE);
                        readObjectNoDataMethod = getPrivateMethod(cl, "readObjectNoData", null, Void.TYPE);
                        hasWriteObjectData = (writeObjectMethod != null);
                    }
                    domains = getProtectionDomains(cons, cl);
                    writeReplaceMethod = getInheritableMethod(cl, "writeReplace", null, Object.class);
                    readResolveMethod = getInheritableMethod(cl, "readResolve", null, Object.class);
                    return null;
                }
            });
        } else {
            suid = Long.valueOf(0);
            fields = NO_FIELDS;
        }
        try {
            fieldRefl = getReflector(fields, this);
        } catch (InvalidClassException ex) {
            throw new InternalError(ex);
        }
        if (deserializeEx == null) {
            if (isEnum) {
                deserializeEx = new ExceptionInfo(name, "enum type");
            } else if (cons == null) {
                deserializeEx = new ExceptionInfo(name, "no valid constructor");
            }
        }
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getField() == null) {
                defaultSerializeEx = new ExceptionInfo(name, "unmatched serializable field(s) declared");
            }
        }
        initialized = true;
    }

    ObjectStreamClass() {
    }

    private ProtectionDomain noPermissionsDomain();

    private ProtectionDomain[] getProtectionDomains(Constructor<?> cons, Class<?> cl);

    void initProxy(Class<?> cl, ClassNotFoundException resolveEx, ObjectStreamClass superDesc) throws InvalidClassException;

    void initNonProxy(ObjectStreamClass model, Class<?> cl, ClassNotFoundException resolveEx, ObjectStreamClass superDesc) throws InvalidClassException;

    void readNonProxy(ObjectInputStream in) throws IOException, ClassNotFoundException;

    void writeNonProxy(ObjectOutputStream out) throws IOException;

    ClassNotFoundException getResolveException();

    private final void requireInitialized();

    void checkDeserialize() throws InvalidClassException;

    void checkSerialize() throws InvalidClassException;

    void checkDefaultSerialize() throws InvalidClassException;

    ObjectStreamClass getSuperDesc();

    ObjectStreamClass getLocalDesc();

    ObjectStreamField[] getFields(boolean copy);

    ObjectStreamField getField(String name, Class<?> type);

    boolean isProxy();

    boolean isEnum();

    boolean isExternalizable();

    boolean isSerializable();

    boolean hasBlockExternalData();

    boolean hasWriteObjectData();

    boolean isInstantiable();

    boolean hasWriteObjectMethod();

    boolean hasReadObjectMethod();

    boolean hasReadObjectNoDataMethod();

    boolean hasWriteReplaceMethod();

    boolean hasReadResolveMethod();

    Object newInstance() throws InstantiationException, InvocationTargetException, UnsupportedOperationException;

    void invokeWriteObject(Object obj, ObjectOutputStream out) throws IOException, UnsupportedOperationException;

    void invokeReadObject(Object obj, ObjectInputStream in) throws ClassNotFoundException, IOException, UnsupportedOperationException;

    void invokeReadObjectNoData(Object obj) throws IOException, UnsupportedOperationException;

    Object invokeWriteReplace(Object obj) throws IOException, UnsupportedOperationException;

    Object invokeReadResolve(Object obj) throws IOException, UnsupportedOperationException;

    static class ClassDataSlot {

        final ObjectStreamClass desc;

        final boolean hasData;

        ClassDataSlot(ObjectStreamClass desc, boolean hasData) {
            this.desc = desc;
            this.hasData = hasData;
        }
    }

    ClassDataSlot[] getClassDataLayout() throws InvalidClassException;

    private ClassDataSlot[] getClassDataLayout0() throws InvalidClassException;

    int getPrimDataSize();

    int getNumObjFields();

    void getPrimFieldValues(Object obj, byte[] buf);

    void setPrimFieldValues(Object obj, byte[] buf);

    void getObjFieldValues(Object obj, Object[] vals);

    void checkObjFieldValueTypes(Object obj, Object[] vals);

    void setObjFieldValues(Object obj, Object[] vals);

    private void computeFieldOffsets() throws InvalidClassException;

    private ObjectStreamClass getVariantFor(Class<?> cl) throws InvalidClassException;

    private static Constructor<?> getExternalizableConstructor(Class<?> cl);

    private static Constructor<?> getSerializableConstructor(Class<?> cl);

    private static Method getInheritableMethod(Class<?> cl, String name, Class<?>[] argTypes, Class<?> returnType);

    private static Method getPrivateMethod(Class<?> cl, String name, Class<?>[] argTypes, Class<?> returnType);

    private static boolean packageEquals(Class<?> cl1, Class<?> cl2);

    private static boolean classNamesEqual(String name1, String name2);

    private static String getMethodSignature(Class<?>[] paramTypes, Class<?> retType);

    private static void throwMiscException(Throwable th) throws IOException;

    private static ObjectStreamField[] getSerialFields(Class<?> cl) throws InvalidClassException;

    private static ObjectStreamField[] getDeclaredSerialFields(Class<?> cl) throws InvalidClassException;

    private static ObjectStreamField[] getDefaultSerialFields(Class<?> cl);

    private static Long getDeclaredSUID(Class<?> cl);

    private static long computeDefaultSUID(Class<?> cl);

    private static native boolean hasStaticInitializer(Class<?> cl);

    private static class MemberSignature {

        public final Member member;

        public final String name;

        public final String signature;

        public MemberSignature(Field field) {
            member = field;
            name = field.getName();
            signature = getClassSignature(field.getType());
        }

        public MemberSignature(Constructor<?> cons) {
            member = cons;
            name = cons.getName();
            signature = getMethodSignature(cons.getParameterTypes(), Void.TYPE);
        }

        public MemberSignature(Method meth) {
            member = meth;
            name = meth.getName();
            signature = getMethodSignature(meth.getParameterTypes(), meth.getReturnType());
        }
    }

    private static class FieldReflector {

        private static final Unsafe unsafe = Unsafe.getUnsafe();

        private final ObjectStreamField[] fields;

        private final int numPrimFields;

        private final long[] readKeys;

        private final long[] writeKeys;

        private final int[] offsets;

        private final char[] typeCodes;

        private final Class<?>[] types;

        FieldReflector(ObjectStreamField[] fields) {
            this.fields = fields;
            int nfields = fields.length;
            readKeys = new long[nfields];
            writeKeys = new long[nfields];
            offsets = new int[nfields];
            typeCodes = new char[nfields];
            ArrayList<Class<?>> typeList = new ArrayList<>();
            Set<Long> usedKeys = new HashSet<>();
            for (int i = 0; i < nfields; i++) {
                ObjectStreamField f = fields[i];
                Field rf = f.getField();
                long key = (rf != null) ? unsafe.objectFieldOffset(rf) : Unsafe.INVALID_FIELD_OFFSET;
                readKeys[i] = key;
                writeKeys[i] = usedKeys.add(key) ? key : Unsafe.INVALID_FIELD_OFFSET;
                offsets[i] = f.getOffset();
                typeCodes[i] = f.getTypeCode();
                if (!f.isPrimitive()) {
                    typeList.add((rf != null) ? rf.getType() : null);
                }
            }
            types = typeList.toArray(new Class<?>[typeList.size()]);
            numPrimFields = nfields - types.length;
        }

        ObjectStreamField[] getFields();

        void getPrimFieldValues(Object obj, byte[] buf);

        void setPrimFieldValues(Object obj, byte[] buf);

        void getObjFieldValues(Object obj, Object[] vals);

        void checkObjectFieldValueTypes(Object obj, Object[] vals);

        void setObjFieldValues(Object obj, Object[] vals);

        private void setObjFieldValues(Object obj, Object[] vals, boolean dryRun);
    }

    private static FieldReflector getReflector(ObjectStreamField[] fields, ObjectStreamClass localDesc) throws InvalidClassException;

    private static class FieldReflectorKey extends WeakReference<Class<?>> {

        private final String sigs;

        private final int hash;

        private final boolean nullClass;

        FieldReflectorKey(Class<?> cl, ObjectStreamField[] fields, ReferenceQueue<Class<?>> queue) {
            super(cl, queue);
            nullClass = (cl == null);
            StringBuilder sbuf = new StringBuilder();
            for (int i = 0; i < fields.length; i++) {
                ObjectStreamField f = fields[i];
                sbuf.append(f.getName()).append(f.getSignature());
            }
            sigs = sbuf.toString();
            hash = System.identityHashCode(cl) + sigs.hashCode();
        }

        public int hashCode();

        public boolean equals(Object obj);
    }

    private static ObjectStreamField[] matchFields(ObjectStreamField[] fields, ObjectStreamClass localDesc) throws InvalidClassException;

    static void processQueue(ReferenceQueue<Class<?>> queue, ConcurrentMap<? extends WeakReference<Class<?>>, ?> map);

    static class WeakClassKey extends WeakReference<Class<?>> {

        private final int hash;

        WeakClassKey(Class<?> cl, ReferenceQueue<Class<?>> refQueue) {
            super(cl, refQueue);
            hash = System.identityHashCode(cl);
        }

        public int hashCode();

        public boolean equals(Object obj);
    }
}
