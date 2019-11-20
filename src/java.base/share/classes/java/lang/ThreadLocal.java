package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.misc.TerminatingThreadLocal;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@CFComment({ "nullness: It is permitted to write a subclass that extends ThreadLocal<@NonNull MyType>", "but in such a case:", "* the subclass must override initialValue to return a non-null value", "* the subclass needs to suppress a warning:", "@SuppressWarnings(\"nullness:type.argument.type.incompatible\") // initialValue returns non-null" })
@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public class ThreadLocal<@Nullable T> {

    protected T initialValue();

    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier);

    public ThreadLocal() {
    }

    public T get();

    public void set(T value);

    public void remove();
}
