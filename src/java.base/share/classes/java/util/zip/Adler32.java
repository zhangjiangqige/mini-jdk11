package java.util.zip;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.ByteBuffer;
import sun.nio.ch.DirectBuffer;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
public class Adler32 implements Checksum {

    private int adler = 1;

    public Adler32() {
    }

    @Override
    public void update(int b);

    @Override
    public void update(byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len);

    @Override
    public void update(ByteBuffer buffer);

    @Override
    public void reset();

    @Override
    public long getValue();

    private static native int update(int adler, int b);

    @HotSpotIntrinsicCandidate
    private static native int updateBytes(int adler, byte[] b, int off, int len);

    @HotSpotIntrinsicCandidate
    private static native int updateByteBuffer(int adler, long addr, int off, int len);

    static {
        ZipUtils.loadLibrary();
    }
}
