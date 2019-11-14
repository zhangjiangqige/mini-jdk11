package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.EventListenerProxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
abstract class ChangeListenerMap<L extends EventListener> {

    private Map<String, L[]> map;

    protected abstract L[] newArray(int length);

    protected abstract L newProxy(String name, L listener);

    public final synchronized void add(String name, L listener);

    public final synchronized void remove(String name, L listener);

    public final synchronized L[] get(String name);

    public final void set(String name, L[] listeners);

    public final synchronized L[] getListeners();

    public final L[] getListeners(String name);

    public final synchronized boolean hasListeners(String name);

    public final Set<Entry<String, L[]>> getEntries();

    public abstract L extract(L listener);
}
