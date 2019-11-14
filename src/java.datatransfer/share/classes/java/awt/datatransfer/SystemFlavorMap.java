package java.awt.datatransfer;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.SoftReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import sun.datatransfer.DataFlavorUtil;
import sun.datatransfer.DesktopDatatransferService;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class SystemFlavorMap implements FlavorMap, FlavorTable {

    private static String JavaMIME = "JAVA_DATAFLAVOR:";

    private static final Object FLAVOR_MAP_KEY = new Object();

    private static final String[] UNICODE_TEXT_CLASSES = { "java.io.Reader", "java.lang.String", "java.nio.CharBuffer", "\"[C\"" };

    private static final String[] ENCODED_TEXT_CLASSES = { "java.io.InputStream", "java.nio.ByteBuffer", "\"[B\"" };

    private static final String TEXT_PLAIN_BASE_TYPE = "text/plain";

    private static final String HTML_TEXT_BASE_TYPE = "text/html";

    private final Map<String, LinkedHashSet<DataFlavor>> nativeToFlavor = new HashMap<>();

    private Map<String, LinkedHashSet<DataFlavor>> getNativeToFlavor();

    private final Map<DataFlavor, LinkedHashSet<String>> flavorToNative = new HashMap<>();

    private synchronized Map<DataFlavor, LinkedHashSet<String>> getFlavorToNative();

    private Map<String, LinkedHashSet<String>> textTypeToNative = new HashMap<>();

    private boolean isMapInitialized = false;

    private synchronized Map<String, LinkedHashSet<String>> getTextTypeToNative();

    private final SoftCache<DataFlavor, String> nativesForFlavorCache = new SoftCache<>();

    private final SoftCache<String, DataFlavor> flavorsForNativeCache = new SoftCache<>();

    private Set<Object> disabledMappingGenerationKeys = new HashSet<>();

    public static FlavorMap getDefaultFlavorMap();

    private SystemFlavorMap() {
    }

    private void initSystemFlavorMap();

    private static String loadConvert(String theString);

    private <H, L> void store(H hashed, L listed, Map<H, LinkedHashSet<L>> map);

    private LinkedHashSet<DataFlavor> nativeToFlavorLookup(String nat);

    private LinkedHashSet<String> flavorToNativeLookup(final DataFlavor flav, final boolean synthesize);

    @Override
    public synchronized List<String> getNativesForFlavor(DataFlavor flav);

    @Override
    public synchronized List<DataFlavor> getFlavorsForNative(String nat);

    @SuppressWarnings("deprecation")
    private static Set<DataFlavor> convertMimeTypeToDataFlavors(final String baseType);

    private static final String[] htmlDocumentTypes = new String[] { "all", "selection", "fragment" };

    private static LinkedHashSet<String> handleHtmlMimeTypes(String baseType, String mimeType);

    @Override
    public synchronized Map<DataFlavor, String> getNativesForFlavors(DataFlavor[] flavors);

    @Override
    public synchronized Map<String, DataFlavor> getFlavorsForNatives(String[] natives);

    public synchronized void addUnencodedNativeForFlavor(DataFlavor flav, String nat);

    public synchronized void setNativesForFlavor(DataFlavor flav, String[] natives);

    public synchronized void addFlavorForUnencodedNative(String nat, DataFlavor flav);

    public synchronized void setFlavorsForNative(String nat, DataFlavor[] flavors);

    public static String encodeJavaMIMEType(String mimeType);

    public static String encodeDataFlavor(DataFlavor flav);

    public static boolean isJavaMIMEType(String str);

    public static String decodeJavaMIMEType(String nat);

    public static DataFlavor decodeDataFlavor(String nat) throws ClassNotFoundException;

    private static final class SoftCache<K, V> {

        Map<K, SoftReference<LinkedHashSet<V>>> cache;

        public void put(K key, LinkedHashSet<V> value);

        public void remove(K key);

        public LinkedHashSet<V> check(K key);
    }
}
