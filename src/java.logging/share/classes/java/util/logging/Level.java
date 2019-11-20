package java.util.logging;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import jdk.internal.loader.ClassLoaderValue;
import jdk.internal.misc.JavaUtilResourceBundleAccess;
import jdk.internal.misc.SharedSecrets;

@AnnotatedFor({ "interning", "nullness", "signature" })
@Interned
public class Level implements java.io.Serializable {

    public static final Level OFF;

    public static final Level SEVERE;

    public static final Level WARNING;

    public static final Level INFO;

    public static final Level CONFIG;

    public static final Level FINE;

    public static final Level FINER;

    public static final Level FINEST;

    public static final Level ALL;

    @SuppressWarnings("signature")
    protected Level(String name, int value) {
    }

    protected Level(String name, int value, @Nullable String resourceBundleName) {
    }

    @Nullable
    @BinaryName
    public String getResourceBundleName();

    public String getName();

    public String getLocalizedName();

    @Override
    public final String toString();

    public final int intValue();

    @CFComment({ "nullness: level is always ensured to be non-null every time it is dereferenced" })
    @SuppressWarnings({ "dereference.of.nullable" })
    public static synchronized Level parse(String name) throws IllegalArgumentException;

    @CFComment({ "nullness: It returns false in case a NullPointerException is thrown" })
    @SuppressWarnings({ "dereference.of.nullable" })
    @Override
    public boolean equals(@Nullable Object ox);

    @Override
    public int hashCode();
}
