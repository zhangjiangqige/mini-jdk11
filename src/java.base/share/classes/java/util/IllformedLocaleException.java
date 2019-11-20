package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index" })
public class IllformedLocaleException extends RuntimeException {

    public IllformedLocaleException() {
    }

    public IllformedLocaleException(String message) {
    }

    public IllformedLocaleException(String message, int errorIndex) {
    }

    public int getErrorIndex();
}
