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
    }

    public FilterAction acceptEvent(AWTEvent event);

    protected abstract FilterAction acceptWindow(Window w);
}
