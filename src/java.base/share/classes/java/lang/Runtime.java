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

    public static Runtime getRuntime();

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

    @Deprecated()
    public void traceInstructions(boolean on);

    @Deprecated()
    public void traceMethodCalls(boolean on);

    @CallerSensitive
    public void load(String filename);

    @CallerSensitive
    public void loadLibrary(String libname);

    public static Version version();

    public static final class Version implements Comparable<Version> {

        public static Version parse(String s);

        public int feature();

        public int interim();

        public int update();

        public int patch();

        @Deprecated()
        public int major();

        @Deprecated()
        public int minor();

        @Deprecated()
        public int security();

        public List<Integer> version();

        public Optional<String> pre();

        public Optional<Integer> build();

        public Optional<String> optional();

        @Override
        public int compareTo(Version obj);

        public int compareToIgnoreOptional(Version obj);

        @Override
        public String toString();

        @Override
        public boolean equals(Object obj);

        public boolean equalsIgnoreOptional(Object obj);

        @Override
        public int hashCode();
    }
}
