package com.sun.org.apache.xml.internal.security.utils;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ByteArrayOutputStream;
import com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;

@AnnotatedFor({ "signedness" })
public class DigesterOutputStream extends ByteArrayOutputStream {

    private static final com.sun.org.slf4j.internal.Logger LOG = com.sun.org.slf4j.internal.LoggerFactory.getLogger(DigesterOutputStream.class);

    final MessageDigestAlgorithm mda;

    public DigesterOutputStream(MessageDigestAlgorithm mda) {
        this.mda = mda;
    }

    public void write(@PolySigned byte[] arg0);

    public void write(int arg0);

    public void write(@PolySigned byte[] arg0, int arg1, int arg2);

    public byte[] getDigestValue();
}
