package sun.util.resources;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import sun.util.ResourceBundleEnumeration;

@AnnotatedFor({ "index" })
public abstract class OpenListResourceBundle extends ResourceBundle {

    protected OpenListResourceBundle() {
    }

    @Override
    protected Object handleGetObject(String key);

    @Override
    public Enumeration<String> getKeys();

    @Override
    protected Set<String> handleKeySet();

    @Override
    public Set<String> keySet();

    protected abstract Object[][] getContents();

    void loadLookupTablesIfNecessary();

    private void loadLookup();

    protected <K, V> Map<K, V> createMap(int size);

    protected <E> Set<E> createSet();

    private volatile Map<String, Object> lookup;

    private volatile Set<String> keyset;
}
