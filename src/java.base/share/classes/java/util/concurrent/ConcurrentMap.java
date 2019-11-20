package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@AnnotatedFor({ "nullness" })
public interface ConcurrentMap<K extends @NonNull Object, V extends @NonNull Object> extends Map<K, V> {
}
