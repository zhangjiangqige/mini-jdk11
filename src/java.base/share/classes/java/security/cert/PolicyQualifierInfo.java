package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import sun.security.util.HexDumpEncoder;
import sun.security.util.DerValue;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PolicyQualifierInfo {

    private byte[] mEncoded;

    private String mId;

    private byte[] mData;

    private String pqiString;

    public PolicyQualifierInfo(byte[] encoded) throws IOException {
        mEncoded = encoded.clone();
        DerValue val = new DerValue(mEncoded);
        if (val.tag != DerValue.tag_Sequence)
            throw new IOException("Invalid encoding for PolicyQualifierInfo");
        mId = (val.data.getDerValue()).getOID().toString();
        byte[] tmp = val.data.toByteArray();
        if (tmp == null) {
            mData = null;
        } else {
            mData = new byte[tmp.length];
            System.arraycopy(tmp, 0, mData, 0, tmp.length);
        }
    }

    public final String getPolicyQualifierId();

    public final byte[] getEncoded();

    public final byte[] getPolicyQualifier();

    public String toString();
}
