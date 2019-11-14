package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Annotation {

    public Annotation(Object value) {
        this.value = value;
    }

    public Object getValue();

    public String toString();

    private Object value;
}
