package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class KeyPair implements java.io.Serializable {

    public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
    }

    public PublicKey getPublic();

    public PrivateKey getPrivate();
}
