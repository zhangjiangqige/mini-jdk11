package java.io;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

@AnnotatedFor({ "nullness" })
public final class SerializablePermission extends BasicPermission {

    public SerializablePermission(String name) {
    }

    public SerializablePermission(String name, @Nullable String actions) {
    }
}
