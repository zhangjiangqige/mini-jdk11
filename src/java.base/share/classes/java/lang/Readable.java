package java.lang;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

@AnnotatedFor({ "lock", "nullness", "index" })
public interface Readable {

    @GTENegativeOne
    public int read(@GuardSatisfied Readable this, java.nio.CharBuffer cb) throws IOException;
}
