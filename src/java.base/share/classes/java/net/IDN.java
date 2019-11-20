package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.net.idn.StringPrep;
import sun.net.idn.Punycode;
import sun.text.normalizer.UCharacterIterator;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class IDN {

    public static final int ALLOW_UNASSIGNED;

    public static final int USE_STD3_ASCII_RULES;

    public static String toASCII(String input, int flag);

    public static String toASCII(String input);

    public static String toUnicode(String input, int flag);

    public static String toUnicode(String input);
}
