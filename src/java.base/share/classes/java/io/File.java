package java.io;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;
import java.security.SecureRandom;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import sun.security.action.GetPropertyAction;

@CFComment({ "nullness:", "This @EnsuresNonNullIfTrue is not true, since the list methods also", "return null in the case of an IO error (instead of throwing IOException).", "EnsuresNonNullIf(expression={\"list()\",\"list(FilenameFilter)\",\"listFiles()\",\"listFiles(FilenameFilter)\",\"listFiles(FileFilter)\"}, result=true)\"" })
@AnnotatedFor({ "index", "interning", "lock", "nullness" })
public class File implements Serializable, Comparable<File> {

    private static enum PathStatus {

        INVALID, CHECKED
    }

    public static final char separatorChar;

    @Interned
    public static final String separator;

    public static final char pathSeparatorChar;

    @Interned
    public static final String pathSeparator;

    public File(String pathname) {
    }

    public File(@Nullable String parent, String child) {
    }

    public File(@Nullable File parent, String child) {
    }

    public File(URI uri) {
    }

    public String getName();

    @Pure
    @Nullable
    public String getParent(@GuardSatisfied File this);

    @Pure
    @Nullable
    public File getParentFile(@GuardSatisfied File this);

    public String getPath();

    @Pure
    public boolean isAbsolute(@GuardSatisfied File this);

    public String getAbsolutePath();

    public File getAbsoluteFile();

    public String getCanonicalPath() throws IOException;

    public File getCanonicalFile() throws IOException;

    @Deprecated
    public URL toURL() throws MalformedURLException;

    public URI toURI();

    public boolean canRead();

    public boolean canWrite();

    public boolean exists();

    @Pure
    public boolean isDirectory(@GuardSatisfied File this);

    @Pure
    public boolean isFile(@GuardSatisfied File this);

    @Pure
    public boolean isHidden(@GuardSatisfied File this);

    public long lastModified();

    @NonNegative
    public long length();

    public boolean createNewFile() throws IOException;

    public boolean delete();

    public void deleteOnExit();

    public String @Nullable [] list();

    public String @Nullable [] list(@Nullable FilenameFilter filter);

    public File @Nullable [] listFiles();

    public File @Nullable [] listFiles(@Nullable FilenameFilter filter);

    public File @Nullable [] listFiles(@Nullable FileFilter filter);

    public boolean mkdir();

    public boolean mkdirs();

    public boolean renameTo(File dest);

    public boolean setLastModified(long time);

    public boolean setReadOnly();

    public boolean setWritable(boolean writable, boolean ownerOnly);

    public boolean setWritable(boolean writable);

    public boolean setReadable(boolean readable, boolean ownerOnly);

    public boolean setReadable(boolean readable);

    public boolean setExecutable(boolean executable, boolean ownerOnly);

    public boolean setExecutable(boolean executable);

    public boolean canExecute();

    public static File @Nullable [] listRoots();

    @NonNegative
    public long getTotalSpace();

    @NonNegative
    public long getFreeSpace();

    @NonNegative
    public long getUsableSpace();

    public static File createTempFile(String prefix, @Nullable String suffix, @Nullable File directory) throws IOException;

    public static File createTempFile(String prefix, @Nullable String suffix) throws IOException;

    @Pure
    public int compareTo(@GuardSatisfied File this, @GuardSatisfied File pathname);

    @Pure
    public boolean equals(@GuardSatisfied File this, @GuardSatisfied @Nullable Object obj);

    @Pure
    public int hashCode(@GuardSatisfied File this);

    @SideEffectFree
    public String toString(@GuardSatisfied File this);

    public Path toPath();
}
