package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.VolatileImage;
import java.awt.image.WritableRaster;
import sun.awt.image.SunVolatileImage;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class GraphicsConfiguration {

    protected GraphicsConfiguration() {
    }

    public abstract GraphicsDevice getDevice();

    public BufferedImage createCompatibleImage(int width, int height);

    public BufferedImage createCompatibleImage(int width, int height, int transparency);

    public VolatileImage createCompatibleVolatileImage(int width, int height);

    public VolatileImage createCompatibleVolatileImage(int width, int height, int transparency);

    public VolatileImage createCompatibleVolatileImage(int width, int height, ImageCapabilities caps) throws AWTException;

    public VolatileImage createCompatibleVolatileImage(int width, int height, ImageCapabilities caps, int transparency) throws AWTException;

    public abstract ColorModel getColorModel();

    public abstract ColorModel getColorModel(int transparency);

    public abstract AffineTransform getDefaultTransform();

    public abstract AffineTransform getNormalizingTransform();

    public abstract Rectangle getBounds();

    public BufferCapabilities getBufferCapabilities();

    public ImageCapabilities getImageCapabilities();

    public boolean isTranslucencyCapable();
}
