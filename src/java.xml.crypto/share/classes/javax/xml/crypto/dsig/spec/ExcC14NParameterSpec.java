package javax.xml.crypto.dsig.spec;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AnnotatedFor({ "interning" })
public final class ExcC14NParameterSpec implements C14NMethodParameterSpec {

    @Interned
    public static final String DEFAULT;

    public ExcC14NParameterSpec() {
    }

    public ExcC14NParameterSpec(List<String> prefixList) {
    }

    public List<String> getPrefixList();
}
