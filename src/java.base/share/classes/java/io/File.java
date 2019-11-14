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

    private static final FileSystem fs = DefaultFileSystem.getFileSystem();

    private final String path;

    private static enum PathStatus {

        INVALID, CHECKED
    }

    private transient PathStatus status = null;

    final boolean isInvalid();

    private final transient int prefixLength;

    int getPrefixLength();

    public static final char separatorChar = fs.getSeparator();

    @Interned
    public static final String separator = "" + separatorChar;

    public static final char pathSeparatorChar = fs.getPathSeparator();

    @Interned
    public static final String pathSeparator = "" + pathSeparatorChar;

    private File(String pathname, int prefixLength) {
        this.path = pathname;
        this.prefixLength = prefixLength;
    }

    private File(String child, File parent) {
        assert parent.path != null;
        assert (!parent.path.equals(""));
        this.path = fs.resolve(parent.path, child);
        this.prefixLength = parent.prefixLength;
    }

    public File(String pathname) {
        if (pathname == null) {
            throw new NullPointerException();
        }
        this.path = fs.normalize(pathname);
        this.prefixLength = fs.prefixLength(this.path);
    }

    public File(@Nullable String parent, String child) {
        if (child == null) {
            throw new NullPointerException();
        }
        if (parent != null) {
            if (parent.equals("")) {
                this.path = fs.resolve(fs.getDefaultParent(), fs.normalize(child));
            } else {
                this.path = fs.resolve(fs.normalize(parent), fs.normalize(child));
            }
        } else {
            this.path = fs.normalize(child);
        }
        this.prefixLength = fs.prefixLength(this.path);
    }

    public File(@Nullable File parent, String child) {
        if (child == null) {
            throw new NullPointerException();
        }
        if (parent != null) {
            if (parent.path.equals("")) {
                this.path = fs.resolve(fs.getDefaultParent(), fs.normalize(child));
            } else {
                this.path = fs.resolve(parent.path, fs.normalize(child));
            }
        } else {
            this.path = fs.normalize(child);
        }
        this.prefixLength = fs.prefixLength(this.path);
    }

    public File(URI uri) {
        if (!uri.isAbsolute())
            throw new IllegalArgumentException("URI is not absolute");
        if (uri.isOpaque())
            throw new IllegalArgumentException("URI is not hierarchical");
        String scheme = uri.getScheme();
        if ((scheme == null) || !scheme.equalsIgnoreCase("file"))
            throw new IllegalArgumentException("URI scheme is not \"file\"");
        if (uri.getRawAuthority() != null)
            throw new IllegalArgumentException("URI has an authority component");
        if (uri.getRawFragment() != null)
            throw new IllegalArgumentException("URI has a fragment component");
        if (uri.getRawQuery() != null)
            throw new IllegalArgumentException("URI has a query component");
        String p = uri.getPath();
        if (p.equals(""))
            throw new IllegalArgumentException("URI path component is empty");
        p = fs.fromURIPath(p);
        if (File.separatorChar != '/')
            p = p.replace('/', File.separatorChar);
        this.path = fs.normalize(p);
        this.prefixLength = fs.prefixLength(this.path);
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

    private static String slashify(String path, boolean isDirectory);

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

    private static class TempDirectory {

        private TempDirectory() {
        }

        private static final File tmpdir = new File(GetPropertyAction.privilegedGetProperty("java.io.tmpdir"));

        static File location();

        private static final SecureRandom random = new SecureRandom();

        private static int shortenSubName(int subNameLength, int excess, int nameMin);

        static File generateFile(String prefix, String suffix, File dir) throws IOException;
    }

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

    private synchronized void writeObject(java.io.ObjectOutputStream s) throws IOException;

    private synchronized void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException;

    private static final jdk.internal.misc.Unsafe UNSAFE = jdk.internal.misc.Unsafe.getUnsafe();

    private static final long PATH_OFFSET = UNSAFE.objectFieldOffset(File.class, "path");

    private static final long PREFIX_LENGTH_OFFSET = UNSAFE.objectFieldOffset(File.class, "prefixLength");

    private static final long serialVersionUID = 301077366599181567L;

    private transient volatile Path filePath;

    public Path toPath();
}
