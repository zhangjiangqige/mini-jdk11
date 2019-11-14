package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class KeyPair implements java.io.Serializable {

    private static final long serialVersionUID = -7565189502268009837L;

    private PrivateKey privateKey;

    private PublicKey publicKey;

    public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public PublicKey getPublic();

    public PrivateKey getPrivate();
}
