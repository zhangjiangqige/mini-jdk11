package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class TexturePaint implements Paint {

    public TexturePaint(BufferedImage txtr, Rectangle2D anchor) {
    }

    public BufferedImage getImage();

    public Rectangle2D getAnchorRect();

    public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints);

    public int getTransparency();
}
