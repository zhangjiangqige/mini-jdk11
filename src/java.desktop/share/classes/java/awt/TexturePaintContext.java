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

    public static ColorModel xrgbmodel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);

    public static ColorModel argbmodel = ColorModel.getRGBdefault();

    ColorModel colorModel;

    int bWidth;

    int bHeight;

    int maxWidth;

    WritableRaster outRas;

    double xOrg;

    double yOrg;

    double incXAcross;

    double incYAcross;

    double incXDown;

    double incYDown;

    int colincx;

    int colincy;

    int colincxerr;

    int colincyerr;

    int rowincx;

    int rowincy;

    int rowincxerr;

    int rowincyerr;

    public static PaintContext getContext(BufferedImage bufImg, AffineTransform xform, RenderingHints hints, Rectangle devBounds);

    public static boolean isFilterableICM(ColorModel cm);

    public static boolean isFilterableDCM(ColorModel cm);

    public static boolean isMaskOK(int mask, boolean canbezero);

    public static ColorModel getInternedColorModel(ColorModel cm);

    TexturePaintContext(ColorModel cm, AffineTransform xform, int bWidth, int bHeight, int maxw) {
        this.colorModel = getInternedColorModel(cm);
        this.bWidth = bWidth;
        this.bHeight = bHeight;
        this.maxWidth = maxw;
        try {
            xform = xform.createInverse();
        } catch (NoninvertibleTransformException e) {
            xform.setToScale(0, 0);
        }
        this.incXAcross = mod(xform.getScaleX(), bWidth);
        this.incYAcross = mod(xform.getShearY(), bHeight);
        this.incXDown = mod(xform.getShearX(), bWidth);
        this.incYDown = mod(xform.getScaleY(), bHeight);
        this.xOrg = xform.getTranslateX();
        this.yOrg = xform.getTranslateY();
        this.colincx = (int) incXAcross;
        this.colincy = (int) incYAcross;
        this.colincxerr = fractAsInt(incXAcross);
        this.colincyerr = fractAsInt(incYAcross);
        this.rowincx = (int) incXDown;
        this.rowincy = (int) incYDown;
        this.rowincxerr = fractAsInt(incXDown);
        this.rowincyerr = fractAsInt(incYDown);
    }

    static int fractAsInt(double d);

    static double mod(double num, double den);

    public void dispose();

    public ColorModel getColorModel();

    public Raster getRaster(int x, int y, int w, int h);

    private static WeakReference<Raster> xrgbRasRef;

    private static WeakReference<Raster> argbRasRef;

    static synchronized WritableRaster makeRaster(ColorModel cm, Raster srcRas, int w, int h);

    static synchronized void dropRaster(ColorModel cm, Raster outRas);

    private static WeakReference<Raster> byteRasRef;

    static synchronized WritableRaster makeByteRaster(Raster srcRas, int w, int h);

    static synchronized void dropByteRaster(Raster outRas);

    public abstract WritableRaster makeRaster(int w, int h);

    public abstract void setRaster(int x, int y, int xerr, int yerr, int w, int h, int bWidth, int bHeight, int colincx, int colincxerr, int colincy, int colincyerr, int rowincx, int rowincxerr, int rowincy, int rowincyerr);

    public static int blend(int[] rgbs, int xmul, int ymul);

    static class Int extends TexturePaintContext {

        IntegerInterleavedRaster srcRas;

        int[] inData;

        int inOff;

        int inSpan;

        int[] outData;

        int outOff;

        int outSpan;

        boolean filter;

        public Int(IntegerInterleavedRaster srcRas, ColorModel cm, AffineTransform xform, int maxw, boolean filter) {
            super(cm, xform, srcRas.getWidth(), srcRas.getHeight(), maxw);
            this.srcRas = srcRas;
            this.inData = srcRas.getDataStorage();
            this.inSpan = srcRas.getScanlineStride();
            this.inOff = srcRas.getDataOffset(0);
            this.filter = filter;
        }

        public WritableRaster makeRaster(int w, int h);

        public void setRaster(int x, int y, int xerr, int yerr, int w, int h, int bWidth, int bHeight, int colincx, int colincxerr, int colincy, int colincyerr, int rowincx, int rowincxerr, int rowincy, int rowincyerr);
    }

    static class Byte extends TexturePaintContext {

        ByteInterleavedRaster srcRas;

        byte[] inData;

        int inOff;

        int inSpan;

        byte[] outData;

        int outOff;

        int outSpan;

        public Byte(ByteInterleavedRaster srcRas, ColorModel cm, AffineTransform xform, int maxw) {
            super(cm, xform, srcRas.getWidth(), srcRas.getHeight(), maxw);
            this.srcRas = srcRas;
            this.inData = srcRas.getDataStorage();
            this.inSpan = srcRas.getScanlineStride();
            this.inOff = srcRas.getDataOffset(0);
        }

        public WritableRaster makeRaster(int w, int h);

        public void dispose();

        public void setRaster(int x, int y, int xerr, int yerr, int w, int h, int bWidth, int bHeight, int colincx, int colincxerr, int colincy, int colincyerr, int rowincx, int rowincxerr, int rowincy, int rowincyerr);
    }

    static class ByteFilter extends TexturePaintContext {

        ByteInterleavedRaster srcRas;

        int[] inPalette;

        byte[] inData;

        int inOff;

        int inSpan;

        int[] outData;

        int outOff;

        int outSpan;

        public ByteFilter(ByteInterleavedRaster srcRas, ColorModel cm, AffineTransform xform, int maxw) {
            super((cm.getTransparency() == Transparency.OPAQUE ? xrgbmodel : argbmodel), xform, srcRas.getWidth(), srcRas.getHeight(), maxw);
            this.inPalette = new int[256];
            ((IndexColorModel) cm).getRGBs(this.inPalette);
            this.srcRas = srcRas;
            this.inData = srcRas.getDataStorage();
            this.inSpan = srcRas.getScanlineStride();
            this.inOff = srcRas.getDataOffset(0);
        }

        public WritableRaster makeRaster(int w, int h);

        public void setRaster(int x, int y, int xerr, int yerr, int w, int h, int bWidth, int bHeight, int colincx, int colincxerr, int colincy, int colincyerr, int rowincx, int rowincxerr, int rowincy, int rowincyerr);
    }

    static class Any extends TexturePaintContext {

        WritableRaster srcRas;

        boolean filter;

        public Any(WritableRaster srcRas, ColorModel cm, AffineTransform xform, int maxw, boolean filter) {
            super(cm, xform, srcRas.getWidth(), srcRas.getHeight(), maxw);
            this.srcRas = srcRas;
            this.filter = filter;
        }

        public WritableRaster makeRaster(int w, int h);

        public void setRaster(int x, int y, int xerr, int yerr, int w, int h, int bWidth, int bHeight, int colincx, int colincxerr, int colincy, int colincyerr, int rowincx, int rowincxerr, int rowincy, int rowincyerr);
    }
}
