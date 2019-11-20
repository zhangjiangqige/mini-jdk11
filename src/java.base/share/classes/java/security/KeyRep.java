package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.util.Locale;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class KeyRep implements Serializable {

    public static enum Type {

        SECRET, PUBLIC, PRIVATE
    }

    public KeyRep(Type type, String algorithm, String format, byte[] encoded) {
    }

    protected Object readResolve() throws ObjectStreamException;
}
