package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.awt.AWTPermissions;
import sun.awt.ComponentFactory;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class MouseInfo {

    public static PointerInfo getPointerInfo() throws HeadlessException;

    public static int getNumberOfButtons() throws HeadlessException;
}
