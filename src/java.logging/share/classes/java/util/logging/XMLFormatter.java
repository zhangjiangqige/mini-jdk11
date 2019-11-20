package java.util.logging;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@AnnotatedFor({ "index" })
public class XMLFormatter extends Formatter {

    public XMLFormatter() {
    }

    @Override
    public String format(LogRecord record);

    @Override
    public String getHead(Handler h);

    @Override
    public String getTail(Handler h);
}
