package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnmappableCharacterException;
import sun.security.action.GetPropertyAction;
import sun.util.PropertyResourceBundleCharset;
import sun.util.ResourceBundleEnumeration;

@AnnotatedFor({ "lock" })
public class PropertyResourceBundle extends ResourceBundle {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PropertyResourceBundle(InputStream stream) throws IOException {
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PropertyResourceBundle(Reader reader) throws IOException {
    }

    public Object handleGetObject(String key);

    public Enumeration<String> getKeys();

    protected Set<String> handleKeySet();
}
