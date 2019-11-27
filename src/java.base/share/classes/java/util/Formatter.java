/*
 * Copyright (c) 2003, 2018, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.formatter.qual.FormatMethod;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.spi.NumberFormatProvider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.UnsupportedTemporalTypeException;
import jdk.internal.math.DoubleConsts;
import jdk.internal.math.FormattedFloatingDecimal;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.ResourceBundleBasedAdapter;

@AnnotatedFor({ "formatter", "index", "lock", "nullness" })
public final class Formatter implements Closeable, Flushable {

    public Formatter() {
    }

    public Formatter(Appendable a) {
    }

    public Formatter(Locale l) {
    }

    public Formatter(Appendable a, Locale l) {
    }

    public Formatter(String fileName) throws FileNotFoundException {
    }

    public Formatter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public Formatter(String fileName, String csn, Locale l) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public Formatter(String fileName, Charset charset, Locale l) throws IOException {
    }

    public Formatter(File file) throws FileNotFoundException {
    }

    public Formatter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public Formatter(File file, String csn, Locale l) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public Formatter(File file, Charset charset, Locale l) throws IOException {
    }

    public Formatter(PrintStream ps) {
    }

    public Formatter(OutputStream os) {
    }

    public Formatter(OutputStream os, String csn) throws UnsupportedEncodingException {
    }

    public Formatter(OutputStream os, String csn, Locale l) throws UnsupportedEncodingException {
    }

    public Formatter(OutputStream os, Charset charset, Locale l) {
    }

    public Locale locale();

    public Appendable out();

    public String toString();

    public void flush();

    public void close();

    public IOException ioException();

    @FormatMethod
    public Formatter format(String format, Object... args);

    @FormatMethod
    public Formatter format(Locale l, String format, Object... args);

    public enum BigDecimalLayoutForm {

        SCIENTIFIC, DECIMAL_FLOAT
    }
}
