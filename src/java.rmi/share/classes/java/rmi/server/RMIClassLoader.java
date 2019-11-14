package java.rmi.server;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.ServiceLoader;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class RMIClassLoader {

    private static final RMIClassLoaderSpi defaultProvider = newDefaultProviderInstance();

    private static final RMIClassLoaderSpi provider = AccessController.doPrivileged(new PrivilegedAction<RMIClassLoaderSpi>() {

        public RMIClassLoaderSpi run() {
            return initializeProvider();
        }
    });

    private RMIClassLoader() {
    }

    @Deprecated
    public static Class<?> loadClass(String name) throws MalformedURLException, ClassNotFoundException;

    public static Class<?> loadClass(URL codebase, String name) throws MalformedURLException, ClassNotFoundException;

    public static Class<?> loadClass(String codebase, String name) throws MalformedURLException, ClassNotFoundException;

    public static Class<?> loadClass(String codebase, String name, ClassLoader defaultLoader) throws MalformedURLException, ClassNotFoundException;

    public static Class<?> loadProxyClass(String codebase, String[] interfaces, ClassLoader defaultLoader) throws ClassNotFoundException, MalformedURLException;

    public static ClassLoader getClassLoader(String codebase) throws MalformedURLException, SecurityException;

    public static String getClassAnnotation(Class<?> cl);

    public static RMIClassLoaderSpi getDefaultProviderInstance();

    @Deprecated
    public static Object getSecurityContext(ClassLoader loader);

    private static RMIClassLoaderSpi newDefaultProviderInstance();

    private static RMIClassLoaderSpi initializeProvider();
}
