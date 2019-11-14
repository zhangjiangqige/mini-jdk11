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

    private static final Debug sdebug = Debug.getInstance("properties");

    private static Properties props;

    private static class ProviderProperty {

        String className;

        Provider provider;
    }

    static {
        AccessController.doPrivileged(new PrivilegedAction<>() {

            public Void run() {
                initialize();
                return null;
            }
        });
    }

    private static void initialize();

    private static void initializeStatic();

    private Security() {
    }

    private static File securityPropFile(String filename);

    private static ProviderProperty getProviderProperty(String key);

    private static String getProviderProperty(String key, Provider provider);

    @Deprecated
    public static String getAlgorithmProperty(String algName, String propName);

    public static synchronized int insertProviderAt(Provider provider, int position);

    public static int addProvider(Provider provider);

    public static synchronized void removeProvider(String name);

    public static Provider[] getProviders();

    public static Provider getProvider(String name);

    public static Provider[] getProviders(String filter);

    public static Provider[] getProviders(Map<String, String> filter);

    private static final Map<String, Class<?>> spiMap = new ConcurrentHashMap<>();

    private static Class<?> getSpiClass(String type);

    static Object[] getImpl(String algorithm, String type, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    static Object[] getImpl(String algorithm, String type, String provider, Object params) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException;

    static Object[] getImpl(String algorithm, String type, Provider provider) throws NoSuchAlgorithmException;

    static Object[] getImpl(String algorithm, String type, Provider provider, Object params) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException;

    public static String getProperty(String key);

    public static void setProperty(String key, String datum);

    private static void invalidateSMCache(String key);

    private static void check(String directive);

    private static void checkInsertProvider(String name);

    private static LinkedHashSet<Provider> getAllQualifyingCandidates(String filterKey, String filterValue, Provider[] allProviders);

    private static LinkedHashSet<Provider> getProvidersNotUsingCache(String serviceName, String algName, String attrName, String filterValue, Provider[] allProviders);

    private static boolean isCriterionSatisfied(Provider prov, String serviceName, String algName, String attrName, String filterValue);

    private static boolean isStandardAttr(String attribute);

    private static boolean isConstraintSatisfied(String attribute, String value, String prop);

    static String[] getFilterComponents(String filterKey, String filterValue);

    public static Set<String> getAlgorithms(String serviceName);
}
