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

    public static final int STORED;

    public static final int DEFLATED;

    public ZipEntry(String name) {
    }

    public ZipEntry(ZipEntry e) {
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
