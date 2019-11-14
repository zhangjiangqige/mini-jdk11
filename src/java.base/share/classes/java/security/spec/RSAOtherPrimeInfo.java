package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class RSAOtherPrimeInfo {

    private BigInteger prime;

    private BigInteger primeExponent;

    private BigInteger crtCoefficient;

    public RSAOtherPrimeInfo(BigInteger prime, BigInteger primeExponent, BigInteger crtCoefficient) {
        if (prime == null) {
            throw new NullPointerException("the prime parameter must be " + "non-null");
        }
        if (primeExponent == null) {
            throw new NullPointerException("the primeExponent parameter " + "must be non-null");
        }
        if (crtCoefficient == null) {
            throw new NullPointerException("the crtCoefficient parameter " + "must be non-null");
        }
        this.prime = prime;
        this.primeExponent = primeExponent;
        this.crtCoefficient = crtCoefficient;
    }

    public final BigInteger getPrime();

    public final BigInteger getExponent();

    public final BigInteger getCrtCoefficient();
}
