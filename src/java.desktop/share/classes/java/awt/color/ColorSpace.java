package java.awt.color;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.Native;
import sun.java2d.cmm.PCMM;
import sun.java2d.cmm.CMSManager;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ColorSpace implements java.io.Serializable {

    static final long serialVersionUID = -409452704308689724L;

    private int type;

    private int numComponents;

    private transient String[] compName = null;

    private static ColorSpace sRGBspace;

    private static ColorSpace XYZspace;

    private static ColorSpace PYCCspace;

    private static ColorSpace GRAYspace;

    private static ColorSpace LINEAR_RGBspace;

    @Native
    public static final int TYPE_XYZ = 0;

    @Native
    public static final int TYPE_Lab = 1;

    @Native
    public static final int TYPE_Luv = 2;

    @Native
    public static final int TYPE_YCbCr = 3;

    @Native
    public static final int TYPE_Yxy = 4;

    @Native
    public static final int TYPE_RGB = 5;

    @Native
    public static final int TYPE_GRAY = 6;

    @Native
    public static final int TYPE_HSV = 7;

    @Native
    public static final int TYPE_HLS = 8;

    @Native
    public static final int TYPE_CMYK = 9;

    @Native
    public static final int TYPE_CMY = 11;

    @Native
    public static final int TYPE_2CLR = 12;

    @Native
    public static final int TYPE_3CLR = 13;

    @Native
    public static final int TYPE_4CLR = 14;

    @Native
    public static final int TYPE_5CLR = 15;

    @Native
    public static final int TYPE_6CLR = 16;

    @Native
    public static final int TYPE_7CLR = 17;

    @Native
    public static final int TYPE_8CLR = 18;

    @Native
    public static final int TYPE_9CLR = 19;

    @Native
    public static final int TYPE_ACLR = 20;

    @Native
    public static final int TYPE_BCLR = 21;

    @Native
    public static final int TYPE_CCLR = 22;

    @Native
    public static final int TYPE_DCLR = 23;

    @Native
    public static final int TYPE_ECLR = 24;

    @Native
    public static final int TYPE_FCLR = 25;

    @Native
    public static final int CS_sRGB = 1000;

    @Native
    public static final int CS_LINEAR_RGB = 1004;

    @Native
    public static final int CS_CIEXYZ = 1001;

    @Native
    public static final int CS_PYCC = 1002;

    @Native
    public static final int CS_GRAY = 1003;

    protected ColorSpace(int type, int numcomponents) {
        this.type = type;
        this.numComponents = numcomponents;
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

    static boolean isCS_CIEXYZ(ColorSpace cspace);
}
