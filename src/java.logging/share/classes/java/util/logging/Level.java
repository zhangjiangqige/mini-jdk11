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

    private static final String defaultBundle = "sun.util.logging.resources.logging";

    private static final class RbAccess {

        static final JavaUtilResourceBundleAccess RB_ACCESS = SharedSecrets.getJavaUtilResourceBundleAccess();
    }

    private final String name;

    private final int value;

    @Nullable
    private final String resourceBundleName;

    @Nullable
    private transient String localizedLevelName;

    @Nullable
    private transient Locale cachedLocale;

    public static final Level OFF = new Level("OFF", Integer.MAX_VALUE, defaultBundle);

    public static final Level SEVERE = new Level("SEVERE", 1000, defaultBundle);

    public static final Level WARNING = new Level("WARNING", 900, defaultBundle);

    public static final Level INFO = new Level("INFO", 800, defaultBundle);

    public static final Level CONFIG = new Level("CONFIG", 700, defaultBundle);

    public static final Level FINE = new Level("FINE", 500, defaultBundle);

    public static final Level FINER = new Level("FINER", 400, defaultBundle);

    public static final Level FINEST = new Level("FINEST", 300, defaultBundle);

    public static final Level ALL = new Level("ALL", Integer.MIN_VALUE, defaultBundle);

    private static final Level[] standardLevels = { OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL };

    @SuppressWarnings("signature")
    protected Level(String name, int value) {
        this(name, value, null);
    }

    protected Level(String name, int value, @Nullable String resourceBundleName) {
        this(name, value, resourceBundleName, true);
    }

    @CFComment({ "nullness: All the fields required by KnownLevel.add method are already initialized before passing it as an argument" })
    @SuppressWarnings({ "argument.type.incompatible" })
    private Level(String name, int value, @Nullable @BinaryName String resourceBundleName, boolean visible) {
        if (name == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.value = value;
        this.resourceBundleName = resourceBundleName;
        this.localizedLevelName = resourceBundleName == null ? name : null;
        this.cachedLocale = null;
        if (visible) {
            KnownLevel.add(this);
        }
    }

    @Nullable
    @BinaryName
    public String getResourceBundleName();

    public String getName();

    public String getLocalizedName();

    final String getLevelName();

    @RequiresNonNull({ "resourceBundleName" })
    private String computeLocalizedLevelName(Locale newLocale);

    @Nullable
    final String getCachedLocalizedLevelName();

    @CFComment({ "nullness: This method assigns 'name' to 'localizedLevelName' in case a NullPointerException is thrown by computeLocalizedLevelName" })
    @SuppressWarnings({ "contracts.precondition.not.satisfied" })
    final synchronized String getLocalizedLevelName();

    @CFComment({ "nullness: level is always ensured to be non-null every time it is dereferenced" })
    @SuppressWarnings({ "dereference.of.nullable" })
    @Nullable
    static Level findLevel(String name);

    @Override
    public final String toString();

    public final int intValue();

    private static final long serialVersionUID = -8176160795706313070L;

    private Object readResolve();

    @CFComment({ "nullness: level is always ensured to be non-null every time it is dereferenced" })
    @SuppressWarnings({ "dereference.of.nullable" })
    public static synchronized Level parse(String name) throws IllegalArgumentException;

    @CFComment({ "nullness: It returns false in case a NullPointerException is thrown" })
    @SuppressWarnings({ "dereference.of.nullable" })
    @Override
    public boolean equals(@Nullable Object ox);

    @Override
    public int hashCode();

    static final class KnownLevel extends WeakReference<Level> {

        private static Map<String, List<KnownLevel>> nameToLevels = new HashMap<>();

        private static Map<Integer, List<KnownLevel>> intToLevels = new HashMap<>();

        private static final ReferenceQueue<Level> QUEUE = new ReferenceQueue<>();

        private static final ClassLoaderValue<List<Level>> CUSTOM_LEVEL_CLV = new ClassLoaderValue<>();

        final Level mirroredLevel;

        KnownLevel(Level l) {
            super(l, QUEUE);
            if (l.getClass() == Level.class) {
                this.mirroredLevel = l;
            } else {
                this.mirroredLevel = new Level(l.name, l.value, l.resourceBundleName, false);
            }
        }

        Optional<Level> mirrored();

        Optional<Level> referent();

        private void remove();

        static synchronized void purge();

        private static void registerWithClassLoader(Level customLevel);

        static synchronized void add(Level l);

        static synchronized Optional<Level> findByName(String name, Function<KnownLevel, Optional<Level>> selector);

        static synchronized Optional<Level> findByValue(int value, Function<KnownLevel, Optional<Level>> selector);

        static synchronized Optional<Level> findByLocalizedLevelName(String name, Function<KnownLevel, Optional<Level>> selector);

        static synchronized Optional<Level> matches(Level l);
    }
}
