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

    @Override
    default V getOrDefault(Object key, V defaultValue);

    @Override
    default void forEach(BiConsumer<? super K, ? super V> action);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    V putIfAbsent(K key, V value);

    boolean remove(Object key, Object value);

    boolean replace(K key, V oldValue, V newValue);

    @Nullable
    V replace(K key, V value);

    @Override
    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    @Override
    default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

    @Override
    default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);
}
