package javax.xml.parsers;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public class FactoryConfigurationError extends Error {

    public FactoryConfigurationError() {
    }

    public FactoryConfigurationError(String msg) {
    }

    public FactoryConfigurationError(Exception e) {
    }

    public FactoryConfigurationError(Exception e, String msg) {
    }

    @Nullable
    public String getMessage();

    @Nullable
    public Exception getException();

    @Override
    @Nullable
    public Throwable getCause();
}
