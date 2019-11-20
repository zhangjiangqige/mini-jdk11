package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.*;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public abstract class Process {

    public Process() {
    }

    @CFComment({ "nullness: These three methods return @NonNull values despite being documented as", "possibly returning a \"null stream\".  A \"null stream\" is a non-null", "Stream with particular behavior, not a @Nullable Stream reference." })
    public abstract OutputStream getOutputStream();

    public abstract InputStream getInputStream();

    public abstract InputStream getErrorStream();

    public abstract int waitFor() throws InterruptedException;

    public boolean waitFor(long timeout, TimeUnit unit) throws InterruptedException;

    public abstract int exitValue();

    public abstract void destroy();

    public Process destroyForcibly();

    public boolean supportsNormalTermination();

    public boolean isAlive();

    public long pid();

    public CompletableFuture<Process> onExit();

    public ProcessHandle toHandle();

    public ProcessHandle.Info info();

    public Stream<ProcessHandle> children();

    public Stream<ProcessHandle> descendants();
}
