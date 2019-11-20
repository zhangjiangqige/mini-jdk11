package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

@AnnotatedFor({ "interning" })
public interface Action extends ActionListener {

    @Interned
    public static final String DEFAULT;

    @Interned
    public static final String NAME;

    @Interned
    public static final String SHORT_DESCRIPTION;

    @Interned
    public static final String LONG_DESCRIPTION;

    @Interned
    public static final String SMALL_ICON;

    @Interned
    public static final String ACTION_COMMAND_KEY;

    @Interned
    public static final String ACCELERATOR_KEY;

    @Interned
    public static final String MNEMONIC_KEY;

    @Interned
    public static final String SELECTED_KEY;

    public static final String DISPLAYED_MNEMONIC_INDEX_KEY;

    @Interned
    public static final String LARGE_ICON_KEY;

    public Object getValue(String key);

    public void putValue(String key, Object value);

    public void setEnabled(boolean b);

    public boolean isEnabled();

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);
}
