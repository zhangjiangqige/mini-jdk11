package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.*;
import javax.security.auth.x500.X500Principal;
import sun.security.util.HexDumpEncoder;
import sun.security.util.Debug;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class X509CertSelector implements CertSelector {

    private static final Debug debug = Debug.getInstance("certpath");

    private static final ObjectIdentifier ANY_EXTENDED_KEY_USAGE = ObjectIdentifier.newInternal(new int[] { 2, 5, 29, 37, 0 });

    static {
        CertPathHelperImpl.initialize();
    }

    private BigInteger serialNumber;

    private X500Principal issuer;

    private X500Principal subject;

    private byte[] subjectKeyID;

    private byte[] authorityKeyID;

    private Date certificateValid;

    private Date privateKeyValid;

    private ObjectIdentifier subjectPublicKeyAlgID;

    private PublicKey subjectPublicKey;

    private byte[] subjectPublicKeyBytes;

    private boolean[] keyUsage;

    private Set<String> keyPurposeSet;

    private Set<ObjectIdentifier> keyPurposeOIDSet;

    private Set<List<?>> subjectAlternativeNames;

    private Set<GeneralNameInterface> subjectAlternativeGeneralNames;

    private CertificatePolicySet policy;

    private Set<String> policySet;

    private Set<List<?>> pathToNames;

    private Set<GeneralNameInterface> pathToGeneralNames;

    private NameConstraintsExtension nc;

    private byte[] ncBytes;

    private int basicConstraints = -1;

    private X509Certificate x509Cert;

    private boolean matchAllSubjectAltNames = true;

    private static final Boolean FALSE = Boolean.FALSE;

    private static final int PRIVATE_KEY_USAGE_ID = 0;

    private static final int SUBJECT_ALT_NAME_ID = 1;

    private static final int NAME_CONSTRAINTS_ID = 2;

    private static final int CERT_POLICIES_ID = 3;

    private static final int EXTENDED_KEY_USAGE_ID = 4;

    private static final int NUM_OF_EXTENSIONS = 5;

    private static final String[] EXTENSION_OIDS = new String[NUM_OF_EXTENSIONS];

    static {
        EXTENSION_OIDS[PRIVATE_KEY_USAGE_ID] = "2.5.29.16";
        EXTENSION_OIDS[SUBJECT_ALT_NAME_ID] = "2.5.29.17";
        EXTENSION_OIDS[NAME_CONSTRAINTS_ID] = "2.5.29.30";
        EXTENSION_OIDS[CERT_POLICIES_ID] = "2.5.29.32";
        EXTENSION_OIDS[EXTENDED_KEY_USAGE_ID] = "2.5.29.37";
    }

    static final int NAME_ANY = 0;

    static final int NAME_RFC822 = 1;

    static final int NAME_DNS = 2;

    static final int NAME_X400 = 3;

    static final int NAME_DIRECTORY = 4;

    static final int NAME_EDI = 5;

    static final int NAME_URI = 6;

    static final int NAME_IP = 7;

    static final int NAME_OID = 8;

    public X509CertSelector() {
    }

    public void setCertificate(X509Certificate cert);

    public void setSerialNumber(BigInteger serial);

    public void setIssuer(X500Principal issuer);

    public void setIssuer(String issuerDN) throws IOException;

    public void setIssuer(byte[] issuerDN) throws IOException;

    public void setSubject(X500Principal subject);

    public void setSubject(String subjectDN) throws IOException;

    public void setSubject(byte[] subjectDN) throws IOException;

    public void setSubjectKeyIdentifier(byte[] subjectKeyID);

    public void setAuthorityKeyIdentifier(byte[] authorityKeyID);

    public void setCertificateValid(Date certValid);

    public void setPrivateKeyValid(Date privateKeyValid);

    public void setSubjectPublicKeyAlgID(String oid) throws IOException;

    public void setSubjectPublicKey(PublicKey key);

    public void setSubjectPublicKey(byte[] key) throws IOException;

    public void setKeyUsage(boolean[] keyUsage);

    public void setExtendedKeyUsage(Set<String> keyPurposeSet) throws IOException;

    public void setMatchAllSubjectAltNames(boolean matchAllNames);

    public void setSubjectAlternativeNames(Collection<List<?>> names) throws IOException;

    public void addSubjectAlternativeName(int type, String name) throws IOException;

    public void addSubjectAlternativeName(int type, byte[] name) throws IOException;

    private void addSubjectAlternativeNameInternal(int type, Object name) throws IOException;

    private static Set<GeneralNameInterface> parseNames(Collection<List<?>> names) throws IOException;

    static boolean equalNames(Collection<?> object1, Collection<?> object2);

    static GeneralNameInterface makeGeneralNameInterface(int type, Object name) throws IOException;

    public void setNameConstraints(byte[] bytes) throws IOException;

    public void setBasicConstraints(int minMaxPathLen);

    public void setPolicy(Set<String> certPolicySet) throws IOException;

    public void setPathToNames(Collection<List<?>> names) throws IOException;

    void setPathToNamesInternal(Set<GeneralNameInterface> names);

    public void addPathToName(int type, String name) throws IOException;

    public void addPathToName(int type, byte[] name) throws IOException;

    private void addPathToNameInternal(int type, Object name) throws IOException;

    public X509Certificate getCertificate();

    public BigInteger getSerialNumber();

    public X500Principal getIssuer();

    public String getIssuerAsString();

    public byte[] getIssuerAsBytes() throws IOException;

    public X500Principal getSubject();

    public String getSubjectAsString();

    public byte[] getSubjectAsBytes() throws IOException;

    public byte[] getSubjectKeyIdentifier();

    public byte[] getAuthorityKeyIdentifier();

    public Date getCertificateValid();

    public Date getPrivateKeyValid();

    public String getSubjectPublicKeyAlgID();

    public PublicKey getSubjectPublicKey();

    public boolean[] getKeyUsage();

    public Set<String> getExtendedKeyUsage();

    public boolean getMatchAllSubjectAltNames();

    public Collection<List<?>> getSubjectAlternativeNames();

    private static Set<List<?>> cloneNames(Collection<List<?>> names);

    private static Set<List<?>> cloneAndCheckNames(Collection<List<?>> names) throws IOException;

    public byte[] getNameConstraints();

    public int getBasicConstraints();

    public Set<String> getPolicy();

    public Collection<List<?>> getPathToNames();

    public String toString();

    private static String keyUsageToString(boolean[] k);

    private static Extension getExtensionObject(X509Certificate cert, int extId) throws IOException;

    public boolean match(Certificate cert);

    private boolean matchSubjectKeyID(X509Certificate xcert);

    private boolean matchAuthorityKeyID(X509Certificate xcert);

    private boolean matchPrivateKeyValid(X509Certificate xcert);

    private boolean matchSubjectPublicKeyAlgID(X509Certificate xcert);

    private boolean matchKeyUsage(X509Certificate xcert);

    private boolean matchExtendedKeyUsage(X509Certificate xcert);

    private boolean matchSubjectAlternativeNames(X509Certificate xcert);

    private boolean matchNameConstraints(X509Certificate xcert);

    private boolean matchPolicy(X509Certificate xcert);

    private boolean matchPathToNames(X509Certificate xcert);

    private boolean matchExcluded(GeneralSubtrees excluded);

    private boolean matchPermitted(GeneralSubtrees permitted);

    private boolean matchBasicConstraints(X509Certificate xcert);

    @SuppressWarnings("unchecked")
    private static <T> Set<T> cloneSet(Set<T> set);

    public Object clone();
}
