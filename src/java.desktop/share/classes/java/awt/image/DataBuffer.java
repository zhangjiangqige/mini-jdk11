package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.java2d.StateTrackable.State;
import static sun.java2d.StateTrackable.State.*;
import sun.java2d.StateTrackableDelegate;
import sun.awt.image.SunWritableRaster;
import java.lang.annotation.Native;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class DataBuffer {

    @Native
    public static final int TYPE_BYTE;

    @Native
    public static final int TYPE_USHORT;

    @Native
    public static final int TYPE_SHORT;

    @Native
    public static final int TYPE_INT;

    @Native
    public static final int TYPE_FLOAT;

    @Native
    public static final int TYPE_DOUBLE;

    @Native
    public static final int TYPE_UNDEFINED;

    protected int dataType;

    protected int banks;

    protected int offset;

    protected int size;

    protected int[] offsets;

    public static int getDataTypeSize(int type);

    protected DataBuffer(int dataType, int size) {
    }

    protected DataBuffer(int dataType, int size, int numBanks) {
    }

    protected DataBuffer(int dataType, int size, int numBanks, int offset) {
    }

    protected DataBuffer(int dataType, int size, int numBanks, int[] offsets) {
    }

    public int getDataType();

    public int getSize();

    public int getOffset();

    public int[] getOffsets();

    public int getNumBanks();

    public int getElem(int i);

    public abstract int getElem(int bank, int i);

    public void setElem(int i, int val);

    public abstract void setElem(int bank, int i, int val);

    public float getElemFloat(int i);

    public float getElemFloat(int bank, int i);

    public void setElemFloat(int i, float val);

    public void setElemFloat(int bank, int i, float val);

    public double getElemDouble(int i);

    public double getElemDouble(int bank, int i);

    public void setElemDouble(int i, double val);

    public void setElemDouble(int bank, int i, double val);
}
