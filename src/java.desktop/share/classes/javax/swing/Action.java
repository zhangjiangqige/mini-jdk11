package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

@AnnotatedFor({ "interning" })
public interface Action extends ActionListener {

    @Interned
    public static final String DEFAULT = "Default";

    @Interned
    public static final String NAME = "Name";

    @Interned
    public static final String SHORT_DESCRIPTION = "ShortDescription";

    @Interned
    public static final String LONG_DESCRIPTION = "LongDescription";

    @Interned
    public static final String SMALL_ICON = "SmallIcon";

    @Interned
    public static final String ACTION_COMMAND_KEY = "ActionCommandKey";

    @Interned
    public static final String ACCELERATOR_KEY = "AcceleratorKey";

    @Interned
    public static final String MNEMONIC_KEY = "MnemonicKey";

    @Interned
    public static final String SELECTED_KEY = "SwingSelectedKey";

    public static final String DISPLAYED_MNEMONIC_INDEX_KEY = "SwingDisplayedMnemonicIndexKey";

    @Interned
    public static final String LARGE_ICON_KEY = "SwingLargeIconKey";

    public Object getValue(String key);

    public void putValue(String key, Object value);

    public void setEnabled(boolean b);

    public boolean isEnabled();

    default boolean accept(Object sender);

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);
}
