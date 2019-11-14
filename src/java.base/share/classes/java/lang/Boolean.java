package java.lang;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "interning", "nullness" })
public final class Boolean implements java.io.Serializable, Comparable<Boolean> {

    @Interned
    public static final Boolean TRUE = new Boolean(true);

    @Interned
    public static final Boolean FALSE = new Boolean(false);

    @SuppressWarnings("unchecked")
    public static final Class<Boolean> TYPE = (Class<Boolean>) Class.getPrimitiveClass("boolean");

    private final boolean value;

    private static final long serialVersionUID = -3665804199014368530L;

    @Deprecated(since = "9")
    public Boolean(boolean value) {
        this.value = value;
    }

    @Deprecated(since = "9")
    public Boolean(@Nullable String s) {
        this(parseBoolean(s));
    }

    @Pure
    public static boolean parseBoolean(@Nullable String s);

    @Pure
    @HotSpotIntrinsicCandidate
    public boolean booleanValue();

    @Pure
    @HotSpotIntrinsicCandidate
    @Interned
    public static Boolean valueOf(boolean b);

    @Pure
    @Interned
    public static Boolean valueOf(@Nullable String s);

    @Pure
    public static String toString(boolean b);

    @SideEffectFree
    public String toString();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(boolean value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @Pure
    public static boolean getBoolean(@Nullable String name);

    @Pure
    public int compareTo(Boolean b);

    public static int compare(boolean x, boolean y);

    public static boolean logicalAnd(boolean a, boolean b);

    public static boolean logicalOr(boolean a, boolean b);

    public static boolean logicalXor(boolean a, boolean b);
}
