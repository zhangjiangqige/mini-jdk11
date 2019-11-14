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

    public static final int DEFAULT_CURSOR = 0;

    public static final int CROSSHAIR_CURSOR = 1;

    public static final int TEXT_CURSOR = 2;

    public static final int WAIT_CURSOR = 3;

    public static final int SW_RESIZE_CURSOR = 4;

    public static final int SE_RESIZE_CURSOR = 5;

    public static final int NW_RESIZE_CURSOR = 6;

    public static final int NE_RESIZE_CURSOR = 7;

    public static final int N_RESIZE_CURSOR = 8;

    public static final int S_RESIZE_CURSOR = 9;

    public static final int W_RESIZE_CURSOR = 10;

    public static final int E_RESIZE_CURSOR = 11;

    public static final int HAND_CURSOR = 12;

    public static final int MOVE_CURSOR = 13;

    @Deprecated
    protected static Cursor[] predefined = new Cursor[14];

    private static final Cursor[] predefinedPrivate = new Cursor[14];

    static final String[][] cursorProperties = { { "AWT.DefaultCursor", "Default Cursor" }, { "AWT.CrosshairCursor", "Crosshair Cursor" }, { "AWT.TextCursor", "Text Cursor" }, { "AWT.WaitCursor", "Wait Cursor" }, { "AWT.SWResizeCursor", "Southwest Resize Cursor" }, { "AWT.SEResizeCursor", "Southeast Resize Cursor" }, { "AWT.NWResizeCursor", "Northwest Resize Cursor" }, { "AWT.NEResizeCursor", "Northeast Resize Cursor" }, { "AWT.NResizeCursor", "North Resize Cursor" }, { "AWT.SResizeCursor", "South Resize Cursor" }, { "AWT.WResizeCursor", "West Resize Cursor" }, { "AWT.EResizeCursor", "East Resize Cursor" }, { "AWT.HandCursor", "Hand Cursor" }, { "AWT.MoveCursor", "Move Cursor" } };

    int type = DEFAULT_CURSOR;

    public static final int CUSTOM_CURSOR = -1;

    private static final Hashtable<String, Cursor> systemCustomCursors = new Hashtable<>(1);

    private static final String RESOURCE_PREFIX = "/sun/awt/resources/cursors/";

    private static final String PROPERTIES_FILE = RESOURCE_PREFIX + "cursors.properties";

    private static Properties systemCustomCursorProperties = null;

    private static final String CURSOR_DOT_PREFIX = "Cursor.";

    private static final String DOT_FILE_SUFFIX = ".File";

    private static final String DOT_HOTSPOT_SUFFIX = ".HotSpot";

    private static final String DOT_NAME_SUFFIX = ".Name";

    private static final long serialVersionUID = 8028237497568985504L;

    private static final PlatformLogger log = PlatformLogger.getLogger("java.awt.Cursor");

    static {
        Toolkit.loadLibraries();
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
        AWTAccessor.setCursorAccessor(new AWTAccessor.CursorAccessor() {

            public long getPData(Cursor cursor) {
                return cursor.pData;
            }

            public void setPData(Cursor cursor, long pData) {
                cursor.pData = pData;
            }

            public int getType(Cursor cursor) {
                return cursor.type;
            }
        });
    }

    private static native void initIDs();

    private transient long pData;

    private transient Object anchor = new Object();

    static class CursorDisposer implements sun.java2d.DisposerRecord {

        volatile long pData;

        public CursorDisposer(long pData) {
            this.pData = pData;
        }

        public void dispose();
    }

    transient CursorDisposer disposer;

    private void setPData(long pData);

    protected String name;

    public static Cursor getPredefinedCursor(int type);

    public static Cursor getSystemCustomCursor(final String name) throws AWTException, HeadlessException;

    public static Cursor getDefaultCursor();

    @ConstructorProperties({ "type" })
    public Cursor(int type) {
        if (type < Cursor.DEFAULT_CURSOR || type > Cursor.MOVE_CURSOR) {
            throw new IllegalArgumentException("illegal cursor type");
        }
        this.type = type;
        name = Toolkit.getProperty(cursorProperties[type][0], cursorProperties[type][1]);
    }

    protected Cursor(String name) {
        this.type = Cursor.CUSTOM_CURSOR;
        this.name = name;
    }

    public int getType();

    public String getName();

    public String toString();

    private static void loadSystemCustomCursorProperties() throws AWTException;

    private static native void finalizeImpl(long pData);
}
