package java.util.logging;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import jdk.internal.logger.SurrogateLogger;

@AnnotatedFor({ "index" })
public class SimpleFormatter extends Formatter {

    static String getLoggingProperty(String name);

    private final String format = SurrogateLogger.getSimpleFormat(SimpleFormatter::getLoggingProperty);

    @Override
    public String format(LogRecord record);
}
