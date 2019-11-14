package java.util.zip;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import static java.util.zip.ZipUtils.*;
import java.nio.file.attribute.FileTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import static java.util.zip.ZipConstants64.*;

@AnnotatedFor({ "index", "interning", "nullness", "signedness" })
@UsesObjectEquals
public class ZipEntry implements ZipConstants, Cloneable {

    String name;

    long xdostime = -1;

    FileTime mtime;

    FileTime atime;

    FileTime ctime;

    long crc = -1;

    long size = -1;

    long csize = -1;

    int method = -1;

    int flag = 0;

    byte[] extra;

    String comment;

    public static final int STORED = 0;

    public static final int DEFLATED = 8;

    static final long DOSTIME_BEFORE_1980 = (1 << 21) | (1 << 16);

    private static final long UPPER_DOSTIME_BOUND = 128L * 365 * 24 * 60 * 60 * 1000;

    public ZipEntry(String name) {
        Objects.requireNonNull(name, "name");
        if (name.length() > 0xFFFF) {
            throw new IllegalArgumentException("entry name too long");
        }
        this.name = name;
    }

    public ZipEntry(ZipEntry e) {
        Objects.requireNonNull(e, "entry");
        name = e.name;
        xdostime = e.xdostime;
        mtime = e.mtime;
        atime = e.atime;
        ctime = e.ctime;
        crc = e.crc;
        size = e.size;
        csize = e.csize;
        method = e.method;
        flag = e.flag;
        extra = e.extra;
        comment = e.comment;
    }

    ZipEntry() {
    }

    public String getName();

    public void setTime(long time);

    public long getTime();

    public void setTimeLocal(LocalDateTime time);

    public LocalDateTime getTimeLocal();

    public ZipEntry setLastModifiedTime(FileTime time);

    public FileTime getLastModifiedTime();

    public ZipEntry setLastAccessTime(FileTime time);

    public FileTime getLastAccessTime();

    public ZipEntry setCreationTime(FileTime time);

    public FileTime getCreationTime();

    public void setSize(@NonNegative long size);

    @NonNegative
    public long getSize();

    public long getCompressedSize();

    public void setCompressedSize(long csize);

    public void setCrc(long crc);

    public long getCrc();

    public void setMethod(int method);

    public int getMethod();

    public void setExtra(byte[] extra);

    void setExtra0(byte[] extra, boolean doZIP64);

    @Pure
    public byte @Nullable [] getExtra();

    public void setComment(String comment);

    @Nullable
    public String getComment();

    public boolean isDirectory();

    public String toString();

    public int hashCode();

    public Object clone();
}
