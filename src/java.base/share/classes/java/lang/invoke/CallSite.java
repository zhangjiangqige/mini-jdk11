package java.lang.invoke;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import static java.lang.invoke.MethodHandleStatics.*;
import static java.lang.invoke.MethodHandles.Lookup.IMPL_LOOKUP;
import jdk.internal.vm.annotation.Stable;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
abstract public class CallSite {

    MethodHandle target;

    CallSite(MethodType type) {
        target = makeUninitializedCallSite(type);
    }

    CallSite(MethodHandle target) {
        target.type();
        this.target = target;
    }

    CallSite(MethodType targetType, MethodHandle createTargetHook) throws Throwable {
        this(targetType);
        ConstantCallSite selfCCS = (ConstantCallSite) this;
        MethodHandle boundTarget = (MethodHandle) createTargetHook.invokeWithArguments(selfCCS);
        checkTargetChange(this.target, boundTarget);
        this.target = boundTarget;
    }

    private final MethodHandleNatives.CallSiteContext context = MethodHandleNatives.CallSiteContext.make(this);

    public MethodType type();

    public abstract MethodHandle getTarget();

    public abstract void setTarget(MethodHandle newTarget);

    void checkTargetChange(MethodHandle oldTarget, MethodHandle newTarget);

    private static WrongMethodTypeException wrongTargetType(MethodHandle target, MethodType type);

    public abstract MethodHandle dynamicInvoker();

    MethodHandle makeDynamicInvoker();

    @Stable
    private static MethodHandle GET_TARGET;

    private static MethodHandle getTargetHandle();

    @Stable
    private static MethodHandle THROW_UCS;

    private static MethodHandle uninitializedCallSiteHandle();

    private static Object uninitializedCallSite(Object... ignore);

    private MethodHandle makeUninitializedCallSite(MethodType targetType);

    @Stable
    private static long TARGET_OFFSET;

    private static long getTargetOffset();

    void setTargetNormal(MethodHandle newTarget);

    MethodHandle getTargetVolatile();

    void setTargetVolatile(MethodHandle newTarget);

    static CallSite makeSite(MethodHandle bootstrapMethod, String name, MethodType type, Object info, Class<?> callerClass);
}
