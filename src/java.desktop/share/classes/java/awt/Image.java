package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.ReplicateScaleFilter;
import sun.awt.image.SurfaceManager;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Image {

    private static ImageCapabilities defaultImageCaps = new ImageCapabilities(false);

    protected float accelerationPriority = .5f;

    public abstract int getWidth(ImageObserver observer);

    public abstract int getHeight(ImageObserver observer);

    public abstract ImageProducer getSource();

    public abstract Graphics getGraphics();

    public abstract Object getProperty(String name, ImageObserver observer);

    public static final Object UndefinedProperty = new Object();

    public Image getScaledInstance(int width, int height, int hints);

    public static final int SCALE_DEFAULT = 1;

    public static final int SCALE_FAST = 2;

    public static final int SCALE_SMOOTH = 4;

    public static final int SCALE_REPLICATE = 8;

    public static final int SCALE_AREA_AVERAGING = 16;

    public void flush();

    public ImageCapabilities getCapabilities(GraphicsConfiguration gc);

    public void setAccelerationPriority(float priority);

    public float getAccelerationPriority();

    SurfaceManager surfaceManager;

    static {
        SurfaceManager.setImageAccessor(new SurfaceManager.ImageAccessor() {

            public SurfaceManager getSurfaceManager(Image img) {
                return img.surfaceManager;
            }

            public void setSurfaceManager(Image img, SurfaceManager mgr) {
                img.surfaceManager = mgr;
            }
        });
    }
}
