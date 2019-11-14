package java.util.zip;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.ByteBuffer;

@AnnotatedFor({ "index" })
public interface Checksum {

    public void update(int b);

    default public void update(byte[] b);

    public void update(byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len);

    default public void update(ByteBuffer buffer);

    public long getValue();

    public void reset();
}
