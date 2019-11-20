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

    public SecureRandom() {
    }

    public SecureRandom(byte[] seed) {
    }

    protected SecureRandom(SecureRandomSpi secureRandomSpi, Provider provider) {
    }

    public static SecureRandom getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static SecureRandom getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static SecureRandom getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public static SecureRandom getInstance(String algorithm, SecureRandomParameters params) throws NoSuchAlgorithmException;

    public static SecureRandom getInstance(String algorithm, SecureRandomParameters params, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static SecureRandom getInstance(String algorithm, SecureRandomParameters params, Provider provider) throws NoSuchAlgorithmException;

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

    public static SecureRandom getInstanceStrong() throws NoSuchAlgorithmException;

    public void reseed();

    public void reseed(SecureRandomParameters params);
}
