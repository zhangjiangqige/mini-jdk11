package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DSAPublicKeySpec implements KeySpec {

    public DSAPublicKeySpec(BigInteger y, BigInteger p, BigInteger q, BigInteger g) {
    }

    public BigInteger getY();

    public BigInteger getP();

    public BigInteger getQ();

    public BigInteger getG();
}
