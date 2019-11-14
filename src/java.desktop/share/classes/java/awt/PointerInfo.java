package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PointerInfo {

    private final GraphicsDevice device;

    private final Point location;

    PointerInfo(final GraphicsDevice device, final Point location) {
        this.device = device;
        this.location = location;
    }

    public GraphicsDevice getDevice();

    public Point getLocation();
}
