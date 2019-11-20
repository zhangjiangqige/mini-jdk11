package javax.swing.table;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.swing.*;
import javax.swing.event.*;
import java.io.Serializable;
import java.util.EventListener;

@AnnotatedFor({ "index" })
@SuppressWarnings("serial")
public abstract class AbstractTableModel implements TableModel, Serializable {

    protected EventListenerList listenerList;

    public String getColumnName(@NonNegative int column);

    @GTENegativeOne
    public int findColumn(String columnName);

    public Class<?> getColumnClass(@NonNegative int columnIndex);

    public boolean isCellEditable(@NonNegative int rowIndex, @NonNegative int columnIndex);

    public void setValueAt(Object aValue, @NonNegative int rowIndex, @NonNegative int columnIndex);

    public void addTableModelListener(TableModelListener l);

    public void removeTableModelListener(TableModelListener l);

    public TableModelListener[] getTableModelListeners();

    public void fireTableDataChanged();

    public void fireTableStructureChanged();

    public void fireTableRowsInserted(@NonNegative int firstRow, @NonNegative int lastRow);

    public void fireTableRowsUpdated(@NonNegative int firstRow, @NonNegative int lastRow);

    public void fireTableRowsDeleted(@NonNegative int firstRow, @NonNegative int lastRow);

    public void fireTableCellUpdated(@NonNegative int row, @NonNegative int column);

    public void fireTableChanged(TableModelEvent e);

    public <T extends EventListener> T[] getListeners(Class<T> listenerType);
}
