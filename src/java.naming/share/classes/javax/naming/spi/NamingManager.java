package javax.naming.spi;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.net.MalformedURLException;
import java.util.*;
import javax.naming.*;
import com.sun.naming.internal.VersionHelper;
import com.sun.naming.internal.ResourceManager;
import com.sun.naming.internal.FactoryEnumeration;

@AnnotatedFor({ "interning" })
public class NamingManager {

    NamingManager() {
    }

    static final VersionHelper helper = VersionHelper.getVersionHelper();

    private static ObjectFactoryBuilder object_factory_builder = null;

    public static synchronized void setObjectFactoryBuilder(ObjectFactoryBuilder builder) throws NamingException;

    static synchronized ObjectFactoryBuilder getObjectFactoryBuilder();

    static ObjectFactory getObjectFactoryFromReference(Reference ref, String factoryName) throws IllegalAccessException, InstantiationException, MalformedURLException;

    private static Object createObjectFromFactories(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception;

    private static String getURLScheme(String str);

    public static Object getObjectInstance(Object refInfo, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception;

    static Object processURLAddrs(Reference ref, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException;

    private static Object processURL(Object refInfo, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException;

    static Context getContext(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException;

    static Resolver getResolver(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException;

    public static Context getURLContext(String scheme, Hashtable<?, ?> environment) throws NamingException;

    private static final String defaultPkgPrefix = "com.sun.jndi.url";

    private static Object getURLObject(String scheme, Object urlInfo, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException;

    private static InitialContextFactoryBuilder initctx_factory_builder = null;

    private static synchronized InitialContextFactoryBuilder getInitialContextFactoryBuilder();

    public static Context getInitialContext(Hashtable<?, ?> env) throws NamingException;

    public static synchronized void setInitialContextFactoryBuilder(InitialContextFactoryBuilder builder) throws NamingException;

    public static boolean hasInitialContextFactoryBuilder();

    @Interned
    public static final String CPE = "java.naming.spi.CannotProceedException";

    @SuppressWarnings("unchecked")
    public static Context getContinuationContext(CannotProceedException cpe) throws NamingException;

    public static Object getStateToBind(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException;
}
