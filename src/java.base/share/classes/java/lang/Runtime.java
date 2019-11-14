package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public class Runtime {

    private static final Runtime currentRuntime = new Runtime();

    private static Version version;

    public static Runtime getRuntime();

    private Runtime() {
    }

    @TerminatesExecution
    public void exit(int status);

    public void addShutdownHook(Thread hook);

    public boolean removeShutdownHook(Thread hook);

    public void halt(int status);

    public Process exec(String command) throws IOException;

    public Process exec(String command, String @Nullable [] envp) throws IOException;

    public Process exec(String command, String @Nullable [] envp, @Nullable File dir) throws IOException;

    public Process exec(String[] cmdarray) throws IOException;

    public Process exec(String[] cmdarray, String @Nullable [] envp) throws IOException;

    public Process exec(String[] cmdarray, String @Nullable [] envp, @Nullable File dir) throws IOException;

    public native int availableProcessors();

    public native long freeMemory();

    public native long totalMemory();

    public native long maxMemory();

    public native void gc();

    public void runFinalization();

    @Deprecated(since = "9", forRemoval = true)
    public void traceInstructions(boolean on);

    @Deprecated(since = "9", forRemoval = true)
    public void traceMethodCalls(boolean on);

    @CallerSensitive
    public void load(String filename);

    synchronized void load0(Class<?> fromClass, String filename);

    @CallerSensitive
    public void loadLibrary(String libname);

    synchronized void loadLibrary0(Class<?> fromClass, String libname);

    public static Version version();

    public static final class Version implements Comparable<Version> {

        private final List<Integer> version;

        private final Optional<String> pre;

        private final Optional<Integer> build;

        private final Optional<String> optional;

        private Version(List<Integer> unmodifiableListOfVersions, Optional<String> pre, Optional<Integer> build, Optional<String> optional) {
            this.version = unmodifiableListOfVersions;
            this.pre = pre;
            this.build = build;
            this.optional = optional;
        }

        public static Version parse(String s);

        private static boolean isSimpleNumber(String s);

        public int feature();

        public int interim();

        public int update();

        public int patch();

        @Deprecated(since = "10")
        public int major();

        @Deprecated(since = "10")
        public int minor();

        @Deprecated(since = "10")
        public int security();

        public List<Integer> version();

        public Optional<String> pre();

        public Optional<Integer> build();

        public Optional<String> optional();

        @Override
        public int compareTo(Version obj);

        public int compareToIgnoreOptional(Version obj);

        private int compare(Version obj, boolean ignoreOpt);

        private int compareVersion(Version obj);

        private int comparePre(Version obj);

        private int compareBuild(Version obj);

        private int compareOptional(Version obj);

        @Override
        public String toString();

        @Override
        public boolean equals(Object obj);

        public boolean equalsIgnoreOptional(Object obj);

        @Override
        public int hashCode();
    }

    private static class VersionPattern {

        private static final String VNUM = "(?<VNUM>[1-9][0-9]*(?:(?:\\.0)*\\.[1-9][0-9]*)*)";

        private static final String PRE = "(?:-(?<PRE>[a-zA-Z0-9]+))?";

        private static final String BUILD = "(?:(?<PLUS>\\+)(?<BUILD>0|[1-9][0-9]*)?)?";

        private static final String OPT = "(?:-(?<OPT>[-a-zA-Z0-9.]+))?";

        private static final String VSTR_FORMAT = VNUM + PRE + BUILD + OPT;

        static final Pattern VSTR_PATTERN = Pattern.compile(VSTR_FORMAT);

        static final String VNUM_GROUP = "VNUM";

        static final String PRE_GROUP = "PRE";

        static final String PLUS_GROUP = "PLUS";

        static final String BUILD_GROUP = "BUILD";

        static final String OPT_GROUP = "OPT";
    }
}
