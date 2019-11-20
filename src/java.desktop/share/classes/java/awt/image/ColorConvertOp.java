package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.color.*;
import sun.java2d.cmm.ColorTransform;
import sun.java2d.cmm.CMSManager;
import sun.java2d.cmm.ProfileDeferralMgr;
import sun.java2d.cmm.PCMM;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.RenderingHints;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ColorConvertOp implements BufferedImageOp, RasterOp {

    public ColorConvertOp(RenderingHints hints) {
    }

    public ColorConvertOp(ColorSpace cspace, RenderingHints hints) {
    }

    public ColorConvertOp(ColorSpace srcCspace, ColorSpace dstCspace, RenderingHints hints) {
    }

    public ColorConvertOp(ICC_Profile[] profiles, RenderingHints hints) {
    }

    public final ICC_Profile[] getICC_Profiles();

    public final BufferedImage filter(BufferedImage src, BufferedImage dest);

    public final WritableRaster filter(Raster src, WritableRaster dest);

    public final Rectangle2D getBounds2D(BufferedImage src);

    public final Rectangle2D getBounds2D(Raster src);

    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM);

    public WritableRaster createCompatibleDestRaster(Raster src);

    public final Point2D getPoint2D(Point2D srcPt, Point2D dstPt);

    public final RenderingHints getRenderingHints();
}
