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

    private List<String> command;

    private File directory;

    private Map<String, String> environment;

    private boolean redirectErrorStream;

    private Redirect[] redirects;

    public ProcessBuilder(List<String> command) {
        if (command == null)
            throw new NullPointerException();
        this.command = command;
    }

    public ProcessBuilder(String... command) {
        this.command = new ArrayList<>(command.length);
        for (String arg : command) this.command.add(arg);
    }

    public ProcessBuilder command(List<String> command);

    public ProcessBuilder command(String... command);

    public List<String> command();

    public Map<String, String> environment();

    ProcessBuilder environment(String[] envp);

    @Nullable
    public File directory();

    public ProcessBuilder directory(@Nullable File directory);

    static class NullInputStream extends InputStream {

        static final NullInputStream INSTANCE = new NullInputStream();

        private NullInputStream() {
        }

        public int read();

        public int available();
    }

    static class NullOutputStream extends OutputStream {

        static final NullOutputStream INSTANCE = new NullOutputStream();

        private NullOutputStream() {
        }

        public void write(int b) throws IOException;
    }

    public abstract static class Redirect {

        private static final File NULL_FILE = new File((GetPropertyAction.privilegedGetProperty("os.name").startsWith("Windows") ? "NUL" : "/dev/null"));

        public enum Type {

            PIPE, INHERIT, READ, WRITE, APPEND
        }

        public abstract Type type();

        public static final Redirect PIPE = new Redirect() {

            public Type type() {
                return Type.PIPE;
            }

            public String toString() {
                return type().toString();
            }
        };

        public static final Redirect INHERIT = new Redirect() {

            public Type type() {
                return Type.INHERIT;
            }

            public String toString() {
                return type().toString();
            }
        };

        public static final Redirect DISCARD = new Redirect() {

            public Type type() {
                return Type.WRITE;
            }

            public String toString() {
                return type().toString();
            }

            public File file() {
                return NULL_FILE;
            }

            boolean append() {
                return false;
            }
        };

        public File file();

        boolean append();

        public static Redirect from(final File file);

        public static Redirect to(final File file);

        public static Redirect appendTo(final File file);

        public boolean equals(Object obj);

        public int hashCode();

        private Redirect() {
        }
    }

    static class RedirectPipeImpl extends Redirect {

        final FileDescriptor fd;

        RedirectPipeImpl() {
            this.fd = new FileDescriptor();
        }

        @Override
        public Type type();

        @Override
        public String toString();

        FileDescriptor getFd();
    }

    private Redirect[] redirects();

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

    private Process start(Redirect[] redirects) throws IOException;

    public static List<Process> startPipeline(List<ProcessBuilder> builders) throws IOException;
}
