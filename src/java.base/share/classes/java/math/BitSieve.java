package java.math;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class BitSieve {

    private long[] bits;

    private int length;

    private static BitSieve smallSieve = new BitSieve();

    private BitSieve() {
        length = 150 * 64;
        bits = new long[(unitIndex(length - 1) + 1)];
        set(0);
        int nextIndex = 1;
        int nextPrime = 3;
        do {
            sieveSingle(length, nextIndex + nextPrime, nextPrime);
            nextIndex = sieveSearch(length, nextIndex + 1);
            nextPrime = 2 * nextIndex + 1;
        } while ((nextIndex > 0) && (nextPrime < length));
    }

    BitSieve(BigInteger base, int searchLen) {
        bits = new long[(unitIndex(searchLen - 1) + 1)];
        length = searchLen;
        int start = 0;
        int step = smallSieve.sieveSearch(smallSieve.length, start);
        int convertedStep = (step * 2) + 1;
        MutableBigInteger b = new MutableBigInteger(base);
        MutableBigInteger q = new MutableBigInteger();
        do {
            start = b.divideOneWord(convertedStep, q);
            start = convertedStep - start;
            if (start % 2 == 0)
                start += convertedStep;
            sieveSingle(searchLen, (start - 1) / 2, convertedStep);
            step = smallSieve.sieveSearch(smallSieve.length, step + 1);
            convertedStep = (step * 2) + 1;
        } while (step > 0);
    }

    private static int unitIndex(int bitIndex);

    private static long bit(int bitIndex);

    private boolean get(int bitIndex);

    private void set(int bitIndex);

    private int sieveSearch(int limit, int start);

    private void sieveSingle(int limit, int start, int step);

    BigInteger retrieve(BigInteger initValue, int certainty, java.util.Random random);
}
