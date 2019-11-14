package java.lang.invoke;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
final class InvokeDynamic {

    private InvokeDynamic() {
        throw new InternalError();
    }
}
