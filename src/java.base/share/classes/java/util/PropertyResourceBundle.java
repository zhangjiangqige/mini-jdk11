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

    private static final String encoding = GetPropertyAction.privilegedGetProperty("java.util.PropertyResourceBundle.encoding", "").toUpperCase(Locale.ROOT);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PropertyResourceBundle(InputStream stream) throws IOException {
        this(new InputStreamReader(stream, "ISO-8859-1".equals(encoding) ? StandardCharsets.ISO_8859_1.newDecoder() : new PropertyResourceBundleCharset("UTF-8".equals(encoding)).newDecoder()));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PropertyResourceBundle(Reader reader) throws IOException {
        Properties properties = new Properties();
        properties.load(reader);
        lookup = new HashMap(properties);
    }

    public Object handleGetObject(String key);

    public Enumeration<String> getKeys();

    protected Set<String> handleKeySet();

    private final Map<String, Object> lookup;
}
