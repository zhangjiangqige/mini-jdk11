package javax.xml.parsers;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import javax.xml.validation.Schema;

@AnnotatedFor("nullness")
public abstract class DocumentBuilderFactory {

    protected DocumentBuilderFactory() {
    }

    public static DocumentBuilderFactory newDefaultInstance();

    public static DocumentBuilderFactory newInstance();

    public static DocumentBuilderFactory newInstance(String factoryClassName, @Nullable ClassLoader classLoader);

    public abstract DocumentBuilder newDocumentBuilder() throws ParserConfigurationException;

    public void setNamespaceAware(boolean awareness);

    public void setValidating(boolean validating);

    public void setIgnoringElementContentWhitespace(boolean whitespace);

    public void setExpandEntityReferences(boolean expandEntityRef);

    public void setIgnoringComments(boolean ignoreComments);

    public void setCoalescing(boolean coalescing);

    public boolean isNamespaceAware();

    public boolean isValidating();

    public boolean isIgnoringElementContentWhitespace();

    public boolean isExpandEntityReferences();

    public boolean isIgnoringComments();

    public boolean isCoalescing();

    public abstract void setAttribute(String name, Object value) throws IllegalArgumentException;

    public abstract Object getAttribute(String name) throws IllegalArgumentException;

    public abstract void setFeature(String name, boolean value) throws ParserConfigurationException;

    public abstract boolean getFeature(String name) throws ParserConfigurationException;

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
