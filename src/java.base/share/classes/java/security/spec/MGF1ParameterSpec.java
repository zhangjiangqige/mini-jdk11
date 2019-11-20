package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.spec.AlgorithmParameterSpec;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class MGF1ParameterSpec implements AlgorithmParameterSpec {

    public static final MGF1ParameterSpec SHA1;

    public static final MGF1ParameterSpec SHA224;

    public static final MGF1ParameterSpec SHA256;

    public static final MGF1ParameterSpec SHA384;

    public static final MGF1ParameterSpec SHA512;

    public static final MGF1ParameterSpec SHA512_224;

    public static final MGF1ParameterSpec SHA512_256;

    public MGF1ParameterSpec(String mdName) {
    }

    public String getDigestAlgorithm();
}
