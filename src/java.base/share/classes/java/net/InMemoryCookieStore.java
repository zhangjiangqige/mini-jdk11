package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.net.URI;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class InMemoryCookieStore implements CookieStore {

    private List<HttpCookie> cookieJar = null;

    private Map<String, List<HttpCookie>> domainIndex = null;

    private Map<URI, List<HttpCookie>> uriIndex = null;

    private ReentrantLock lock = null;

    public InMemoryCookieStore() {
        cookieJar = new ArrayList<>();
        domainIndex = new HashMap<>();
        uriIndex = new HashMap<>();
        lock = new ReentrantLock(false);
    }

    public void add(URI uri, HttpCookie cookie);

    public List<HttpCookie> get(URI uri);

    public List<HttpCookie> getCookies();

    public List<URI> getURIs();

    public boolean remove(URI uri, HttpCookie ck);

    public boolean removeAll();

    private boolean netscapeDomainMatches(String domain, String host);

    private void getInternal1(List<HttpCookie> cookies, Map<String, List<HttpCookie>> cookieIndex, String host, boolean secureLink);

    private <T> void getInternal2(List<HttpCookie> cookies, Map<T, List<HttpCookie>> cookieIndex, Comparable<T> comparator, boolean secureLink);

    private <T> void addIndex(Map<T, List<HttpCookie>> indexStore, T index, HttpCookie cookie);

    private URI getEffectiveURI(URI uri);
}
