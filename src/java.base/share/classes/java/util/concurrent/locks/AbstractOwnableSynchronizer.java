package java.util.concurrent.locks;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AbstractOwnableSynchronizer implements java.io.Serializable {

    protected AbstractOwnableSynchronizer() {
    }

    protected final void setExclusiveOwnerThread(Thread thread);

    protected final Thread getExclusiveOwnerThread();
}
