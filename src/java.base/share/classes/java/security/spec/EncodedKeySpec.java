package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class EncodedKeySpec implements KeySpec {

    private byte[] encodedKey;

    private String algorithmName;

    public EncodedKeySpec(byte[] encodedKey) {
        this.encodedKey = encodedKey.clone();
    }

    protected EncodedKeySpec(byte[] encodedKey, String algorithm) {
        if (algorithm == null) {
            throw new NullPointerException("algorithm name may not be null");
        }
        if (algorithm.isEmpty()) {
            throw new IllegalArgumentException("algorithm name " + "may not be empty");
        }
        this.encodedKey = encodedKey.clone();
        this.algorithmName = algorithm;
    }

    public String getAlgorithm();

    public byte[] getEncoded();

    public abstract String getFormat();
}
