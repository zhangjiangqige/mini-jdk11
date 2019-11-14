package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@UsesObjectEquals
public class EventObject implements java.io.Serializable {

    private static final long serialVersionUID = 5516075349620653480L;

    protected transient Object source;

    public EventObject(Object source) {
        if (source == null)
            throw new IllegalArgumentException("null source");
        this.source = source;
    }

    public Object getSource(@GuardSatisfied EventObject this);

    @SideEffectFree
    public String toString(@GuardSatisfied EventObject this);
}
