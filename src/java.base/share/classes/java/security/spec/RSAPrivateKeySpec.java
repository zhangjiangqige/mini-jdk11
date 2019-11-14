package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class RSAPrivateKeySpec implements KeySpec {

    private final BigInteger modulus;

    private final BigInteger privateExponent;

    private final AlgorithmParameterSpec params;

    public RSAPrivateKeySpec(BigInteger modulus, BigInteger privateExponent) {
        this(modulus, privateExponent, null);
    }

    public RSAPrivateKeySpec(BigInteger modulus, BigInteger privateExponent, AlgorithmParameterSpec params) {
        this.modulus = modulus;
        this.privateExponent = privateExponent;
        this.params = params;
    }

    public BigInteger getModulus();

    public BigInteger getPrivateExponent();

    public AlgorithmParameterSpec getParams();
}
