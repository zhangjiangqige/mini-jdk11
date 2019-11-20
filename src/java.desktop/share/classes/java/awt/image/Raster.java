package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Rectangle;
import java.awt.Point;
import sun.awt.image.ByteInterleavedRaster;
import sun.awt.image.ShortInterleavedRaster;
import sun.awt.image.IntegerInterleavedRaster;
import sun.awt.image.ByteBandedRaster;
import sun.awt.image.ShortBandedRaster;
import sun.awt.image.BytePackedRaster;
import sun.awt.image.SunWritableRaster;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Raster {

    protected SampleModel sampleModel;

    protected DataBuffer dataBuffer;

    protected int minX;

    protected int minY;

    protected int width;

    protected int height;

    protected int sampleModelTranslateX;

    protected int sampleModelTranslateY;

    protected int numBands;

    protected int numDataElements;

    protected Raster parent;

    public static WritableRaster createInterleavedRaster(int dataType, int w, int h, int bands, Point location);

    public static WritableRaster createInterleavedRaster(int dataType, int w, int h, int scanlineStride, int pixelStride, int[] bandOffsets, Point location);

    public static WritableRaster createBandedRaster(int dataType, int w, int h, int bands, Point location);

    public static WritableRaster createBandedRaster(int dataType, int w, int h, int scanlineStride, int[] bankIndices, int[] bandOffsets, Point location);

    public static WritableRaster createPackedRaster(int dataType, int w, int h, int[] bandMasks, Point location);

    public static WritableRaster createPackedRaster(int dataType, int w, int h, int bands, int bitsPerBand, Point location);

    public static WritableRaster createInterleavedRaster(DataBuffer dataBuffer, int w, int h, int scanlineStride, int pixelStride, int[] bandOffsets, Point location);

    public static WritableRaster createBandedRaster(DataBuffer dataBuffer, int w, int h, int scanlineStride, int[] bankIndices, int[] bandOffsets, Point location);

    public static WritableRaster createPackedRaster(DataBuffer dataBuffer, int w, int h, int scanlineStride, int[] bandMasks, Point location);

    public static WritableRaster createPackedRaster(DataBuffer dataBuffer, int w, int h, int bitsPerPixel, Point location);

    public static Raster createRaster(SampleModel sm, DataBuffer db, Point location);

    public static WritableRaster createWritableRaster(SampleModel sm, Point location);

    public static WritableRaster createWritableRaster(SampleModel sm, DataBuffer db, Point location);

    protected Raster(SampleModel sampleModel, Point origin) {
    }

    protected Raster(SampleModel sampleModel, DataBuffer dataBuffer, Point origin) {
    }

    protected Raster(SampleModel sampleModel, DataBuffer dataBuffer, Rectangle aRegion, Point sampleModelTranslate, Raster parent) {
    }

    public Raster getParent();

    public final int getSampleModelTranslateX();

    public final int getSampleModelTranslateY();

    public WritableRaster createCompatibleWritableRaster();

    public WritableRaster createCompatibleWritableRaster(int w, int h);

    public WritableRaster createCompatibleWritableRaster(Rectangle rect);

    public WritableRaster createCompatibleWritableRaster(int x, int y, int w, int h);

    public Raster createTranslatedChild(int childMinX, int childMinY);

    public Raster createChild(int parentX, int parentY, int width, int height, int childMinX, int childMinY, int[] bandList);

    public Rectangle getBounds();

    public final int getMinX();

    public final int getMinY();

    public final int getWidth();

    public final int getHeight();

    public final int getNumBands();

    public final int getNumDataElements();

    public final int getTransferType();

    public DataBuffer getDataBuffer();

    public SampleModel getSampleModel();

    public Object getDataElements(int x, int y, Object outData);

    public Object getDataElements(int x, int y, int w, int h, Object outData);

    public int[] getPixel(int x, int y, int[] iArray);

    public float[] getPixel(int x, int y, float[] fArray);

    public double[] getPixel(int x, int y, double[] dArray);

    public int[] getPixels(int x, int y, int w, int h, int[] iArray);

    public float[] getPixels(int x, int y, int w, int h, float[] fArray);

    public double[] getPixels(int x, int y, int w, int h, double[] dArray);

    public int getSample(int x, int y, int b);

    public float getSampleFloat(int x, int y, int b);

    public double getSampleDouble(int x, int y, int b);

    public int[] getSamples(int x, int y, int w, int h, int b, int[] iArray);

    public float[] getSamples(int x, int y, int w, int h, int b, float[] fArray);

    public double[] getSamples(int x, int y, int w, int h, int b, double[] dArray);
}
