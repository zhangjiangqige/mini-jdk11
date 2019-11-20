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

    public ObjectStreamField(String name, Class<?> type) {
    }

    public ObjectStreamField(String name, Class<?> type, boolean unshared) {
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
}
