package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.UnsupportedEncodingException;
import java.io.CharArrayWriter;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;
import java.util.Objects;
import sun.security.action.GetPropertyAction;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class URLEncoder {

    static BitSet dontNeedEncoding;

    static final int caseDiff = ('a' - 'A');

    static String dfltEncName = null;

    static {
        dontNeedEncoding = new BitSet(256);
        int i;
        for (i = 'a'; i <= 'z'; i++) {
            dontNeedEncoding.set(i);
        }
        for (i = 'A'; i <= 'Z'; i++) {
            dontNeedEncoding.set(i);
        }
        for (i = '0'; i <= '9'; i++) {
            dontNeedEncoding.set(i);
        }
        dontNeedEncoding.set(' ');
        dontNeedEncoding.set('-');
        dontNeedEncoding.set('_');
        dontNeedEncoding.set('.');
        dontNeedEncoding.set('*');
        dfltEncName = GetPropertyAction.privilegedGetProperty("file.encoding");
    }

    private URLEncoder() {
    }

    @Deprecated
    public static String encode(String s);

    public static String encode(String s, String enc) throws UnsupportedEncodingException;

    public static String encode(String s, Charset charset);
}
