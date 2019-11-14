package java.sql;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public interface SQLType {

    String getName();

    String getVendor();

    Integer getVendorTypeNumber();
}
