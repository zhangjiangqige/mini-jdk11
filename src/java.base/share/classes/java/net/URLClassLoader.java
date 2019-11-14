package java.net;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Closeable;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import jdk.internal.loader.Resource;
import jdk.internal.loader.URLClassPath;
import jdk.internal.misc.JavaNetURLClassLoaderAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.perf.PerfCounter;
import sun.net.www.ParseUtil;
import sun.security.util.SecurityConstants;

@AnnotatedFor("nullness")
public class URLClassLoader extends SecureClassLoader implements Closeable {

    private final URLClassPath ucp;

    private final AccessControlContext acc;

    public URLClassLoader(URL[] urls, @Nullable ClassLoader parent) {
        super(parent);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = AccessController.getContext();
        this.ucp = new URLClassPath(urls, acc);
    }

    URLClassLoader(String name, URL[] urls, ClassLoader parent, AccessControlContext acc) {
        super(name, parent);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = acc;
        this.ucp = new URLClassPath(urls, acc);
    }

    public URLClassLoader(URL[] urls) {
        super();
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = AccessController.getContext();
        this.ucp = new URLClassPath(urls, acc);
    }

    URLClassLoader(URL[] urls, AccessControlContext acc) {
        super();
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = acc;
        this.ucp = new URLClassPath(urls, acc);
    }

    public URLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(parent);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = AccessController.getContext();
        this.ucp = new URLClassPath(urls, factory, acc);
    }

    public URLClassLoader(String name, URL[] urls, ClassLoader parent) {
        super(name, parent);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = AccessController.getContext();
        this.ucp = new URLClassPath(urls, acc);
    }

    public URLClassLoader(String name, URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(name, parent);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = AccessController.getContext();
        this.ucp = new URLClassPath(urls, factory, acc);
    }

    private WeakHashMap<Closeable, Void> closeables = new WeakHashMap<>();

    @Nullable
    public InputStream getResourceAsStream(String name);

    public void close() throws IOException;

    protected void addURL(@Nullable URL url);

    public URL[] getURLs();

    protected Class<?> findClass(final String name) throws ClassNotFoundException;

    @Nullable
    private Package getAndVerifyPackage(String pkgname, Manifest man, URL url);

    private Class<?> defineClass(String name, Resource res) throws IOException;

    protected Package definePackage(String name, Manifest man, @Nullable URL url);

    private boolean isSealed(String name, Manifest man);

    @Nullable
    public URL findResource(final String name);

    public Enumeration<URL> findResources(final String name) throws IOException;

    protected PermissionCollection getPermissions(CodeSource codesource);

    public static URLClassLoader newInstance(final URL[] urls, final ClassLoader parent);

    public static URLClassLoader newInstance(final URL[] urls);

    static {
        SharedSecrets.setJavaNetURLClassLoaderAccess(new JavaNetURLClassLoaderAccess() {

            @Override
            public AccessControlContext getAccessControlContext(URLClassLoader u) {
                return u.acc;
            }
        });
        ClassLoader.registerAsParallelCapable();
    }
}

final class FactoryURLClassLoader extends URLClassLoader {

    static {
        ClassLoader.registerAsParallelCapable();
    }

    FactoryURLClassLoader(String name, URL[] urls, ClassLoader parent, AccessControlContext acc) {
        super(name, urls, parent, acc);
    }

    FactoryURLClassLoader(URL[] urls, AccessControlContext acc) {
        super(urls, acc);
    }

    public final Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException;
}
