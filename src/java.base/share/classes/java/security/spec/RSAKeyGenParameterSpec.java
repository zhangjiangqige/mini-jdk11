package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class RSAKeyGenParameterSpec implements AlgorithmParameterSpec {

    public static final BigInteger F0;

    public static final BigInteger F4;

    public RSAKeyGenParameterSpec(int keysize, BigInteger publicExponent) {
    }

    public RSAKeyGenParameterSpec(int keysize, BigInteger publicExponent, AlgorithmParameterSpec keyParams) {
    }

    public int getKeysize();

    public BigInteger getPublicExponent();

    public AlgorithmParameterSpec getKeyParams();
}
