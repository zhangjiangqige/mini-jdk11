package sun.security.provider.certpath;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Date;
import java.util.Set;
import java.security.cert.X509CertSelector;
import java.security.cert.X509CRLSelector;
import sun.security.x509.GeneralNameInterface;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class CertPathHelper {

    protected static CertPathHelper instance;

    protected CertPathHelper() {
    }

    protected abstract void implSetPathToNames(X509CertSelector sel, Set<GeneralNameInterface> names);

    protected abstract void implSetDateAndTime(X509CRLSelector sel, Date date, long skew);

    public static void setDateAndTime(X509CRLSelector sel, Date date, long skew);
}
