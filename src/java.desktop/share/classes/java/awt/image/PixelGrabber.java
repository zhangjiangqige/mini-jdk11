package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;
import java.awt.image.ImageProducer;
import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.Image;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PixelGrabber implements ImageConsumer {

    ImageProducer producer;

    int dstX;

    int dstY;

    int dstW;

    int dstH;

    ColorModel imageModel;

    byte[] bytePixels;

    int[] intPixels;

    int dstOff;

    int dstScan;

    private boolean grabbing;

    private int flags;

    private static final int GRABBEDBITS = (ImageObserver.FRAMEBITS | ImageObserver.ALLBITS);

    private static final int DONEBITS = (GRABBEDBITS | ImageObserver.ERROR);

    public PixelGrabber(Image img, int x, int y, int w, int h, int[] pix, int off, int scansize) {
        this(img.getSource(), x, y, w, h, pix, off, scansize);
    }

    public PixelGrabber(ImageProducer ip, int x, int y, int w, int h, int[] pix, int off, int scansize) {
        producer = ip;
        dstX = x;
        dstY = y;
        dstW = w;
        dstH = h;
        dstOff = off;
        dstScan = scansize;
        intPixels = pix;
        imageModel = ColorModel.getRGBdefault();
    }

    public PixelGrabber(Image img, int x, int y, int w, int h, boolean forceRGB) {
        producer = img.getSource();
        dstX = x;
        dstY = y;
        dstW = w;
        dstH = h;
        if (forceRGB) {
            imageModel = ColorModel.getRGBdefault();
        }
    }

    public synchronized void startGrabbing();

    public synchronized void abortGrabbing();

    public boolean grabPixels() throws InterruptedException;

    public synchronized boolean grabPixels(long ms) throws InterruptedException;

    public synchronized int getStatus();

    public synchronized int getWidth();

    public synchronized int getHeight();

    public synchronized Object getPixels();

    public synchronized ColorModel getColorModel();

    public void setDimensions(int width, int height);

    public void setHints(int hints);

    public void setProperties(Hashtable<?, ?> props);

    public void setColorModel(ColorModel model);

    private void convertToRGB();

    public void setPixels(int srcX, int srcY, int srcW, int srcH, ColorModel model, byte[] pixels, int srcOff, int srcScan);

    public void setPixels(int srcX, int srcY, int srcW, int srcH, ColorModel model, int[] pixels, int srcOff, int srcScan);

    public synchronized void imageComplete(int status);

    public synchronized int status();
}
