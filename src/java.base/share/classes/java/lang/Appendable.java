package java.lang;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LessThan;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

@AnnotatedFor({ "nullness", "index" })
public interface Appendable {

    Appendable append(@Nullable CharSequence csq) throws IOException;

    Appendable append(@Nullable CharSequence csq, @IndexOrHigh({ "#1" }) @LessThan({ "#3 + 1" }) int start, @IndexOrHigh({ "#1" }) int end) throws IOException;

    Appendable append(char c) throws IOException;
}
