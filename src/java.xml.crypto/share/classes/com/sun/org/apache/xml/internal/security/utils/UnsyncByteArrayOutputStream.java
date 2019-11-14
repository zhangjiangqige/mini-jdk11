package com.sun.org.apache.xml.internal.security.utils;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.OutputStream;

@AnnotatedFor({ "signedness" })
public class UnsyncByteArrayOutputStream extends OutputStream {

    private static final int VM_ARRAY_INDEX_MAX_VALUE = Integer.MAX_VALUE - 8;

    private static final int INITIAL_SIZE = 8192;

    private byte[] buf;

    private int size = INITIAL_SIZE;

    private int pos;

    public UnsyncByteArrayOutputStream() {
        buf = new byte[INITIAL_SIZE];
    }

    public void write(@PolySigned byte[] arg0);

    public void write(@PolySigned byte[] arg0, int arg1, int arg2);

    public void write(int arg0);

    public byte[] toByteArray();

    public void reset();

    public void writeTo(OutputStream out) throws IOException;

    private void expandSize(int newPos);
}
