package java.util.regex;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
public class PatternSyntaxException extends IllegalArgumentException {

    private static final long serialVersionUID = -3864639126226059218L;

    private final String desc;

    private final String pattern;

    private final int index;

    @SideEffectFree
    public PatternSyntaxException(String desc, String regex, int index) {
        this.desc = desc;
        this.pattern = regex;
        this.index = index;
    }

    @Pure
    public int getIndex(@GuardSatisfied PatternSyntaxException this);

    @Pure
    public String getDescription(@GuardSatisfied PatternSyntaxException this);

    @Pure
    public String getPattern(@GuardSatisfied PatternSyntaxException this);

    @Pure
    public String getMessage(@GuardSatisfied PatternSyntaxException this);
}
