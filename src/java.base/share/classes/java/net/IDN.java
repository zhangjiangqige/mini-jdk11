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

    public static final int ALLOW_UNASSIGNED = 0x01;

    public static final int USE_STD3_ASCII_RULES = 0x02;

    public static String toASCII(String input, int flag);

    public static String toASCII(String input);

    public static String toUnicode(String input, int flag);

    public static String toUnicode(String input);

    private static final String ACE_PREFIX = "xn--";

    private static final int ACE_PREFIX_LENGTH = ACE_PREFIX.length();

    private static final int MAX_LABEL_LENGTH = 63;

    private static StringPrep namePrep = null;

    static {
        InputStream stream = null;
        try {
            final String IDN_PROFILE = "uidna.spp";
            if (System.getSecurityManager() != null) {
                stream = AccessController.doPrivileged(new PrivilegedAction<>() {

                    public InputStream run() {
                        return StringPrep.class.getResourceAsStream(IDN_PROFILE);
                    }
                });
            } else {
                stream = StringPrep.class.getResourceAsStream(IDN_PROFILE);
            }
            namePrep = new StringPrep(stream);
            stream.close();
        } catch (IOException e) {
            assert false;
        }
    }

    private IDN() {
    }

    private static String toASCIIInternal(String label, int flag);

    private static String toUnicodeInternal(String label, int flag);

    private static boolean isNonLDHAsciiCodePoint(int ch);

    private static int searchDots(String s, int start);

    private static boolean isRootLabel(String s);

    private static boolean isLabelSeparator(char c);

    private static boolean isAllASCII(String input);

    private static boolean startsWithACEPrefix(StringBuffer input);

    private static char toASCIILower(char ch);

    private static StringBuffer toASCIILower(StringBuffer input);
}
