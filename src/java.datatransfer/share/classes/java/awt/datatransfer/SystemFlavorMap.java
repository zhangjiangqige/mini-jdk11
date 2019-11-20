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

    public static FlavorMap getDefaultFlavorMap();

    @Override
    public synchronized List<String> getNativesForFlavor(DataFlavor flav);

    @Override
    public synchronized List<DataFlavor> getFlavorsForNative(String nat);

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
}
