package java.lang.invoke;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class SwitchPoint {

    public SwitchPoint() {
    }

    public boolean hasBeenInvalidated();

    public MethodHandle guardWithTest(MethodHandle target, MethodHandle fallback);

    public static void invalidateAll(SwitchPoint[] switchPoints);
}
