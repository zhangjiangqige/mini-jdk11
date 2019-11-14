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

    private static final long serialVersionUID = -4757683898830641853L;

    public static enum Type {

        SECRET, PUBLIC, PRIVATE
    }

    private static final String PKCS8 = "PKCS#8";

    private static final String X509 = "X.509";

    private static final String RAW = "RAW";

    private Type type;

    private String algorithm;

    private String format;

    private byte[] encoded;

    public KeyRep(Type type, String algorithm, String format, byte[] encoded) {
        if (type == null || algorithm == null || format == null || encoded == null) {
            throw new NullPointerException("invalid null input(s)");
        }
        this.type = type;
        this.algorithm = algorithm;
        this.format = format.toUpperCase(Locale.ENGLISH);
        this.encoded = encoded.clone();
    }

    protected Object readResolve() throws ObjectStreamException;
}
