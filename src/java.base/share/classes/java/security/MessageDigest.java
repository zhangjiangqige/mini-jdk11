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

    private static final Debug pdebug = Debug.getInstance("provider", "Provider");

    private static final boolean skipDebug = Debug.isOn("engine=") && !Debug.isOn("messagedigest");

    private String algorithm;

    private static final int INITIAL = 0;

    private static final int IN_PROGRESS = 1;

    private int state = INITIAL;

    private Provider provider;

    protected MessageDigest(String algorithm) {
        this.algorithm = algorithm;
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

    private String getProviderName();

    public String toString();

    public static boolean isEqual(byte[] digesta, byte[] digestb);

    public void reset();

    public final String getAlgorithm();

    public final int getDigestLength();

    public Object clone() throws CloneNotSupportedException;

    static class Delegate extends MessageDigest implements MessageDigestSpi2 {

        private MessageDigestSpi digestSpi;

        public Delegate(MessageDigestSpi digestSpi, String algorithm) {
            super(algorithm);
            this.digestSpi = digestSpi;
        }

        public Object clone() throws CloneNotSupportedException;

        protected int engineGetDigestLength();

        protected void engineUpdate(byte input);

        protected void engineUpdate(@PolySigned byte[] input, int offset, int len);

        protected void engineUpdate(ByteBuffer input);

        public void engineUpdate(SecretKey key) throws InvalidKeyException;

        @PolySigned
        protected byte[] engineDigest();

        protected int engineDigest(@PolySigned byte[] buf, int offset, int len) throws DigestException;

        protected void engineReset();
    }
}
