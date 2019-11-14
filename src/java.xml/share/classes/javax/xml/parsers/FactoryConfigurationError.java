package javax.xml.parsers;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public class FactoryConfigurationError extends Error {

    private static final long serialVersionUID = -827108682472263355L;

    @Nullable
    private Exception exception;

    public FactoryConfigurationError() {
        super();
        this.exception = null;
    }

    public FactoryConfigurationError(String msg) {
        super(msg);
        this.exception = null;
    }

    public FactoryConfigurationError(Exception e) {
        super(e.toString());
        this.exception = e;
    }

    public FactoryConfigurationError(Exception e, String msg) {
        super(msg);
        this.exception = e;
    }

    @Nullable
    public String getMessage();

    @Nullable
    public Exception getException();

    @Override
    @Nullable
    public Throwable getCause();
}
