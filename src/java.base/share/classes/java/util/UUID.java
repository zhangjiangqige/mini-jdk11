package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.*;
import jdk.internal.misc.JavaLangAccess;
import jdk.internal.misc.SharedSecrets;

@AnnotatedFor({ "lock", "nullness", "index" })
public final class UUID implements java.io.Serializable, Comparable<UUID> {

    public UUID(long mostSigBits, long leastSigBits) {
    }

    public static UUID randomUUID();

    public static UUID nameUUIDFromBytes(byte[] name);

    public static UUID fromString(String name);

    public long getLeastSignificantBits(@GuardSatisfied UUID this);

    public long getMostSignificantBits(@GuardSatisfied UUID this);

    public int version();

    public int variant();

    public long timestamp();

    public int clockSequence();

    public long node();

    @SideEffectFree
    public String toString(@GuardSatisfied UUID this);

    @Pure
    public int hashCode(@GuardSatisfied UUID this);

    @Pure
    public boolean equals(@GuardSatisfied UUID this, @GuardSatisfied @Nullable Object obj);

    @Pure
    public int compareTo(@GuardSatisfied UUID this, @GuardSatisfied UUID val);
}
