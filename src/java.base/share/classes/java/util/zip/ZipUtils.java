package java.util.zip;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.attribute.FileTime;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static java.util.zip.ZipConstants.ENDHDR;
import jdk.internal.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

@AnnotatedFor({ "signedness" })
class ZipUtils {

    private static final long WINDOWS_EPOCH_IN_MICROSECONDS = -11644473600000000L;

    public static final long WINDOWS_TIME_NOT_AVAILABLE = Long.MIN_VALUE;

    static final ByteBuffer defaultBuf = ByteBuffer.allocate(0);

    public static final FileTime winTimeToFileTime(long wtime);

    public static final long fileTimeToWinTime(FileTime ftime);

    public static final long UPPER_UNIXTIME_BOUND = 0x7fffffff;

    public static final FileTime unixTimeToFileTime(long utime);

    public static final long fileTimeToUnixTime(FileTime ftime);

    public static long dosToJavaTime(long dtime);

    @SuppressWarnings("deprecation")
    private static long overflowDosToJavaTime(int year, int month, int day, int hour, int minute, int second);

    public static long extendedDosToJavaTime(long xdostime);

    private static long javaToDosTime(long time);

    public static long javaToExtendedDosTime(long time);

    public static final int get16(byte[] b, int off);

    public static final long get32(byte[] b, int off);

    public static final long get64(byte[] b, int off);

    public static final int get32S(byte[] b, int off);

    static final int CH(byte[] b, int n);

    static final int SH(byte[] b, int n);

    static final long LG(byte[] b, int n);

    static final long LL(byte[] b, int n);

    static final long GETSIG(byte[] b);

    static final long LOCSIG(byte[] b);

    static final int LOCVER(byte[] b);

    static final int LOCFLG(byte[] b);

    static final int LOCHOW(byte[] b);

    static final long LOCTIM(byte[] b);

    static final long LOCCRC(byte[] b);

    static final long LOCSIZ(byte[] b);

    static final long LOCLEN(byte[] b);

    static final int LOCNAM(byte[] b);

    static final int LOCEXT(byte[] b);

    static final long EXTCRC(byte[] b);

    static final long EXTSIZ(byte[] b);

    static final long EXTLEN(byte[] b);

    static final int ENDSUB(byte[] b);

    static final int ENDTOT(byte[] b);

    static final long ENDSIZ(byte[] b);

    static final long ENDOFF(byte[] b);

    static final int ENDCOM(byte[] b);

    static final int ENDCOM(byte[] b, int off);

    static final long ZIP64_ENDTOD(byte[] b);

    static final long ZIP64_ENDTOT(byte[] b);

    static final long ZIP64_ENDSIZ(byte[] b);

    static final long ZIP64_ENDOFF(byte[] b);

    static final long ZIP64_LOCOFF(byte[] b);

    static final long CENSIG(byte[] b, int pos);

    static final int CENVEM(byte[] b, int pos);

    static final int CENVER(byte[] b, int pos);

    static final int CENFLG(byte[] b, int pos);

    static final int CENHOW(byte[] b, int pos);

    static final long CENTIM(byte[] b, int pos);

    static final long CENCRC(byte[] b, int pos);

    static final long CENSIZ(byte[] b, int pos);

    static final long CENLEN(byte[] b, int pos);

    static final int CENNAM(byte[] b, int pos);

    static final int CENEXT(byte[] b, int pos);

    static final int CENCOM(byte[] b, int pos);

    static final int CENDSK(byte[] b, int pos);

    static final int CENATT(byte[] b, int pos);

    static final long CENATX(byte[] b, int pos);

    static final long CENOFF(byte[] b, int pos);

    static final long END_MAXLEN = 0xFFFF + ENDHDR;

    static final int READBLOCKSZ = 128;

    static void loadLibrary();

    private static final Unsafe unsafe = Unsafe.getUnsafe();

    private static final long byteBufferArrayOffset = unsafe.objectFieldOffset(ByteBuffer.class, "hb");

    private static final long byteBufferOffsetOffset = unsafe.objectFieldOffset(ByteBuffer.class, "offset");

    static byte[] getBufferArray(ByteBuffer byteBuffer);

    static int getBufferOffset(ByteBuffer byteBuffer);
}
