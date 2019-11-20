package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class RSAPrivateKeySpec implements KeySpec {

    public RSAPrivateKeySpec(BigInteger modulus, BigInteger privateExponent) {
    }

    public RSAPrivateKeySpec(BigInteger modulus, BigInteger privateExponent, AlgorithmParameterSpec params) {
    }

    public BigInteger getModulus();

    public BigInteger getPrivateExponent();

    public AlgorithmParameterSpec getParams();
}
