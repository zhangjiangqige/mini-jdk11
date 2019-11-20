package java.awt.dnd;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.Native;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class DnDConstants {

    @Native
    public static final int ACTION_NONE;

    @Native
    public static final int ACTION_COPY;

    @Native
    public static final int ACTION_MOVE;

    @Native
    public static final int ACTION_COPY_OR_MOVE;

    @Native
    public static final int ACTION_LINK;

    @Native
    public static final int ACTION_REFERENCE;
}
