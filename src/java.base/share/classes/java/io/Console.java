package java.io;

import org.checkerframework.checker.formatter.qual.FormatMethod;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.nio.charset.Charset;
import jdk.internal.misc.JavaIOAccess;
import jdk.internal.misc.SharedSecrets;
import sun.nio.cs.StreamDecoder;
import sun.nio.cs.StreamEncoder;

@AnnotatedFor({ "formatter", "index", "interning", "nullness" })
@UsesObjectEquals
public final class Console implements Flushable {

    public PrintWriter writer();

    public Reader reader();

    @FormatMethod
    public Console format(String fmt, @Nullable Object... args);

    @FormatMethod
    public Console printf(String format, @Nullable Object... args);

    @Nullable
    public String readLine(String fmt, @Nullable Object... args);

    @Nullable
    public String readLine();

    public char @Nullable [] readPassword(String fmt, @Nullable Object... args);

    public char @Nullable [] readPassword();

    public void flush();
}
