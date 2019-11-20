package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class EncodedKeySpec implements KeySpec {

    public EncodedKeySpec(byte[] encodedKey) {
    }

    protected EncodedKeySpec(byte[] encodedKey, String algorithm) {
    }

    public String getAlgorithm();

    public byte[] getEncoded();

    public abstract String getFormat();
}
