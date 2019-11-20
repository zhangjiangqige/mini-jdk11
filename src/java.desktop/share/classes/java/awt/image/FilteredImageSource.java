package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Image;
import java.awt.image.ImageFilter;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.util.Hashtable;
import java.awt.image.ColorModel;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class FilteredImageSource implements ImageProducer {

    public FilteredImageSource(ImageProducer orig, ImageFilter imgf) {
    }

    public synchronized void addConsumer(ImageConsumer ic);

    public synchronized boolean isConsumer(ImageConsumer ic);

    public synchronized void removeConsumer(ImageConsumer ic);

    public synchronized void startProduction(ImageConsumer ic);

    public synchronized void requestTopDownLeftRightResend(ImageConsumer ic);
}
