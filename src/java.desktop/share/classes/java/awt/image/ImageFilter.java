package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ImageFilter implements ImageConsumer, Cloneable {

    protected ImageConsumer consumer;

    public ImageFilter getFilterInstance(ImageConsumer ic);

    public void setDimensions(int width, int height);

    public void setProperties(Hashtable<?, ?> props);

    public void setColorModel(ColorModel model);

    public void setHints(int hints);

    public void setPixels(int x, int y, int w, int h, ColorModel model, byte[] pixels, int off, int scansize);

    public void setPixels(int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize);

    public void imageComplete(int status);

    public void resendTopDownLeftRight(ImageProducer ip);

    public Object clone();
}
