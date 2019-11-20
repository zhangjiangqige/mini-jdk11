package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class AlgorithmParameterGenerator {

    protected AlgorithmParameterGenerator(AlgorithmParameterGeneratorSpi paramGenSpi, Provider provider, String algorithm) {
    }

    public final String getAlgorithm();

    public static AlgorithmParameterGenerator getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static AlgorithmParameterGenerator getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static AlgorithmParameterGenerator getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public final Provider getProvider();

    public final void init(int size);

    public final void init(int size, SecureRandom random);

    public final void init(AlgorithmParameterSpec genParamSpec) throws InvalidAlgorithmParameterException;

    public final void init(AlgorithmParameterSpec genParamSpec, SecureRandom random) throws InvalidAlgorithmParameterException;

    public final AlgorithmParameters generateParameters();
}
