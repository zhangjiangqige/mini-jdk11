package java.security.spec;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;
import java.security.spec.MGF1ParameterSpec;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PSSParameterSpec implements AlgorithmParameterSpec {

    private final String mdName;

    private final String mgfName;

    private final AlgorithmParameterSpec mgfSpec;

    private final int saltLen;

    private final int trailerField;

    public static final int TRAILER_FIELD_BC = 1;

    public static final PSSParameterSpec DEFAULT = new PSSParameterSpec("SHA-1", "MGF1", MGF1ParameterSpec.SHA1, 20, TRAILER_FIELD_BC);

    private PSSParameterSpec() {
        throw new RuntimeException("default constructor not allowed");
    }

    public PSSParameterSpec(String mdName, String mgfName, AlgorithmParameterSpec mgfSpec, int saltLen, int trailerField) {
        Objects.requireNonNull(mdName, "digest algorithm is null");
        Objects.requireNonNull(mgfName, "mask generation function algorithm is null");
        if (saltLen < 0) {
            throw new IllegalArgumentException("negative saltLen value: " + saltLen);
        }
        if (trailerField < 0) {
            throw new IllegalArgumentException("negative trailerField: " + trailerField);
        }
        this.mdName = mdName;
        this.mgfName = mgfName;
        this.mgfSpec = mgfSpec;
        this.saltLen = saltLen;
        this.trailerField = trailerField;
    }

    public PSSParameterSpec(int saltLen) {
        this("SHA-1", "MGF1", MGF1ParameterSpec.SHA1, saltLen, TRAILER_FIELD_BC);
    }

    public String getDigestAlgorithm();

    public String getMGFAlgorithm();

    public AlgorithmParameterSpec getMGFParameters();

    public int getSaltLength();

    public int getTrailerField();
}
