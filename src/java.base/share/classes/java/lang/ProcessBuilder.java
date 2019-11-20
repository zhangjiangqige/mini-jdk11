package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sun.security.action.GetPropertyAction;

@AnnotatedFor({ "nullness" })
public final class ProcessBuilder {

    public ProcessBuilder(List<String> command) {
    }

    public ProcessBuilder(String... command) {
    }

    public ProcessBuilder command(List<String> command);

    public ProcessBuilder command(String... command);

    public List<String> command();

    public Map<String, String> environment();

    @Nullable
    public File directory();

    public ProcessBuilder directory(@Nullable File directory);

    public abstract static class Redirect {

        public enum Type {

            PIPE, INHERIT, READ, WRITE, APPEND
        }

        public abstract Type type();

        public static final Redirect PIPE;

        public static final Redirect INHERIT;

        public static final Redirect DISCARD;

        public File file();

        public static Redirect from(final File file);

        public static Redirect to(final File file);

        public static Redirect appendTo(final File file);

        public boolean equals(Object obj);

        public int hashCode();
    }

    public ProcessBuilder redirectInput(Redirect source);

    public ProcessBuilder redirectOutput(Redirect destination);

    public ProcessBuilder redirectError(Redirect destination);

    public ProcessBuilder redirectInput(File file);

    public ProcessBuilder redirectOutput(File file);

    public ProcessBuilder redirectError(File file);

    public Redirect redirectInput();

    public Redirect redirectOutput();

    public Redirect redirectError();

    public ProcessBuilder inheritIO();

    public boolean redirectErrorStream();

    public ProcessBuilder redirectErrorStream(boolean redirectErrorStream);

    public Process start() throws IOException;

    public static List<Process> startPipeline(List<ProcessBuilder> builders) throws IOException;
}
