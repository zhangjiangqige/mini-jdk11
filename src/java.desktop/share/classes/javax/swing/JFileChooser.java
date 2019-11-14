package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FileChooserUI;
import javax.accessibility.*;
import java.io.*;
import java.util.Vector;
import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.*;
import java.beans.JavaBean;
import java.beans.BeanProperty;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.lang.ref.WeakReference;

@AnnotatedFor({ "interning" })
@JavaBean(defaultProperty = "UI", description = "A component which allows for the interactive selection of a file.")
@SwingContainer(false)
@SuppressWarnings("serial")
public class JFileChooser extends JComponent implements Accessible {

    private static final String uiClassID = "FileChooserUI";

    public static final int OPEN_DIALOG = 0;

    public static final int SAVE_DIALOG = 1;

    public static final int CUSTOM_DIALOG = 2;

    public static final int CANCEL_OPTION = 1;

    public static final int APPROVE_OPTION = 0;

    public static final int ERROR_OPTION = -1;

    public static final int FILES_ONLY = 0;

    public static final int DIRECTORIES_ONLY = 1;

    public static final int FILES_AND_DIRECTORIES = 2;

    @Interned
    public static final String CANCEL_SELECTION = "CancelSelection";

    @Interned
    public static final String APPROVE_SELECTION = "ApproveSelection";

    @Interned
    public static final String APPROVE_BUTTON_TEXT_CHANGED_PROPERTY = "ApproveButtonTextChangedProperty";

    @Interned
    public static final String APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY = "ApproveButtonToolTipTextChangedProperty";

    @Interned
    public static final String APPROVE_BUTTON_MNEMONIC_CHANGED_PROPERTY = "ApproveButtonMnemonicChangedProperty";

    @Interned
    public static final String CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY = "ControlButtonsAreShownChangedProperty";

    @Interned
    public static final String DIRECTORY_CHANGED_PROPERTY = "directoryChanged";

    @Interned
    public static final String SELECTED_FILE_CHANGED_PROPERTY = "SelectedFileChangedProperty";

    public static final String SELECTED_FILES_CHANGED_PROPERTY = "SelectedFilesChangedProperty";

    @Interned
    public static final String MULTI_SELECTION_ENABLED_CHANGED_PROPERTY = "MultiSelectionEnabledChangedProperty";

    @Interned
    public static final String FILE_SYSTEM_VIEW_CHANGED_PROPERTY = "FileSystemViewChanged";

    @Interned
    public static final String FILE_VIEW_CHANGED_PROPERTY = "fileViewChanged";

    public static final String FILE_HIDING_CHANGED_PROPERTY = "FileHidingChanged";

    @Interned
    public static final String FILE_FILTER_CHANGED_PROPERTY = "fileFilterChanged";

    @Interned
    public static final String FILE_SELECTION_MODE_CHANGED_PROPERTY = "fileSelectionChanged";

    @Interned
    public static final String ACCESSORY_CHANGED_PROPERTY = "AccessoryChangedProperty";

    @Interned
    public static final String ACCEPT_ALL_FILE_FILTER_USED_CHANGED_PROPERTY = "acceptAllFileFilterUsedChanged";

    @Interned
    public static final String DIALOG_TITLE_CHANGED_PROPERTY = "DialogTitleChangedProperty";

    @Interned
    public static final String DIALOG_TYPE_CHANGED_PROPERTY = "DialogTypeChangedProperty";

    @Interned
    public static final String CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY = "ChoosableFileFilterChangedProperty";

    private String dialogTitle = null;

    private String approveButtonText = null;

    private String approveButtonToolTipText = null;

    private int approveButtonMnemonic = 0;

    private Vector<FileFilter> filters = new Vector<FileFilter>(5);

    private JDialog dialog = null;

    private int dialogType = OPEN_DIALOG;

    private int returnValue = ERROR_OPTION;

    private JComponent accessory = null;

    private FileView fileView = null;

    private boolean controlsShown = true;

    private boolean useFileHiding = true;

    private static final String SHOW_HIDDEN_PROP = "awt.file.showHiddenFiles";

    private transient PropertyChangeListener showFilesListener = null;

    private int fileSelectionMode = FILES_ONLY;

    private boolean multiSelectionEnabled = false;

    private boolean useAcceptAllFileFilter = true;

    private boolean dragEnabled = false;

    private FileFilter fileFilter = null;

    private FileSystemView fileSystemView = null;

    private File currentDirectory = null;

    private File selectedFile = null;

    private File[] selectedFiles;

    public JFileChooser() {
        this((File) null, (FileSystemView) null);
    }

    public JFileChooser(String currentDirectoryPath) {
        this(currentDirectoryPath, (FileSystemView) null);
    }

    public JFileChooser(File currentDirectory) {
        this(currentDirectory, (FileSystemView) null);
    }

    public JFileChooser(FileSystemView fsv) {
        this((File) null, fsv);
    }

    public JFileChooser(File currentDirectory, FileSystemView fsv) {
        setup(fsv);
        setCurrentDirectory(currentDirectory);
    }

    public JFileChooser(String currentDirectoryPath, FileSystemView fsv) {
        setup(fsv);
        if (currentDirectoryPath == null) {
            setCurrentDirectory(null);
        } else {
            setCurrentDirectory(fileSystemView.createFileObject(currentDirectoryPath));
        }
    }

    protected void setup(FileSystemView view);

    private void installHierarchyListener();

    private void installShowFilesListener();

    @BeanProperty(bound = false, description = "determines whether automatic drag handling is enabled")
    public void setDragEnabled(boolean b);

    private static void checkDragEnabled(boolean b);

    public boolean getDragEnabled();

    public File getSelectedFile();

    @BeanProperty(preferred = true)
    public void setSelectedFile(File file);

    public File[] getSelectedFiles();

    @BeanProperty(description = "The list of selected files if the chooser is in multiple selection mode.")
    public void setSelectedFiles(File[] selectedFiles);

    public File getCurrentDirectory();

    @BeanProperty(preferred = true, description = "The directory that the JFileChooser is showing files of.")
    public void setCurrentDirectory(File dir);

    public void changeToParentDirectory();

    public void rescanCurrentDirectory();

    public void ensureFileIsVisible(File f);

    public int showOpenDialog(Component parent) throws HeadlessException;

    public int showSaveDialog(Component parent) throws HeadlessException;

    @SuppressWarnings("deprecation")
    public int showDialog(Component parent, String approveButtonText) throws HeadlessException;

    protected JDialog createDialog(Component parent) throws HeadlessException;

    public boolean getControlButtonsAreShown();

    @BeanProperty(preferred = true, description = "Sets whether the approve & cancel buttons are shown.")
    public void setControlButtonsAreShown(boolean b);

    public int getDialogType();

    @BeanProperty(preferred = true, enumerationValues = { "JFileChooser.OPEN_DIALOG", "JFileChooser.SAVE_DIALOG", "JFileChooser.CUSTOM_DIALOG" }, description = "The type (open, save, custom) of the JFileChooser.")
    public void setDialogType(int dialogType);

    private static void checkDialogType(int dialogType);

    @BeanProperty(preferred = true, description = "The title of the JFileChooser dialog window.")
    public void setDialogTitle(String dialogTitle);

    public String getDialogTitle();

    @BeanProperty(preferred = true, description = "The tooltip text for the ApproveButton.")
    public void setApproveButtonToolTipText(String toolTipText);

    public String getApproveButtonToolTipText();

    public int getApproveButtonMnemonic();

    @BeanProperty(preferred = true, description = "The mnemonic key accelerator for the ApproveButton.")
    public void setApproveButtonMnemonic(int mnemonic);

    public void setApproveButtonMnemonic(char mnemonic);

    @BeanProperty(preferred = true, description = "The text that goes in the ApproveButton.")
    public void setApproveButtonText(String approveButtonText);

    public String getApproveButtonText();

    @BeanProperty(bound = false)
    public FileFilter[] getChoosableFileFilters();

    @BeanProperty(preferred = true, description = "Adds a filter to the list of user choosable file filters.")
    public void addChoosableFileFilter(FileFilter filter);

    public boolean removeChoosableFileFilter(FileFilter f);

    public void resetChoosableFileFilters();

    @BeanProperty(bound = false)
    public FileFilter getAcceptAllFileFilter();

    public boolean isAcceptAllFileFilterUsed();

    @BeanProperty(preferred = true, description = "Sets whether the AcceptAll FileFilter is used as an available choice in the choosable filter list.")
    public void setAcceptAllFileFilterUsed(boolean b);

    public JComponent getAccessory();

    @BeanProperty(preferred = true, description = "Sets the accessory component on the JFileChooser.")
    public void setAccessory(JComponent newAccessory);

    @BeanProperty(preferred = true, enumerationValues = { "JFileChooser.FILES_ONLY", "JFileChooser.DIRECTORIES_ONLY", "JFileChooser.FILES_AND_DIRECTORIES" }, description = "Sets the types of files that the JFileChooser can choose.")
    public void setFileSelectionMode(int mode);

    private static void checkFileSelectionMode(int mode);

    public int getFileSelectionMode();

    @BeanProperty(bound = false)
    public boolean isFileSelectionEnabled();

    @BeanProperty(bound = false)
    public boolean isDirectorySelectionEnabled();

    @BeanProperty(description = "Sets multiple file selection mode.")
    public void setMultiSelectionEnabled(boolean b);

    public boolean isMultiSelectionEnabled();

    public boolean isFileHidingEnabled();

    @BeanProperty(preferred = true, description = "Sets file hiding on or off.")
    public void setFileHidingEnabled(boolean b);

    @BeanProperty(preferred = true, description = "Sets the File Filter used to filter out files of type.")
    public void setFileFilter(FileFilter filter);

    public FileFilter getFileFilter();

    @BeanProperty(preferred = true, description = "Sets the File View used to get file type information.")
    public void setFileView(FileView fileView);

    public FileView getFileView();

    public String getName(File f);

    public String getDescription(File f);

    public String getTypeDescription(File f);

    public Icon getIcon(File f);

    public boolean isTraversable(File f);

    public boolean accept(File f);

    @BeanProperty(expert = true, description = "Sets the FileSytemView used to get filesystem information.")
    public void setFileSystemView(FileSystemView fsv);

    public FileSystemView getFileSystemView();

    public void approveSelection();

    public void cancelSelection();

    public void addActionListener(ActionListener l);

    public void removeActionListener(ActionListener l);

    @BeanProperty(bound = false)
    public ActionListener[] getActionListeners();

    @SuppressWarnings("deprecation")
    protected void fireActionPerformed(String command);

    private static class WeakPCL implements PropertyChangeListener {

        WeakReference<JFileChooser> jfcRef;

        public WeakPCL(JFileChooser jfc) {
            jfcRef = new WeakReference<JFileChooser>(jfc);
        }

        public void propertyChange(PropertyChangeEvent ev);
    }

    public void updateUI();

    @BeanProperty(bound = false, expert = true, description = "A string that specifies the name of the L&F class.")
    public String getUIClassID();

    @BeanProperty(bound = false)
    public FileChooserUI getUI();

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException;

    private void writeObject(ObjectOutputStream s) throws IOException;

    protected String paramString();

    protected AccessibleContext accessibleContext = null;

    @BeanProperty(bound = false)
    public AccessibleContext getAccessibleContext();

    @SuppressWarnings("serial")
    protected class AccessibleJFileChooser extends AccessibleJComponent {

        public AccessibleRole getAccessibleRole();
    }

    private class FCHierarchyListener implements HierarchyListener, Serializable {

        @Override
        public void hierarchyChanged(HierarchyEvent e);
    }
}
