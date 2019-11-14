package java.nio;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class ByteOrder {

    private String name;

    private ByteOrder(String name) {
        this.name = name;
    }

    public static final ByteOrder BIG_ENDIAN = new ByteOrder("BIG_ENDIAN");

    public static final ByteOrder LITTLE_ENDIAN = new ByteOrder("LITTLE_ENDIAN");

    private static final ByteOrder NATIVE_ORDER = Unsafe.getUnsafe().isBigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;

    public static ByteOrder nativeOrder();

    public String toString();
}
