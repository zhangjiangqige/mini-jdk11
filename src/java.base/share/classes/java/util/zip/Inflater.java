package java.util.zip;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.Cleaner.Cleanable;
import java.lang.ref.Reference;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Objects;
import jdk.internal.ref.CleanerFactory;
import sun.nio.ch.DirectBuffer;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
public class Inflater {

    private final InflaterZStreamRef zsRef;

    private ByteBuffer input = ZipUtils.defaultBuf;

    private byte[] inputArray;

    private int inputPos, inputLim;

    private boolean finished;

    private boolean needDict;

    private long bytesRead;

    private long bytesWritten;

    private int inputConsumed;

    private int outputConsumed;

    static {
        ZipUtils.loadLibrary();
        initIDs();
    }

    public Inflater(boolean nowrap) {
        this.zsRef = InflaterZStreamRef.get(this, init(nowrap));
    }

    public Inflater() {
        this(false);
    }

    public void setInput(byte[] input, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len);

    public void setInput(byte[] input);

    public void setInput(ByteBuffer input);

    public void setDictionary(byte[] dictionary, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len);

    public void setDictionary(byte[] dictionary);

    public void setDictionary(ByteBuffer dictionary);

    @NonNegative
    public int getRemaining();

    public boolean needsInput();

    public boolean needsDictionary();

    public boolean finished();

    @GTENegativeOne
    public int inflate(byte[] output, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws DataFormatException;

    @GTENegativeOne
    public int inflate(byte[] output) throws DataFormatException;

    public int inflate(ByteBuffer output) throws DataFormatException;

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

    private static native void initIDs();

    private static native long init(boolean nowrap);

    private static native void setDictionary(long addr, byte[] b, int off, int len);

    private static native void setDictionaryBuffer(long addr, long bufAddress, int len);

    private native long inflateBytesBytes(long addr, byte[] inputArray, int inputOff, int inputLen, byte[] outputArray, int outputOff, int outputLen) throws DataFormatException;

    private native long inflateBytesBuffer(long addr, byte[] inputArray, int inputOff, int inputLen, long outputAddress, int outputLen) throws DataFormatException;

    private native long inflateBufferBytes(long addr, long inputAddress, int inputLen, byte[] outputArray, int outputOff, int outputLen) throws DataFormatException;

    private native long inflateBufferBuffer(long addr, long inputAddress, int inputLen, long outputAddress, int outputLen) throws DataFormatException;

    private static native int getAdler(long addr);

    private static native void reset(long addr);

    private static native void end(long addr);

    static class InflaterZStreamRef implements Runnable {

        private long address;

        private final Cleanable cleanable;

        private InflaterZStreamRef(Inflater owner, long addr) {
            this.cleanable = (owner != null) ? CleanerFactory.cleaner().register(owner, this) : null;
            this.address = addr;
        }

        long address();

        void clean();

        public synchronized void run();

        static InflaterZStreamRef get(Inflater owner, long addr);

        private static class FinalizableZStreamRef extends InflaterZStreamRef {

            final Inflater owner;

            FinalizableZStreamRef(Inflater owner, long addr) {
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
