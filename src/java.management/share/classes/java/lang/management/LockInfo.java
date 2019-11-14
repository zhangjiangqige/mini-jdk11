package java.lang.management;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.signature.qual.ClassGetName;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.management.openmbean.CompositeData;
import java.util.concurrent.locks.*;
import sun.management.LockInfoCompositeData;

@AnnotatedFor({ "signature", "interning" })
@UsesObjectEquals
public class LockInfo {

    private String className;

    private int identityHashCode;

    public LockInfo(@ClassGetName String className, int identityHashCode) {
        if (className == null) {
            throw new NullPointerException("Parameter className cannot be null");
        }
        this.className = className;
        this.identityHashCode = identityHashCode;
    }

    LockInfo(Object lock) {
        this.className = lock.getClass().getName();
        this.identityHashCode = System.identityHashCode(lock);
    }

    @ClassGetName
    public String getClassName();

    public int getIdentityHashCode();

    public static LockInfo from(CompositeData cd);

    public String toString();
}
