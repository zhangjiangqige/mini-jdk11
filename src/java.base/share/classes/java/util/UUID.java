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

    private static final long serialVersionUID = -4856846361193249489L;

    private final long mostSigBits;

    private final long leastSigBits;

    private static final JavaLangAccess jla = SharedSecrets.getJavaLangAccess();

    private static class Holder {

        static final SecureRandom numberGenerator = new SecureRandom();
    }

    private UUID(byte[] data) {
        long msb = 0;
        long lsb = 0;
        assert data.length == 16 : "data must be 16 bytes in length";
        for (int i = 0; i < 8; i++) msb = (msb << 8) | (data[i] & 0xff);
        for (int i = 8; i < 16; i++) lsb = (lsb << 8) | (data[i] & 0xff);
        this.mostSigBits = msb;
        this.leastSigBits = lsb;
    }

    public UUID(long mostSigBits, long leastSigBits) {
        this.mostSigBits = mostSigBits;
        this.leastSigBits = leastSigBits;
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
