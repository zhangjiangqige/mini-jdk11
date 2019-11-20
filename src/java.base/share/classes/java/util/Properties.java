package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.propkey.qual.PropertyKey;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.Unsafe;
import jdk.internal.util.xml.PropertiesDefaultHandler;

@AnnotatedFor({ "index", "lock", "nullness", "propkey" })
public class Properties extends Hashtable<Object, Object> {

    protected volatile Properties defaults;

    public Properties() {
    }

    public Properties(int initialCapacity) {
    }

    public Properties(Properties defaults) {
    }

    @Nullable
    public synchronized Object setProperty(@GuardSatisfied Properties this, @PropertyKey String key, String value);

    public synchronized void load(Reader reader) throws IOException;

    public synchronized void load(InputStream inStream) throws IOException;

    @Deprecated
    public void save(OutputStream out, @Nullable String comments);

    public void store(Writer writer, @Nullable String comments) throws IOException;

    public void store(OutputStream out, @Nullable String comments) throws IOException;

    public synchronized void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException;

    public void storeToXML(OutputStream os, @Nullable String comment) throws IOException;

    public void storeToXML(OutputStream os, @Nullable String comment, String encoding) throws IOException;

    public void storeToXML(OutputStream os, String comment, Charset charset) throws IOException;

    @Pure
    @Nullable
    public String getProperty(@GuardSatisfied Properties this, @PropertyKey String key);

    @Pure
    @PolyNull
    public String getProperty(@GuardSatisfied Properties this, @PropertyKey String key, @PolyNull String defaultValue);

    public Enumeration<?> propertyNames();

    public Set<String> stringPropertyNames();

    public void list(PrintStream out);

    public void list(PrintWriter out);

    @Override
    public int size();

    @Override
    public boolean isEmpty();

    @Override
    public Enumeration<Object> keys();

    @Override
    public Enumeration<Object> elements();

    @Override
    public boolean contains(Object value);

    @Override
    public boolean containsValue(Object value);

    @Override
    public boolean containsKey(Object key);

    @Override
    public Object get(Object key);

    @Override
    public synchronized Object put(Object key, Object value);

    @Override
    public synchronized Object remove(Object key);

    @Override
    public synchronized void putAll(Map<?, ?> t);

    @Override
    public synchronized void clear();

    @Override
    public synchronized String toString();

    @Override
    public Set<@KeyFor("this") Object> keySet();

    @Override
    public Collection<Object> values();

    @Override
    public Set<Map.Entry<@KeyFor("this") Object, Object>> entrySet();

    @Override
    public synchronized boolean equals(Object o);

    @Override
    public synchronized int hashCode();

    @Override
    public Object getOrDefault(Object key, Object defaultValue);

    @Override
    public synchronized void forEach(BiConsumer<? super Object, ? super Object> action);

    @Override
    public synchronized void replaceAll(BiFunction<? super Object, ? super Object, ?> function);

    @Override
    public synchronized Object putIfAbsent(Object key, Object value);

    @Override
    public synchronized boolean remove(Object key, Object value);

    @Override
    public synchronized boolean replace(Object key, Object oldValue, Object newValue);

    @Override
    public synchronized Object replace(Object key, Object value);

    @Override
    public synchronized Object computeIfAbsent(Object key, Function<? super Object, ?> mappingFunction);

    @Override
    public synchronized Object computeIfPresent(Object key, BiFunction<? super Object, ? super Object, ?> remappingFunction);

    @Override
    public synchronized Object compute(Object key, BiFunction<? super Object, ? super Object, ?> remappingFunction);

    @Override
    public synchronized Object merge(Object key, Object value, BiFunction<? super Object, ? super Object, ?> remappingFunction);

    @Override
    protected void rehash();

    @Override
    public synchronized Object clone();
}
