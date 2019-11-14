package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.util.Map;
import java.util.List;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class CacheResponse {

    public abstract Map<String, List<String>> getHeaders() throws IOException;

    public abstract InputStream getBody() throws IOException;
}
