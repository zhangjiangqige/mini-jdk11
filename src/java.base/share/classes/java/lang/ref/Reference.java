package java.lang.ref;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.misc.JavaLangRefAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.ref.Cleaner;

@AnnotatedFor({ "lock", "nullness" })
@SuppressWarnings({ "rawtypes" })
public abstract class Reference<T> {

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    @Nullable
    public T get(@GuardSatisfied Reference<T> this);

    public void clear();

    public boolean isEnqueued();

    public boolean enqueue();

    @Override
    protected Object clone() throws CloneNotSupportedException;

    @ForceInline
    public static void reachabilityFence(Object ref);
}
