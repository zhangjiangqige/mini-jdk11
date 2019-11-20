package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DSAPrivateKeySpec implements KeySpec {

    public DSAPrivateKeySpec(BigInteger x, BigInteger p, BigInteger q, BigInteger g) {
    }

    public BigInteger getX();

    public BigInteger getP();

    public BigInteger getQ();

    public BigInteger getG();
}
