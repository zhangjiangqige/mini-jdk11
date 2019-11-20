package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.spi.FileSystemProvider;
import java.nio.file.spi.FileTypeDetector;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiPredicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import sun.nio.ch.FileChannelImpl;
import sun.nio.fs.AbstractFileSystemProvider;

@AnnotatedFor({ "interning", "signedness" })
@UsesObjectEquals
public final class Files {

    public static InputStream newInputStream(Path path, OpenOption... options) throws IOException;

    public static OutputStream newOutputStream(Path path, OpenOption... options) throws IOException;

    public static SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException;

    public static SeekableByteChannel newByteChannel(Path path, OpenOption... options) throws IOException;

    public static DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException;

    public static DirectoryStream<Path> newDirectoryStream(Path dir, String glob) throws IOException;

    public static DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) throws IOException;

    public static Path createFile(Path path, FileAttribute<?>... attrs) throws IOException;

    public static Path createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException;

    public static Path createDirectories(Path dir, FileAttribute<?>... attrs) throws IOException;

    public static Path createTempFile(Path dir, String prefix, String suffix, FileAttribute<?>... attrs) throws IOException;

    public static Path createTempFile(String prefix, String suffix, FileAttribute<?>... attrs) throws IOException;

    public static Path createTempDirectory(Path dir, String prefix, FileAttribute<?>... attrs) throws IOException;

    public static Path createTempDirectory(String prefix, FileAttribute<?>... attrs) throws IOException;

    public static Path createSymbolicLink(Path link, Path target, FileAttribute<?>... attrs) throws IOException;

    public static Path createLink(Path link, Path existing) throws IOException;

    public static void delete(Path path) throws IOException;

    public static boolean deleteIfExists(Path path) throws IOException;

    public static Path copy(Path source, Path target, CopyOption... options) throws IOException;

    public static Path move(Path source, Path target, CopyOption... options) throws IOException;

    public static Path readSymbolicLink(Path link) throws IOException;

    public static FileStore getFileStore(Path path) throws IOException;

    public static boolean isSameFile(Path path, Path path2) throws IOException;

    public static boolean isHidden(Path path) throws IOException;

    public static String probeContentType(Path path) throws IOException;

    public static <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options);

    public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) throws IOException;

    public static Path setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException;

    public static Object getAttribute(Path path, String attribute, LinkOption... options) throws IOException;

    public static Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException;

    public static Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... options) throws IOException;

    public static Path setPosixFilePermissions(Path path, Set<PosixFilePermission> perms) throws IOException;

    public static UserPrincipal getOwner(Path path, LinkOption... options) throws IOException;

    public static Path setOwner(Path path, UserPrincipal owner) throws IOException;

    public static boolean isSymbolicLink(Path path);

    public static boolean isDirectory(Path path, LinkOption... options);

    public static boolean isRegularFile(Path path, LinkOption... options);

    public static FileTime getLastModifiedTime(Path path, LinkOption... options) throws IOException;

    public static Path setLastModifiedTime(Path path, FileTime time) throws IOException;

    public static long size(Path path) throws IOException;

    public static boolean exists(Path path, LinkOption... options);

    public static boolean notExists(Path path, LinkOption... options);

    public static boolean isReadable(Path path);

    public static boolean isWritable(Path path);

    public static boolean isExecutable(Path path);

    public static Path walkFileTree(Path start, Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path> visitor) throws IOException;

    public static Path walkFileTree(Path start, FileVisitor<? super Path> visitor) throws IOException;

    public static BufferedReader newBufferedReader(Path path, Charset cs) throws IOException;

    public static BufferedReader newBufferedReader(Path path) throws IOException;

    public static BufferedWriter newBufferedWriter(Path path, Charset cs, OpenOption... options) throws IOException;

    public static BufferedWriter newBufferedWriter(Path path, OpenOption... options) throws IOException;

    public static long copy(InputStream in, Path target, CopyOption... options) throws IOException;

    public static long copy(Path source, OutputStream out) throws IOException;

    public static byte[] readAllBytes(Path path) throws IOException;

    public static String readString(Path path) throws IOException;

    public static String readString(Path path, Charset cs) throws IOException;

    public static List<String> readAllLines(Path path, Charset cs) throws IOException;

    public static List<String> readAllLines(Path path) throws IOException;

    public static Path write(Path path, @PolySigned byte[] bytes, OpenOption... options) throws IOException;

    public static Path write(Path path, Iterable<? extends CharSequence> lines, Charset cs, OpenOption... options) throws IOException;

    public static Path write(Path path, Iterable<? extends CharSequence> lines, OpenOption... options) throws IOException;

    public static Path writeString(Path path, CharSequence csq, OpenOption... options) throws IOException;

    public static Path writeString(Path path, CharSequence csq, Charset cs, OpenOption... options) throws IOException;

    public static Stream<Path> list(Path dir) throws IOException;

    public static Stream<Path> walk(Path start, int maxDepth, FileVisitOption... options) throws IOException;

    public static Stream<Path> walk(Path start, FileVisitOption... options) throws IOException;

    public static Stream<Path> find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitOption... options) throws IOException;

    public static Stream<String> lines(Path path, Charset cs) throws IOException;

    public static Stream<String> lines(Path path) throws IOException;
}
