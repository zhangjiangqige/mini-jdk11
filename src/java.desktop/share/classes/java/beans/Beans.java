package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import com.sun.beans.finder.ClassFinder;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.applet.AudioClip;
import java.awt.Image;
import java.beans.beancontext.BeanContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.StreamCorruptedException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Beans {

    public static Object instantiate(ClassLoader cls, String beanName) throws IOException, ClassNotFoundException;

    @SuppressWarnings("deprecation")
    public static Object instantiate(ClassLoader cls, String beanName, BeanContext beanContext) throws IOException, ClassNotFoundException;

    @Deprecated(since = "9")
    public static Object instantiate(ClassLoader cls, String beanName, BeanContext beanContext, AppletInitializer initializer) throws IOException, ClassNotFoundException;

    @SuppressWarnings("unchecked")
    private static void unsafeBeanContextAdd(BeanContext beanContext, Object res);

    public static Object getInstanceOf(Object bean, Class<?> targetType);

    public static boolean isInstanceOf(Object bean, Class<?> targetType);

    public static boolean isDesignTime();

    public static boolean isGuiAvailable();

    public static void setDesignTime(boolean isDesignTime) throws SecurityException;

    public static void setGuiAvailable(boolean isGuiAvailable) throws SecurityException;
}

class ObjectInputStreamWithLoader extends ObjectInputStream {

    private ClassLoader loader;

    public ObjectInputStreamWithLoader(InputStream in, ClassLoader loader) throws IOException, StreamCorruptedException {
        super(in);
        if (loader == null) {
            throw new IllegalArgumentException("Illegal null argument to ObjectInputStreamWithLoader");
        }
        this.loader = loader;
    }

    @SuppressWarnings("rawtypes")
    protected Class resolveClass(ObjectStreamClass classDesc) throws IOException, ClassNotFoundException;
}

@Deprecated(since = "9")
class BeansAppletContext implements AppletContext {

    Applet target;

    Hashtable<URL, Object> imageCache = new Hashtable<>();

    BeansAppletContext(Applet target) {
        this.target = target;
    }

    public AudioClip getAudioClip(URL url);

    public synchronized Image getImage(URL url);

    public Applet getApplet(String name);

    public Enumeration<Applet> getApplets();

    public void showDocument(URL url);

    public void showDocument(URL url, String target);

    public void showStatus(String status);

    public void setStream(String key, InputStream stream) throws IOException;

    public InputStream getStream(String key);

    public Iterator<String> getStreamKeys();
}

@Deprecated(since = "9")
class BeansAppletStub implements AppletStub {

    transient boolean active;

    transient Applet target;

    transient AppletContext context;

    transient URL codeBase;

    transient URL docBase;

    BeansAppletStub(Applet target, AppletContext context, URL codeBase, URL docBase) {
        this.target = target;
        this.context = context;
        this.codeBase = codeBase;
        this.docBase = docBase;
    }

    public boolean isActive();

    public URL getDocumentBase();

    public URL getCodeBase();

    public String getParameter(String name);

    public AppletContext getAppletContext();

    public void appletResize(int width, int height);
}
