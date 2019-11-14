package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Locale;
import java.util.ResourceBundle;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class ComponentOrientation implements java.io.Serializable {

    private static final long serialVersionUID = -4113291392143563828L;

    private static final int UNK_BIT = 1;

    private static final int HORIZ_BIT = 2;

    private static final int LTR_BIT = 4;

    public static final ComponentOrientation LEFT_TO_RIGHT = new ComponentOrientation(HORIZ_BIT | LTR_BIT);

    public static final ComponentOrientation RIGHT_TO_LEFT = new ComponentOrientation(HORIZ_BIT);

    public static final ComponentOrientation UNKNOWN = new ComponentOrientation(HORIZ_BIT | LTR_BIT | UNK_BIT);

    public boolean isHorizontal();

    public boolean isLeftToRight();

    public static ComponentOrientation getOrientation(Locale locale);

    @Deprecated
    public static ComponentOrientation getOrientation(ResourceBundle bdl);

    private int orientation;

    private ComponentOrientation(int value) {
        orientation = value;
    }
}
