package java.awt.color;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.Native;
import sun.java2d.cmm.PCMM;
import sun.java2d.cmm.CMSManager;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ColorSpace implements java.io.Serializable {

    @Native
    public static final int TYPE_XYZ;

    @Native
    public static final int TYPE_Lab;

    @Native
    public static final int TYPE_Luv;

    @Native
    public static final int TYPE_YCbCr;

    @Native
    public static final int TYPE_Yxy;

    @Native
    public static final int TYPE_RGB;

    @Native
    public static final int TYPE_GRAY;

    @Native
    public static final int TYPE_HSV;

    @Native
    public static final int TYPE_HLS;

    @Native
    public static final int TYPE_CMYK;

    @Native
    public static final int TYPE_CMY;

    @Native
    public static final int TYPE_2CLR;

    @Native
    public static final int TYPE_3CLR;

    @Native
    public static final int TYPE_4CLR;

    @Native
    public static final int TYPE_5CLR;

    @Native
    public static final int TYPE_6CLR;

    @Native
    public static final int TYPE_7CLR;

    @Native
    public static final int TYPE_8CLR;

    @Native
    public static final int TYPE_9CLR;

    @Native
    public static final int TYPE_ACLR;

    @Native
    public static final int TYPE_BCLR;

    @Native
    public static final int TYPE_CCLR;

    @Native
    public static final int TYPE_DCLR;

    @Native
    public static final int TYPE_ECLR;

    @Native
    public static final int TYPE_FCLR;

    @Native
    public static final int CS_sRGB;

    @Native
    public static final int CS_LINEAR_RGB;

    @Native
    public static final int CS_CIEXYZ;

    @Native
    public static final int CS_PYCC;

    @Native
    public static final int CS_GRAY;

    protected ColorSpace(int type, int numcomponents) {
    }

    public static ColorSpace getInstance(int colorspace);

    public boolean isCS_sRGB();

    public abstract float[] toRGB(float[] colorvalue);

    public abstract float[] fromRGB(float[] rgbvalue);

    public abstract float[] toCIEXYZ(float[] colorvalue);

    public abstract float[] fromCIEXYZ(float[] colorvalue);

    public int getType();

    public int getNumComponents();

    public String getName(int idx);

    public float getMinValue(int component);

    public float getMaxValue(int component);
}
