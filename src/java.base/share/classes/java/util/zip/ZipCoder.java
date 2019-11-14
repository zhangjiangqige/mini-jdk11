package java.util.zip;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import static java.nio.charset.StandardCharsets.UTF_8;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
class ZipCoder {

    private static final jdk.internal.misc.JavaLangAccess JLA = jdk.internal.misc.SharedSecrets.getJavaLangAccess();

    static final class UTF8 extends ZipCoder {

        UTF8(Charset utf8) {
            super(utf8);
        }

        @Override
        boolean isUTF8();

        @Override
        String toString(byte[] ba, int off, int length);

        @Override
        byte[] getBytes(String s);
    }

    private static ZipCoder utf8 = new UTF8(UTF_8);

    public static ZipCoder get(Charset charset);

    String toString(byte[] ba, int off, int length);

    String toString(byte[] ba, int length);

    String toString(byte[] ba);

    byte[] getBytes(String s);

    byte[] getBytesUTF8(String s);

    String toStringUTF8(byte[] ba, int len);

    String toStringUTF8(byte[] ba, int off, int len);

    boolean isUTF8();

    private Charset cs;

    private CharsetDecoder dec;

    private CharsetEncoder enc;

    private ZipCoder(Charset cs) {
        this.cs = cs;
    }

    protected CharsetDecoder decoder();

    protected CharsetEncoder encoder();
}
