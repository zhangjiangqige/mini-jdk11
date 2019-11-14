package java.lang.invoke;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class SwitchPoint {

    private static final MethodHandle K_true = MethodHandles.constant(boolean.class, true), K_false = MethodHandles.constant(boolean.class, false);

    private final MutableCallSite mcs;

    private final MethodHandle mcsInvoker;

    public SwitchPoint() {
        this.mcs = new MutableCallSite(K_true);
        this.mcsInvoker = mcs.dynamicInvoker();
    }

    public boolean hasBeenInvalidated();

    public MethodHandle guardWithTest(MethodHandle target, MethodHandle fallback);

    public static void invalidateAll(SwitchPoint[] switchPoints);
}
