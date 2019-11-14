package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.MultiResolutionImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.peer.RobotPeer;
import sun.awt.AWTPermissions;
import sun.awt.ComponentFactory;
import sun.awt.SunToolkit;
import sun.awt.image.SunWritableRaster;
import sun.java2d.SunGraphicsEnvironment;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Robot {

    private static final int MAX_DELAY = 60000;

    private RobotPeer peer;

    private boolean isAutoWaitForIdle = false;

    private int autoDelay = 0;

    private static int LEGAL_BUTTON_MASK = 0;

    private DirectColorModel screenCapCM = null;

    public Robot() throws AWTException {
        if (GraphicsEnvironment.isHeadless()) {
            throw new AWTException("headless environment");
        }
        init(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
    }

    public Robot(GraphicsDevice screen) throws AWTException {
        checkIsScreenDevice(screen);
        init(screen);
    }

    private void init(GraphicsDevice screen) throws AWTException;

    @SuppressWarnings("deprecation")
    private static synchronized void initLegalButtonMask();

    private void checkRobotAllowed();

    private void checkIsScreenDevice(GraphicsDevice device);

    private transient Object anchor = new Object();

    static class RobotDisposer implements sun.java2d.DisposerRecord {

        private final RobotPeer peer;

        public RobotDisposer(RobotPeer peer) {
            this.peer = peer;
        }

        public void dispose();
    }

    private transient RobotDisposer disposer;

    public synchronized void mouseMove(int x, int y);

    public synchronized void mousePress(int buttons);

    public synchronized void mouseRelease(int buttons);

    private void checkButtonsArgument(int buttons);

    public synchronized void mouseWheel(int wheelAmt);

    public synchronized void keyPress(int keycode);

    public synchronized void keyRelease(int keycode);

    private void checkKeycodeArgument(int keycode);

    public synchronized Color getPixelColor(int x, int y);

    public synchronized BufferedImage createScreenCapture(Rectangle screenRect);

    public synchronized MultiResolutionImage createMultiResolutionScreenCapture(Rectangle screenRect);

    private synchronized BufferedImage[] createCompatibleImage(Rectangle screenRect, boolean isHiDPI);

    private static void checkValidRect(Rectangle rect);

    private static void checkScreenCaptureAllowed();

    private void afterEvent();

    public synchronized boolean isAutoWaitForIdle();

    public synchronized void setAutoWaitForIdle(boolean isOn);

    private void autoWaitForIdle();

    public synchronized int getAutoDelay();

    public synchronized void setAutoDelay(int ms);

    private void autoDelay();

    public synchronized void delay(int ms);

    private void checkDelayArgument(int ms);

    public synchronized void waitForIdle();

    private void checkNotDispatchThread();

    @Override
    public synchronized String toString();
}
