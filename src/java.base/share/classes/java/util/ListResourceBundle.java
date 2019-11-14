package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.util.ResourceBundleEnumeration;

@AnnotatedFor({ "lock", "nullness", "index" })
public abstract class ListResourceBundle extends ResourceBundle {

    public ListResourceBundle() {
    }

    @Nullable
    public final Object handleGetObject(@Nullable String key);

    public Enumeration<String> getKeys(@GuardSatisfied ListResourceBundle this);

    protected Set<String> handleKeySet();

    protected abstract Object[][] getContents();

    private synchronized void loadLookup();

    private volatile Map<String, Object> lookup = null;
}
