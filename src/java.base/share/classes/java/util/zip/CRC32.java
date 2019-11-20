package java.util.zip;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.ByteBuffer;
import java.util.Objects;
import sun.nio.ch.DirectBuffer;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
public class CRC32 implements Checksum {

    public CRC32() {
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
}
