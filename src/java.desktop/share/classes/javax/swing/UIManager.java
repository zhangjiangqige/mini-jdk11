package javax.swing;

import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.guieffect.qual.UIType;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventPostProcessor;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.security.AccessController;
import javax.swing.plaf.ComponentUI;
import javax.swing.border.Border;
import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Locale;
import sun.awt.SunToolkit;
import sun.awt.OSInfo;
import sun.security.action.GetPropertyAction;
import sun.swing.SwingUtilities2;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;
import sun.awt.AppContext;
import sun.awt.AWTAccessor;

@AnnotatedFor({ "guieffect" })
@UIType
@SuppressWarnings("serial")
public class UIManager implements Serializable {

    private static class LAFState {

        Properties swingProps;

        private UIDefaults[] tables = new UIDefaults[2];

        boolean initialized = false;

        boolean focusPolicyInitialized = false;

        MultiUIDefaults multiUIDefaults = new MultiUIDefaults(tables);

        LookAndFeel lookAndFeel;

        LookAndFeel multiLookAndFeel = null;

        Vector<LookAndFeel> auxLookAndFeels = null;

        SwingPropertyChangeSupport changeSupport;

        LookAndFeelInfo[] installedLAFs;

        UIDefaults getLookAndFeelDefaults();

        void setLookAndFeelDefaults(UIDefaults x);

        UIDefaults getSystemDefaults();

        void setSystemDefaults(UIDefaults x);

        public synchronized SwingPropertyChangeSupport getPropertyChangeSupport(boolean create);
    }

    private static final Object classLock = new Object();

    private static LAFState getLAFState();

    private static final String defaultLAFKey = "swing.defaultlaf";

    private static final String auxiliaryLAFsKey = "swing.auxiliarylaf";

    private static final String multiplexingLAFKey = "swing.plaf.multiplexinglaf";

    private static final String installedLAFsKey = "swing.installedlafs";

    private static final String disableMnemonicKey = "swing.disablenavaids";

    private static String makeInstalledLAFKey(String laf, String attr);

    private static String makeSwingPropertiesFilename();

    public static class LookAndFeelInfo {

        private String name;

        private String className;

        public LookAndFeelInfo(String name, String className) {
            this.name = name;
            this.className = className;
        }

        public String getName();

        public String getClassName();

        public String toString();
    }

    private static LookAndFeelInfo[] installedLAFs;

    static {
        ArrayList<LookAndFeelInfo> iLAFs = new ArrayList<LookAndFeelInfo>(4);
        iLAFs.add(new LookAndFeelInfo("Metal", "javax.swing.plaf.metal.MetalLookAndFeel"));
        iLAFs.add(new LookAndFeelInfo("Nimbus", "javax.swing.plaf.nimbus.NimbusLookAndFeel"));
        iLAFs.add(new LookAndFeelInfo("CDE/Motif", "com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
        OSInfo.OSType osType = AccessController.doPrivileged(OSInfo.getOSTypeAction());
        if (osType == OSInfo.OSType.WINDOWS) {
            iLAFs.add(new LookAndFeelInfo("Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));
            if (Toolkit.getDefaultToolkit().getDesktopProperty("win.xpstyle.themeActive") != null) {
                iLAFs.add(new LookAndFeelInfo("Windows Classic", "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"));
            }
        } else if (osType == OSInfo.OSType.MACOSX) {
            iLAFs.add(new LookAndFeelInfo("Mac OS X", "com.apple.laf.AquaLookAndFeel"));
        } else {
            iLAFs.add(new LookAndFeelInfo("GTK+", "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"));
        }
        installedLAFs = iLAFs.toArray(new LookAndFeelInfo[iLAFs.size()]);
    }

    public static LookAndFeelInfo[] getInstalledLookAndFeels();

    public static void setInstalledLookAndFeels(LookAndFeelInfo[] infos) throws SecurityException;

    public static void installLookAndFeel(LookAndFeelInfo info);

    public static void installLookAndFeel(String name, String className);

    public static LookAndFeel getLookAndFeel();

    @SuppressWarnings("deprecation")
    public static LookAndFeel createLookAndFeel(String name) throws UnsupportedLookAndFeelException;

    @SafeEffect
    public static void setLookAndFeel(LookAndFeel newLookAndFeel) throws UnsupportedLookAndFeelException;

    @SafeEffect
    @SuppressWarnings("deprecation")
    public static void setLookAndFeel(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException;

    public static String getSystemLookAndFeelClassName();

    public static String getCrossPlatformLookAndFeelClassName();

    public static UIDefaults getDefaults();

    public static Font getFont(Object key);

    public static Font getFont(Object key, Locale l);

    public static Color getColor(Object key);

    public static Color getColor(Object key, Locale l);

    public static Icon getIcon(Object key);

    public static Icon getIcon(Object key, Locale l);

    public static Border getBorder(Object key);

    public static Border getBorder(Object key, Locale l);

    public static String getString(Object key);

    public static String getString(Object key, Locale l);

    static String getString(Object key, Component c);

    public static int getInt(Object key);

    public static int getInt(Object key, Locale l);

    public static boolean getBoolean(Object key);

    public static boolean getBoolean(Object key, Locale l);

    public static Insets getInsets(Object key);

    public static Insets getInsets(Object key, Locale l);

    public static Dimension getDimension(Object key);

    public static Dimension getDimension(Object key, Locale l);

    public static Object get(Object key);

    public static Object get(Object key, Locale l);

    public static Object put(Object key, Object value);

    public static ComponentUI getUI(JComponent target);

    public static UIDefaults getLookAndFeelDefaults();

    @SuppressWarnings("deprecation")
    private static LookAndFeel getMultiLookAndFeel();

    public static void addAuxiliaryLookAndFeel(LookAndFeel laf);

    public static boolean removeAuxiliaryLookAndFeel(LookAndFeel laf);

    public static LookAndFeel[] getAuxiliaryLookAndFeels();

    public static void addPropertyChangeListener(PropertyChangeListener listener);

    public static void removePropertyChangeListener(PropertyChangeListener listener);

    public static PropertyChangeListener[] getPropertyChangeListeners();

    private static Properties loadSwingProperties();

    private static void checkProperty(Properties props, String key);

    private static void initializeInstalledLAFs(Properties swingProps);

    private static void initializeDefaultLAF(Properties swingProps);

    @SuppressWarnings("deprecation")
    private static void initializeAuxiliaryLAFs(Properties swingProps);

    private static void initializeSystemDefaults(Properties swingProps);

    private static void maybeInitialize();

    @SuppressWarnings("deprecation")
    private static void maybeInitializeFocusPolicy(JComponent comp);

    private static void initialize();
}
