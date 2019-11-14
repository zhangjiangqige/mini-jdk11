package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.ImageObserver;
import sun.awt.image.MultiResolutionToolkitImage;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class MediaTracker implements java.io.Serializable {

    Component target;

    MediaEntry head;

    private static final long serialVersionUID = -483174189758638095L;

    public MediaTracker(Component comp) {
        target = comp;
    }

    public void addImage(Image image, int id);

    public synchronized void addImage(Image image, int id, int w, int h);

    private void addImageImpl(Image image, int id, int w, int h);

    public static final int LOADING = 1;

    public static final int ABORTED = 2;

    public static final int ERRORED = 4;

    public static final int COMPLETE = 8;

    static final int DONE = (ABORTED | ERRORED | COMPLETE);

    public boolean checkAll();

    public boolean checkAll(boolean load);

    private synchronized boolean checkAll(boolean load, boolean verify);

    public synchronized boolean isErrorAny();

    public synchronized Object[] getErrorsAny();

    public void waitForAll() throws InterruptedException;

    public synchronized boolean waitForAll(long ms) throws InterruptedException;

    public int statusAll(boolean load);

    private synchronized int statusAll(boolean load, boolean verify);

    public boolean checkID(int id);

    public boolean checkID(int id, boolean load);

    private synchronized boolean checkID(int id, boolean load, boolean verify);

    public synchronized boolean isErrorID(int id);

    public synchronized Object[] getErrorsID(int id);

    public void waitForID(int id) throws InterruptedException;

    public synchronized boolean waitForID(int id, long ms) throws InterruptedException;

    public int statusID(int id, boolean load);

    private synchronized int statusID(int id, boolean load, boolean verify);

    public synchronized void removeImage(Image image);

    private void removeImageImpl(Image image);

    public synchronized void removeImage(Image image, int id);

    private void removeImageImpl(Image image, int id);

    public synchronized void removeImage(Image image, int id, int width, int height);

    private void removeImageImpl(Image image, int id, int width, int height);

    synchronized void setDone();

    private static Image getResolutionVariant(Image image);
}

abstract class MediaEntry {

    MediaTracker tracker;

    int ID;

    MediaEntry next;

    int status;

    boolean cancelled;

    MediaEntry(MediaTracker mt, int id) {
        tracker = mt;
        ID = id;
    }

    abstract Object getMedia();

    static MediaEntry insert(MediaEntry head, MediaEntry me);

    int getID();

    abstract void startLoad();

    void cancel();

    static final int LOADING = MediaTracker.LOADING;

    static final int ABORTED = MediaTracker.ABORTED;

    static final int ERRORED = MediaTracker.ERRORED;

    static final int COMPLETE = MediaTracker.COMPLETE;

    static final int LOADSTARTED = (LOADING | ERRORED | COMPLETE);

    static final int DONE = (ABORTED | ERRORED | COMPLETE);

    synchronized int getStatus(boolean doLoad, boolean doVerify);

    void setStatus(int flag);
}

class ImageMediaEntry extends MediaEntry implements ImageObserver, java.io.Serializable {

    Image image;

    int width;

    int height;

    private static final long serialVersionUID = 4739377000350280650L;

    ImageMediaEntry(MediaTracker mt, Image img, int c, int w, int h) {
        super(mt, c);
        image = img;
        width = w;
        height = h;
    }

    boolean matches(Image img, int w, int h);

    Object getMedia();

    synchronized int getStatus(boolean doLoad, boolean doVerify);

    void startLoad();

    int parseflags(int infoflags);

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h);
}
