package java.util.zip;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.Cleaner.Cleanable;
import java.lang.ref.Reference;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Objects;
import jdk.internal.ref.CleanerFactory;
import sun.nio.ch.DirectBuffer;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Deflater {

    private final DeflaterZStreamRef zsRef;

    private ByteBuffer input = ZipUtils.defaultBuf;

    private byte[] inputArray;

    private int inputPos, inputLim;

    private int level, strategy;

    private boolean setParams;

    private boolean finish, finished;

    private long bytesRead;

    private long bytesWritten;

    public static final int DEFLATED = 8;

    public static final int NO_COMPRESSION = 0;

    public static final int BEST_SPEED = 1;

    public static final int BEST_COMPRESSION = 9;

    public static final int DEFAULT_COMPRESSION = -1;

    public static final int FILTERED = 1;

    public static final int HUFFMAN_ONLY = 2;

    public static final int DEFAULT_STRATEGY = 0;

    public static final int NO_FLUSH = 0;

    public static final int SYNC_FLUSH = 2;

    public static final int FULL_FLUSH = 3;

    private static final int FINISH = 4;

    static {
        ZipUtils.loadLibrary();
    }

    public Deflater(int level, boolean nowrap) {
        this.level = level;
        this.strategy = DEFAULT_STRATEGY;
        this.zsRef = DeflaterZStreamRef.get(this, init(level, DEFAULT_STRATEGY, nowrap));
    }

    public Deflater(int level) {
        this(level, false);
    }

    public Deflater() {
        this(DEFAULT_COMPRESSION, false);
    }

    public void setInput(byte[] input, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len);

    public void setInput(byte[] input);

    public void setInput(ByteBuffer input);

    public void setDictionary(byte[] dictionary, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len);

    public void setDictionary(byte[] dictionary);

    public void setDictionary(ByteBuffer dictionary);

    public void setStrategy(int strategy);

    public void setLevel(int level);

    public boolean needsInput();

    public void finish();

    public boolean finished();

    @GTENegativeOne
    public int deflate(byte[] output, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len);

    @GTENegativeOne
    public int deflate(byte[] output);

    public int deflate(ByteBuffer output);

    @GTENegativeOne
    public int deflate(byte[] output, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len, int flush);

    public int deflate(ByteBuffer output, int flush);

    public int getAdler();

    public int getTotalIn();

    public long getBytesRead();

    public int getTotalOut();

    public long getBytesWritten();

    public void reset();

    public void end();

    @Deprecated(since = "9", forRemoval = true)
    protected void finalize();

    private void ensureOpen();

    private static native long init(int level, int strategy, boolean nowrap);

    private static native void setDictionary(long addr, byte[] b, int off, int len);

    private static native void setDictionaryBuffer(long addr, long bufAddress, int len);

    private native long deflateBytesBytes(long addr, byte[] inputArray, int inputOff, int inputLen, byte[] outputArray, int outputOff, int outputLen, int flush, int params);

    private native long deflateBytesBuffer(long addr, byte[] inputArray, int inputOff, int inputLen, long outputAddress, int outputLen, int flush, int params);

    private native long deflateBufferBytes(long addr, long inputAddress, int inputLen, byte[] outputArray, int outputOff, int outputLen, int flush, int params);

    private native long deflateBufferBuffer(long addr, long inputAddress, int inputLen, long outputAddress, int outputLen, int flush, int params);

    private static native int getAdler(long addr);

    private static native void reset(long addr);

    private static native void end(long addr);

    static class DeflaterZStreamRef implements Runnable {

        private long address;

        private final Cleanable cleanable;

        private DeflaterZStreamRef(Deflater owner, long addr) {
            this.cleanable = (owner != null) ? CleanerFactory.cleaner().register(owner, this) : null;
            this.address = addr;
        }

        long address();

        void clean();

        public synchronized void run();

        static DeflaterZStreamRef get(Deflater owner, long addr);

        private static class FinalizableZStreamRef extends DeflaterZStreamRef {

            final Deflater owner;

            FinalizableZStreamRef(Deflater owner, long addr) {
                super(null, addr);
                this.owner = owner;
            }

            @Override
            void clean();

            @Override
            @SuppressWarnings("deprecation")
            protected void finalize();
        }
    }
}
