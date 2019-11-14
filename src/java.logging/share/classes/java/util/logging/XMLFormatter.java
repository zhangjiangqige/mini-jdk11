package java.util.logging;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@AnnotatedFor({ "index" })
public class XMLFormatter extends Formatter {

    private final LogManager manager = LogManager.getLogManager();

    private final boolean useInstant;

    public XMLFormatter() {
        useInstant = (manager == null) || manager.getBooleanProperty(this.getClass().getName() + ".useInstant", true);
    }

    private void a2(StringBuilder sb, int x);

    private void appendISO8601(StringBuilder sb, long millis);

    private void escape(StringBuilder sb, String text);

    @Override
    public String format(LogRecord record);

    @Override
    public String getHead(Handler h);

    @Override
    public String getTail(Handler h);
}
