package java.sql;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DriverPropertyInfo {

    public DriverPropertyInfo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String name;

    public String description = null;

    public boolean required = false;

    public String value = null;

    public String[] choices = null;
}
