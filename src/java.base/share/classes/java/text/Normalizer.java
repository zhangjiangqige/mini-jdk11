package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.text.normalizer.NormalizerBase;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Normalizer {

    private Normalizer() {
    }

    public static enum Form {

        NFD, NFC, NFKD, NFKC
    }

    public static String normalize(CharSequence src, Form form);

    public static boolean isNormalized(CharSequence src, Form form);
}
