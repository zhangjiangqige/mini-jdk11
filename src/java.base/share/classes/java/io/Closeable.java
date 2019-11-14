package java.io;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.IOException;

@AnnotatedFor({ "lock", "nullness" })
public interface Closeable extends AutoCloseable {

    @CFComment({ "lock: Note that the @GuardSatisfied is for locks that are external to the implementation of close(). The close() method itselfcould release locks if it wanted to." })
    public void close(@GuardSatisfied Closeable this) throws IOException;
}
