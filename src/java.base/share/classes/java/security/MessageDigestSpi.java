package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.ByteBuffer;
import sun.security.jca.JCAUtil;

@AnnotatedFor({ "interning", "signedness" })
@UsesObjectEquals
public abstract class MessageDigestSpi {

    private byte[] tempArray;

    protected int engineGetDigestLength();

    protected abstract void engineUpdate(byte input);

    protected abstract void engineUpdate(@PolySigned byte[] input, int offset, int len);

    protected void engineUpdate(ByteBuffer input);

    @PolySigned
    protected abstract byte[] engineDigest();

    protected int engineDigest(@PolySigned byte[] buf, int offset, int len) throws DigestException;

    protected abstract void engineReset();

    public Object clone() throws CloneNotSupportedException;
}
