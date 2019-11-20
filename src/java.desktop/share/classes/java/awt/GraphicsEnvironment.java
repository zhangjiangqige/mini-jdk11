package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.image.BufferedImage;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;
import sun.font.FontManager;
import sun.font.FontManagerFactory;
import sun.java2d.HeadlessGraphicsEnvironment;
import sun.java2d.SunGraphicsEnvironment;
import sun.security.action.GetPropertyAction;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class GraphicsEnvironment {

    protected GraphicsEnvironment() {
    }

    public static GraphicsEnvironment getLocalGraphicsEnvironment();

    public static boolean isHeadless();

    public boolean isHeadlessInstance();

    public abstract GraphicsDevice[] getScreenDevices() throws HeadlessException;

    public abstract GraphicsDevice getDefaultScreenDevice() throws HeadlessException;

    public abstract Graphics2D createGraphics(BufferedImage img);

    public abstract Font[] getAllFonts();

    public abstract String[] getAvailableFontFamilyNames();

    public abstract String[] getAvailableFontFamilyNames(Locale l);

    public boolean registerFont(Font font);

    public void preferLocaleFonts();

    public void preferProportionalFonts();

    public Point getCenterPoint() throws HeadlessException;

    public Rectangle getMaximumWindowBounds() throws HeadlessException;
}
