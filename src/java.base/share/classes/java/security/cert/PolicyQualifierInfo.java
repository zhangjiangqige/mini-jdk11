package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import sun.security.util.HexDumpEncoder;
import sun.security.util.DerValue;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PolicyQualifierInfo {

    public PolicyQualifierInfo(byte[] encoded) throws IOException {
    }

    public final String getPolicyQualifierId();

    public final byte[] getEncoded();

    public final byte[] getPolicyQualifier();

    public String toString();
}
