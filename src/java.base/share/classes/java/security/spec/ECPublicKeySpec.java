package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ECPublicKeySpec implements KeySpec {

    public ECPublicKeySpec(ECPoint w, ECParameterSpec params) {
    }

    public ECPoint getW();

    public ECParameterSpec getParams();
}
