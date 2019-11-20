package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;
import java.security.spec.MGF1ParameterSpec;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PSSParameterSpec implements AlgorithmParameterSpec {

    public static final int TRAILER_FIELD_BC;

    public static final PSSParameterSpec DEFAULT;

    public PSSParameterSpec(String mdName, String mgfName, AlgorithmParameterSpec mgfSpec, int saltLen, int trailerField) {
    }

    public PSSParameterSpec(int saltLen) {
    }

    public String getDigestAlgorithm();

    public String getMGFAlgorithm();

    public AlgorithmParameterSpec getMGFParameters();

    public int getSaltLength();

    public int getTrailerField();
}
