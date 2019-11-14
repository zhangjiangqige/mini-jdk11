package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class RSAPublicKeySpec implements KeySpec {

    private final BigInteger modulus;

    private final BigInteger publicExponent;

    private final AlgorithmParameterSpec params;

    public RSAPublicKeySpec(BigInteger modulus, BigInteger publicExponent) {
        this(modulus, publicExponent, null);
    }

    public RSAPublicKeySpec(BigInteger modulus, BigInteger publicExponent, AlgorithmParameterSpec params) {
        this.modulus = modulus;
        this.publicExponent = publicExponent;
        this.params = params;
    }

    public BigInteger getModulus();

    public BigInteger getPublicExponent();

    public AlgorithmParameterSpec getParams();
}
