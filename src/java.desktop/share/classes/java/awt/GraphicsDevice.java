package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.image.ColorModel;
import sun.awt.AppContext;
import sun.awt.SunToolkit;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class GraphicsDevice {

    protected GraphicsDevice() {
    }

    public static final int TYPE_RASTER_SCREEN;

    public static final int TYPE_PRINTER;

    public static final int TYPE_IMAGE_BUFFER;

    public static enum WindowTranslucency {

        PERPIXEL_TRANSPARENT, TRANSLUCENT, PERPIXEL_TRANSLUCENT
    }

    public abstract int getType();

    public abstract String getIDstring();

    public abstract GraphicsConfiguration[] getConfigurations();

    public abstract GraphicsConfiguration getDefaultConfiguration();

    public GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate gct);

    public boolean isFullScreenSupported();

    public void setFullScreenWindow(Window w);

    public Window getFullScreenWindow();

    public boolean isDisplayChangeSupported();

    public void setDisplayMode(DisplayMode dm);

    public DisplayMode getDisplayMode();

    public DisplayMode[] getDisplayModes();

    public int getAvailableAcceleratedMemory();

    public boolean isWindowTranslucencySupported(WindowTranslucency translucencyKind);
}
