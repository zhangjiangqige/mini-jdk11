package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.beans.ConstructorProperties;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import sun.awt.AWTAccessor;
import sun.util.logging.PlatformLogger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Cursor implements java.io.Serializable {

    public static final int DEFAULT_CURSOR;

    public static final int CROSSHAIR_CURSOR;

    public static final int TEXT_CURSOR;

    public static final int WAIT_CURSOR;

    public static final int SW_RESIZE_CURSOR;

    public static final int SE_RESIZE_CURSOR;

    public static final int NW_RESIZE_CURSOR;

    public static final int NE_RESIZE_CURSOR;

    public static final int N_RESIZE_CURSOR;

    public static final int S_RESIZE_CURSOR;

    public static final int W_RESIZE_CURSOR;

    public static final int E_RESIZE_CURSOR;

    public static final int HAND_CURSOR;

    public static final int MOVE_CURSOR;

    @Deprecated
    protected static Cursor[] predefined;

    public static final int CUSTOM_CURSOR;

    protected String name;

    public static Cursor getPredefinedCursor(int type);

    public static Cursor getSystemCustomCursor(final String name) throws AWTException, HeadlessException;

    public static Cursor getDefaultCursor();

    @ConstructorProperties({ "type" })
    public Cursor(int type) {
    }

    protected Cursor(String name) {
    }

    public int getType();

    public String getName();

    public String toString();
}
