package java.sql;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DriverPropertyInfo {

    public DriverPropertyInfo(String name, String value) {
    }

    public String name;

    public String description;

    public boolean required;

    public String value;

    public String[] choices;
}
