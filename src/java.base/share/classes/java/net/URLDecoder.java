package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Objects;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class URLDecoder {

    static String dfltEncName = URLEncoder.dfltEncName;

    @Deprecated
    public static String decode(String s);

    public static String decode(String s, String enc) throws UnsupportedEncodingException;

    public static String decode(String s, Charset charset);
}
