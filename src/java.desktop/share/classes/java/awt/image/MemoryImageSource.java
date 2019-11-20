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

    public MemoryImageSource(int w, int h, ColorModel cm, byte[] pix, int off, int scan) {
    }

    public MemoryImageSource(int w, int h, ColorModel cm, byte[] pix, int off, int scan, Hashtable<?, ?> props) {
    }

    public MemoryImageSource(int w, int h, ColorModel cm, int[] pix, int off, int scan) {
    }

    public MemoryImageSource(int w, int h, ColorModel cm, int[] pix, int off, int scan, Hashtable<?, ?> props) {
    }

    public MemoryImageSource(int w, int h, int[] pix, int off, int scan) {
    }

    public MemoryImageSource(int w, int h, int[] pix, int off, int scan, Hashtable<?, ?> props) {
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
}
