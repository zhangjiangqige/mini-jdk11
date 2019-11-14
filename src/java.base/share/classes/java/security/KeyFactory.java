package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.security.Provider.Service;
import java.security.spec.KeySpec;
import java.security.spec.InvalidKeySpecException;
import sun.security.util.Debug;
import sun.security.jca.*;
import sun.security.jca.GetInstance.Instance;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class KeyFactory {

    private static final Debug debug = Debug.getInstance("jca", "KeyFactory");

    private final String algorithm;

    private Provider provider;

    private volatile KeyFactorySpi spi;

    private final Object lock = new Object();

    private Iterator<Service> serviceIterator;

    protected KeyFactory(KeyFactorySpi keyFacSpi, Provider provider, String algorithm) {
        this.spi = keyFacSpi;
        this.provider = provider;
        this.algorithm = algorithm;
    }

    private KeyFactory(String algorithm) throws NoSuchAlgorithmException {
        this.algorithm = algorithm;
        List<Service> list = GetInstance.getServices("KeyFactory", algorithm);
        serviceIterator = list.iterator();
        if (nextSpi(null) == null) {
            throw new NoSuchAlgorithmException(algorithm + " KeyFactory not available");
        }
    }

    public static KeyFactory getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static KeyFactory getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static KeyFactory getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public final Provider getProvider();

    public final String getAlgorithm();

    private KeyFactorySpi nextSpi(KeyFactorySpi oldSpi);

    public final PublicKey generatePublic(KeySpec keySpec) throws InvalidKeySpecException;

    public final PrivateKey generatePrivate(KeySpec keySpec) throws InvalidKeySpecException;

    public final <T extends KeySpec> T getKeySpec(Key key, Class<T> keySpec) throws InvalidKeySpecException;

    public final Key translateKey(Key key) throws InvalidKeyException;
}
