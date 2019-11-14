package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.DataInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Locale;
import java.util.zip.InflaterInputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
class CharacterName {

    private static SoftReference<CharacterName> refCharName;

    private final byte[] strPool;

    private final int[] lookup;

    private final int[] bkIndices;

    private final int[] cpEntries;

    private final int[] hsIndices;

    private CharacterName() {
        try (DataInputStream dis = new DataInputStream(new InflaterInputStream(AccessController.doPrivileged(new PrivilegedAction<>() {

            public InputStream run() {
                return getClass().getResourceAsStream("uniName.dat");
            }
        })))) {
            int total = dis.readInt();
            int bkNum = dis.readInt();
            int cpNum = dis.readInt();
            int cpEnd = dis.readInt();
            byte[] ba = new byte[cpEnd];
            lookup = new int[bkNum * 256];
            bkIndices = new int[(Character.MAX_CODE_POINT + 1) >> 8];
            strPool = new byte[total - cpEnd];
            cpEntries = new int[cpNum * 3];
            hsIndices = new int[(cpNum / 2) | 1];
            Arrays.fill(bkIndices, -1);
            Arrays.fill(hsIndices, -1);
            dis.readFully(ba);
            dis.readFully(strPool);
            int nameOff = 0;
            int cpOff = 0;
            int cp = 0;
            int bk = -1;
            int prevBk = -1;
            int idx = 0;
            int next = -1;
            int hash = 0;
            int hsh = 0;
            do {
                int len = ba[cpOff++] & 0xff;
                if (len == 0) {
                    len = ba[cpOff++] & 0xff;
                    cp = ((ba[cpOff++] & 0xff) << 16) | ((ba[cpOff++] & 0xff) << 8) | ((ba[cpOff++] & 0xff));
                } else {
                    cp++;
                }
                int hi = cp >> 8;
                if (prevBk != hi) {
                    bk++;
                    bkIndices[hi] = bk;
                    prevBk = hi;
                }
                lookup[(bk << 8) + (cp & 0xff)] = (nameOff << 8) | len;
                hash = hashN(strPool, nameOff, len);
                hsh = (hash & 0x7fffffff) % hsIndices.length;
                next = hsIndices[hsh];
                hsIndices[hsh] = idx;
                idx = addCp(idx, hash, next, cp);
                nameOff += len;
            } while (cpOff < cpEnd);
        } catch (Exception x) {
            throw new InternalError(x.getMessage(), x);
        }
    }

    private static final int hashN(byte[] a, int off, int len);

    private int addCp(int idx, int hash, int next, int cp);

    private int getCpHash(int idx);

    private int getCpNext(int idx);

    private int getCp(int idx);

    public static CharacterName getInstance();

    public String getName(int cp);

    public int getCodePoint(String name);
}
