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

    public MediaTracker(Component comp) {
    }

    public void addImage(Image image, int id);

    public synchronized void addImage(Image image, int id, int w, int h);

    public static final int LOADING;

    public static final int ABORTED;

    public static final int ERRORED;

    public static final int COMPLETE;

    public boolean checkAll();

    public boolean checkAll(boolean load);

    public synchronized boolean isErrorAny();

    public synchronized Object[] getErrorsAny();

    public void waitForAll() throws InterruptedException;

    public synchronized boolean waitForAll(long ms) throws InterruptedException;

    public int statusAll(boolean load);

    public boolean checkID(int id);

    public boolean checkID(int id, boolean load);

    public synchronized boolean isErrorID(int id);

    public synchronized Object[] getErrorsID(int id);

    public void waitForID(int id) throws InterruptedException;

    public synchronized boolean waitForID(int id, long ms) throws InterruptedException;

    public int statusID(int id, boolean load);

    public synchronized void removeImage(Image image);

    public synchronized void removeImage(Image image, int id);

    public synchronized void removeImage(Image image, int id, int width, int height);
}
