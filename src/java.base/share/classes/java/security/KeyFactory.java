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

    protected KeyFactory(KeyFactorySpi keyFacSpi, Provider provider, String algorithm) {
    }

    public static KeyFactory getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static KeyFactory getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static KeyFactory getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public final Provider getProvider();

    public final String getAlgorithm();

    public final PublicKey generatePublic(KeySpec keySpec) throws InvalidKeySpecException;

    public final PrivateKey generatePrivate(KeySpec keySpec) throws InvalidKeySpecException;

    public final <T extends KeySpec> T getKeySpec(Key key, Class<T> keySpec) throws InvalidKeySpecException;

    public final Key translateKey(Key key) throws InvalidKeyException;
}
