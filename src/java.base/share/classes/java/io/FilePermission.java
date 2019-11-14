package java.io;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.net.URI;
import java.nio.file.*;
import java.security.*;
import java.util.Enumeration;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import jdk.internal.misc.JavaIOFilePermissionAccess;
import jdk.internal.misc.SharedSecrets;
import sun.nio.fs.DefaultFileSystemProvider;
import sun.security.action.GetPropertyAction;
import sun.security.util.FilePermCompat;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "lock", "nullness", "index" })
public final class FilePermission extends Permission implements Serializable {

    private static final int EXECUTE = 0x1;

    private static final int WRITE = 0x2;

    private static final int READ = 0x4;

    private static final int DELETE = 0x8;

    private static final int READLINK = 0x10;

    private static final int ALL = READ | WRITE | EXECUTE | DELETE | READLINK;

    private static final int NONE = 0x0;

    private transient int mask;

    private transient boolean directory;

    private transient boolean recursive;

    private String actions;

    private transient String cpath;

    private transient Path npath;

    private transient Path npath2;

    private transient boolean allFiles;

    private transient boolean invalid;

    private static final char RECURSIVE_CHAR = '-';

    private static final char WILD_CHAR = '*';

    private static final long serialVersionUID = 7930732926638008763L;

    private static final java.nio.file.FileSystem builtInFS = DefaultFileSystemProvider.create().getFileSystem(URI.create("file:///"));

    private static final Path here = builtInFS.getPath(GetPropertyAction.privilegedGetProperty("user.dir"));

    private static final Path EMPTY_PATH = builtInFS.getPath("");

    private static final Path DASH_PATH = builtInFS.getPath("-");

    private static final Path DOTDOT_PATH = builtInFS.getPath("..");

    private FilePermission(String name, FilePermission input, Path npath, Path npath2, int mask, String actions) {
        super(name);
        this.npath = npath;
        this.npath2 = npath2;
        this.actions = actions;
        this.mask = mask;
        this.allFiles = input.allFiles;
        this.invalid = input.invalid;
        this.recursive = input.recursive;
        this.directory = input.directory;
        this.cpath = input.cpath;
    }

    private static Path altPath(Path in);

    static {
        SharedSecrets.setJavaIOFilePermissionAccess(new JavaIOFilePermissionAccess() {

            public FilePermission newPermPlusAltPath(FilePermission input) {
                if (!input.invalid && input.npath2 == null && !input.allFiles) {
                    Path npath2 = altPath(input.npath);
                    if (npath2 != null) {
                        return new FilePermission(input.getName() + "#plus", input, input.npath, npath2, input.mask, input.actions);
                    }
                }
                return input;
            }

            public FilePermission newPermUsingAltPath(FilePermission input) {
                if (!input.invalid && !input.allFiles) {
                    Path npath2 = altPath(input.npath);
                    if (npath2 != null) {
                        return new FilePermission(input.getName() + "#using", input, npath2, null, input.mask, input.actions);
                    }
                }
                return null;
            }
        });
    }

    private void init(int mask);

    public FilePermission(String path, String actions) {
        super(path);
        init(getMask(actions));
    }

    FilePermission(String path, int mask) {
        super(path);
        init(mask);
    }

    @Override
    public boolean implies(@Nullable Permission p);

    boolean impliesIgnoreMask(FilePermission that);

    private static int containsPath(Path p1, Path p2);

    @Pure
    @Override
    public boolean equals(@GuardSatisfied FilePermission this, @GuardSatisfied @Nullable Object obj);

    @Pure
    @Override
    public int hashCode(@GuardSatisfied FilePermission this);

    private static int getMask(String actions);

    int getMask();

    private static String getActions(int mask);

    @Override
    public String getActions();

    @Override
    public PermissionCollection newPermissionCollection();

    private void writeObject(ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException;

    FilePermission withNewActions(int effective);
}

final class FilePermissionCollection extends PermissionCollection implements Serializable {

    private transient ConcurrentHashMap<String, Permission> perms;

    public FilePermissionCollection() {
        perms = new ConcurrentHashMap<>();
    }

    @Override
    public void add(Permission permission);

    @Override
    public boolean implies(Permission permission);

    @Override
    public Enumeration<Permission> elements();

    private static final long serialVersionUID = 2202956749081564585L;

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("permissions", Vector.class) };

    private void writeObject(ObjectOutputStream out) throws IOException;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;
}
