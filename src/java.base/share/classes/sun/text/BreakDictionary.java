package sun.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.MissingResourceException;
import sun.text.CompactByteArray;
import sun.text.SupplementaryCharacterData;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class BreakDictionary {

    private static int supportedVersion = 1;

    private CompactByteArray columnMap = null;

    private SupplementaryCharacterData supplementaryCharColumnMap = null;

    private int numCols;

    private int numColGroups;

    private short[] table = null;

    private short[] rowIndex = null;

    private int[] rowIndexFlags = null;

    private short[] rowIndexFlagsIndex = null;

    private byte[] rowIndexShifts = null;

    BreakDictionary(String dictionaryName, byte[] dictionaryData) {
        try {
            setupDictionary(dictionaryName, dictionaryData);
        } catch (BufferUnderflowException bue) {
            MissingResourceException e;
            e = new MissingResourceException("Corrupted dictionary data", dictionaryName, "");
            e.initCause(bue);
            throw e;
        }
    }

    private void setupDictionary(String dictionaryName, byte[] dictionaryData);

    public final short getNextStateFromCharacter(int row, int ch);

    public final short getNextState(int row, int col);

    private boolean cellIsPopulated(int row, int col);

    private short internalAt(int row, int col);
}
