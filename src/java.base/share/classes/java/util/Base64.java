package java.util;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "signedness" })
public class Base64 {

    public static Encoder getEncoder();

    public static Encoder getUrlEncoder();

    public static Encoder getMimeEncoder();

    public static Encoder getMimeEncoder(int lineLength, byte[] lineSeparator);

    public static Decoder getDecoder();

    public static Decoder getUrlDecoder();

    public static Decoder getMimeDecoder();

    public static class Encoder {

        public byte[] encode(byte[] src);

        public int encode(byte[] src, byte[] dst);

        @SuppressWarnings("deprecation")
        public String encodeToString(@PolySigned byte[] src);

        public ByteBuffer encode(ByteBuffer buffer);

        public OutputStream wrap(OutputStream os);

        public Encoder withoutPadding();
    }

    public static class Decoder {

        public byte[] decode(byte[] src);

        @PolySigned
        public byte[] decode(String src);

        public int decode(byte[] src, byte[] dst);

        public ByteBuffer decode(ByteBuffer buffer);

        public InputStream wrap(InputStream is);
    }
}
