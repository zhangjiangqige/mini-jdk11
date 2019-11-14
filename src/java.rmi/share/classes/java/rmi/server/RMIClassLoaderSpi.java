package java.rmi.server;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.net.MalformedURLException;
import java.net.URL;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class RMIClassLoaderSpi {

    public abstract Class<?> loadClass(String codebase, String name, ClassLoader defaultLoader) throws MalformedURLException, ClassNotFoundException;

    public abstract Class<?> loadProxyClass(String codebase, String[] interfaces, ClassLoader defaultLoader) throws MalformedURLException, ClassNotFoundException;

    public abstract ClassLoader getClassLoader(String codebase) throws MalformedURLException;

    public abstract String getClassAnnotation(Class<?> cl);
}
