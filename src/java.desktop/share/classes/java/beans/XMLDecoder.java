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

    private final AccessControlContext acc = AccessController.getContext();

    private final DocumentHandler handler = new DocumentHandler();

    private final InputSource input;

    private Object owner;

    private Object[] array;

    private int index;

    public XMLDecoder(InputStream in) {
        this(in, null);
    }

    public XMLDecoder(InputStream in, Object owner) {
        this(in, owner, null);
    }

    public XMLDecoder(InputStream in, Object owner, ExceptionListener exceptionListener) {
        this(in, owner, exceptionListener, null);
    }

    public XMLDecoder(InputStream in, Object owner, ExceptionListener exceptionListener, ClassLoader cl) {
        this(new InputSource(in), owner, exceptionListener, cl);
    }

    public XMLDecoder(InputSource is) {
        this(is, null, null, null);
    }

    private XMLDecoder(InputSource is, Object owner, ExceptionListener el, ClassLoader cl) {
        this.input = is;
        this.owner = owner;
        setExceptionListener(el);
        this.handler.setClassLoader(cl);
        this.handler.setOwner(this);
    }

    public void close();

    private void close(Closeable in);

    private boolean parsingComplete();

    public void setExceptionListener(ExceptionListener exceptionListener);

    public ExceptionListener getExceptionListener();

    public Object readObject();

    public void setOwner(Object owner);

    public Object getOwner();

    public static DefaultHandler createHandler(Object owner, ExceptionListener el, ClassLoader cl);
}
