package org.checkerframework.checker.signedness;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.checkerframework.checker.signedness.qual.Unsigned;

public final class SignednessUtil {

    private SignednessUtil() {
        throw new Error("Do not instantiate");
    }

    @SuppressWarnings("signedness")
    public static ByteBuffer wrapUnsigned(@Unsigned byte[] array);

    @SuppressWarnings("signedness")
    public static ByteBuffer wrapUnsigned(@Unsigned byte[] array, int offset, int length);

    @SuppressWarnings("signedness")
    @Unsigned
    public static int getUnsignedInt(ByteBuffer b);

    @SuppressWarnings("signedness")
    @Unsigned
    public static short getUnsignedShort(ByteBuffer b);

    @SuppressWarnings("signedness")
    @Unsigned
    public static byte getUnsigned(ByteBuffer b);

    @SuppressWarnings("signedness")
    @Unsigned
    public static byte getUnsigned(ByteBuffer b, int i);

    @SuppressWarnings("signedness")
    public static ByteBuffer getUnsigned(ByteBuffer b, byte[] bs, int i, int l);

    @SuppressWarnings("signedness")
    public static ByteBuffer putUnsigned(ByteBuffer b, @Unsigned byte ubyte);

    @SuppressWarnings("signedness")
    public static ByteBuffer putUnsigned(ByteBuffer b, int i, @Unsigned byte ubyte);

    @SuppressWarnings("signedness")
    public static IntBuffer putUnsigned(IntBuffer b, @Unsigned int uint);

    @SuppressWarnings("signedness")
    public static IntBuffer putUnsigned(IntBuffer b, int i, @Unsigned int uint);

    @SuppressWarnings("signedness")
    public static IntBuffer putUnsigned(IntBuffer b, @Unsigned int[] uints);

    @SuppressWarnings("signedness")
    public static IntBuffer putUnsigned(IntBuffer b, @Unsigned int[] uints, int i, int l);

    @SuppressWarnings("signedness")
    @Unsigned
    public static int getUnsigned(IntBuffer b, int i);

    @SuppressWarnings("signedness")
    public static ByteBuffer putUnsignedShort(ByteBuffer b, @Unsigned short ushort);

    @SuppressWarnings("signedness")
    public static ByteBuffer putUnsignedShort(ByteBuffer b, int i, @Unsigned short ushort);

    @SuppressWarnings("signedness")
    public static ByteBuffer putUnsignedInt(ByteBuffer b, @Unsigned int uint);

    @SuppressWarnings("signedness")
    public static ByteBuffer putUnsignedInt(ByteBuffer b, int i, @Unsigned int uint);

    @SuppressWarnings("signedness")
    public static ByteBuffer putUnsignedLong(ByteBuffer b, int i, @Unsigned long ulong);

    @Deprecated
    @SuppressWarnings("signedness")
    @Unsigned
    public static byte readUnsignedByte(RandomAccessFile f) throws IOException;

    @SuppressWarnings("signedness")
    @Unsigned
    public static char readUnsignedChar(RandomAccessFile f) throws IOException;

    @Deprecated
    @SuppressWarnings("signedness")
    @Unsigned
    public static short readUnsignedShort(RandomAccessFile f) throws IOException;

    @SuppressWarnings("signedness")
    @Unsigned
    public static int readUnsignedInt(RandomAccessFile f) throws IOException;

    @SuppressWarnings("signedness")
    @Unsigned
    public static long readUnsignedLong(RandomAccessFile f) throws IOException;

    @SuppressWarnings("signedness")
    public static int readUnsigned(RandomAccessFile f, @Unsigned byte[] b, int off, int len) throws IOException;

    @SuppressWarnings("signedness")
    public static void readFullyUnsigned(RandomAccessFile f, @Unsigned byte[] b) throws IOException;

    @SuppressWarnings("signedness")
    public static void writeUnsigned(RandomAccessFile f, @Unsigned byte[] bs, int off, int len) throws IOException;

    @SuppressWarnings("signedness")
    public static void writeUnsignedByte(RandomAccessFile f, @Unsigned byte b) throws IOException;

    @SuppressWarnings("signedness")
    public static void writeUnsignedChar(RandomAccessFile f, @Unsigned char c) throws IOException;

    @SuppressWarnings("signedness")
    public static void writeUnsignedShort(RandomAccessFile f, @Unsigned short s) throws IOException;

    @SuppressWarnings("signedness")
    public static void writeUnsignedInt(RandomAccessFile f, @Unsigned int i) throws IOException;

    @SuppressWarnings("signedness")
    public static void writeUnsignedLong(RandomAccessFile f, @Unsigned long l) throws IOException;

    @SuppressWarnings("signedness")
    public static void getUnsigned(ByteBuffer b, @Unsigned byte[] bs);

    @Deprecated
    @SuppressWarnings("signedness")
    public static int compareUnsigned(@Unsigned long x, @Unsigned long y);

    @Deprecated
    @SuppressWarnings("signedness")
    public static int compareUnsigned(@Unsigned int x, @Unsigned int y);

    @SuppressWarnings("signedness")
    public static int compareUnsigned(@Unsigned short x, @Unsigned short y);

    @SuppressWarnings("signedness")
    public static int compareUnsigned(@Unsigned byte x, @Unsigned byte y);

    @Deprecated
    @SuppressWarnings("signedness")
    public static String toUnsignedString(@Unsigned long l);

    @Deprecated
    @SuppressWarnings("signedness")
    public static String toUnsignedString(@Unsigned long l, int radix);

    @Deprecated
    @SuppressWarnings("signedness")
    public static String toUnsignedString(@Unsigned int i);

    @Deprecated
    @SuppressWarnings("signedness")
    public static String toUnsignedString(@Unsigned int i, int radix);

    @SuppressWarnings("signedness")
    public static String toUnsignedString(@Unsigned short s);

    @SuppressWarnings("signedness")
    public static String toUnsignedString(@Unsigned short s, int radix);

    @SuppressWarnings("signedness")
    public static String toUnsignedString(@Unsigned byte b);

    @SuppressWarnings("signedness")
    public static String toUnsignedString(@Unsigned byte b, int radix);

    @SuppressWarnings("signedness")
    @Unsigned
    private static BigInteger toUnsignedBigInteger(@Unsigned long l);

    @Deprecated
    @Unsigned
    public static long toUnsignedLong(@Unsigned int i);

    @Deprecated
    @Unsigned
    public static long toUnsignedLong(@Unsigned short s);

    @Deprecated
    @Unsigned
    public static int toUnsignedInt(@Unsigned short s);

    @Deprecated
    @Unsigned
    public static long toUnsignedLong(@Unsigned byte b);

    @Deprecated
    @Unsigned
    public static int toUnsignedInt(@Unsigned byte b);

    @Unsigned
    public static short toUnsignedShort(@Unsigned byte b);

    @Unsigned
    public static long toUnsignedLong(@Unsigned char c);

    @Unsigned
    public static int toUnsignedInt(@Unsigned char c);

    @Unsigned
    public static short toUnsignedShort(@Unsigned char c);

    public static float toFloat(@Unsigned byte b);

    public static float toFloat(@Unsigned short s);

    public static float toFloat(@Unsigned int i);

    public static float toFloat(@Unsigned long l);

    public static double toDouble(@Unsigned byte b);

    public static double toDouble(@Unsigned short s);

    public static double toDouble(@Unsigned int i);

    public static double toDouble(@Unsigned long l);

    @SuppressWarnings("signedness")
    @Unsigned
    public static byte byteFromFloat(float f);

    @SuppressWarnings("signedness")
    @Unsigned
    public static short shortFromFloat(float f);

    @SuppressWarnings("signedness")
    @Unsigned
    public static int intFromFloat(float f);

    @SuppressWarnings("signedness")
    @Unsigned
    public static long longFromFloat(float f);

    @SuppressWarnings("signedness")
    @Unsigned
    public static byte byteFromDouble(double d);

    @SuppressWarnings("signedness")
    @Unsigned
    public static short shortFromDouble(double d);

    @SuppressWarnings("signedness")
    @Unsigned
    public static int intFromDouble(double d);

    @SuppressWarnings("signedness")
    @Unsigned
    public static long longFromDouble(double d);
}
