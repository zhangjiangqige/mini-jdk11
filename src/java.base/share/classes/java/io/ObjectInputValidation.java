package java.io;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public interface ObjectInputValidation {

    public void validateObject() throws InvalidObjectException;
}
