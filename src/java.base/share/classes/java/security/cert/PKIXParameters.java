package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PKIXParameters implements CertPathParameters {

    public PKIXParameters(Set<TrustAnchor> trustAnchors) throws InvalidAlgorithmParameterException {
    }

    public PKIXParameters(KeyStore keystore) throws KeyStoreException, InvalidAlgorithmParameterException {
    }

    public Set<TrustAnchor> getTrustAnchors();

    public void setTrustAnchors(Set<TrustAnchor> trustAnchors) throws InvalidAlgorithmParameterException;

    public Set<String> getInitialPolicies();

    public void setInitialPolicies(Set<String> initialPolicies);

    public void setCertStores(List<CertStore> stores);

    public void addCertStore(CertStore store);

    public List<CertStore> getCertStores();

    public void setRevocationEnabled(boolean val);

    public boolean isRevocationEnabled();

    public void setExplicitPolicyRequired(boolean val);

    public boolean isExplicitPolicyRequired();

    public void setPolicyMappingInhibited(boolean val);

    public boolean isPolicyMappingInhibited();

    public void setAnyPolicyInhibited(boolean val);

    public boolean isAnyPolicyInhibited();

    public void setPolicyQualifiersRejected(boolean qualifiersRejected);

    public boolean getPolicyQualifiersRejected();

    public Date getDate();

    public void setDate(Date date);

    public void setCertPathCheckers(List<PKIXCertPathChecker> checkers);

    public List<PKIXCertPathChecker> getCertPathCheckers();

    public void addCertPathChecker(PKIXCertPathChecker checker);

    public String getSigProvider();

    public void setSigProvider(String sigProvider);

    public CertSelector getTargetCertConstraints();

    public void setTargetCertConstraints(CertSelector selector);

    public Object clone();

    public String toString();
}
