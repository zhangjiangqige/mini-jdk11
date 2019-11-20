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

    public static SplashScreen getSplashScreen();

    public void setImageURL(URL imageURL) throws NullPointerException, IOException, IllegalStateException;

    @SuppressWarnings("deprecation")
    public URL getImageURL() throws IllegalStateException;

    public Rectangle getBounds() throws IllegalStateException;

    public Dimension getSize() throws IllegalStateException;

    public Graphics2D createGraphics() throws IllegalStateException;

    public void update() throws IllegalStateException;

    public void close() throws IllegalStateException;

    public boolean isVisible();
}
