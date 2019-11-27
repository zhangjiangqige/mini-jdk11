/*
 * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
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
package java.util.jar;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.JavaUtilZipFileAccess;
import sun.security.action.GetPropertyAction;
import sun.security.util.ManifestEntryVerifier;
import sun.security.util.SignatureFileVerifier;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

@AnnotatedFor({ "nullness" })
public class JarFile extends ZipFile {

    @Interned
    public static final String MANIFEST_NAME;

    public static Runtime.Version baseVersion();

    public static Runtime.Version runtimeVersion();

    public JarFile(String name) throws IOException {
    }

    public JarFile(String name, boolean verify) throws IOException {
    }

    public JarFile(File file) throws IOException {
    }

    public JarFile(File file, boolean verify) throws IOException {
    }

    public JarFile(File file, boolean verify, int mode) throws IOException {
    }

    public JarFile(File file, boolean verify, int mode, Runtime.Version version) throws IOException {
    }

    public final Runtime.Version getVersion();

    public final boolean isMultiRelease();

    @Nullable
    public Manifest getManifest() throws IOException;

    @Nullable
    public JarEntry getJarEntry(String name);

    @Nullable
    public ZipEntry getEntry(String name);

    public Enumeration<JarEntry> entries();

    public Stream<JarEntry> stream();

    public Stream<JarEntry> versionedStream();

    public synchronized InputStream getInputStream(ZipEntry ze) throws IOException;
}
