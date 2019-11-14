package java.io;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.reflect.Field;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public class ObjectStreamField implements Comparable<Object> {

    private final String name;

    private final String signature;

    private final Class<?> type;

    private String typeSignature;

    private final boolean unshared;

    private final Field field;

    private int offset;

    public ObjectStreamField(String name, Class<?> type) {
        this(name, type, false);
    }

    public ObjectStreamField(String name, Class<?> type, boolean unshared) {
        if (name == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.type = type;
        this.unshared = unshared;
        this.field = null;
        this.signature = null;
    }

    ObjectStreamField(String name, String signature, boolean unshared) {
        if (name == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.signature = signature.intern();
        this.unshared = unshared;
        this.field = null;
        switch(signature.charAt(0)) {
            case 'Z':
                type = Boolean.TYPE;
                break;
            case 'B':
                type = Byte.TYPE;
                break;
            case 'C':
                type = Character.TYPE;
                break;
            case 'S':
                type = Short.TYPE;
                break;
            case 'I':
                type = Integer.TYPE;
                break;
            case 'J':
                type = Long.TYPE;
                break;
            case 'F':
                type = Float.TYPE;
                break;
            case 'D':
                type = Double.TYPE;
                break;
            case 'L':
            case '[':
                type = Object.class;
                break;
            default:
                throw new IllegalArgumentException("illegal signature");
        }
    }

    private static String getPrimitiveSignature(Class<?> cl);

    static String getClassSignature(Class<?> cl);

    static StringBuilder appendClassSignature(StringBuilder sbuf, Class<?> cl);

    ObjectStreamField(Field field, boolean unshared, boolean showType) {
        this.field = field;
        this.unshared = unshared;
        name = field.getName();
        Class<?> ftype = field.getType();
        type = (showType || ftype.isPrimitive()) ? ftype : Object.class;
        signature = getClassSignature(ftype).intern();
    }

    public String getName();

    @CallerSensitive
    public Class<?> getType();

    public char getTypeCode();

    @Nullable
    @Interned
    public String getTypeString();

    public int getOffset();

    protected void setOffset(int offset);

    @Pure
    public boolean isPrimitive(@GuardSatisfied ObjectStreamField this);

    @Pure
    public boolean isUnshared(@GuardSatisfied ObjectStreamField this);

    @Pure
    public int compareTo(@GuardSatisfied ObjectStreamField this, @GuardSatisfied Object obj);

    @SideEffectFree
    public String toString(@GuardSatisfied ObjectStreamField this);

    Field getField();

    String getSignature();
}
