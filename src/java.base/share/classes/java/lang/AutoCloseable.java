package java.lang;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock" })
public interface AutoCloseable {

    void close(@GuardSatisfied AutoCloseable this) throws Exception;
}
