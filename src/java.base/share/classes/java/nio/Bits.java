package java.nio;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.JavaLangRefAccess;
import jdk.internal.misc.JavaNioAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.Unsafe;
import jdk.internal.misc.VM;
import java.util.concurrent.atomic.AtomicLong;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class Bits {

    private Bits() {
    }

    static short swap(short x);

    static char swap(char x);

    static int swap(int x);

    static long swap(long x);

    private static final Unsafe UNSAFE = Unsafe.getUnsafe();

    private static int PAGE_SIZE = -1;

    static int pageSize();

    static int pageCount(long size);

    private static boolean UNALIGNED = UNSAFE.unalignedAccess();

    static boolean unaligned();

    private static volatile long MAX_MEMORY = VM.maxDirectMemory();

    private static final AtomicLong RESERVED_MEMORY = new AtomicLong();

    private static final AtomicLong TOTAL_CAPACITY = new AtomicLong();

    private static final AtomicLong COUNT = new AtomicLong();

    private static volatile boolean MEMORY_LIMIT_SET;

    private static final int MAX_SLEEPS = 9;

    static void reserveMemory(long size, int cap);

    private static boolean tryReserveMemory(long size, int cap);

    static void unreserveMemory(long size, int cap);

    static final JavaNioAccess.BufferPool BUFFER_POOL = new JavaNioAccess.BufferPool() {

        @Override
        public String getName() {
            return "direct";
        }

        @Override
        public long getCount() {
            return Bits.COUNT.get();
        }

        @Override
        public long getTotalCapacity() {
            return Bits.TOTAL_CAPACITY.get();
        }

        @Override
        public long getMemoryUsed() {
            return Bits.RESERVED_MEMORY.get();
        }
    };

    static final int JNI_COPY_TO_ARRAY_THRESHOLD = 6;

    static final int JNI_COPY_FROM_ARRAY_THRESHOLD = 6;
}
