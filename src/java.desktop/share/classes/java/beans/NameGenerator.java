package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import static java.util.Locale.ENGLISH;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class NameGenerator {

    private Map<Object, String> valueToName;

    private Map<String, Integer> nameToCount;

    public NameGenerator() {
        valueToName = new IdentityHashMap<>();
        nameToCount = new HashMap<>();
    }

    public void clear();

    @SuppressWarnings("rawtypes")
    public static String unqualifiedClassName(Class type);

    public static String capitalize(String name);

    public String instanceName(Object instance);
}
