package javax.print;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public abstract class ServiceUIFactory {

    @Interned
    public static final String JCOMPONENT_UI;

    @Interned
    public static final String PANEL_UI;

    @Interned
    public static final String DIALOG_UI;

    @Interned
    public static final String JDIALOG_UI;

    public static final int ABOUT_UIROLE;

    public static final int ADMIN_UIROLE;

    public static final int MAIN_UIROLE;

    public static final int RESERVED_UIROLE;

    public abstract Object getUI(int role, String ui);

    public abstract String[] getUIClassNamesForRole(int role);
}
