package java.security;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.util.regex.*;
import java.security.Provider.Service;
import sun.security.jca.*;
import sun.security.jca.GetInstance.Instance;
import sun.security.util.Debug;

@AnnotatedFor({ "signedness" })
public class SecureRandom extends java.util.Random {

    private static final Debug pdebug = Debug.getInstance("provider", "Provider");

    private static final boolean skipDebug = Debug.isOn("engine=") && !Debug.isOn("securerandom");

    private Provider provider = null;

    private SecureRandomSpi secureRandomSpi = null;

    private final boolean threadSafe;

    private String algorithm;

    private static volatile SecureRandom seedGenerator;

    public SecureRandom() {
        super(0);
        getDefaultPRNG(false, null);
        this.threadSafe = getThreadSafe();
    }

    private boolean getThreadSafe();

    public SecureRandom(byte[] seed) {
        super(0);
        getDefaultPRNG(true, seed);
        this.threadSafe = getThreadSafe();
    }

    private void getDefaultPRNG(boolean setSeed, byte[] seed);

    protected SecureRandom(SecureRandomSpi secureRandomSpi, Provider provider) {
        this(secureRandomSpi, provider, null);
    }

    private SecureRandom(SecureRandomSpi secureRandomSpi, Provider provider, String algorithm) {
        super(0);
        this.secureRandomSpi = secureRandomSpi;
        this.provider = provider;
        this.algorithm = algorithm;
        this.threadSafe = getThreadSafe();
        if (!skipDebug && pdebug != null) {
            pdebug.println("SecureRandom." + algorithm + " algorithm from: " + getProviderName());
        }
    }

    private String getProviderName();

    public static SecureRandom getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static SecureRandom getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static SecureRandom getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public static SecureRandom getInstance(String algorithm, SecureRandomParameters params) throws NoSuchAlgorithmException;

    public static SecureRandom getInstance(String algorithm, SecureRandomParameters params, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static SecureRandom getInstance(String algorithm, SecureRandomParameters params, Provider provider) throws NoSuchAlgorithmException;

    SecureRandomSpi getSecureRandomSpi();

    public final Provider getProvider();

    public String getAlgorithm();

    @Override
    public String toString();

    public SecureRandomParameters getParameters();

    public void setSeed(byte[] seed);

    @Override
    public void setSeed(long seed);

    @Override
    public void nextBytes(@PolySigned byte[] bytes);

    public void nextBytes(byte[] bytes, SecureRandomParameters params);

    @Override
    protected final int next(int numBits);

    public static byte[] getSeed(int numBytes);

    public byte[] generateSeed(int numBytes);

    private static byte[] longToByteArray(long l);

    private static String getPrngAlgorithm();

    private static final class StrongPatternHolder {

        private static Pattern pattern = Pattern.compile("\\s*([\\S&&[^:,]]*)(\\:([\\S&&[^,]]*))?\\s*(\\,(.*))?");
    }

    public static SecureRandom getInstanceStrong() throws NoSuchAlgorithmException;

    public void reseed();

    public void reseed(SecureRandomParameters params);

    static final long serialVersionUID = 4940670005562187L;

    private byte[] state;

    private MessageDigest digest = null;

    private byte[] randomBytes;

    private int randomBytesUsed;

    private long counter;
}
