package java.lang.reflect;

import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.LengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
public final class Array {

    public static Object newInstance(Class<?> componentType, @NonNegative int length) throws NegativeArraySizeException;

    public static Object newInstance(Class<?> componentType, @NonNegative int... dimensions) throws IllegalArgumentException, NegativeArraySizeException;

    @HotSpotIntrinsicCandidate
    @LengthOf({ "#1" })
    public static native int getLength(Object array) throws IllegalArgumentException;

    public static native Object get(Object array, @IndexFor({ "#1" }) int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native boolean getBoolean(Object array, @IndexFor({ "#1" }) int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native byte getByte(Object array, @IndexFor({ "#1" }) int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native char getChar(Object array, @IndexFor({ "#1" }) int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native short getShort(Object array, @IndexFor({ "#1" }) int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native int getInt(Object array, @IndexFor({ "#1" }) int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native long getLong(Object array, @IndexFor({ "#1" }) int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native float getFloat(Object array, @IndexFor({ "#1" }) int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native double getDouble(Object array, @IndexFor({ "#1" }) int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native void set(Object array, @IndexFor({ "#1" }) int index, Object value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native void setBoolean(Object array, @IndexFor({ "#1" }) int index, boolean z) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native void setByte(Object array, @IndexFor({ "#1" }) int index, byte b) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native void setChar(Object array, @IndexFor({ "#1" }) int index, char c) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native void setShort(Object array, @IndexFor({ "#1" }) int index, short s) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native void setInt(Object array, @IndexFor({ "#1" }) int index, int i) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native void setLong(Object array, @IndexFor({ "#1" }) int index, long l) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native void setFloat(Object array, @IndexFor({ "#1" }) int index, float f) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    public static native void setDouble(Object array, @IndexFor({ "#1" }) int index, double d) throws IllegalArgumentException, ArrayIndexOutOfBoundsException;
}
