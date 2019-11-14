package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.*;
import sun.awt.AppContext;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ModalEventFilter implements EventFilter {

    protected Dialog modalDialog;

    protected boolean disabled;

    protected ModalEventFilter(Dialog modalDialog) {
        this.modalDialog = modalDialog;
        disabled = false;
    }

    Dialog getModalDialog();

    public FilterAction acceptEvent(AWTEvent event);

    protected abstract FilterAction acceptWindow(Window w);

    void disable();

    int compareTo(ModalEventFilter another);

    static ModalEventFilter createFilterForDialog(Dialog modalDialog);

    private static class ToolkitModalEventFilter extends ModalEventFilter {

        private AppContext appContext;

        ToolkitModalEventFilter(Dialog modalDialog) {
            super(modalDialog);
            appContext = modalDialog.appContext;
        }

        protected FilterAction acceptWindow(Window w);
    }

    private static class ApplicationModalEventFilter extends ModalEventFilter {

        private AppContext appContext;

        ApplicationModalEventFilter(Dialog modalDialog) {
            super(modalDialog);
            appContext = modalDialog.appContext;
        }

        protected FilterAction acceptWindow(Window w);
    }

    private static class DocumentModalEventFilter extends ModalEventFilter {

        private Window documentRoot;

        DocumentModalEventFilter(Dialog modalDialog) {
            super(modalDialog);
            documentRoot = modalDialog.getDocumentRoot();
        }

        protected FilterAction acceptWindow(Window w);
    }
}
