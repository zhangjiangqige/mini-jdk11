package java.nio.charset.spi;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.charset.Charset;
import java.util.Iterator;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class CharsetProvider {

    private static Void checkPermission();

    private CharsetProvider(Void ignore) {
    }

    protected CharsetProvider() {
        this(checkPermission());
    }

    public abstract Iterator<Charset> charsets();

    public abstract Charset charsetForName(String charsetName);
}