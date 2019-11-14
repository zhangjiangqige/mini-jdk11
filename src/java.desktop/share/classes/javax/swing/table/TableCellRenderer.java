package javax.swing.table;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Component;
import javax.swing.*;

@AnnotatedFor({ "index" })
public interface TableCellRenderer {

    Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, @NonNegative int row, @NonNegative int column);
}
