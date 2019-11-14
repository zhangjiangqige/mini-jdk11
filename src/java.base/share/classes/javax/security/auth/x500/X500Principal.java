package javax.security.auth.x500;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import sun.security.x509.X500Name;
import sun.security.util.*;

@AnnotatedFor({ "interning" })
public final class X500Principal implements Principal, java.io.Serializable {

    private static final long serialVersionUID = -500463348111345721L;

    @Interned
    public static final String RFC1779 = "RFC1779";

    @Interned
    public static final String RFC2253 = "RFC2253";

    @Interned
    public static final String CANONICAL = "CANONICAL";

    private transient X500Name thisX500Name;

    X500Principal(X500Name x500Name) {
        thisX500Name = x500Name;
    }

    public X500Principal(String name) {
        this(name, Collections.<String, String>emptyMap());
    }

    public X500Principal(String name, Map<String, String> keywordMap) {
        if (name == null) {
            throw new NullPointerException(sun.security.util.ResourcesMgr.getString("provided.null.name"));
        }
        if (keywordMap == null) {
            throw new NullPointerException(sun.security.util.ResourcesMgr.getString("provided.null.keyword.map"));
        }
        try {
            thisX500Name = new X500Name(name, keywordMap);
        } catch (Exception e) {
            IllegalArgumentException iae = new IllegalArgumentException("improperly specified input name: " + name);
            iae.initCause(e);
            throw iae;
        }
    }

    public X500Principal(byte[] name) {
        try {
            thisX500Name = new X500Name(name);
        } catch (Exception e) {
            IllegalArgumentException iae = new IllegalArgumentException("improperly specified input name");
            iae.initCause(e);
            throw iae;
        }
    }

    public X500Principal(InputStream is) {
        if (is == null) {
            throw new NullPointerException("provided null input stream");
        }
        try {
            if (is.markSupported())
                is.mark(is.available() + 1);
            DerValue der = new DerValue(is);
            thisX500Name = new X500Name(der.data);
        } catch (Exception e) {
            if (is.markSupported()) {
                try {
                    is.reset();
                } catch (IOException ioe) {
                    IllegalArgumentException iae = new IllegalArgumentException("improperly specified input stream " + ("and unable to reset input stream"));
                    iae.initCause(e);
                    throw iae;
                }
            }
            IllegalArgumentException iae = new IllegalArgumentException("improperly specified input stream");
            iae.initCause(e);
            throw iae;
        }
    }

    public String getName();

    public String getName(String format);

    public String getName(String format, Map<String, String> oidMap);

    public byte[] getEncoded();

    public String toString();

    public boolean equals(Object o);

    public int hashCode();

    private void writeObject(java.io.ObjectOutputStream s) throws IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, java.io.NotActiveException, ClassNotFoundException;
}
