package java.lang;

import org.checkerframework.checker.guieffect.qual.PolyUI;
import org.checkerframework.checker.guieffect.qual.PolyUIType;
import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "guieffect", "index", "lock", "nullness" })
@PolyUIType
public class Object {

    private static native void registerNatives();

    static {
        registerNatives();
    }

    @HotSpotIntrinsicCandidate
    public Object() {
    }

    @SafeEffect
    @Pure
    @HotSpotIntrinsicCandidate
    public final native Class<?> getClass(@PolyUI @GuardSatisfied @UnknownInitialization Object this);

    @Pure
    @HotSpotIntrinsicCandidate
    public native int hashCode(@GuardSatisfied Object this);

    @Pure
    public boolean equals(@GuardSatisfied Object this, @GuardSatisfied @Nullable Object obj);

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    protected native Object clone(@GuardSatisfied Object this) throws CloneNotSupportedException;

    @CFComment({ "nullness: toString() is @SideEffectFree rather than @Pure because it returns a string", "that differs according to ==, and @Deterministic requires that the results of", "two calls of the method are ==." })
    @SideEffectFree
    public String toString(@GuardSatisfied Object this);

    @HotSpotIntrinsicCandidate
    public final native void notify();

    @HotSpotIntrinsicCandidate
    public final native void notifyAll();

    public final void wait(@UnknownInitialization Object this) throws InterruptedException;

    public final native void wait(@NonNegative long timeoutMillis) throws InterruptedException;

    public final void wait(@UnknownInitialization Object this, long timeoutMillis, @NonNegative int nanos) throws InterruptedException;

    @Deprecated(since = "9")
    protected void finalize() throws Throwable;
}
