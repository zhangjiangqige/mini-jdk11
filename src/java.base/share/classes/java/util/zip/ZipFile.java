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

    private final String name;

    private volatile boolean closeRequested;

    @Stable
    private final ZipCoder zc;

    @Stable
    private final CleanableResource res;

    private static final int STORED = ZipEntry.STORED;

    private static final int DEFLATED = ZipEntry.DEFLATED;

    public static final int OPEN_READ = 0x1;

    public static final int OPEN_DELETE = 0x4;

    public ZipFile(String name) throws IOException {
        this(new File(name), OPEN_READ);
    }

    public ZipFile(File file, int mode) throws IOException {
        this(file, mode, StandardCharsets.UTF_8);
    }

    public ZipFile(File file) throws ZipException, IOException {
        this(file, OPEN_READ);
    }

    public ZipFile(File file, int mode, Charset charset) throws IOException {
        if (((mode & OPEN_READ) == 0) || ((mode & ~(OPEN_READ | OPEN_DELETE)) != 0)) {
            throw new IllegalArgumentException("Illegal mode: 0x" + Integer.toHexString(mode));
        }
        String name = file.getPath();
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkRead(name);
            if ((mode & OPEN_DELETE) != 0) {
                sm.checkDelete(name);
            }
        }
        Objects.requireNonNull(charset, "charset");
        this.zc = ZipCoder.get(charset);
        this.name = name;
        long t0 = System.nanoTime();
        this.res = CleanableResource.get(this, file, mode);
        PerfCounter.getZipFileOpenTime().addElapsedTimeFrom(t0);
        PerfCounter.getZipFileCount().increment();
    }

    public ZipFile(String name, Charset charset) throws IOException {
        this(new File(name), OPEN_READ, charset);
    }

    public ZipFile(File file, Charset charset) throws IOException {
        this(file, OPEN_READ, charset);
    }

    @Nullable
    public String getComment();

    @Nullable
    public ZipEntry getEntry(String name);

    private ZipEntry getEntry(String name, Function<String, ? extends ZipEntry> func);

    @Nullable
    public InputStream getInputStream(ZipEntry entry) throws IOException;

    private static class InflaterCleanupAction implements Runnable {

        private final Inflater inf;

        private final CleanableResource res;

        InflaterCleanupAction(Inflater inf, CleanableResource res) {
            this.inf = inf;
            this.res = res;
        }

        @Override
        public void run();
    }

    private class ZipFileInflaterInputStream extends InflaterInputStream {

        private volatile boolean closeRequested;

        private boolean eof = false;

        private final Cleanable cleanable;

        ZipFileInflaterInputStream(ZipFileInputStream zfin, CleanableResource res, int size) {
            this(zfin, res, res.getInflater(), size);
        }

        private ZipFileInflaterInputStream(ZipFileInputStream zfin, CleanableResource res, Inflater inf, int size) {
            super(zfin, inf, size);
            this.cleanable = CleanerFactory.cleaner().register(this, new InflaterCleanupAction(inf, res));
        }

        public void close() throws IOException;

        protected void fill() throws IOException;

        public int available() throws IOException;
    }

    public String getName();

    private class ZipEntryIterator<T extends ZipEntry> implements Enumeration<T>, Iterator<T> {

        private int i = 0;

        private final int entryCount;

        private final Function<String, T> gen;

        public ZipEntryIterator(int entryCount, Function<String, T> gen) {
            this.entryCount = entryCount;
            this.gen = gen;
        }

        @Override
        public boolean hasMoreElements();

        @Override
        public boolean hasNext();

        @Override
        public T nextElement();

        @Override
        @SuppressWarnings("unchecked")
        public T next();

        @Override
        public Iterator<T> asIterator();
    }

    public Enumeration<? extends ZipEntry> entries();

    private Enumeration<JarEntry> entries(Function<String, JarEntry> func);

    private class EntrySpliterator<T> extends Spliterators.AbstractSpliterator<T> {

        private int index;

        private final int fence;

        private final IntFunction<T> gen;

        EntrySpliterator(int index, int fence, IntFunction<T> gen) {
            super((long) fence, Spliterator.ORDERED | Spliterator.DISTINCT | Spliterator.IMMUTABLE | Spliterator.NONNULL);
            this.index = index;
            this.fence = fence;
            this.gen = gen;
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action);
    }

    public Stream<? extends ZipEntry> stream();

    private String getEntryName(int pos);

    private Stream<String> entryNameStream();

    private Stream<JarEntry> stream(Function<String, JarEntry> func);

    private String lastEntryName;

    private int lastEntryPos;

    private ZipEntry getZipEntry(String name, byte[] bname, int pos, Function<String, ? extends ZipEntry> func);

    @Pure
    @NonNegative
    public int size();

    private static class CleanableResource implements Runnable {

        final Set<InputStream> istreams;

        Deque<Inflater> inflaterCache;

        final Cleanable cleanable;

        Source zsrc;

        CleanableResource(ZipFile zf, File file, int mode) throws IOException {
            this.cleanable = CleanerFactory.cleaner().register(zf, this);
            this.istreams = Collections.newSetFromMap(new WeakHashMap<>());
            this.inflaterCache = new ArrayDeque<>();
            this.zsrc = Source.get(file, (mode & OPEN_DELETE) != 0);
        }

        void clean();

        Inflater getInflater();

        void releaseInflater(Inflater inf);

        public void run();

        CleanableResource(File file, int mode) throws IOException {
            this.cleanable = null;
            this.istreams = Collections.newSetFromMap(new WeakHashMap<>());
            this.inflaterCache = new ArrayDeque<>();
            this.zsrc = Source.get(file, (mode & OPEN_DELETE) != 0);
        }

        static CleanableResource get(ZipFile zf, File file, int mode) throws IOException;

        static class FinalizableResource extends CleanableResource {

            ZipFile zf;

            FinalizableResource(ZipFile zf, File file, int mode) throws IOException {
                super(file, mode);
                this.zf = zf;
            }

            @Override
            void clean();

            @Override
            @SuppressWarnings("deprecation")
            protected void finalize() throws IOException;
        }
    }

    public void close() throws IOException;

    @Deprecated(since = "9", forRemoval = true)
    protected void finalize() throws IOException;

    private void ensureOpen();

    private void ensureOpenOrZipException() throws IOException;

    private class ZipFileInputStream extends InputStream {

        private volatile boolean closeRequested;

        private long pos;

        protected long rem;

        protected long size;

        ZipFileInputStream(byte[] cen, int cenpos) {
            rem = CENSIZ(cen, cenpos);
            size = CENLEN(cen, cenpos);
            pos = CENOFF(cen, cenpos);
            if (rem == ZIP64_MAGICVAL || size == ZIP64_MAGICVAL || pos == ZIP64_MAGICVAL) {
                checkZIP64(cen, cenpos);
            }
            pos = -(pos + ZipFile.this.res.zsrc.locpos);
        }

        private void checkZIP64(byte[] cen, int cenpos);

        private long initDataOffset() throws IOException;

        @GTENegativeOne
        @LTEqLengthOf({ "#1" })
        public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

        public int read() throws IOException;

        public long skip(long n) throws IOException;

        public int available();

        public long size();

        public void close();
    }

    private String[] getMetaInfEntryNames();

    private static boolean isWindows;

    private static final JavaLangAccess JLA;

    static {
        SharedSecrets.setJavaUtilZipFileAccess(new JavaUtilZipFileAccess() {

            @Override
            public boolean startsWithLocHeader(ZipFile zip) {
                return zip.res.zsrc.startsWithLoc;
            }

            @Override
            public String[] getMetaInfEntryNames(ZipFile zip) {
                return zip.getMetaInfEntryNames();
            }

            @Override
            public JarEntry getEntry(ZipFile zip, String name, Function<String, JarEntry> func) {
                return (JarEntry) zip.getEntry(name, func);
            }

            @Override
            public Enumeration<JarEntry> entries(ZipFile zip, Function<String, JarEntry> func) {
                return zip.entries(func);
            }

            @Override
            public Stream<JarEntry> stream(ZipFile zip, Function<String, JarEntry> func) {
                return zip.stream(func);
            }

            @Override
            public Stream<String> entryNameStream(ZipFile zip) {
                return zip.entryNameStream();
            }
        });
        JLA = jdk.internal.misc.SharedSecrets.getJavaLangAccess();
        isWindows = VM.getSavedProperty("os.name").contains("Windows");
    }

    private static class Source {

        private final Key key;

        private int refs = 1;

        private RandomAccessFile zfile;

        private byte[] cen;

        private long locpos;

        private byte[] comment;

        private int[] metanames;

        private final boolean startsWithLoc;

        private int[] entries;

        private int addEntry(int index, int hash, int next, int pos);

        private int getEntryHash(int index);

        private int getEntryNext(int index);

        private int getEntryPos(int index);

        private static final int ZIP_ENDCHAIN = -1;

        private int total;

        private int[] table;

        private int tablelen;

        private static class Key {

            BasicFileAttributes attrs;

            File file;

            public Key(File file, BasicFileAttributes attrs) {
                this.attrs = attrs;
                this.file = file;
            }

            public int hashCode();

            public boolean equals(Object obj);
        }

        private static final HashMap<Key, Source> files = new HashMap<>();

        static Source get(File file, boolean toDelete) throws IOException;

        static void release(Source src) throws IOException;

        private Source(Key key, boolean toDelete) throws IOException {
            this.key = key;
            if (toDelete) {
                if (isWindows) {
                    this.zfile = SharedSecrets.getJavaIORandomAccessFileAccess().openAndDelete(key.file, "r");
                } else {
                    this.zfile = new RandomAccessFile(key.file, "r");
                    key.file.delete();
                }
            } else {
                this.zfile = new RandomAccessFile(key.file, "r");
            }
            try {
                initCEN(-1);
                byte[] buf = new byte[4];
                readFullyAt(buf, 0, 4, 0);
                this.startsWithLoc = (LOCSIG(buf) == LOCSIG);
            } catch (IOException x) {
                try {
                    this.zfile.close();
                } catch (IOException xx) {
                }
                throw x;
            }
        }

        private void close() throws IOException;

        private static final int BUF_SIZE = 8192;

        private final int readFullyAt(byte[] buf, int off, int len, long pos) throws IOException;

        private final int readAt(byte[] buf, int off, int len, long pos) throws IOException;

        private static final int hashN(byte[] a, int off, int len);

        private static final int hash_append(int hash, byte b);

        private static class End {

            int centot;

            long cenlen;

            long cenoff;

            long endpos;
        }

        private End findEND() throws IOException;

        private void initCEN(int knownTotal) throws IOException;

        private static void zerror(String msg) throws ZipException;

        private int getEntryPos(byte[] name, boolean addSlash);

        private static boolean isMetaName(byte[] name, int off, int len);

        private static int countCENHeaders(byte[] cen, int size);
    }
}
