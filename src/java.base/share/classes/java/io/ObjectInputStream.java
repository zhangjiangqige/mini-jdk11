package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectStreamClass.WeakClassKey;
import java.lang.System.Logger;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import static java.io.ObjectStreamClass.processQueue;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.Unsafe;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "nullness", "index" })
public class ObjectInputStream extends InputStream implements ObjectInput, ObjectStreamConstants {

    private static final int NULL_HANDLE = -1;

    private static final Object unsharedMarker = new Object();

    private static final Map<String, Class<?>> primClasses = Map.of("boolean", boolean.class, "byte", byte.class, "char", char.class, "short", short.class, "int", int.class, "long", long.class, "float", float.class, "double", double.class, "void", void.class);

    private static class Caches {

        static final ConcurrentMap<WeakClassKey, Boolean> subclassAudits = new ConcurrentHashMap<>();

        static final ReferenceQueue<Class<?>> subclassAuditsQueue = new ReferenceQueue<>();
    }

    private static class Logging {

        static final System.Logger filterLogger;

        static {
            Logger filterLog = System.getLogger("java.io.serialization");
            filterLogger = (filterLog.isLoggable(Logger.Level.DEBUG) || filterLog.isLoggable(Logger.Level.TRACE)) ? filterLog : null;
        }
    }

    private final BlockDataInputStream bin;

    private final ValidationList vlist;

    private long depth;

    private long totalObjectRefs;

    private boolean closed;

    private final HandleTable handles;

    private int passHandle = NULL_HANDLE;

    private boolean defaultDataEnd = false;

    private final boolean enableOverride;

    private boolean enableResolve;

    private SerialCallbackContext curContext;

    private ObjectInputFilter serialFilter;

    public ObjectInputStream(InputStream in) throws IOException {
        verifySubclass();
        bin = new BlockDataInputStream(in);
        handles = new HandleTable(10);
        vlist = new ValidationList();
        serialFilter = ObjectInputFilter.Config.getSerialFilter();
        enableOverride = false;
        readStreamHeader();
        bin.setBlockDataMode(true);
    }

    protected ObjectInputStream() throws IOException, SecurityException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
        }
        bin = null;
        handles = null;
        vlist = null;
        serialFilter = ObjectInputFilter.Config.getSerialFilter();
        enableOverride = true;
    }

    public final Object readObject() throws IOException, ClassNotFoundException;

    protected Object readObjectOverride() throws IOException, ClassNotFoundException;

    public Object readUnshared() throws IOException, ClassNotFoundException;

    public void defaultReadObject() throws IOException, ClassNotFoundException;

    public ObjectInputStream.GetField readFields() throws IOException, ClassNotFoundException;

    public void registerValidation(ObjectInputValidation obj, int prio) throws NotActiveException, InvalidObjectException;

    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException;

    protected Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException;

    protected Object resolveObject(Object obj) throws IOException;

    protected boolean enableResolveObject(boolean enable) throws SecurityException;

    protected void readStreamHeader() throws IOException, StreamCorruptedException;

    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException;

    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public int available() throws IOException;

    public void close() throws IOException;

    public boolean readBoolean() throws IOException;

    public byte readByte() throws IOException;

    @NonNegative
    public int readUnsignedByte() throws IOException;

    public char readChar() throws IOException;

    public short readShort() throws IOException;

    @NonNegative
    public int readUnsignedShort() throws IOException;

    public int readInt() throws IOException;

    public long readLong() throws IOException;

    public float readFloat() throws IOException;

    public double readDouble() throws IOException;

    public void readFully(byte[] buf) throws IOException;

    public void readFully(byte[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public int skipBytes(@NonNegative int len) throws IOException;

    @Deprecated
    @Nullable
    public String readLine() throws IOException;

    public String readUTF() throws IOException;

    public final ObjectInputFilter getObjectInputFilter();

    public final void setObjectInputFilter(ObjectInputFilter filter);

    private void filterCheck(Class<?> clazz, int arrayLength) throws InvalidClassException;

    private void checkArray(Class<?> arrayType, int arrayLength) throws InvalidClassException;

    public abstract static class GetField {

        public abstract ObjectStreamClass getObjectStreamClass();

        public abstract boolean defaulted(String name) throws IOException;

        public abstract boolean get(String name, boolean val) throws IOException;

        public abstract byte get(String name, byte val) throws IOException;

        public abstract char get(String name, char val) throws IOException;

        public abstract short get(String name, short val) throws IOException;

        public abstract int get(String name, int val) throws IOException;

        public abstract long get(String name, long val) throws IOException;

        public abstract float get(String name, float val) throws IOException;

        public abstract double get(String name, double val) throws IOException;

        @Nullable
        public abstract Object get(String name, @Nullable Object val) throws IOException;
    }

    private void verifySubclass();

    private static Boolean auditSubclass(Class<?> subcl);

    private void clear();

    private Object readObject0(boolean unshared) throws IOException;

    private Object checkResolve(Object obj) throws IOException;

    String readTypeString() throws IOException;

    private Object readNull() throws IOException;

    private Object readHandle(boolean unshared) throws IOException;

    private Class<?> readClass(boolean unshared) throws IOException;

    private ObjectStreamClass readClassDesc(boolean unshared) throws IOException;

    private boolean isCustomSubclass();

    private ObjectStreamClass readProxyDesc(boolean unshared) throws IOException;

    private ObjectStreamClass readNonProxyDesc(boolean unshared) throws IOException;

    private String readString(boolean unshared) throws IOException;

    private Object readArray(boolean unshared) throws IOException;

    private Enum<?> readEnum(boolean unshared) throws IOException;

    private Object readOrdinaryObject(boolean unshared) throws IOException;

    private void readExternalData(Externalizable obj, ObjectStreamClass desc) throws IOException;

    private void readSerialData(Object obj, ObjectStreamClass desc) throws IOException;

    private void skipCustomData() throws IOException;

    private class FieldValues {

        final byte[] primValues;

        final Object[] objValues;

        FieldValues(byte[] primValues, Object[] objValues) {
            this.primValues = primValues;
            this.objValues = objValues;
        }
    }

    private FieldValues defaultReadFields(Object obj, ObjectStreamClass desc) throws IOException;

    private void defaultCheckFieldValues(Object obj, ObjectStreamClass desc, FieldValues values);

    private void defaultSetFieldValues(Object obj, ObjectStreamClass desc, FieldValues values);

    private IOException readFatalException() throws IOException;

    private void handleReset() throws StreamCorruptedException;

    private static native void bytesToFloats(byte[] src, int srcpos, float[] dst, int dstpos, int nfloats);

    private static native void bytesToDoubles(byte[] src, int srcpos, double[] dst, int dstpos, int ndoubles);

    private static ClassLoader latestUserDefinedLoader();

    private class GetFieldImpl extends GetField {

        private final ObjectStreamClass desc;

        private final byte[] primVals;

        private final Object[] objVals;

        private final int[] objHandles;

        GetFieldImpl(ObjectStreamClass desc) {
            this.desc = desc;
            primVals = new byte[desc.getPrimDataSize()];
            objVals = new Object[desc.getNumObjFields()];
            objHandles = new int[objVals.length];
        }

        public ObjectStreamClass getObjectStreamClass();

        public boolean defaulted(String name) throws IOException;

        public boolean get(String name, boolean val) throws IOException;

        public byte get(String name, byte val) throws IOException;

        public char get(String name, char val) throws IOException;

        public short get(String name, short val) throws IOException;

        public int get(String name, int val) throws IOException;

        public float get(String name, float val) throws IOException;

        public long get(String name, long val) throws IOException;

        public double get(String name, double val) throws IOException;

        public Object get(String name, Object val) throws IOException;

        void readFields() throws IOException;

        private int getFieldOffset(String name, Class<?> type);
    }

    private static class ValidationList {

        private static class Callback {

            final ObjectInputValidation obj;

            final int priority;

            Callback next;

            final AccessControlContext acc;

            Callback(ObjectInputValidation obj, int priority, Callback next, AccessControlContext acc) {
                this.obj = obj;
                this.priority = priority;
                this.next = next;
                this.acc = acc;
            }
        }

        private Callback list;

        ValidationList() {
        }

        void register(ObjectInputValidation obj, int priority) throws InvalidObjectException;

        void doCallbacks() throws InvalidObjectException;

        public void clear();
    }

    static class FilterValues implements ObjectInputFilter.FilterInfo {

        final Class<?> clazz;

        final long arrayLength;

        final long totalObjectRefs;

        final long depth;

        final long streamBytes;

        public FilterValues(Class<?> clazz, long arrayLength, long totalObjectRefs, long depth, long streamBytes) {
            this.clazz = clazz;
            this.arrayLength = arrayLength;
            this.totalObjectRefs = totalObjectRefs;
            this.depth = depth;
            this.streamBytes = streamBytes;
        }

        @Override
        public Class<?> serialClass();

        @Override
        public long arrayLength();

        @Override
        public long references();

        @Override
        public long depth();

        @Override
        public long streamBytes();
    }

    private static class PeekInputStream extends InputStream {

        private final InputStream in;

        private int peekb = -1;

        private long totalBytesRead = 0;

        PeekInputStream(InputStream in) {
            this.in = in;
        }

        int peek() throws IOException;

        public int read() throws IOException;

        public int read(byte[] b, int off, int len) throws IOException;

        void readFully(byte[] b, int off, int len) throws IOException;

        public long skip(long n) throws IOException;

        public int available() throws IOException;

        public void close() throws IOException;

        public long getBytesRead();
    }

    private static final Unsafe UNSAFE = Unsafe.getUnsafe();

    private void freeze();

    private class BlockDataInputStream extends InputStream implements DataInput {

        private static final int MAX_BLOCK_SIZE = 1024;

        private static final int MAX_HEADER_SIZE = 5;

        private static final int CHAR_BUF_SIZE = 256;

        private static final int HEADER_BLOCKED = -2;

        private final byte[] buf = new byte[MAX_BLOCK_SIZE];

        private final byte[] hbuf = new byte[MAX_HEADER_SIZE];

        private final char[] cbuf = new char[CHAR_BUF_SIZE];

        private boolean blkmode = false;

        private int pos = 0;

        private int end = -1;

        private int unread = 0;

        private final PeekInputStream in;

        private final DataInputStream din;

        BlockDataInputStream(InputStream in) {
            this.in = new PeekInputStream(in);
            din = new DataInputStream(this);
        }

        boolean setBlockDataMode(boolean newmode) throws IOException;

        boolean getBlockDataMode();

        void skipBlockData() throws IOException;

        private int readBlockHeader(boolean canBlock) throws IOException;

        private void refill() throws IOException;

        int currentBlockRemaining();

        int peek() throws IOException;

        byte peekByte() throws IOException;

        public int read() throws IOException;

        public int read(byte[] b, int off, int len) throws IOException;

        public long skip(long len) throws IOException;

        public int available() throws IOException;

        public void close() throws IOException;

        int read(byte[] b, int off, int len, boolean copy) throws IOException;

        public void readFully(byte[] b) throws IOException;

        public void readFully(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

        public void readFully(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len, boolean copy) throws IOException;

        public int skipBytes(int n) throws IOException;

        public boolean readBoolean() throws IOException;

        public byte readByte() throws IOException;

        public int readUnsignedByte() throws IOException;

        public char readChar() throws IOException;

        public short readShort() throws IOException;

        public int readUnsignedShort() throws IOException;

        public int readInt() throws IOException;

        public float readFloat() throws IOException;

        public long readLong() throws IOException;

        public double readDouble() throws IOException;

        public String readUTF() throws IOException;

        @SuppressWarnings("deprecation")
        public String readLine() throws IOException;

        void readBooleans(boolean[] v, int off, int len) throws IOException;

        void readChars(char[] v, int off, int len) throws IOException;

        void readShorts(short[] v, int off, int len) throws IOException;

        void readInts(int[] v, int off, int len) throws IOException;

        void readFloats(float[] v, int off, int len) throws IOException;

        void readLongs(long[] v, int off, int len) throws IOException;

        void readDoubles(double[] v, int off, int len) throws IOException;

        String readLongUTF() throws IOException;

        private String readUTFBody(long utflen) throws IOException;

        private long readUTFSpan(StringBuilder sbuf, long utflen) throws IOException;

        private int readUTFChar(StringBuilder sbuf, long utflen) throws IOException;

        long getBytesRead();
    }

    private static class HandleTable {

        private static final byte STATUS_OK = 1;

        private static final byte STATUS_UNKNOWN = 2;

        private static final byte STATUS_EXCEPTION = 3;

        byte[] status;

        Object[] entries;

        HandleList[] deps;

        int lowDep = -1;

        int size = 0;

        HandleTable(int initialCapacity) {
            status = new byte[initialCapacity];
            entries = new Object[initialCapacity];
            deps = new HandleList[initialCapacity];
        }

        int assign(Object obj);

        void markDependency(int dependent, int target);

        void markException(int handle, ClassNotFoundException ex);

        void finish(int handle);

        void setObject(int handle, Object obj);

        Object lookupObject(int handle);

        ClassNotFoundException lookupException(int handle);

        void clear();

        int size();

        private void grow();

        private static class HandleList {

            private int[] list = new int[4];

            private int size = 0;

            public HandleList() {
            }

            public void add(int handle);

            public int get(int index);

            public int size();
        }
    }

    private static Object cloneArray(Object array);

    static {
        SharedSecrets.setJavaObjectInputStreamAccess(ObjectInputStream::checkArray);
    }
}
