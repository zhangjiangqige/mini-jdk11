package javax.swing.tree;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import javax.swing.event.*;
import javax.swing.DefaultListSelectionModel;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class DefaultTreeSelectionModel implements Cloneable, Serializable, TreeSelectionModel {

    @Interned
    public static final String SELECTION_MODE_PROPERTY;

    protected SwingPropertyChangeSupport changeSupport;

    protected TreePath[] selection;

    protected EventListenerList listenerList;

    protected transient RowMapper rowMapper;

    protected DefaultListSelectionModel listSelectionModel;

    protected int selectionMode;

    protected TreePath leadPath;

    protected int leadIndex;

    protected int leadRow;

    public DefaultTreeSelectionModel() {
    }

    public void setRowMapper(RowMapper newMapper);

    public RowMapper getRowMapper();

    public void setSelectionMode(int mode);

    public int getSelectionMode();

    public void setSelectionPath(TreePath path);

    public void setSelectionPaths(TreePath[] pPaths);

    public void addSelectionPath(TreePath path);

    public void addSelectionPaths(TreePath[] paths);

    public void removeSelectionPath(TreePath path);

    public void removeSelectionPaths(TreePath[] paths);

    public TreePath getSelectionPath();

    public TreePath[] getSelectionPaths();

    public int getSelectionCount();

    public boolean isPathSelected(TreePath path);

    public boolean isSelectionEmpty();

    public void clearSelection();

    public void addTreeSelectionListener(TreeSelectionListener x);

    public void removeTreeSelectionListener(TreeSelectionListener x);

    public TreeSelectionListener[] getTreeSelectionListeners();

    protected void fireValueChanged(TreeSelectionEvent e);

    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    public int[] getSelectionRows();

    public int getMinSelectionRow();

    public int getMaxSelectionRow();

    public boolean isRowSelected(int row);

    public void resetRowSelection();

    public int getLeadSelectionRow();

    public TreePath getLeadSelectionPath();

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener);

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener);

    public PropertyChangeListener[] getPropertyChangeListeners();

    protected void insureRowContinuity();

    protected boolean arePathsContiguous(TreePath[] paths);

    protected boolean canPathsBeAdded(TreePath[] paths);

    protected boolean canPathsBeRemoved(TreePath[] paths);

    @Deprecated
    protected void notifyPathChange(Vector<?> changedPaths, TreePath oldLeadSelection);

    protected void updateLeadIndex();

    protected void insureUniqueness();

    public String toString();

    public Object clone() throws CloneNotSupportedException;
}
