package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Collection;
import java.util.Set;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class PKIXCertPathChecker implements CertPathChecker, Cloneable {

    protected PKIXCertPathChecker() {
    }

    @Override
    public abstract void init(boolean forward) throws CertPathValidatorException;

    @Override
    public abstract boolean isForwardCheckingSupported();

    public abstract Set<String> getSupportedExtensions();

    public abstract void check(Certificate cert, Collection<String> unresolvedCritExts) throws CertPathValidatorException;

    @Override
    public void check(Certificate cert) throws CertPathValidatorException;

    @Override
    public Object clone();
}
