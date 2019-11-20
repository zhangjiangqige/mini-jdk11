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

    public static synchronized void setObjectFactoryBuilder(ObjectFactoryBuilder builder) throws NamingException;

    public static Object getObjectInstance(Object refInfo, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception;

    public static Context getURLContext(String scheme, Hashtable<?, ?> environment) throws NamingException;

    public static Context getInitialContext(Hashtable<?, ?> env) throws NamingException;

    public static synchronized void setInitialContextFactoryBuilder(InitialContextFactoryBuilder builder) throws NamingException;

    public static boolean hasInitialContextFactoryBuilder();

    @Interned
    public static final String CPE;

    @SuppressWarnings("unchecked")
    public static Context getContinuationContext(CannotProceedException cpe) throws NamingException;

    public static Object getStateToBind(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException;
}
