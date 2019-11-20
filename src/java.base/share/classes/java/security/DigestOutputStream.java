package java.security;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.EOFException;
import java.io.OutputStream;
import java.io.FilterOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

@AnnotatedFor({ "signedness" })
public class DigestOutputStream extends FilterOutputStream {

    protected MessageDigest digest;

    public DigestOutputStream(OutputStream stream, MessageDigest digest) {
    }

    public MessageDigest getMessageDigest();

    public void setMessageDigest(MessageDigest digest);

    public void write(int b) throws IOException;

    public void write(@PolySigned byte[] b, int off, int len) throws IOException;

    public void on(boolean on);

    public String toString();
}
