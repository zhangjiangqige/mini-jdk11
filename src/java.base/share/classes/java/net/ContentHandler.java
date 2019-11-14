package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ContentHandler {

    public abstract Object getContent(URLConnection urlc) throws IOException;

    @SuppressWarnings("rawtypes")
    public Object getContent(URLConnection urlc, Class[] classes) throws IOException;
}
