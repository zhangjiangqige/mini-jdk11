package java.rmi.server;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.util.*;

@AnnotatedFor({ "signedness" })
@Deprecated
public class LogStream extends PrintStream {

    @Deprecated
    public static LogStream log(String name);

    @Deprecated
    public static synchronized PrintStream getDefaultStream();

    @Deprecated
    public static synchronized void setDefaultStream(PrintStream newDefault);

    @Deprecated
    public synchronized OutputStream getOutputStream();

    @Deprecated
    public synchronized void setOutputStream(OutputStream out);

    @Deprecated
    public void write(int b);

    @Deprecated
    public void write(@PolySigned byte[] b, int off, int len);

    @Deprecated
    public String toString();

    public static final int SILENT;

    public static final int BRIEF;

    public static final int VERBOSE;

    @Deprecated
    public static int parseLevel(String s);
}
