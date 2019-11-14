package java.lang.invoke;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.module.IllegalAccessLogger;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import jdk.internal.vm.annotation.ForceInline;
import sun.invoke.util.ValueConversions;
import sun.invoke.util.VerifyAccess;
import sun.invoke.util.Wrapper;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstants;
import java.lang.invoke.LambdaForm.BasicType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ReflectPermission;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.lang.invoke.MethodHandleImpl.Intrinsic;
import static java.lang.invoke.MethodHandleNatives.Constants.*;
import static java.lang.invoke.MethodHandleStatics.newIllegalArgumentException;
import static java.lang.invoke.MethodType.methodType;

public class MethodHandles {

    private MethodHandles() {
    }

    static final MemberName.Factory IMPL_NAMES = MemberName.getFactory();

    @CallerSensitive
    @ForceInline
    public static Lookup lookup();

    @CallerSensitive
    private static Lookup reflected$lookup();

    public static Lookup publicLookup();

    public static Lookup privateLookupIn(Class<?> targetClass, Lookup lookup) throws IllegalAccessException;

    public static <T extends Member> T reflectAs(Class<T> expected, MethodHandle target);

    private static final java.security.Permission ACCESS_PERMISSION = new ReflectPermission("suppressAccessChecks");

    public static final class Lookup {

        private final Class<?> lookupClass;

        private final int allowedModes;

        public static final int PUBLIC = Modifier.PUBLIC;

        public static final int PRIVATE = Modifier.PRIVATE;

        public static final int PROTECTED = Modifier.PROTECTED;

        public static final int PACKAGE = Modifier.STATIC;

        public static final int MODULE = PACKAGE << 1;

        public static final int UNCONDITIONAL = PACKAGE << 2;

        private static final int ALL_MODES = (PUBLIC | PRIVATE | PROTECTED | PACKAGE | MODULE | UNCONDITIONAL);

        private static final int FULL_POWER_MODES = (ALL_MODES & ~UNCONDITIONAL);

        private static final int TRUSTED = -1;

        private static int fixmods(int mods);

        public Class<?> lookupClass();

        private Class<?> lookupClassOrNull();

        public int lookupModes();

        Lookup(Class<?> lookupClass) {
            this(lookupClass, FULL_POWER_MODES);
            checkUnprivilegedlookupClass(lookupClass);
        }

        private Lookup(Class<?> lookupClass, int allowedModes) {
            this.lookupClass = lookupClass;
            this.allowedModes = allowedModes;
        }

        public Lookup in(Class<?> requestedLookupClass);

        public Lookup dropLookupMode(int modeToDrop);

        public Class<?> defineClass(byte[] bytes) throws IllegalAccessException;

        private ProtectionDomain lookupClassProtectionDomain();

        private ProtectionDomain protectionDomain(Class<?> clazz);

        private volatile ProtectionDomain cachedProtectionDomain;

        static {
            IMPL_NAMES.getClass();
        }

        static final Lookup IMPL_LOOKUP = new Lookup(Object.class, TRUSTED);

        static final Lookup PUBLIC_LOOKUP = new Lookup(Object.class, (PUBLIC | UNCONDITIONAL));

        private static void checkUnprivilegedlookupClass(Class<?> lookupClass);

        @Override
        public String toString();

        public MethodHandle findStatic(Class<?> refc, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException;

        public MethodHandle findVirtual(Class<?> refc, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException;

        private MethodHandle findVirtualForMH(String name, MethodType type);

        private MethodHandle findVirtualForVH(String name, MethodType type);

        public MethodHandle findConstructor(Class<?> refc, MethodType type) throws NoSuchMethodException, IllegalAccessException;

        public Class<?> findClass(String targetName) throws ClassNotFoundException, IllegalAccessException;

        public Class<?> accessClass(Class<?> targetClass) throws IllegalAccessException;

        public MethodHandle findSpecial(Class<?> refc, String name, MethodType type, Class<?> specialCaller) throws NoSuchMethodException, IllegalAccessException;

        public MethodHandle findGetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public MethodHandle findSetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public VarHandle findVarHandle(Class<?> recv, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public MethodHandle findStaticGetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public MethodHandle findStaticSetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public VarHandle findStaticVarHandle(Class<?> decl, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public MethodHandle bind(Object receiver, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException;

        public MethodHandle unreflect(Method m) throws IllegalAccessException;

        private MethodHandle unreflectForMH(Method m);

        private MethodHandle unreflectForVH(Method m);

        public MethodHandle unreflectSpecial(Method m, Class<?> specialCaller) throws IllegalAccessException;

        public MethodHandle unreflectConstructor(Constructor<?> c) throws IllegalAccessException;

        public MethodHandle unreflectGetter(Field f) throws IllegalAccessException;

        private MethodHandle unreflectField(Field f, boolean isSetter) throws IllegalAccessException;

        public MethodHandle unreflectSetter(Field f) throws IllegalAccessException;

        public VarHandle unreflectVarHandle(Field f) throws IllegalAccessException;

        public MethodHandleInfo revealDirect(MethodHandle target);

        MemberName resolveOrFail(byte refKind, Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        MemberName resolveOrFail(byte refKind, Class<?> refc, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException;

        MemberName resolveOrFail(byte refKind, MemberName member) throws ReflectiveOperationException;

        MemberName resolveOrNull(byte refKind, MemberName member);

        void checkSymbolicClass(Class<?> refc) throws IllegalAccessException;

        boolean isClassAccessible(Class<?> refc);

        void checkMethodName(byte refKind, String name) throws NoSuchMethodException;

        Class<?> findBoundCallerClass(MemberName m) throws IllegalAccessException;

        public boolean hasPrivateAccess();

        void checkSecurityManager(Class<?> refc, MemberName m);

        void checkMethod(byte refKind, Class<?> refc, MemberName m) throws IllegalAccessException;

        void checkField(byte refKind, Class<?> refc, MemberName m) throws IllegalAccessException;

        void checkAccess(byte refKind, Class<?> refc, MemberName m) throws IllegalAccessException;

        String accessFailedMessage(Class<?> refc, MemberName m);

        private void checkSpecialCaller(Class<?> specialCaller, Class<?> refc) throws IllegalAccessException;

        private boolean restrictProtectedReceiver(MemberName method);

        private MethodHandle restrictReceiver(MemberName method, DirectMethodHandle mh, Class<?> caller) throws IllegalAccessException;

        private MethodHandle getDirectMethod(byte refKind, Class<?> refc, MemberName method, Class<?> boundCallerClass) throws IllegalAccessException;

        private MethodHandle getDirectMethodNoRestrictInvokeSpecial(Class<?> refc, MemberName method, Class<?> boundCallerClass) throws IllegalAccessException;

        private MethodHandle getDirectMethodNoSecurityManager(byte refKind, Class<?> refc, MemberName method, Class<?> boundCallerClass) throws IllegalAccessException;

        private MethodHandle getDirectMethodCommon(byte refKind, Class<?> refc, MemberName method, boolean checkSecurity, boolean doRestrict, Class<?> boundCallerClass) throws IllegalAccessException;

        private MethodHandle maybeBindCaller(MemberName method, MethodHandle mh, Class<?> boundCallerClass) throws IllegalAccessException;

        private MethodHandle getDirectField(byte refKind, Class<?> refc, MemberName field) throws IllegalAccessException;

        private MethodHandle getDirectFieldNoSecurityManager(byte refKind, Class<?> refc, MemberName field) throws IllegalAccessException;

        private MethodHandle getDirectFieldCommon(byte refKind, Class<?> refc, MemberName field, boolean checkSecurity) throws IllegalAccessException;

        private VarHandle getFieldVarHandle(byte getRefKind, byte putRefKind, Class<?> refc, MemberName getField, MemberName putField) throws IllegalAccessException;

        private VarHandle getFieldVarHandleNoSecurityManager(byte getRefKind, byte putRefKind, Class<?> refc, MemberName getField, MemberName putField) throws IllegalAccessException;

        private VarHandle getFieldVarHandleCommon(byte getRefKind, byte putRefKind, Class<?> refc, MemberName getField, MemberName putField, boolean checkSecurity) throws IllegalAccessException;

        private MethodHandle getDirectConstructor(Class<?> refc, MemberName ctor) throws IllegalAccessException;

        private MethodHandle getDirectConstructorNoSecurityManager(Class<?> refc, MemberName ctor) throws IllegalAccessException;

        private MethodHandle getDirectConstructorCommon(Class<?> refc, MemberName ctor, boolean checkSecurity) throws IllegalAccessException;

        MethodHandle linkMethodHandleConstant(byte refKind, Class<?> defc, String name, Object type) throws ReflectiveOperationException;

        private boolean canBeCached(byte refKind, Class<?> defc, MemberName member);

        private MethodHandle getDirectMethodForConstant(byte refKind, Class<?> defc, MemberName member) throws ReflectiveOperationException;

        static ConcurrentHashMap<MemberName, DirectMethodHandle> LOOKASIDE_TABLE = new ConcurrentHashMap<>();
    }

    public static MethodHandle arrayConstructor(Class<?> arrayClass) throws IllegalArgumentException;

    public static MethodHandle arrayLength(Class<?> arrayClass) throws IllegalArgumentException;

    public static MethodHandle arrayElementGetter(Class<?> arrayClass) throws IllegalArgumentException;

    public static MethodHandle arrayElementSetter(Class<?> arrayClass) throws IllegalArgumentException;

    public static VarHandle arrayElementVarHandle(Class<?> arrayClass) throws IllegalArgumentException;

    public static VarHandle byteArrayViewVarHandle(Class<?> viewArrayClass, ByteOrder byteOrder) throws IllegalArgumentException;

    public static VarHandle byteBufferViewVarHandle(Class<?> viewArrayClass, ByteOrder byteOrder) throws IllegalArgumentException;

    public static MethodHandle spreadInvoker(MethodType type, int leadingArgCount);

    public static MethodHandle exactInvoker(MethodType type);

    public static MethodHandle invoker(MethodType type);

    static public MethodHandle varHandleExactInvoker(VarHandle.AccessMode accessMode, MethodType type);

    static public MethodHandle varHandleInvoker(VarHandle.AccessMode accessMode, MethodType type);

    static MethodHandle basicInvoker(MethodType type);

    public static MethodHandle explicitCastArguments(MethodHandle target, MethodType newType);

    private static void explicitCastArgumentsChecks(MethodHandle target, MethodType newType);

    public static MethodHandle permuteArguments(MethodHandle target, MethodType newType, int... reorder);

    private static int findFirstDupOrDrop(int[] reorder, int newArity);

    private static boolean permuteArgumentChecks(int[] reorder, MethodType newType, MethodType oldType);

    public static MethodHandle constant(Class<?> type, Object value);

    public static MethodHandle identity(Class<?> type);

    public static MethodHandle zero(Class<?> type);

    private static MethodHandle identityOrVoid(Class<?> type);

    public static MethodHandle empty(MethodType type);

    private static final MethodHandle[] IDENTITY_MHS = new MethodHandle[Wrapper.COUNT];

    private static MethodHandle makeIdentity(Class<?> ptype);

    private static MethodHandle zero(Wrapper btw, Class<?> rtype);

    private static final MethodHandle[] ZERO_MHS = new MethodHandle[Wrapper.COUNT];

    private static MethodHandle makeZero(Class<?> rtype);

    private static synchronized MethodHandle setCachedMethodHandle(MethodHandle[] cache, int pos, MethodHandle value);

    public static MethodHandle insertArguments(MethodHandle target, int pos, Object... values);

    private static BoundMethodHandle insertArgumentPrimitive(BoundMethodHandle result, int pos, Class<?> ptype, Object value);

    private static Class<?>[] insertArgumentsChecks(MethodHandle target, int insCount, int pos) throws RuntimeException;

    public static MethodHandle dropArguments(MethodHandle target, int pos, List<Class<?>> valueTypes);

    private static List<Class<?>> copyTypes(Object[] array);

    private static MethodHandle dropArguments0(MethodHandle target, int pos, List<Class<?>> valueTypes);

    private static int dropArgumentChecks(MethodType oldType, int pos, List<Class<?>> valueTypes);

    public static MethodHandle dropArguments(MethodHandle target, int pos, Class<?>... valueTypes);

    private static MethodHandle dropArgumentsToMatch(MethodHandle target, int skip, List<Class<?>> newTypes, int pos, boolean nullOnFailure);

    public static MethodHandle dropArgumentsToMatch(MethodHandle target, int skip, List<Class<?>> newTypes, int pos);

    public static MethodHandle filterArguments(MethodHandle target, int pos, MethodHandle... filters);

    static MethodHandle filterArgument(MethodHandle target, int pos, MethodHandle filter);

    private static void filterArgumentsCheckArity(MethodHandle target, int pos, MethodHandle[] filters);

    private static void filterArgumentChecks(MethodHandle target, int pos, MethodHandle filter) throws RuntimeException;

    public static MethodHandle collectArguments(MethodHandle target, int pos, MethodHandle filter);

    private static MethodType collectArgumentsChecks(MethodHandle target, int pos, MethodHandle filter) throws RuntimeException;

    public static MethodHandle filterReturnValue(MethodHandle target, MethodHandle filter);

    private static void filterReturnValueChecks(MethodType targetType, MethodType filterType) throws RuntimeException;

    public static MethodHandle foldArguments(MethodHandle target, MethodHandle combiner);

    public static MethodHandle foldArguments(MethodHandle target, int pos, MethodHandle combiner);

    static MethodHandle foldArguments(MethodHandle target, int pos, MethodHandle combiner, int... argPositions);

    private static Class<?> foldArgumentChecks(int foldPos, MethodType targetType, MethodType combinerType);

    private static Class<?> foldArgumentChecks(int foldPos, MethodType targetType, MethodType combinerType, int... argPos);

    public static MethodHandle guardWithTest(MethodHandle test, MethodHandle target, MethodHandle fallback);

    static <T> RuntimeException misMatchedTypes(String what, T t1, T t2);

    public static MethodHandle catchException(MethodHandle target, Class<? extends Throwable> exType, MethodHandle handler);

    public static MethodHandle throwException(Class<?> returnType, Class<? extends Throwable> exType);

    public static MethodHandle loop(MethodHandle[]... clauses);

    private static void loopChecks0(MethodHandle[][] clauses);

    private static void loopChecks1a(int i, MethodHandle in, MethodHandle st);

    private static List<Class<?>> longestParameterList(Stream<MethodHandle> mhs, int skipSize);

    private static List<Class<?>> longestParameterList(List<List<Class<?>>> lists);

    private static List<Class<?>> buildCommonSuffix(List<MethodHandle> init, List<MethodHandle> step, List<MethodHandle> pred, List<MethodHandle> fini, int cpSize);

    private static void loopChecks1b(List<MethodHandle> init, List<Class<?>> commonSuffix);

    private static void loopChecks1cd(List<MethodHandle> pred, List<MethodHandle> fini, Class<?> loopReturnType);

    private static void loopChecks2(List<MethodHandle> step, List<MethodHandle> pred, List<MethodHandle> fini, List<Class<?>> commonParameterSequence);

    private static List<MethodHandle> fillParameterTypes(List<MethodHandle> hs, final List<Class<?>> targetParams);

    private static List<MethodHandle> fixArities(List<MethodHandle> hs);

    public static MethodHandle whileLoop(MethodHandle init, MethodHandle pred, MethodHandle body);

    public static MethodHandle doWhileLoop(MethodHandle init, MethodHandle body, MethodHandle pred);

    private static void whileLoopChecks(MethodHandle init, MethodHandle pred, MethodHandle body);

    public static MethodHandle countedLoop(MethodHandle iterations, MethodHandle init, MethodHandle body);

    public static MethodHandle countedLoop(MethodHandle start, MethodHandle end, MethodHandle init, MethodHandle body);

    private static void countedLoopChecks(MethodHandle start, MethodHandle end, MethodHandle init, MethodHandle body);

    public static MethodHandle iteratedLoop(MethodHandle iterator, MethodHandle init, MethodHandle body);

    private static Class<?> iteratedLoopChecks(MethodHandle iterator, MethodHandle init, MethodHandle body);

    static MethodHandle swapArguments(MethodHandle mh, int i, int j);

    public static MethodHandle tryFinally(MethodHandle target, MethodHandle cleanup);

    private static void tryFinallyChecks(MethodHandle target, MethodHandle cleanup);
}
