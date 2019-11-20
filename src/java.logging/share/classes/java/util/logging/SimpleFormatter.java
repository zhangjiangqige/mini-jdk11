package java.util.logging;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import jdk.internal.logger.SurrogateLogger;

@AnnotatedFor({ "index" })
public class SimpleFormatter extends Formatter {

    @Override
    public String format(LogRecord record);
}
