package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;
import java.net.URL;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.util.StaticProperty;
import sun.security.util.Debug;
import sun.security.util.PropertyExpander;
import sun.security.jca.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Security {

    @Deprecated
    public static String getAlgorithmProperty(String algName, String propName);

    public static synchronized int insertProviderAt(Provider provider, int position);

    public static int addProvider(Provider provider);

    public static synchronized void removeProvider(String name);

    public static Provider[] getProviders();

    public static Provider getProvider(String name);

    public static Provider[] getProviders(String filter);

    public static Provider[] getProviders(Map<String, String> filter);

    public static String getProperty(String key);

    public static void setProperty(String key, String datum);

    public static Set<String> getAlgorithms(String serviceName);
}
