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

    private final static CopyOnWriteArrayList<DriverInfo> registeredDrivers = new CopyOnWriteArrayList<>();

    private static volatile int loginTimeout = 0;

    private static volatile java.io.PrintWriter logWriter = null;

    private static volatile java.io.PrintStream logStream = null;

    private final static Object logSync = new Object();

    private final static Object lockForInitDrivers = new Object();

    private static volatile boolean driversInitialized;

    private static final String JDBC_DRIVERS_PROPERTY = "jdbc.drivers";

    private DriverManager() {
    }

    final static SQLPermission SET_LOG_PERMISSION = new SQLPermission("setLog");

    final static SQLPermission DEREGISTER_DRIVER_PERMISSION = new SQLPermission("deregisterDriver");

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

    private static List<Driver> getDrivers(Class<?> callerClass);

    public static void setLoginTimeout(int seconds);

    public static int getLoginTimeout();

    @Deprecated(since = "1.2")
    public static void setLogStream(java.io.PrintStream out);

    @Deprecated(since = "1.2")
    public static java.io.PrintStream getLogStream();

    public static void println(String message);

    private static boolean isDriverAllowed(Driver driver, Class<?> caller);

    private static boolean isDriverAllowed(Driver driver, ClassLoader classLoader);

    private static void ensureDriversInitialized();

    private static Connection getConnection(String url, java.util.Properties info, Class<?> caller) throws SQLException;
}

class DriverInfo {

    final Driver driver;

    DriverAction da;

    DriverInfo(Driver driver, DriverAction action) {
        this.driver = driver;
        da = action;
    }

    @Override
    public boolean equals(Object other);

    @Override
    public int hashCode();

    @Override
    public String toString();

    DriverAction action();
}
