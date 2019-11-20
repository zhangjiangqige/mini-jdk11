package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Locale;
import java.util.ResourceBundle;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class ComponentOrientation implements java.io.Serializable {

    public static final ComponentOrientation LEFT_TO_RIGHT;

    public static final ComponentOrientation RIGHT_TO_LEFT;

    public static final ComponentOrientation UNKNOWN;

    public boolean isHorizontal();

    public boolean isLeftToRight();

    public static ComponentOrientation getOrientation(Locale locale);

    @Deprecated
    public static ComponentOrientation getOrientation(ResourceBundle bdl);
}
