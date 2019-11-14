package javax.xml.parsers;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.validation.Schema;
import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@AnnotatedFor("nullness")
public abstract class DocumentBuilder {

    protected DocumentBuilder() {
    }

    @CFComment({ "nullness: this.getClass().getPackage() is non-null as this class is in the `parsers` package" })
    @SuppressWarnings({ "nullness" })
    public void reset();

    public Document parse(InputStream is) throws SAXException, IOException;

    public Document parse(InputStream is, String systemId) throws SAXException, IOException;

    public Document parse(String uri) throws SAXException, IOException;

    public Document parse(File f) throws SAXException, IOException;

    public abstract Document parse(InputSource is) throws SAXException, IOException;

    public abstract boolean isNamespaceAware();

    public abstract boolean isValidating();

    public abstract void setEntityResolver(@Nullable EntityResolver er);

    public abstract void setErrorHandler(@Nullable ErrorHandler eh);

    public abstract Document newDocument();

    public abstract DOMImplementation getDOMImplementation();

    @CFComment("nullness: this.getClass().getPackage() is non-null as this class is in the `parsers` package")
    @SuppressWarnings({ "nullness" })
    @Nullable
    public Schema getSchema();

    @CFComment("nullness: this.getClass().getPackage() is non-null as this class is in the `parsers` package")
    @SuppressWarnings({ "nullness" })
    public boolean isXIncludeAware();
}
