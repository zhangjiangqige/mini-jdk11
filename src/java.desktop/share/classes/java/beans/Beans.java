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

    @Deprecated()
    public static Object instantiate(ClassLoader cls, String beanName, BeanContext beanContext, AppletInitializer initializer) throws IOException, ClassNotFoundException;

    public static Object getInstanceOf(Object bean, Class<?> targetType);

    public static boolean isInstanceOf(Object bean, Class<?> targetType);

    public static boolean isDesignTime();

    public static boolean isGuiAvailable();

    public static void setDesignTime(boolean isDesignTime) throws SecurityException;

    public static void setGuiAvailable(boolean isGuiAvailable) throws SecurityException;
}
