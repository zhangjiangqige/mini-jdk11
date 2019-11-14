package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Frame;
import java.awt.Point;
import java.awt.HeadlessException;
import java.awt.Window;
import java.beans.JavaBean;
import java.beans.BeanProperty;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.accessibility.*;
import static javax.swing.ClientPropertyKey.PopupFactory_FORCE_HEAVYWEIGHT_POPUP;
import sun.awt.AWTAccessor;

@AnnotatedFor({ "interning" })
@JavaBean(defaultProperty = "UI", description = "A component which implements standard dialog box controls.")
@SwingContainer
@SuppressWarnings("serial")
public class JOptionPane extends JComponent implements Accessible {

    private static final String uiClassID = "OptionPaneUI";

    public static final Object UNINITIALIZED_VALUE = "uninitializedValue";

    public static final int DEFAULT_OPTION = -1;

    public static final int YES_NO_OPTION = 0;

    public static final int YES_NO_CANCEL_OPTION = 1;

    public static final int OK_CANCEL_OPTION = 2;

    public static final int YES_OPTION = 0;

    public static final int NO_OPTION = 1;

    public static final int CANCEL_OPTION = 2;

    public static final int OK_OPTION = 0;

    public static final int CLOSED_OPTION = -1;

    public static final int ERROR_MESSAGE = 0;

    public static final int INFORMATION_MESSAGE = 1;

    public static final int WARNING_MESSAGE = 2;

    public static final int QUESTION_MESSAGE = 3;

    public static final int PLAIN_MESSAGE = -1;

    @Interned
    public static final String ICON_PROPERTY = "icon";

    @Interned
    public static final String MESSAGE_PROPERTY = "message";

    @Interned
    public static final String VALUE_PROPERTY = "value";

    @Interned
    public static final String OPTIONS_PROPERTY = "options";

    @Interned
    public static final String INITIAL_VALUE_PROPERTY = "initialValue";

    @Interned
    public static final String MESSAGE_TYPE_PROPERTY = "messageType";

    @Interned
    public static final String OPTION_TYPE_PROPERTY = "optionType";

    @Interned
    public static final String SELECTION_VALUES_PROPERTY = "selectionValues";

    @Interned
    public static final String INITIAL_SELECTION_VALUE_PROPERTY = "initialSelectionValue";

    @Interned
    public static final String INPUT_VALUE_PROPERTY = "inputValue";

    @Interned
    public static final String WANTS_INPUT_PROPERTY = "wantsInput";

    protected transient Icon icon;

    protected transient Object message;

    protected transient Object[] options;

    protected transient Object initialValue;

    protected int messageType;

    protected int optionType;

    protected transient Object value;

    protected transient Object[] selectionValues;

    protected transient Object inputValue;

    protected transient Object initialSelectionValue;

    protected boolean wantsInput;

    public static String showInputDialog(Object message) throws HeadlessException;

    public static String showInputDialog(Object message, Object initialSelectionValue);

    public static String showInputDialog(Component parentComponent, Object message) throws HeadlessException;

    public static String showInputDialog(Component parentComponent, Object message, Object initialSelectionValue);

    public static String showInputDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException;

    @SuppressWarnings("deprecation")
    public static Object showInputDialog(Component parentComponent, Object message, String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue) throws HeadlessException;

    public static void showMessageDialog(Component parentComponent, Object message) throws HeadlessException;

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException;

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon) throws HeadlessException;

    public static int showConfirmDialog(Component parentComponent, Object message) throws HeadlessException;

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) throws HeadlessException;

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) throws HeadlessException;

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon) throws HeadlessException;

    @SuppressWarnings("deprecation")
    public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) throws HeadlessException;

    public JDialog createDialog(Component parentComponent, String title) throws HeadlessException;

    public JDialog createDialog(String title) throws HeadlessException;

    private JDialog createDialog(Component parentComponent, String title, int style) throws HeadlessException;

    private void initDialog(final JDialog dialog, int style, Component parentComponent);

    public static void showInternalMessageDialog(Component parentComponent, Object message);

    public static void showInternalMessageDialog(Component parentComponent, Object message, String title, int messageType);

    public static void showInternalMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon);

    public static int showInternalConfirmDialog(Component parentComponent, Object message);

    public static int showInternalConfirmDialog(Component parentComponent, Object message, String title, int optionType);

    public static int showInternalConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType);

    public static int showInternalConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon);

    private static boolean checkFrameForComponent(Component parentComponent);

    public static int showInternalOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue);

    public static String showInternalInputDialog(Component parentComponent, Object message);

    public static String showInternalInputDialog(Component parentComponent, Object message, String title, int messageType);

    public static Object showInternalInputDialog(Component parentComponent, Object message, String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue);

    public JInternalFrame createInternalFrame(Component parentComponent, String title);

    public static Frame getFrameForComponent(Component parentComponent) throws HeadlessException;

    static Window getWindowForComponent(Component parentComponent) throws HeadlessException;

    public static JDesktopPane getDesktopPaneForComponent(Component parentComponent);

    private static final Object sharedFrameKey = JOptionPane.class;

    public static void setRootFrame(Frame newRootFrame);

    public static Frame getRootFrame() throws HeadlessException;

    public JOptionPane() {
        this("JOptionPane message");
    }

    public JOptionPane(Object message) {
        this(message, PLAIN_MESSAGE);
    }

    public JOptionPane(Object message, int messageType) {
        this(message, messageType, DEFAULT_OPTION);
    }

    public JOptionPane(Object message, int messageType, int optionType) {
        this(message, messageType, optionType, null);
    }

    public JOptionPane(Object message, int messageType, int optionType, Icon icon) {
        this(message, messageType, optionType, icon, null);
    }

    public JOptionPane(Object message, int messageType, int optionType, Icon icon, Object[] options) {
        this(message, messageType, optionType, icon, options, null);
    }

    public JOptionPane(Object message, int messageType, int optionType, Icon icon, Object[] options, Object initialValue) {
        this.message = message;
        this.options = options == null ? null : Arrays.copyOf(options, options.length);
        this.initialValue = initialValue;
        this.icon = icon;
        setMessageType(messageType);
        setOptionType(optionType);
        value = UNINITIALIZED_VALUE;
        inputValue = UNINITIALIZED_VALUE;
        updateUI();
    }

    @BeanProperty(hidden = true, description = "The UI object that implements the optionpane's LookAndFeel")
    public void setUI(OptionPaneUI ui);

    public OptionPaneUI getUI();

    public void updateUI();

    @BeanProperty(bound = false)
    public String getUIClassID();

    @BeanProperty(preferred = true, description = "The optionpane's message object.")
    public void setMessage(Object newMessage);

    public Object getMessage();

    @BeanProperty(preferred = true, description = "The option pane's type icon.")
    public void setIcon(Icon newIcon);

    public Icon getIcon();

    @BeanProperty(preferred = true, description = "The option pane's value object.")
    public void setValue(Object newValue);

    public Object getValue();

    @BeanProperty(description = "The option pane's options objects.")
    public void setOptions(Object[] newOptions);

    public Object[] getOptions();

    @BeanProperty(preferred = true, description = "The option pane's initial value object.")
    public void setInitialValue(Object newInitialValue);

    public Object getInitialValue();

    @BeanProperty(preferred = true, description = "The option pane's message type.")
    public void setMessageType(int newType);

    private static void checkMessageType(int newType);

    public int getMessageType();

    @BeanProperty(preferred = true, description = "The option pane's option type.")
    public void setOptionType(int newType);

    private static void checkOptionType(int newType);

    public int getOptionType();

    @BeanProperty(description = "The option pane's selection values.")
    public void setSelectionValues(Object[] newValues);

    public Object[] getSelectionValues();

    @BeanProperty(description = "The option pane's initial selection value object.")
    public void setInitialSelectionValue(Object newValue);

    public Object getInitialSelectionValue();

    @BeanProperty(preferred = true, description = "The option pane's input value object.")
    public void setInputValue(Object newValue);

    public Object getInputValue();

    @BeanProperty(bound = false)
    public int getMaxCharactersPerLineCount();

    @BeanProperty(preferred = true, description = "Flag which allows the user to input a value.")
    public void setWantsInput(boolean newValue);

    public boolean getWantsInput();

    public void selectInitialValue();

    private static int styleFromMessageType(int messageType);

    private void writeObject(ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException;

    protected String paramString();

    @BeanProperty(bound = false, expert = true, description = "The AccessibleContext associated with this option pane")
    public AccessibleContext getAccessibleContext();

    @SuppressWarnings("serial")
    protected class AccessibleJOptionPane extends AccessibleJComponent {

        public AccessibleRole getAccessibleRole();
    }
}