package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class MemoryImageSource implements ImageProducer {

    int width;

    int height;

    ColorModel model;

    Object pixels;

    int pixeloffset;

    int pixelscan;

    Hashtable<?, ?> properties;

    Vector<ImageConsumer> theConsumers = new Vector<>();

    boolean animating;

    boolean fullbuffers;

    public MemoryImageSource(int w, int h, ColorModel cm, byte[] pix, int off, int scan) {
        initialize(w, h, cm, (Object) pix, off, scan, null);
    }

    public MemoryImageSource(int w, int h, ColorModel cm, byte[] pix, int off, int scan, Hashtable<?, ?> props) {
        initialize(w, h, cm, (Object) pix, off, scan, props);
    }

    public MemoryImageSource(int w, int h, ColorModel cm, int[] pix, int off, int scan) {
        initialize(w, h, cm, (Object) pix, off, scan, null);
    }

    public MemoryImageSource(int w, int h, ColorModel cm, int[] pix, int off, int scan, Hashtable<?, ?> props) {
        initialize(w, h, cm, (Object) pix, off, scan, props);
    }

    private void initialize(int w, int h, ColorModel cm, Object pix, int off, int scan, Hashtable<?, ?> props);

    public MemoryImageSource(int w, int h, int[] pix, int off, int scan) {
        initialize(w, h, ColorModel.getRGBdefault(), (Object) pix, off, scan, null);
    }

    public MemoryImageSource(int w, int h, int[] pix, int off, int scan, Hashtable<?, ?> props) {
        initialize(w, h, ColorModel.getRGBdefault(), (Object) pix, off, scan, props);
    }

    public synchronized void addConsumer(ImageConsumer ic);

    public synchronized boolean isConsumer(ImageConsumer ic);

    public synchronized void removeConsumer(ImageConsumer ic);

    public void startProduction(ImageConsumer ic);

    public void requestTopDownLeftRightResend(ImageConsumer ic);

    public synchronized void setAnimated(boolean animated);

    public synchronized void setFullBufferUpdates(boolean fullbuffers);

    public void newPixels();

    public synchronized void newPixels(int x, int y, int w, int h);

    public synchronized void newPixels(int x, int y, int w, int h, boolean framenotify);

    public synchronized void newPixels(byte[] newpix, ColorModel newmodel, int offset, int scansize);

    public synchronized void newPixels(int[] newpix, ColorModel newmodel, int offset, int scansize);

    private void initConsumer(ImageConsumer ic);

    private void sendPixels(ImageConsumer ic, int x, int y, int w, int h);
}
