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

    protected float accelerationPriority;

    public abstract int getWidth(ImageObserver observer);

    public abstract int getHeight(ImageObserver observer);

    public abstract ImageProducer getSource();

    public abstract Graphics getGraphics();

    public abstract Object getProperty(String name, ImageObserver observer);

    public static final Object UndefinedProperty;

    public Image getScaledInstance(int width, int height, int hints);

    public static final int SCALE_DEFAULT;

    public static final int SCALE_FAST;

    public static final int SCALE_SMOOTH;

    public static final int SCALE_REPLICATE;

    public static final int SCALE_AREA_AVERAGING;

    public void flush();

    public ImageCapabilities getCapabilities(GraphicsConfiguration gc);

    public void setAccelerationPriority(float priority);

    public float getAccelerationPriority();
}
