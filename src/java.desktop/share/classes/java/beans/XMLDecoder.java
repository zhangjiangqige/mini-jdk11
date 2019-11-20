package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import com.sun.beans.decoder.DocumentHandler;
import java.io.Closeable;
import java.io.InputStream;
import java.io.IOException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class XMLDecoder implements AutoCloseable {

    public XMLDecoder(InputStream in) {
    }

    public XMLDecoder(InputStream in, Object owner) {
    }

    public XMLDecoder(InputStream in, Object owner, ExceptionListener exceptionListener) {
    }

    public XMLDecoder(InputStream in, Object owner, ExceptionListener exceptionListener, ClassLoader cl) {
    }

    public XMLDecoder(InputSource is) {
    }

    public void close();

    public void setExceptionListener(ExceptionListener exceptionListener);

    public ExceptionListener getExceptionListener();

    public Object readObject();

    public void setOwner(Object owner);

    public Object getOwner();

    public static DefaultHandler createHandler(Object owner, ExceptionListener el, ClassLoader cl);
}
