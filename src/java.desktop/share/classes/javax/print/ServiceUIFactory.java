package javax.print;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public abstract class ServiceUIFactory {

    @Interned
    public static final String JCOMPONENT_UI = "javax.swing.JComponent";

    @Interned
    public static final String PANEL_UI = "java.awt.Panel";

    @Interned
    public static final String DIALOG_UI = "java.awt.Dialog";

    @Interned
    public static final String JDIALOG_UI = "javax.swing.JDialog";

    public static final int ABOUT_UIROLE = 1;

    public static final int ADMIN_UIROLE = 2;

    public static final int MAIN_UIROLE = 3;

    public static final int RESERVED_UIROLE = 99;

    public abstract Object getUI(int role, String ui);

    public abstract String[] getUIClassNamesForRole(int role);
}
