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

    ICC_Profile[] profileList;

    ColorSpace[] CSList;

    ColorTransform thisTransform, thisRasterTransform;

    ICC_Profile thisSrcProfile, thisDestProfile;

    RenderingHints hints;

    boolean gotProfiles;

    float[] srcMinVals, srcMaxVals, dstMinVals, dstMaxVals;

    static {
        if (ProfileDeferralMgr.deferring) {
            ProfileDeferralMgr.activateProfiles();
        }
    }

    public ColorConvertOp(RenderingHints hints) {
        profileList = new ICC_Profile[0];
        this.hints = hints;
    }

    public ColorConvertOp(ColorSpace cspace, RenderingHints hints) {
        if (cspace == null) {
            throw new NullPointerException("ColorSpace cannot be null");
        }
        if (cspace instanceof ICC_ColorSpace) {
            profileList = new ICC_Profile[1];
            profileList[0] = ((ICC_ColorSpace) cspace).getProfile();
        } else {
            CSList = new ColorSpace[1];
            CSList[0] = cspace;
        }
        this.hints = hints;
    }

    public ColorConvertOp(ColorSpace srcCspace, ColorSpace dstCspace, RenderingHints hints) {
        if ((srcCspace == null) || (dstCspace == null)) {
            throw new NullPointerException("ColorSpaces cannot be null");
        }
        if ((srcCspace instanceof ICC_ColorSpace) && (dstCspace instanceof ICC_ColorSpace)) {
            profileList = new ICC_Profile[2];
            profileList[0] = ((ICC_ColorSpace) srcCspace).getProfile();
            profileList[1] = ((ICC_ColorSpace) dstCspace).getProfile();
            getMinMaxValsFromColorSpaces(srcCspace, dstCspace);
        } else {
            CSList = new ColorSpace[2];
            CSList[0] = srcCspace;
            CSList[1] = dstCspace;
        }
        this.hints = hints;
    }

    public ColorConvertOp(ICC_Profile[] profiles, RenderingHints hints) {
        if (profiles == null) {
            throw new NullPointerException("Profiles cannot be null");
        }
        gotProfiles = true;
        profileList = new ICC_Profile[profiles.length];
        for (int i1 = 0; i1 < profiles.length; i1++) {
            profileList[i1] = profiles[i1];
        }
        this.hints = hints;
    }

    public final ICC_Profile[] getICC_Profiles();

    public final BufferedImage filter(BufferedImage src, BufferedImage dest);

    private BufferedImage ICCBIFilter(BufferedImage src, ColorSpace srcColorSpace, BufferedImage dest, ColorSpace destColorSpace);

    private void updateBITransform(ICC_Profile srcProfile, ICC_Profile destProfile);

    public final WritableRaster filter(Raster src, WritableRaster dest);

    public final Rectangle2D getBounds2D(BufferedImage src);

    public final Rectangle2D getBounds2D(Raster src);

    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM);

    private BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM, ColorSpace destCS);

    public WritableRaster createCompatibleDestRaster(Raster src);

    public final Point2D getPoint2D(Point2D srcPt, Point2D dstPt);

    private int getRenderingIntent(ICC_Profile profile);

    public final RenderingHints getRenderingHints();

    private BufferedImage nonICCBIFilter(BufferedImage src, ColorSpace srcColorSpace, BufferedImage dst, ColorSpace dstColorSpace);

    private WritableRaster nonICCRasterFilter(Raster src, WritableRaster dst);

    private void getMinMaxValsFromProfiles(ICC_Profile srcProfile, ICC_Profile dstProfile);

    private void setMinMax(int type, int nc, float[] minVals, float[] maxVals);

    private void getMinMaxValsFromColorSpaces(ColorSpace srcCspace, ColorSpace dstCspace);
}
