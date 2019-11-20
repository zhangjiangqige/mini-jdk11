package java.lang.invoke;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import static java.lang.invoke.MethodHandleStatics.*;
import static java.lang.invoke.MethodHandles.Lookup.IMPL_LOOKUP;
import jdk.internal.vm.annotation.Stable;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
abstract public class CallSite {

    public MethodType type();

    public abstract MethodHandle getTarget();

    public abstract void setTarget(MethodHandle newTarget);

    public abstract MethodHandle dynamicInvoker();
}
