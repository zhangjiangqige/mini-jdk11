package java.lang;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.*;

@AnnotatedFor({ "lock", "nullness", "index" })
public interface Comparable<T extends @NonNull Object> {

    @CFComment("nullness: arguments may NOT be null")
    @Pure
    public int compareTo(@GuardSatisfied Comparable<T> this, @NonNull T o);
}
