package java.nio.charset;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CodingErrorAction {

    private String name;

    private CodingErrorAction(String name) {
        this.name = name;
    }

    public static final CodingErrorAction IGNORE = new CodingErrorAction("IGNORE");

    public static final CodingErrorAction REPLACE = new CodingErrorAction("REPLACE");

    public static final CodingErrorAction REPORT = new CodingErrorAction("REPORT");

    public String toString();
}
