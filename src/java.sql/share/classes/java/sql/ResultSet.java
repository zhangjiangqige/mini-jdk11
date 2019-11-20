package java.sql;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigDecimal;
import java.util.Calendar;
import java.io.Reader;
import java.io.InputStream;

@AnnotatedFor("nullness")
public interface ResultSet extends Wrapper, AutoCloseable {

    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException;

    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException;
}
