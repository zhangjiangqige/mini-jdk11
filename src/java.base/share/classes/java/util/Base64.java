/*
 * Copyright (c) 2012, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
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
