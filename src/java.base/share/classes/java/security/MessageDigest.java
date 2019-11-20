package java.security;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import sun.security.util.Debug;
import sun.security.util.MessageDigestSpi2;
import javax.crypto.SecretKey;

@AnnotatedFor({ "nullness", "signedness" })
public abstract class MessageDigest extends MessageDigestSpi {

    protected MessageDigest(String algorithm) {
    }

    public static MessageDigest getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static MessageDigest getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static MessageDigest getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public final Provider getProvider();

    public void update(byte input);

    public void update(@PolySigned byte[] input, int offset, int len);

    public void update(@PolySigned byte[] input);

    public final void update(ByteBuffer input);

    @PolySigned
    public byte[] digest();

    public int digest(@PolySigned byte[] buf, int offset, int len) throws DigestException;

    @PolySigned
    public byte[] digest(@PolySigned byte[] input);

    public String toString();

    public static boolean isEqual(byte[] digesta, byte[] digestb);

    public void reset();

    public final String getAlgorithm();

    public final int getDigestLength();

    public Object clone() throws CloneNotSupportedException;
}
