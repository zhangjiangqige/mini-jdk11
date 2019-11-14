package java.util.prefs;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.ServiceConfigurationError;
import java.lang.RuntimePermission;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Float;
import java.lang.Double;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Preferences {

    private static final PreferencesFactory factory = factory();

    private static PreferencesFactory factory();

    private static PreferencesFactory factory1();

    public static final int MAX_KEY_LENGTH = 80;

    public static final int MAX_VALUE_LENGTH = 8 * 1024;

    public static final int MAX_NAME_LENGTH = 80;

    public static Preferences userNodeForPackage(Class<?> c);

    public static Preferences systemNodeForPackage(Class<?> c);

    private static String nodeName(Class<?> c);

    private static Permission prefsPerm = new RuntimePermission("preferences");

    public static Preferences userRoot();

    public static Preferences systemRoot();

    protected Preferences() {
    }

    public abstract void put(String key, String value);

    public abstract String get(String key, String def);

    public abstract void remove(String key);

    public abstract void clear() throws BackingStoreException;

    public abstract void putInt(String key, int value);

    public abstract int getInt(String key, int def);

    public abstract void putLong(String key, long value);

    public abstract long getLong(String key, long def);

    public abstract void putBoolean(String key, boolean value);

    public abstract boolean getBoolean(String key, boolean def);

    public abstract void putFloat(String key, float value);

    public abstract float getFloat(String key, float def);

    public abstract void putDouble(String key, double value);

    public abstract double getDouble(String key, double def);

    public abstract void putByteArray(String key, byte[] value);

    public abstract byte[] getByteArray(String key, byte[] def);

    public abstract String[] keys() throws BackingStoreException;

    public abstract String[] childrenNames() throws BackingStoreException;

    public abstract Preferences parent();

    public abstract Preferences node(String pathName);

    public abstract boolean nodeExists(String pathName) throws BackingStoreException;

    public abstract void removeNode() throws BackingStoreException;

    public abstract String name();

    public abstract String absolutePath();

    public abstract boolean isUserNode();

    public abstract String toString();

    public abstract void flush() throws BackingStoreException;

    public abstract void sync() throws BackingStoreException;

    public abstract void addPreferenceChangeListener(PreferenceChangeListener pcl);

    public abstract void removePreferenceChangeListener(PreferenceChangeListener pcl);

    public abstract void addNodeChangeListener(NodeChangeListener ncl);

    public abstract void removeNodeChangeListener(NodeChangeListener ncl);

    public abstract void exportNode(OutputStream os) throws IOException, BackingStoreException;

    public abstract void exportSubtree(OutputStream os) throws IOException, BackingStoreException;

    public static void importPreferences(InputStream is) throws IOException, InvalidPreferencesFormatException;
}
