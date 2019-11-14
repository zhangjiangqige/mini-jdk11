package java.util.jar;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.checkerframework.framework.qual.CFComment;
import sun.util.logging.PlatformLogger;

@CFComment({ "signature: ", "TODO: Attributes does not declare a toString method.", "This declaration then pollutes java.lang.Object.toString, making", "any override illegal.", "public class Attributes implements Map<Object,Object>, Cloneable {", "public @Interned String toString();", "}" })
@AnnotatedFor({ "nullness" })
public class Attributes implements Map<Object, Object>, Cloneable {

    protected Map<Object, Object> map;

    public Attributes() {
        this(11);
    }

    public Attributes(int size) {
        map = new LinkedHashMap<>(size);
    }

    public Attributes(Attributes attr) {
        map = new LinkedHashMap<>(attr);
    }

    public Object get(Object name);

    public String getValue(String name);

    public String getValue(Name name);

    public Object put(Object name, Object value);

    public String putValue(String name, String value);

    public Object remove(Object name);

    public boolean containsValue(Object value);

    public boolean containsKey(Object name);

    public void putAll(Map<?, ?> attr);

    public void clear();

    public int size();

    public boolean isEmpty();

    public Set<Object> keySet();

    public Collection<Object> values();

    public Set<Map.Entry<Object, Object>> entrySet();

    public boolean equals(Object o);

    public int hashCode();

    public Object clone();

    @SuppressWarnings("deprecation")
    void write(DataOutputStream os) throws IOException;

    @SuppressWarnings("deprecation")
    void writeMain(DataOutputStream out) throws IOException;

    @SuppressWarnings("deprecation")
    void read(Manifest.FastInputStream is, byte[] lbuf) throws IOException;

    public static class Name {

        private final String name;

        private final int hashCode;

        private static final Map<String, Name> KNOWN_NAMES;

        static final Name of(String name);

        public Name(String name) {
            this.hashCode = hash(name);
            this.name = name.intern();
        }

        private final int hash(String name);

        public boolean equals(Object o);

        public int hashCode();

        public String toString();

        public static final Name MANIFEST_VERSION = new Name("Manifest-Version");

        public static final Name SIGNATURE_VERSION = new Name("Signature-Version");

        public static final Name CONTENT_TYPE = new Name("Content-Type");

        public static final Name CLASS_PATH = new Name("Class-Path");

        public static final Name MAIN_CLASS = new Name("Main-Class");

        public static final Name SEALED = new Name("Sealed");

        public static final Name EXTENSION_LIST = new Name("Extension-List");

        public static final Name EXTENSION_NAME = new Name("Extension-Name");

        @Deprecated
        public static final Name EXTENSION_INSTALLATION = new Name("Extension-Installation");

        public static final Name IMPLEMENTATION_TITLE = new Name("Implementation-Title");

        public static final Name IMPLEMENTATION_VERSION = new Name("Implementation-Version");

        public static final Name IMPLEMENTATION_VENDOR = new Name("Implementation-Vendor");

        @Deprecated
        public static final Name IMPLEMENTATION_VENDOR_ID = new Name("Implementation-Vendor-Id");

        @Deprecated
        public static final Name IMPLEMENTATION_URL = new Name("Implementation-URL");

        public static final Name SPECIFICATION_TITLE = new Name("Specification-Title");

        public static final Name SPECIFICATION_VERSION = new Name("Specification-Version");

        public static final Name SPECIFICATION_VENDOR = new Name("Specification-Vendor");

        public static final Name MULTI_RELEASE = new Name("Multi-Release");

        private static void addName(Map<String, Name> names, Name name);

        static {
            var names = new HashMap<String, Name>(64);
            addName(names, MANIFEST_VERSION);
            addName(names, SIGNATURE_VERSION);
            addName(names, CONTENT_TYPE);
            addName(names, CLASS_PATH);
            addName(names, MAIN_CLASS);
            addName(names, SEALED);
            addName(names, EXTENSION_LIST);
            addName(names, EXTENSION_NAME);
            addName(names, IMPLEMENTATION_TITLE);
            addName(names, IMPLEMENTATION_VERSION);
            addName(names, IMPLEMENTATION_VENDOR);
            addName(names, SPECIFICATION_TITLE);
            addName(names, SPECIFICATION_VERSION);
            addName(names, SPECIFICATION_VENDOR);
            addName(names, MULTI_RELEASE);
            addName(names, new Name("Add-Exports"));
            addName(names, new Name("Add-Opens"));
            addName(names, new Name("Ant-Version"));
            addName(names, new Name("Archiver-Version"));
            addName(names, new Name("Build-Jdk"));
            addName(names, new Name("Built-By"));
            addName(names, new Name("Bnd-LastModified"));
            addName(names, new Name("Bundle-Description"));
            addName(names, new Name("Bundle-DocURL"));
            addName(names, new Name("Bundle-License"));
            addName(names, new Name("Bundle-ManifestVersion"));
            addName(names, new Name("Bundle-Name"));
            addName(names, new Name("Bundle-Vendor"));
            addName(names, new Name("Bundle-Version"));
            addName(names, new Name("Bundle-SymbolicName"));
            addName(names, new Name("Created-By"));
            addName(names, new Name("Export-Package"));
            addName(names, new Name("Import-Package"));
            addName(names, new Name("Name"));
            addName(names, new Name("SHA1-Digest"));
            addName(names, new Name("X-Compile-Source-JDK"));
            addName(names, new Name("X-Compile-Target-JDK"));
            KNOWN_NAMES = names;
        }
    }
}
