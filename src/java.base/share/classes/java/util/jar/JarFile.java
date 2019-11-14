package java.util.jar;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.JavaUtilZipFileAccess;
import sun.security.action.GetPropertyAction;
import sun.security.util.ManifestEntryVerifier;
import sun.security.util.SignatureFileVerifier;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

@AnnotatedFor({ "nullness" })
public class JarFile extends ZipFile {

    private final static Runtime.Version BASE_VERSION;

    private final static int BASE_VERSION_FEATURE;

    private final static Runtime.Version RUNTIME_VERSION;

    private final static boolean MULTI_RELEASE_ENABLED;

    private final static boolean MULTI_RELEASE_FORCED;

    private SoftReference<Manifest> manRef;

    private JarEntry manEntry;

    private JarVerifier jv;

    private boolean jvInitialized;

    private boolean verify;

    private final Runtime.Version version;

    private final int versionFeature;

    private boolean isMultiRelease;

    private boolean hasClassPathAttribute;

    private volatile boolean hasCheckedSpecialAttributes;

    private static final JavaUtilZipFileAccess JUZFA;

    static {
        SharedSecrets.setJavaUtilJarAccess(new JavaUtilJarAccessImpl());
        JUZFA = jdk.internal.misc.SharedSecrets.getJavaUtilZipFileAccess();
        BASE_VERSION = Runtime.Version.parse(Integer.toString(8));
        BASE_VERSION_FEATURE = BASE_VERSION.feature();
        String jarVersion = GetPropertyAction.privilegedGetProperty("jdk.util.jar.version");
        int runtimeVersion = Runtime.version().feature();
        if (jarVersion != null) {
            int jarVer = Integer.parseInt(jarVersion);
            runtimeVersion = (jarVer > runtimeVersion) ? runtimeVersion : Math.max(jarVer, BASE_VERSION_FEATURE);
        }
        RUNTIME_VERSION = Runtime.Version.parse(Integer.toString(runtimeVersion));
        String enableMultiRelease = GetPropertyAction.privilegedGetProperty("jdk.util.jar.enableMultiRelease", "true");
        switch(enableMultiRelease) {
            case "true":
            default:
                MULTI_RELEASE_ENABLED = true;
                MULTI_RELEASE_FORCED = false;
                break;
            case "false":
                MULTI_RELEASE_ENABLED = false;
                MULTI_RELEASE_FORCED = false;
                break;
            case "force":
                MULTI_RELEASE_ENABLED = true;
                MULTI_RELEASE_FORCED = true;
                break;
        }
    }

    private static final String META_INF = "META-INF/";

    private static final String META_INF_VERSIONS = META_INF + "versions/";

    @Interned
    public static final String MANIFEST_NAME = META_INF + "MANIFEST.MF";

    public static Runtime.Version baseVersion();

    public static Runtime.Version runtimeVersion();

    public JarFile(String name) throws IOException {
        this(new File(name), true, ZipFile.OPEN_READ);
    }

    public JarFile(String name, boolean verify) throws IOException {
        this(new File(name), verify, ZipFile.OPEN_READ);
    }

    public JarFile(File file) throws IOException {
        this(file, true, ZipFile.OPEN_READ);
    }

    public JarFile(File file, boolean verify) throws IOException {
        this(file, verify, ZipFile.OPEN_READ);
    }

    public JarFile(File file, boolean verify, int mode) throws IOException {
        this(file, verify, mode, BASE_VERSION);
    }

    public JarFile(File file, boolean verify, int mode, Runtime.Version version) throws IOException {
        super(file, mode);
        this.verify = verify;
        Objects.requireNonNull(version);
        if (MULTI_RELEASE_FORCED || version.feature() == RUNTIME_VERSION.feature()) {
            this.version = RUNTIME_VERSION;
        } else if (version.feature() <= BASE_VERSION_FEATURE) {
            this.version = BASE_VERSION;
        } else {
            this.version = Runtime.Version.parse(Integer.toString(version.feature()));
        }
        this.versionFeature = this.version.feature();
    }

    public final Runtime.Version getVersion();

    public final boolean isMultiRelease();

    @Nullable
    public Manifest getManifest() throws IOException;

    @Nullable
    private Manifest getManifestFromReference() throws IOException;

    private String[] getMetaInfEntryNames();

    @Nullable
    public JarEntry getJarEntry(String name);

    @Nullable
    public ZipEntry getEntry(String name);

    public Enumeration<JarEntry> entries();

    public Stream<JarEntry> stream();

    public Stream<JarEntry> versionedStream();

    private JarFileEntry getEntry0(String name);

    private String getBasename(String name);

    private JarEntry getVersionedEntry(String name, JarEntry je);

    String getRealName(JarEntry entry);

    private class JarFileEntry extends JarEntry {

        private String basename;

        JarFileEntry(String name) {
            super(name);
            this.basename = name;
        }

        JarFileEntry(String name, ZipEntry vze) {
            super(vze);
            this.basename = name;
        }

        @Override
        @Nullable
        public Attributes getAttributes() throws IOException;

        @Override
        public Certificate @Nullable [] getCertificates();

        @Override
        public CodeSigner @Nullable [] getCodeSigners();

        @Override
        public String getRealName();

        @Override
        public String getName();

        JarFileEntry realEntry();

        JarFileEntry withBasename(String name);
    }

    private void maybeInstantiateVerifier() throws IOException;

    private void initializeVerifier();

    private byte[] getBytes(ZipEntry ze) throws IOException;

    public synchronized InputStream getInputStream(ZipEntry ze) throws IOException;

    private JarEntry verifiableEntry(ZipEntry ze);

    private static final byte[] CLASSPATH_CHARS = { 'C', 'L', 'A', 'S', 'S', '-', 'P', 'A', 'T', 'H', ':', ' ' };

    private static final byte[] CLASSPATH_LASTOCC;

    private static final byte[] CLASSPATH_OPTOSFT;

    private static final byte[] MULTIRELEASE_CHARS = { 'M', 'U', 'L', 'T', 'I', '-', 'R', 'E', 'L', 'E', 'A', 'S', 'E', ':', ' ', 'T', 'R', 'U', 'E' };

    private static final byte[] MULTIRELEASE_LASTOCC;

    private static final byte[] MULTIRELEASE_OPTOSFT;

    static {
        CLASSPATH_LASTOCC = new byte[65];
        CLASSPATH_OPTOSFT = new byte[12];
        CLASSPATH_LASTOCC[(int) 'C' - 32] = 1;
        CLASSPATH_LASTOCC[(int) 'L' - 32] = 2;
        CLASSPATH_LASTOCC[(int) 'S' - 32] = 5;
        CLASSPATH_LASTOCC[(int) '-' - 32] = 6;
        CLASSPATH_LASTOCC[(int) 'P' - 32] = 7;
        CLASSPATH_LASTOCC[(int) 'A' - 32] = 8;
        CLASSPATH_LASTOCC[(int) 'T' - 32] = 9;
        CLASSPATH_LASTOCC[(int) 'H' - 32] = 10;
        CLASSPATH_LASTOCC[(int) ':' - 32] = 11;
        CLASSPATH_LASTOCC[(int) ' ' - 32] = 12;
        for (int i = 0; i < 11; i++) {
            CLASSPATH_OPTOSFT[i] = 12;
        }
        CLASSPATH_OPTOSFT[11] = 1;
        MULTIRELEASE_LASTOCC = new byte[65];
        MULTIRELEASE_OPTOSFT = new byte[19];
        MULTIRELEASE_LASTOCC[(int) 'M' - 32] = 1;
        MULTIRELEASE_LASTOCC[(int) 'I' - 32] = 5;
        MULTIRELEASE_LASTOCC[(int) '-' - 32] = 6;
        MULTIRELEASE_LASTOCC[(int) 'L' - 32] = 9;
        MULTIRELEASE_LASTOCC[(int) 'A' - 32] = 11;
        MULTIRELEASE_LASTOCC[(int) 'S' - 32] = 12;
        MULTIRELEASE_LASTOCC[(int) ':' - 32] = 14;
        MULTIRELEASE_LASTOCC[(int) ' ' - 32] = 15;
        MULTIRELEASE_LASTOCC[(int) 'T' - 32] = 16;
        MULTIRELEASE_LASTOCC[(int) 'R' - 32] = 17;
        MULTIRELEASE_LASTOCC[(int) 'U' - 32] = 18;
        MULTIRELEASE_LASTOCC[(int) 'E' - 32] = 19;
        for (int i = 0; i < 17; i++) {
            MULTIRELEASE_OPTOSFT[i] = 19;
        }
        MULTIRELEASE_OPTOSFT[17] = 6;
        MULTIRELEASE_OPTOSFT[18] = 1;
    }

    private JarEntry getManEntry();

    boolean hasClassPathAttribute() throws IOException;

    private int match(byte[] src, byte[] b, byte[] lastOcc, byte[] optoSft);

    private void checkForSpecialAttributes() throws IOException;

    private synchronized void ensureInitialization();

    JarEntry newEntry(JarEntry je);

    JarEntry newEntry(String name);

    Enumeration<String> entryNames(CodeSource[] cs);

    Enumeration<JarEntry> entries2();

    CodeSource @Nullable [] getCodeSources(URL url);

    private Enumeration<String> unsignedEntryNames();

    CodeSource getCodeSource(URL url, String name);

    void setEagerValidation(boolean eager);

    List<Object> getManifestDigests();
}
