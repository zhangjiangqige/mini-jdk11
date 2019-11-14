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
    public static final int TYPE_BYTE = 0;

    @Native
    public static final int TYPE_USHORT = 1;

    @Native
    public static final int TYPE_SHORT = 2;

    @Native
    public static final int TYPE_INT = 3;

    @Native
    public static final int TYPE_FLOAT = 4;

    @Native
    public static final int TYPE_DOUBLE = 5;

    @Native
    public static final int TYPE_UNDEFINED = 32;

    protected int dataType;

    protected int banks;

    protected int offset;

    protected int size;

    protected int[] offsets;

    StateTrackableDelegate theTrackable;

    private static final int[] dataTypeSize = { 8, 16, 16, 32, 32, 64 };

    public static int getDataTypeSize(int type);

    protected DataBuffer(int dataType, int size) {
        this(UNTRACKABLE, dataType, size);
    }

    DataBuffer(State initialState, int dataType, int size) {
        this.theTrackable = StateTrackableDelegate.createInstance(initialState);
        this.dataType = dataType;
        this.banks = 1;
        this.size = size;
        this.offset = 0;
        this.offsets = new int[1];
    }

    protected DataBuffer(int dataType, int size, int numBanks) {
        this(UNTRACKABLE, dataType, size, numBanks);
    }

    DataBuffer(State initialState, int dataType, int size, int numBanks) {
        this.theTrackable = StateTrackableDelegate.createInstance(initialState);
        this.dataType = dataType;
        this.banks = numBanks;
        this.size = size;
        this.offset = 0;
        this.offsets = new int[banks];
    }

    protected DataBuffer(int dataType, int size, int numBanks, int offset) {
        this(UNTRACKABLE, dataType, size, numBanks, offset);
    }

    DataBuffer(State initialState, int dataType, int size, int numBanks, int offset) {
        this.theTrackable = StateTrackableDelegate.createInstance(initialState);
        this.dataType = dataType;
        this.banks = numBanks;
        this.size = size;
        this.offset = offset;
        this.offsets = new int[numBanks];
        for (int i = 0; i < numBanks; i++) {
            this.offsets[i] = offset;
        }
    }

    protected DataBuffer(int dataType, int size, int numBanks, int[] offsets) {
        this(UNTRACKABLE, dataType, size, numBanks, offsets);
    }

    DataBuffer(State initialState, int dataType, int size, int numBanks, int[] offsets) {
        if (numBanks != offsets.length) {
            throw new ArrayIndexOutOfBoundsException("Number of banks" + " does not match number of bank offsets");
        }
        this.theTrackable = StateTrackableDelegate.createInstance(initialState);
        this.dataType = dataType;
        this.banks = numBanks;
        this.size = size;
        this.offset = offsets[0];
        this.offsets = offsets.clone();
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

    static int[] toIntArray(Object obj);

    static {
        SunWritableRaster.setDataStealer(new SunWritableRaster.DataStealer() {

            public byte[] getData(DataBufferByte dbb, int bank) {
                return dbb.bankdata[bank];
            }

            public short[] getData(DataBufferUShort dbus, int bank) {
                return dbus.bankdata[bank];
            }

            public int[] getData(DataBufferInt dbi, int bank) {
                return dbi.bankdata[bank];
            }

            public StateTrackableDelegate getTrackable(DataBuffer db) {
                return db.theTrackable;
            }

            public void setTrackable(DataBuffer db, StateTrackableDelegate trackable) {
                db.theTrackable = trackable;
            }
        });
    }
}
