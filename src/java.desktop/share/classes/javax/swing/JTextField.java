package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.*;
import java.awt.event.*;
import java.beans.JavaBean;
import java.beans.BeanProperty;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.accessibility.*;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;

@AnnotatedFor({ "interning" })
@JavaBean(defaultProperty = "UIClassID", description = "A component which allows for the editing of a single line of text.")
@SwingContainer(false)
@SuppressWarnings("serial")
public class JTextField extends JTextComponent implements SwingConstants {

    public JTextField() {
        this(null, null, 0);
    }

    public JTextField(String text) {
        this(null, text, 0);
    }

    public JTextField(int columns) {
        this(null, null, columns);
    }

    public JTextField(String text, int columns) {
        this(null, text, columns);
    }

    public JTextField(Document doc, String text, int columns) {
        if (columns < 0) {
            throw new IllegalArgumentException("columns less than zero.");
        }
        visibility = new DefaultBoundedRangeModel();
        visibility.addChangeListener(new ScrollRepainter());
        this.columns = columns;
        if (doc == null) {
            doc = createDefaultModel();
        }
        setDocument(doc);
        if (text != null) {
            setText(text);
        }
    }

    @BeanProperty(bound = false)
    public String getUIClassID();

    @BeanProperty(expert = true, description = "the text document model")
    public void setDocument(Document doc);

    @Override
    public boolean isValidateRoot();

    public int getHorizontalAlignment();

    @BeanProperty(preferred = true, enumerationValues = { "JTextField.LEFT", "JTextField.CENTER", "JTextField.RIGHT", "JTextField.LEADING", "JTextField.TRAILING" }, description = "Set the field alignment to LEFT, CENTER, RIGHT, LEADING (the default) or TRAILING")
    public void setHorizontalAlignment(int alignment);

    protected Document createDefaultModel();

    public int getColumns();

    @BeanProperty(bound = false, description = "the number of columns preferred for display")
    public void setColumns(int columns);

    protected int getColumnWidth();

    public Dimension getPreferredSize();

    public void setFont(Font f);

    public synchronized void addActionListener(ActionListener l);

    public synchronized void removeActionListener(ActionListener l);

    @BeanProperty(bound = false)
    public synchronized ActionListener[] getActionListeners();

    @SuppressWarnings("deprecation")
    protected void fireActionPerformed();

    public void setActionCommand(String command);

    private Action action;

    private PropertyChangeListener actionPropertyChangeListener;

    @BeanProperty(visualUpdate = true, description = "the Action instance connected with this ActionEvent source")
    public void setAction(Action a);

    private boolean isListener(Class<?> c, ActionListener a);

    public Action getAction();

    protected void configurePropertiesFromAction(Action a);

    protected void actionPropertyChanged(Action action, String propertyName);

    private void setActionCommandFromAction(Action action);

    protected PropertyChangeListener createActionPropertyChangeListener(Action a);

    private static class TextFieldActionPropertyChangeListener extends ActionPropertyChangeListener<JTextField> {

        TextFieldActionPropertyChangeListener(JTextField tf, Action a) {
            super(tf, a);
        }

        protected void actionPropertyChanged(JTextField textField, Action action, PropertyChangeEvent e);
    }

    @BeanProperty(bound = false)
    public Action[] getActions();

    public void postActionEvent();

    @BeanProperty(bound = false)
    public BoundedRangeModel getHorizontalVisibility();

    public int getScrollOffset();

    public void setScrollOffset(int scrollOffset);

    public void scrollRectToVisible(Rectangle r);

    boolean hasActionListener();

    @Interned
    public static final String notifyAction = "notify-field-accept";

    private BoundedRangeModel visibility;

    private int horizontalAlignment = LEADING;

    private int columns;

    private int columnWidth;

    private String command;

    private static final Action[] defaultActions = { new NotifyAction() };

    private static final String uiClassID = "TextFieldUI";

    static class NotifyAction extends TextAction {

        NotifyAction() {
            super(notifyAction);
        }

        public void actionPerformed(ActionEvent e);

        public boolean isEnabled();
    }

    class ScrollRepainter implements ChangeListener, Serializable {

        public void stateChanged(ChangeEvent e);
    }

    private void writeObject(ObjectOutputStream s) throws IOException;

    protected String paramString();

    @BeanProperty(bound = false)
    public AccessibleContext getAccessibleContext();

    @SuppressWarnings("serial")
    protected class AccessibleJTextField extends AccessibleJTextComponent {

        public AccessibleStateSet getAccessibleStateSet();
    }
}
