package java.nio.charset;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CodingErrorAction {

    public static final CodingErrorAction IGNORE;

    public static final CodingErrorAction REPLACE;

    public static final CodingErrorAction REPORT;

    public String toString();
}
