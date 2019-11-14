package javax.swing.table;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.swing.*;
import javax.swing.event.*;

@AnnotatedFor({ "index" })
public interface TableModel {

    @NonNegative
    public int getRowCount();

    @NonNegative
    public int getColumnCount();

    public String getColumnName(@NonNegative int columnIndex);

    public Class<?> getColumnClass(@NonNegative int columnIndex);

    public boolean isCellEditable(@NonNegative int rowIndex, @NonNegative int columnIndex);

    public Object getValueAt(@NonNegative int rowIndex, @NonNegative int columnIndex);

    public void setValueAt(Object aValue, @NonNegative int rowIndex, @NonNegative int columnIndex);

    public void addTableModelListener(TableModelListener l);

    public void removeTableModelListener(TableModelListener l);
}
