package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ResponseCache {

    public static synchronized ResponseCache getDefault();

    public static synchronized void setDefault(ResponseCache responseCache);

    public abstract CacheResponse get(URI uri, String rqstMethod, Map<String, List<String>> rqstHeaders) throws IOException;

    public abstract CacheRequest put(URI uri, URLConnection conn) throws IOException;
}
