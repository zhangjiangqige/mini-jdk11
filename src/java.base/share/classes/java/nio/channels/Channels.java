package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import sun.nio.ch.ChannelInputStream;
import sun.nio.cs.StreamDecoder;
import sun.nio.cs.StreamEncoder;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Channels {

    public static InputStream newInputStream(ReadableByteChannel ch);

    public static OutputStream newOutputStream(WritableByteChannel ch);

    public static InputStream newInputStream(AsynchronousByteChannel ch);

    public static OutputStream newOutputStream(AsynchronousByteChannel ch);

    public static ReadableByteChannel newChannel(InputStream in);

    public static WritableByteChannel newChannel(OutputStream out);

    public static Reader newReader(ReadableByteChannel ch, CharsetDecoder dec, int minBufferCap);

    public static Reader newReader(ReadableByteChannel ch, String csName);

    public static Reader newReader(ReadableByteChannel ch, Charset charset);

    public static Writer newWriter(WritableByteChannel ch, CharsetEncoder enc, int minBufferCap);

    public static Writer newWriter(WritableByteChannel ch, String csName);

    public static Writer newWriter(WritableByteChannel ch, Charset charset);
}
