package java.io;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
class Bits {

    static boolean getBoolean(byte[] b, int off);

    static char getChar(byte[] b, int off);

    static short getShort(byte[] b, int off);

    static int getInt(byte[] b, int off);

    static float getFloat(byte[] b, int off);

    static long getLong(byte[] b, int off);

    static double getDouble(byte[] b, int off);

    static void putBoolean(byte[] b, int off, boolean val);

    static void putChar(byte[] b, int off, char val);

    static void putShort(byte[] b, int off, short val);

    static void putInt(byte[] b, int off, int val);

    static void putFloat(byte[] b, int off, float val);

    static void putLong(byte[] b, int off, long val);

    static void putDouble(byte[] b, int off, double val);
}
