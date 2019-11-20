package java.nio;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class ByteOrder {

    public static final ByteOrder BIG_ENDIAN;

    public static final ByteOrder LITTLE_ENDIAN;

    public static ByteOrder nativeOrder();

    public String toString();
}
