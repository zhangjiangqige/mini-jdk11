package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectStreamClass.WeakClassKey;
import java.lang.ref.ReferenceQueue;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import static java.io.ObjectStreamClass.processQueue;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "nullness", "index", "signedness" })
public class ObjectOutputStream extends OutputStream implements ObjectOutput, ObjectStreamConstants {

    private static class Caches {

        static final ConcurrentMap<WeakClassKey, Boolean> subclassAudits = new ConcurrentHashMap<>();

        static final ReferenceQueue<Class<?>> subclassAuditsQueue = new ReferenceQueue<>();
    }

    private final BlockDataOutputStream bout;

    private final HandleTable handles;

    private final ReplaceTable subs;

    private int protocol = PROTOCOL_VERSION_2;

    private int depth;

    private byte[] primVals;

    private final boolean enableOverride;

    private boolean enableReplace;

    private SerialCallbackContext curContext;

    private PutFieldImpl curPut;

    private final DebugTraceInfoStack debugInfoStack;

    private static final boolean extendedDebugInfo = java.security.AccessController.doPrivileged(new sun.security.action.GetBooleanAction("sun.io.serialization.extendedDebugInfo")).booleanValue();

    public ObjectOutputStream(OutputStream out) throws IOException {
        verifySubclass();
        bout = new BlockDataOutputStream(out);
        handles = new HandleTable(10, (float) 3.00);
        subs = new ReplaceTable(10, (float) 3.00);
        enableOverride = false;
        writeStreamHeader();
        bout.setBlockDataMode(true);
        if (extendedDebugInfo) {
            debugInfoStack = new DebugTraceInfoStack();
        } else {
            debugInfoStack = null;
        }
    }

    protected ObjectOutputStream() throws IOException, SecurityException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
        }
        bout = null;
        handles = null;
        subs = null;
        enableOverride = true;
        debugInfoStack = null;
    }

    public void useProtocolVersion(int version) throws IOException;

    public final void writeObject(@Nullable Object obj) throws IOException;

    protected void writeObjectOverride(Object obj) throws IOException;

    public void writeUnshared(@Nullable Object obj) throws IOException;

    public void defaultWriteObject() throws IOException;

    public ObjectOutputStream.PutField putFields() throws IOException;

    public void writeFields() throws IOException;

    public void reset() throws IOException;

    protected void annotateClass(Class<?> cl) throws IOException;

    protected void annotateProxyClass(Class<?> cl) throws IOException;

    protected Object replaceObject(Object obj) throws IOException;

    protected boolean enableReplaceObject(boolean enable) throws SecurityException;

    protected void writeStreamHeader() throws IOException;

    protected void writeClassDescriptor(ObjectStreamClass desc) throws IOException;

    public void write(@PolySigned int val) throws IOException;

    public void write(@PolySigned byte[] buf) throws IOException;

    public void write(@PolySigned byte[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void flush() throws IOException;

    protected void drain() throws IOException;

    public void close() throws IOException;

    public void writeBoolean(boolean val) throws IOException;

    public void writeByte(int val) throws IOException;

    public void writeShort(int val) throws IOException;

    public void writeChar(int val) throws IOException;

    public void writeInt(int val) throws IOException;

    public void writeLong(long val) throws IOException;

    public void writeFloat(float val) throws IOException;

    public void writeDouble(double val) throws IOException;

    public void writeBytes(String str) throws IOException;

    public void writeChars(String str) throws IOException;

    public void writeUTF(String str) throws IOException;

    public abstract static class PutField {

        public abstract void put(String name, boolean val);

        public abstract void put(String name, byte val);

        public abstract void put(String name, char val);

        public abstract void put(String name, short val);

        public abstract void put(String name, int val);

        public abstract void put(String name, long val);

        public abstract void put(String name, float val);

        public abstract void put(String name, double val);

        public abstract void put(String name, @Nullable Object val);

        @Deprecated
        public abstract void write(ObjectOutput out) throws IOException;
    }

    int getProtocolVersion();

    void writeTypeString(String str) throws IOException;

    private void verifySubclass();

    private static Boolean auditSubclass(Class<?> subcl);

    private void clear();

    private void writeObject0(Object obj, boolean unshared) throws IOException;

    private void writeNull() throws IOException;

    private void writeHandle(int handle) throws IOException;

    private void writeClass(Class<?> cl, boolean unshared) throws IOException;

    private void writeClassDesc(ObjectStreamClass desc, boolean unshared) throws IOException;

    private boolean isCustomSubclass();

    private void writeProxyDesc(ObjectStreamClass desc, boolean unshared) throws IOException;

    private void writeNonProxyDesc(ObjectStreamClass desc, boolean unshared) throws IOException;

    private void writeString(String str, boolean unshared) throws IOException;

    private void writeArray(Object array, ObjectStreamClass desc, boolean unshared) throws IOException;

    private void writeEnum(Enum<?> en, ObjectStreamClass desc, boolean unshared) throws IOException;

    private void writeOrdinaryObject(Object obj, ObjectStreamClass desc, boolean unshared) throws IOException;

    private void writeExternalData(Externalizable obj) throws IOException;

    private void writeSerialData(Object obj, ObjectStreamClass desc) throws IOException;

    private void defaultWriteFields(Object obj, ObjectStreamClass desc) throws IOException;

    private void writeFatalException(IOException ex) throws IOException;

    private static native void floatsToBytes(float[] src, int srcpos, byte[] dst, int dstpos, int nfloats);

    private static native void doublesToBytes(double[] src, int srcpos, byte[] dst, int dstpos, int ndoubles);

    private class PutFieldImpl extends PutField {

        private final ObjectStreamClass desc;

        private final byte[] primVals;

        private final Object[] objVals;

        PutFieldImpl(ObjectStreamClass desc) {
            this.desc = desc;
            primVals = new byte[desc.getPrimDataSize()];
            objVals = new Object[desc.getNumObjFields()];
        }

        public void put(String name, boolean val);

        public void put(String name, byte val);

        public void put(String name, char val);

        public void put(String name, short val);

        public void put(String name, int val);

        public void put(String name, float val);

        public void put(String name, long val);

        public void put(String name, double val);

        public void put(String name, Object val);

        public void write(ObjectOutput out) throws IOException;

        void writeFields() throws IOException;

        private int getFieldOffset(String name, Class<?> type);
    }

    private static class BlockDataOutputStream extends OutputStream implements DataOutput {

        private static final int MAX_BLOCK_SIZE = 1024;

        private static final int MAX_HEADER_SIZE = 5;

        private static final int CHAR_BUF_SIZE = 256;

        private final byte[] buf = new byte[MAX_BLOCK_SIZE];

        private final byte[] hbuf = new byte[MAX_HEADER_SIZE];

        private final char[] cbuf = new char[CHAR_BUF_SIZE];

        private boolean blkmode = false;

        private int pos = 0;

        private final OutputStream out;

        private final DataOutputStream dout;

        BlockDataOutputStream(OutputStream out) {
            this.out = out;
            dout = new DataOutputStream(this);
        }

        boolean setBlockDataMode(boolean mode) throws IOException;

        boolean getBlockDataMode();

        public void write(int b) throws IOException;

        public void write(byte[] b) throws IOException;

        public void write(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

        public void flush() throws IOException;

        public void close() throws IOException;

        void write(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len, boolean copy) throws IOException;

        void drain() throws IOException;

        private void writeBlockHeader(int len) throws IOException;

        public void writeBoolean(boolean v) throws IOException;

        public void writeByte(int v) throws IOException;

        public void writeChar(int v) throws IOException;

        public void writeShort(int v) throws IOException;

        public void writeInt(int v) throws IOException;

        public void writeFloat(float v) throws IOException;

        public void writeLong(long v) throws IOException;

        public void writeDouble(double v) throws IOException;

        public void writeBytes(String s) throws IOException;

        public void writeChars(String s) throws IOException;

        public void writeUTF(String s) throws IOException;

        void writeBooleans(boolean[] v, int off, int len) throws IOException;

        void writeChars(char[] v, int off, int len) throws IOException;

        void writeShorts(short[] v, int off, int len) throws IOException;

        void writeInts(int[] v, int off, int len) throws IOException;

        void writeFloats(float[] v, int off, int len) throws IOException;

        void writeLongs(long[] v, int off, int len) throws IOException;

        void writeDoubles(double[] v, int off, int len) throws IOException;

        long getUTFLength(String s);

        void writeUTF(String s, long utflen) throws IOException;

        void writeLongUTF(String s) throws IOException;

        void writeLongUTF(String s, long utflen) throws IOException;

        private void writeUTFBody(String s) throws IOException;
    }

    private static class HandleTable {

        private int size;

        private int threshold;

        private final float loadFactor;

        private int[] spine;

        private int[] next;

        private Object[] objs;

        HandleTable(int initialCapacity, float loadFactor) {
            this.loadFactor = loadFactor;
            spine = new int[initialCapacity];
            next = new int[initialCapacity];
            objs = new Object[initialCapacity];
            threshold = (int) (initialCapacity * loadFactor);
            clear();
        }

        int assign(Object obj);

        int lookup(Object obj);

        void clear();

        int size();

        private void insert(Object obj, int handle);

        private void growSpine();

        private void growEntries();

        private int hash(Object obj);
    }

    private static class ReplaceTable {

        private final HandleTable htab;

        private Object[] reps;

        ReplaceTable(int initialCapacity, float loadFactor) {
            htab = new HandleTable(initialCapacity, loadFactor);
            reps = new Object[initialCapacity];
        }

        void assign(Object obj, Object rep);

        Object lookup(Object obj);

        void clear();

        int size();

        private void grow();
    }

    private static class DebugTraceInfoStack {

        private final List<String> stack;

        DebugTraceInfoStack() {
            stack = new ArrayList<>();
        }

        void clear();

        void pop();

        void push(String entry);

        public String toString();
    }
}
