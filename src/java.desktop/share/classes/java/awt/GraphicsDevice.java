package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.image.ColorModel;
import sun.awt.AppContext;
import sun.awt.SunToolkit;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class GraphicsDevice {

    private Window fullScreenWindow;

    private AppContext fullScreenAppContext;

    private final Object fsAppContextLock = new Object();

    private Rectangle windowedModeBounds;

    protected GraphicsDevice() {
    }

    public static final int TYPE_RASTER_SCREEN = 0;

    public static final int TYPE_PRINTER = 1;

    public static final int TYPE_IMAGE_BUFFER = 2;

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

    static boolean isWindowShapingSupported();

    static boolean isWindowOpacitySupported();

    boolean isWindowPerpixelTranslucencySupported();

    GraphicsConfiguration getTranslucencyCapableGC();
}
