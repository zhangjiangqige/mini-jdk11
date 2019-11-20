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

    public Robot() throws AWTException {
    }

    public Robot(GraphicsDevice screen) throws AWTException {
    }

    public synchronized void mouseMove(int x, int y);

    public synchronized void mousePress(int buttons);

    public synchronized void mouseRelease(int buttons);

    public synchronized void mouseWheel(int wheelAmt);

    public synchronized void keyPress(int keycode);

    public synchronized void keyRelease(int keycode);

    public synchronized Color getPixelColor(int x, int y);

    public synchronized BufferedImage createScreenCapture(Rectangle screenRect);

    public synchronized MultiResolutionImage createMultiResolutionScreenCapture(Rectangle screenRect);

    public synchronized boolean isAutoWaitForIdle();

    public synchronized void setAutoWaitForIdle(boolean isOn);

    public synchronized int getAutoDelay();

    public synchronized void setAutoDelay(int ms);

    public synchronized void delay(int ms);

    public synchronized void waitForIdle();

    @Override
    public synchronized String toString();
}
