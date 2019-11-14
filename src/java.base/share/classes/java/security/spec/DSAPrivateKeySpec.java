package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DSAPrivateKeySpec implements KeySpec {

    private BigInteger x;

    private BigInteger p;

    private BigInteger q;

    private BigInteger g;

    public DSAPrivateKeySpec(BigInteger x, BigInteger p, BigInteger q, BigInteger g) {
        this.x = x;
        this.p = p;
        this.q = q;
        this.g = g;
    }

    public BigInteger getX();

    public BigInteger getP();

    public BigInteger getQ();

    public BigInteger getG();
}
