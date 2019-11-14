package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.awt.image.*;
import java.net.URL;
import java.net.URLConnection;
import java.io.File;
import sun.util.logging.PlatformLogger;
import sun.awt.image.SunWritableRaster;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class SplashScreen {

    SplashScreen(long ptr) {
        splashPtr = ptr;
    }

    public static SplashScreen getSplashScreen();

    public void setImageURL(URL imageURL) throws NullPointerException, IOException, IllegalStateException;

    private void checkVisible();

    @SuppressWarnings("deprecation")
    public URL getImageURL() throws IllegalStateException;

    public Rectangle getBounds() throws IllegalStateException;

    public Dimension getSize() throws IllegalStateException;

    public Graphics2D createGraphics() throws IllegalStateException;

    public void update() throws IllegalStateException;

    public void close() throws IllegalStateException;

    static void markClosed();

    public boolean isVisible();

    private BufferedImage image;

    private final long splashPtr;

    private static boolean wasClosed = false;

    private URL imageURL;

    private static SplashScreen theInstance = null;

    private static final PlatformLogger log = PlatformLogger.getLogger("java.awt.SplashScreen");

    private static native void _update(long splashPtr, int[] data, int x, int y, int width, int height, int scanlineStride);

    private static native boolean _isVisible(long splashPtr);

    private static native Rectangle _getBounds(long splashPtr);

    private static native long _getInstance();

    private static native void _close(long splashPtr);

    private static native String _getImageFileName(long splashPtr);

    private static native String _getImageJarName(long SplashPtr);

    private static native boolean _setImageData(long SplashPtr, byte[] data);

    private static native float _getScaleFactor(long SplashPtr);
}
