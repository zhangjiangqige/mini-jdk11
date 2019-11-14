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

    private Base64() {
    }

    public static Encoder getEncoder();

    public static Encoder getUrlEncoder();

    public static Encoder getMimeEncoder();

    public static Encoder getMimeEncoder(int lineLength, byte[] lineSeparator);

    public static Decoder getDecoder();

    public static Decoder getUrlDecoder();

    public static Decoder getMimeDecoder();

    public static class Encoder {

        private final byte[] newline;

        private final int linemax;

        private final boolean isURL;

        private final boolean doPadding;

        private Encoder(boolean isURL, byte[] newline, int linemax, boolean doPadding) {
            this.isURL = isURL;
            this.newline = newline;
            this.linemax = linemax;
            this.doPadding = doPadding;
        }

        private static final char[] toBase64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

        private static final char[] toBase64URL = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_' };

        private static final int MIMELINEMAX = 76;

        private static final byte[] CRLF = new byte[] { '\r', '\n' };

        static final Encoder RFC4648 = new Encoder(false, null, -1, true);

        static final Encoder RFC4648_URLSAFE = new Encoder(true, null, -1, true);

        static final Encoder RFC2045 = new Encoder(false, CRLF, MIMELINEMAX, true);

        private final int outLength(int srclen);

        public byte[] encode(byte[] src);

        public int encode(byte[] src, byte[] dst);

        @SuppressWarnings("deprecation")
        public String encodeToString(@PolySigned byte[] src);

        public ByteBuffer encode(ByteBuffer buffer);

        public OutputStream wrap(OutputStream os);

        public Encoder withoutPadding();

        @HotSpotIntrinsicCandidate
        private void encodeBlock(byte[] src, int sp, int sl, byte[] dst, int dp, boolean isURL);

        private int encode0(byte[] src, int off, int end, byte[] dst);
    }

    public static class Decoder {

        private final boolean isURL;

        private final boolean isMIME;

        private Decoder(boolean isURL, boolean isMIME) {
            this.isURL = isURL;
            this.isMIME = isMIME;
        }

        private static final int[] fromBase64 = new int[256];

        static {
            Arrays.fill(fromBase64, -1);
            for (int i = 0; i < Encoder.toBase64.length; i++) fromBase64[Encoder.toBase64[i]] = i;
            fromBase64['='] = -2;
        }

        private static final int[] fromBase64URL = new int[256];

        static {
            Arrays.fill(fromBase64URL, -1);
            for (int i = 0; i < Encoder.toBase64URL.length; i++) fromBase64URL[Encoder.toBase64URL[i]] = i;
            fromBase64URL['='] = -2;
        }

        static final Decoder RFC4648 = new Decoder(false, false);

        static final Decoder RFC4648_URLSAFE = new Decoder(true, false);

        static final Decoder RFC2045 = new Decoder(false, true);

        public byte[] decode(byte[] src);

        @PolySigned
        public byte[] decode(String src);

        public int decode(byte[] src, byte[] dst);

        public ByteBuffer decode(ByteBuffer buffer);

        public InputStream wrap(InputStream is);

        private int outLength(byte[] src, int sp, int sl);

        private int decode0(byte[] src, int sp, int sl, byte[] dst);
    }

    private static class EncOutputStream extends FilterOutputStream {

        private int leftover = 0;

        private int b0, b1, b2;

        private boolean closed = false;

        private final char[] base64;

        private final byte[] newline;

        private final int linemax;

        private final boolean doPadding;

        private int linepos = 0;

        private byte[] buf;

        EncOutputStream(OutputStream os, char[] base64, byte[] newline, int linemax, boolean doPadding) {
            super(os);
            this.base64 = base64;
            this.newline = newline;
            this.linemax = linemax;
            this.doPadding = doPadding;
            this.buf = new byte[linemax <= 0 ? 8124 : linemax];
        }

        @Override
        public void write(int b) throws IOException;

        private void checkNewline() throws IOException;

        private void writeb4(char b1, char b2, char b3, char b4) throws IOException;

        @Override
        public void write(byte[] b, int off, int len) throws IOException;

        @Override
        public void close() throws IOException;
    }

    private static class DecInputStream extends InputStream {

        private final InputStream is;

        private final boolean isMIME;

        private final int[] base64;

        private int bits = 0;

        private int nextin = 18;

        private int nextout = -8;

        private boolean eof = false;

        private boolean closed = false;

        DecInputStream(InputStream is, int[] base64, boolean isMIME) {
            this.is = is;
            this.base64 = base64;
            this.isMIME = isMIME;
        }

        private byte[] sbBuf = new byte[1];

        @Override
        public int read() throws IOException;

        private int eof(byte[] b, int off, int len, int oldOff) throws IOException;

        private int padding(byte[] b, int off, int len, int oldOff) throws IOException;

        @Override
        public int read(byte[] b, int off, int len) throws IOException;

        @Override
        public int available() throws IOException;

        @Override
        public void close() throws IOException;
    }
}
