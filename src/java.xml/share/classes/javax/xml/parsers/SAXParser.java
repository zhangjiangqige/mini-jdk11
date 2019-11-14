package javax.xml.parsers;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.validation.Schema;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

@AnnotatedFor("nullness")
@SuppressWarnings("deprecation")
public abstract class SAXParser {

    protected SAXParser() {
    }

    @CFComment("nullness: this.getClass().getPackage() is non-null as this class is in the `parsers` package")
    @SuppressWarnings({ "nullness" })
    public void reset();

    public void parse(InputStream is, HandlerBase hb) throws SAXException, IOException;

    public void parse(InputStream is, HandlerBase hb, String systemId) throws SAXException, IOException;

    public void parse(InputStream is, DefaultHandler dh) throws SAXException, IOException;

    public void parse(InputStream is, DefaultHandler dh, String systemId) throws SAXException, IOException;

    public void parse(String uri, HandlerBase hb) throws SAXException, IOException;

    public void parse(String uri, DefaultHandler dh) throws SAXException, IOException;

    public void parse(File f, HandlerBase hb) throws SAXException, IOException;

    public void parse(File f, DefaultHandler dh) throws SAXException, IOException;

    public void parse(InputSource is, HandlerBase hb) throws SAXException, IOException;

    public void parse(InputSource is, DefaultHandler dh) throws SAXException, IOException;

    public abstract org.xml.sax.Parser getParser() throws SAXException;

    public abstract org.xml.sax.XMLReader getXMLReader() throws SAXException;

    public abstract boolean isNamespaceAware();

    public abstract boolean isValidating();

    public abstract void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException;

    public abstract Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException;

    @CFComment("nullness: this.getClass().getPackage() is non-null as this class is in the `parsers` package")
    @SuppressWarnings({ "nullness" })
    @Nullable
    public Schema getSchema();

    @CFComment("nullness: this.getClass().getPackage() is non-null as this class is in the `parsers` package")
    @SuppressWarnings({ "nullness" })
    public boolean isXIncludeAware();
}
