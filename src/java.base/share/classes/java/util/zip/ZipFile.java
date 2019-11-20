package java.util.zip;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Closeable;
import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.lang.ref.Cleaner.Cleanable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.misc.JavaLangAccess;
import jdk.internal.misc.JavaUtilZipFileAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.VM;
import jdk.internal.perf.PerfCounter;
import jdk.internal.ref.CleanerFactory;
import jdk.internal.vm.annotation.Stable;
import static java.util.zip.ZipConstants64.*;
import static java.util.zip.ZipUtils.*;

@AnnotatedFor({ "index", "interning", "nullness" })
@UsesObjectEquals
public class ZipFile implements ZipConstants, Closeable {

    public static final int OPEN_READ;

    public static final int OPEN_DELETE;

    public ZipFile(String name) throws IOException {
    }

    public ZipFile(File file, int mode) throws IOException {
    }

    public ZipFile(File file) throws ZipException, IOException {
    }

    public ZipFile(File file, int mode, Charset charset) throws IOException {
    }

    public ZipFile(String name, Charset charset) throws IOException {
    }

    public ZipFile(File file, Charset charset) throws IOException {
    }

    @Nullable
    public String getComment();

    @Nullable
    public ZipEntry getEntry(String name);

    @Nullable
    public InputStream getInputStream(ZipEntry entry) throws IOException;

    public String getName();

    public Enumeration<? extends ZipEntry> entries();

    public Stream<? extends ZipEntry> stream();

    @Pure
    @NonNegative
    public int size();

    public void close() throws IOException;

    @Deprecated()
    protected void finalize() throws IOException;
}
