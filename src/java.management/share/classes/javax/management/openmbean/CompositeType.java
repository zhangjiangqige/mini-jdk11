package javax.management.openmbean;

import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.Set;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Iterator;

@AnnotatedFor("nullness")
public class CompositeType extends OpenType<CompositeData> {

    public CompositeType(String typeName, String description, String[] itemNames, String[] itemDescriptions, OpenType<?>[] itemTypes) throws OpenDataException {
    }

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    public boolean containsKey(@Nullable String itemName);

    @Nullable
    public String getDescription(@Nullable String itemName);

    @Nullable
    public OpenType<?> getType(@Nullable String itemName);

    public Set<String> keySet();

    public boolean isValue(@Nullable Object obj);

    public boolean equals(@Nullable Object obj);

    public int hashCode();

    public String toString();
}
