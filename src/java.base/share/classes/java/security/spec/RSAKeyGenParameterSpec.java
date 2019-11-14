package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class RSAKeyGenParameterSpec implements AlgorithmParameterSpec {

    private int keysize;

    private BigInteger publicExponent;

    private AlgorithmParameterSpec keyParams;

    public static final BigInteger F0 = BigInteger.valueOf(3);

    public static final BigInteger F4 = BigInteger.valueOf(65537);

    public RSAKeyGenParameterSpec(int keysize, BigInteger publicExponent) {
        this(keysize, publicExponent, null);
    }

    public RSAKeyGenParameterSpec(int keysize, BigInteger publicExponent, AlgorithmParameterSpec keyParams) {
        this.keysize = keysize;
        this.publicExponent = publicExponent;
        this.keyParams = keyParams;
    }

    public int getKeysize();

    public BigInteger getPublicExponent();

    public AlgorithmParameterSpec getKeyParams();
}
