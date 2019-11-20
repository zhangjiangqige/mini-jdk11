package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class RSAPublicKeySpec implements KeySpec {

    public RSAPublicKeySpec(BigInteger modulus, BigInteger publicExponent) {
    }

    public RSAPublicKeySpec(BigInteger modulus, BigInteger publicExponent, AlgorithmParameterSpec params) {
    }

    public BigInteger getModulus();

    public BigInteger getPublicExponent();

    public AlgorithmParameterSpec getParams();
}
