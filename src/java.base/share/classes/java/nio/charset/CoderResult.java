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

    public String toString();

    public boolean isUnderflow();

    public boolean isOverflow();

    public boolean isError();

    public boolean isMalformed();

    public boolean isUnmappable();

    public int length();

    public static final CoderResult UNDERFLOW;

    public static final CoderResult OVERFLOW;

    public static CoderResult malformedForLength(int length);

    public static CoderResult unmappableForLength(int length);

    public void throwException() throws CharacterCodingException;
}
