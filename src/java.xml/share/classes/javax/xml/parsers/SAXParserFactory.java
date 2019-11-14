package javax.xml.parsers;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl;
import javax.xml.validation.Schema;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

@CFComment("nullness")
public abstract class SAXParserFactory {

    private boolean validating = false;

    private boolean namespaceAware = false;

    protected SAXParserFactory() {
    }

    public static SAXParserFactory newDefaultInstance();

    public static SAXParserFactory newInstance();

    public static SAXParserFactory newInstance(String factoryClassName, @Nullable ClassLoader classLoader);

    public abstract SAXParser newSAXParser() throws ParserConfigurationException, SAXException;

    public void setNamespaceAware(boolean awareness);

    public void setValidating(boolean validating);

    public boolean isNamespaceAware();

    public boolean isValidating();

    public abstract void setFeature(String name, boolean value) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException;

    public abstract boolean getFeature(String name) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException;

    @CFComment("nullness: this.getClass().getPackage() is non-null as this class is in the `parsers` package")
    @SuppressWarnings({ "nullness" })
    @Nullable
    public Schema getSchema();

    @CFComment("nullness: this.getClass().getPackage() is non-null as this class is in the `parsers` package")
    @SuppressWarnings({ "nullness" })
    public void setSchema(@Nullable Schema schema);

    public void setXIncludeAware(final boolean state);

    @CFComment("nullness: this.getClass().getPackage() is non-null as this class is in the `parsers` package")
    @SuppressWarnings({ "nullness" })
    public boolean isXIncludeAware();
}
