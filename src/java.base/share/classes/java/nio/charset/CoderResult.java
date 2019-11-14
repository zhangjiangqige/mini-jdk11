package java.nio.charset;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CoderResult {

    private static final int CR_UNDERFLOW = 0;

    private static final int CR_OVERFLOW = 1;

    private static final int CR_ERROR_MIN = 2;

    private static final int CR_MALFORMED = 2;

    private static final int CR_UNMAPPABLE = 3;

    private static final String[] names = { "UNDERFLOW", "OVERFLOW", "MALFORMED", "UNMAPPABLE" };

    private final int type;

    private final int length;

    private CoderResult(int type, int length) {
        this.type = type;
        this.length = length;
    }

    public String toString();

    public boolean isUnderflow();

    public boolean isOverflow();

    public boolean isError();

    public boolean isMalformed();

    public boolean isUnmappable();

    public int length();

    public static final CoderResult UNDERFLOW = new CoderResult(CR_UNDERFLOW, 0);

    public static final CoderResult OVERFLOW = new CoderResult(CR_OVERFLOW, 0);

    private static final class Cache {

        static final Cache INSTANCE = new Cache();

        private Cache() {
        }

        final Map<Integer, CoderResult> unmappable = new ConcurrentHashMap<>();

        final Map<Integer, CoderResult> malformed = new ConcurrentHashMap<>();
    }

    private static final CoderResult[] malformed4 = new CoderResult[] { new CoderResult(CR_MALFORMED, 1), new CoderResult(CR_MALFORMED, 2), new CoderResult(CR_MALFORMED, 3), new CoderResult(CR_MALFORMED, 4) };

    public static CoderResult malformedForLength(int length);

    private static final CoderResult[] unmappable4 = new CoderResult[] { new CoderResult(CR_UNMAPPABLE, 1), new CoderResult(CR_UNMAPPABLE, 2), new CoderResult(CR_UNMAPPABLE, 3), new CoderResult(CR_UNMAPPABLE, 4) };

    public static CoderResult unmappableForLength(int length);

    public void throwException() throws CharacterCodingException;
}
