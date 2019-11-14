package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.*;
import java.awt.event.*;
import java.beans.JavaBean;
import java.beans.BeanProperty;
import java.io.*;
import java.util.*;
import javax.swing.colorchooser.*;
import javax.swing.plaf.ColorChooserUI;
import javax.accessibility.*;
import sun.swing.SwingUtilities2;

@AnnotatedFor({ "interning" })
@JavaBean(defaultProperty = "UI", description = "A component that supports selecting a Color.")
@SwingContainer(false)
@SuppressWarnings("serial")
public class JColorChooser extends JComponent implements Accessible {

    private static final String uiClassID = "ColorChooserUI";

    private ColorSelectionModel selectionModel;

    private JComponent previewPanel = ColorChooserComponentFactory.getPreviewPanel();

    private AbstractColorChooserPanel[] chooserPanels = new AbstractColorChooserPanel[0];

    private boolean dragEnabled;

    @Interned
    public static final String SELECTION_MODEL_PROPERTY = "selectionModel";

    @Interned
    public static final String PREVIEW_PANEL_PROPERTY = "previewPanel";

    @Interned
    public static final String CHOOSER_PANELS_PROPERTY = "chooserPanels";

    public static Color showDialog(Component component, String title, Color initialColor) throws HeadlessException;

    @SuppressWarnings("deprecation")
    public static Color showDialog(Component component, String title, Color initialColor, boolean colorTransparencySelectionEnabled) throws HeadlessException;

    public static JDialog createDialog(Component c, String title, boolean modal, JColorChooser chooserPane, ActionListener okListener, ActionListener cancelListener) throws HeadlessException;

    public JColorChooser() {
        this(Color.white);
    }

    public JColorChooser(Color initialColor) {
        this(new DefaultColorSelectionModel(initialColor));
    }

    public JColorChooser(ColorSelectionModel model) {
        selectionModel = model;
        updateUI();
        dragEnabled = false;
    }

    public ColorChooserUI getUI();

    @BeanProperty(hidden = true, description = "The UI object that implements the color chooser's LookAndFeel.")
    public void setUI(ColorChooserUI ui);

    public void updateUI();

    @BeanProperty(bound = false)
    public String getUIClassID();

    public Color getColor();

    @BeanProperty(bound = false, description = "The current color the chooser is to display.")
    public void setColor(Color color);

    public void setColor(int r, int g, int b);

    public void setColor(int c);

    @BeanProperty(bound = false, description = "Determines whether automatic drag handling is enabled.")
    public void setDragEnabled(boolean b);

    public boolean getDragEnabled();

    @BeanProperty(hidden = true, description = "The UI component which displays the current color.")
    public void setPreviewPanel(JComponent preview);

    public JComponent getPreviewPanel();

    public void addChooserPanel(AbstractColorChooserPanel panel);

    public AbstractColorChooserPanel removeChooserPanel(AbstractColorChooserPanel panel);

    @BeanProperty(hidden = true, description = "An array of different chooser types.")
    public void setChooserPanels(AbstractColorChooserPanel[] panels);

    public AbstractColorChooserPanel[] getChooserPanels();

    public ColorSelectionModel getSelectionModel();

    @BeanProperty(hidden = true, description = "The model which contains the currently selected color.")
    public void setSelectionModel(ColorSelectionModel newModel);

    private void writeObject(ObjectOutputStream s) throws IOException;

    protected String paramString();

    protected AccessibleContext accessibleContext = null;

    @BeanProperty(bound = false)
    public AccessibleContext getAccessibleContext();

    protected class AccessibleJColorChooser extends AccessibleJComponent {

        public AccessibleRole getAccessibleRole();
    }
}

@SuppressWarnings("serial")
class ColorChooserDialog extends JDialog {

    private Color initialColor;

    private JColorChooser chooserPane;

    private JButton cancelButton;

    public ColorChooserDialog(Dialog owner, String title, boolean modal, Component c, JColorChooser chooserPane, ActionListener okListener, ActionListener cancelListener) throws HeadlessException {
        super(owner, title, modal);
        initColorChooserDialog(c, chooserPane, okListener, cancelListener);
    }

    public ColorChooserDialog(Frame owner, String title, boolean modal, Component c, JColorChooser chooserPane, ActionListener okListener, ActionListener cancelListener) throws HeadlessException {
        super(owner, title, modal);
        initColorChooserDialog(c, chooserPane, okListener, cancelListener);
    }

    protected void initColorChooserDialog(Component c, JColorChooser chooserPane, ActionListener okListener, ActionListener cancelListener);

    @SuppressWarnings("deprecation")
    public void show();

    public void reset();

    @SuppressWarnings("serial")
    class Closer extends WindowAdapter implements Serializable {

        @SuppressWarnings("deprecation")
        public void windowClosing(WindowEvent e);
    }

    @SuppressWarnings("serial")
    static class DisposeOnClose extends ComponentAdapter implements Serializable {

        public void componentHidden(ComponentEvent e);
    }
}

@SuppressWarnings("serial")
class ColorTracker implements ActionListener, Serializable {

    JColorChooser chooser;

    Color color;

    public ColorTracker(JColorChooser c) {
        chooser = c;
    }

    public void actionPerformed(ActionEvent e);

    public Color getColor();
}
