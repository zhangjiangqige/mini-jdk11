package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class CookieHandler {

    private static CookieHandler cookieHandler;

    public static synchronized CookieHandler getDefault();

    public static synchronized void setDefault(CookieHandler cHandler);

    public abstract Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders) throws IOException;

    public abstract void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException;
}
