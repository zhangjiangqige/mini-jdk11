package java.sql;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DriverManager {

    public static java.io.PrintWriter getLogWriter();

    public static void setLogWriter(java.io.PrintWriter out);

    @CallerSensitive
    public static Connection getConnection(String url, java.util.Properties info) throws SQLException;

    @CallerSensitive
    public static Connection getConnection(String url, String user, String password) throws SQLException;

    @CallerSensitive
    public static Connection getConnection(String url) throws SQLException;

    @CallerSensitive
    public static Driver getDriver(String url) throws SQLException;

    public static void registerDriver(java.sql.Driver driver) throws SQLException;

    public static void registerDriver(java.sql.Driver driver, DriverAction da) throws SQLException;

    @CallerSensitive
    public static void deregisterDriver(Driver driver) throws SQLException;

    @CallerSensitive
    public static Enumeration<Driver> getDrivers();

    @CallerSensitive
    public static Stream<Driver> drivers();

    public static void setLoginTimeout(int seconds);

    public static int getLoginTimeout();

    @Deprecated()
    public static void setLogStream(java.io.PrintStream out);

    @Deprecated()
    public static java.io.PrintStream getLogStream();

    public static void println(String message);
}
