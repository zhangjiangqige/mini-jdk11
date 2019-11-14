package java.rmi.server;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.util.*;

@AnnotatedFor({ "signedness" })
@Deprecated
public class LogStream extends PrintStream {

    private static Map<String, LogStream> known = new HashMap<>(5);

    private static PrintStream defaultStream = System.err;

    private String name;

    private OutputStream logOut;

    private OutputStreamWriter logWriter;

    private StringBuffer buffer = new StringBuffer();

    private ByteArrayOutputStream bufOut;

    @Deprecated
    private LogStream(String name, OutputStream out) {
        super(new ByteArrayOutputStream());
        bufOut = (ByteArrayOutputStream) super.out;
        this.name = name;
        setOutputStream(out);
    }

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

    public static final int SILENT = 0;

    public static final int BRIEF = 10;

    public static final int VERBOSE = 20;

    @Deprecated
    public static int parseLevel(String s);
}
