package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.lang.ref.*;

@CFComment({ "nullness: See comment in ThreadLocal class about type parameter annotation." })
@AnnotatedFor({ "nullness" })
public class InheritableThreadLocal<@Nullable T> extends ThreadLocal<T> {

    protected T childValue(T parentValue);
}
