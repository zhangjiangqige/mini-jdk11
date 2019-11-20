package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.lang.ref.WeakReference;
import sun.awt.image.SunWritableRaster;
import sun.awt.image.IntegerInterleavedRaster;
import sun.awt.image.ByteInterleavedRaster;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class TexturePaintContext implements PaintContext {

    public static ColorModel xrgbmodel;

    public static ColorModel argbmodel;

    public static PaintContext getContext(BufferedImage bufImg, AffineTransform xform, RenderingHints hints, Rectangle devBounds);

    public static boolean isFilterableICM(ColorModel cm);

    public static boolean isFilterableDCM(ColorModel cm);

    public static boolean isMaskOK(int mask, boolean canbezero);

    public static ColorModel getInternedColorModel(ColorModel cm);

    public void dispose();

    public ColorModel getColorModel();

    public Raster getRaster(int x, int y, int w, int h);

    public abstract WritableRaster makeRaster(int w, int h);

    public abstract void setRaster(int x, int y, int xerr, int yerr, int w, int h, int bWidth, int bHeight, int colincx, int colincxerr, int colincy, int colincyerr, int rowincx, int rowincxerr, int rowincy, int rowincyerr);

    public static int blend(int[] rgbs, int xmul, int ymul);
}
