package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.spec.AlgorithmParameterSpec;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class KeyPairGeneratorSpi {

    public abstract void initialize(int keysize, SecureRandom random);

    public void initialize(AlgorithmParameterSpec params, SecureRandom random) throws InvalidAlgorithmParameterException;

    public abstract KeyPair generateKeyPair();
}
