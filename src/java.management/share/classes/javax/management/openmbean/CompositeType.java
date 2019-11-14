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

    static final long serialVersionUID = -5366242454346948798L;

    private TreeMap<String, String> nameToDescription;

    private TreeMap<String, OpenType<?>> nameToType;

    @MonotonicNonNull
    private transient Integer myHashCode = null;

    @MonotonicNonNull
    private transient String myToString = null;

    @MonotonicNonNull
    private transient Set<String> myNamesSet = null;

    public CompositeType(String typeName, String description, String[] itemNames, String[] itemDescriptions, OpenType<?>[] itemTypes) throws OpenDataException {
        super(CompositeData.class.getName(), typeName, description, false);
        checkForNullElement(itemNames, "itemNames");
        checkForNullElement(itemDescriptions, "itemDescriptions");
        checkForNullElement(itemTypes, "itemTypes");
        checkForEmptyString(itemNames, "itemNames");
        checkForEmptyString(itemDescriptions, "itemDescriptions");
        if ((itemNames.length != itemDescriptions.length) || (itemNames.length != itemTypes.length)) {
            throw new IllegalArgumentException("Array arguments itemNames[], itemDescriptions[] and itemTypes[] " + "should be of same length (got " + itemNames.length + ", " + itemDescriptions.length + " and " + itemTypes.length + ").");
        }
        nameToDescription = new TreeMap<String, String>();
        nameToType = new TreeMap<String, OpenType<?>>();
        String key;
        for (int i = 0; i < itemNames.length; i++) {
            key = itemNames[i].trim();
            if (nameToDescription.containsKey(key)) {
                throw new OpenDataException("Argument's element itemNames[" + i + "]=\"" + itemNames[i] + "\" duplicates a previous item names.");
            }
            nameToDescription.put(key, itemDescriptions[i].trim());
            nameToType.put(key, itemTypes[i]);
        }
    }

    private static void checkForNullElement(Object[] arg, String argName);

    private static void checkForEmptyString(String[] arg, String argName);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    public boolean containsKey(@Nullable String itemName);

    @Nullable
    public String getDescription(@Nullable String itemName);

    @Nullable
    public OpenType<?> getType(@Nullable String itemName);

    public Set<String> keySet();

    public boolean isValue(@Nullable Object obj);

    @Override
    boolean isAssignableFrom(OpenType<?> ot);

    public boolean equals(@Nullable Object obj);

    public int hashCode();

    public String toString();
}
