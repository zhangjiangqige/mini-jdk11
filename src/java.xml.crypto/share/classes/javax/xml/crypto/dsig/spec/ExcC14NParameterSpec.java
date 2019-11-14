package javax.xml.crypto.dsig.spec;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AnnotatedFor({ "interning" })
public final class ExcC14NParameterSpec implements C14NMethodParameterSpec {

    private final List<String> prefixList;

    @Interned
    public static final String DEFAULT = "#default";

    public ExcC14NParameterSpec() {
        prefixList = Collections.emptyList();
    }

    public ExcC14NParameterSpec(List<String> prefixList) {
        if (prefixList == null) {
            throw new NullPointerException("prefixList cannot be null");
        }
        List<String> tempList = Collections.checkedList(new ArrayList<>(), String.class);
        tempList.addAll(prefixList);
        this.prefixList = Collections.unmodifiableList(tempList);
    }

    public List<String> getPrefixList();
}
