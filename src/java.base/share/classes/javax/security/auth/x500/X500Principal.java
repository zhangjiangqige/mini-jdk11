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

    @Interned
    public static final String RFC1779;

    @Interned
    public static final String RFC2253;

    @Interned
    public static final String CANONICAL;

    public X500Principal(String name) {
    }

    public X500Principal(String name, Map<String, String> keywordMap) {
    }

    public X500Principal(byte[] name) {
    }

    public X500Principal(InputStream is) {
    }

    public String getName();

    public String getName(String format);

    public String getName(String format, Map<String, String> oidMap);

    public byte[] getEncoded();

    public String toString();

    public boolean equals(Object o);

    public int hashCode();
}
