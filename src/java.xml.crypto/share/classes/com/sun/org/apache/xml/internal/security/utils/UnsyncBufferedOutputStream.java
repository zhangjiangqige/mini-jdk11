package com.sun.org.apache.xml.internal.security.utils;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@AnnotatedFor({ "signedness" })
public class UnsyncBufferedOutputStream extends FilterOutputStream {

    protected byte[] buffer;

    protected int count;

    public UnsyncBufferedOutputStream(OutputStream out) {
        super(out);
        buffer = new byte[8192];
    }

    public UnsyncBufferedOutputStream(OutputStream out, int size) {
        super(out);
        if (size <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }
        buffer = new byte[size];
    }

    @Override
    public void flush() throws IOException;

    @Override
    public void write(@PolySigned byte[] bytes, int offset, int length) throws IOException;

    @Override
    public void write(int oneByte) throws IOException;

    private void flushInternal() throws IOException;
}
