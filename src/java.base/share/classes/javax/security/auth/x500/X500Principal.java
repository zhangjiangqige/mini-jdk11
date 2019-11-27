/*
 * Copyright (c) 2000, 2014, Oracle and/or its affiliates. All rights reserved.
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
package javax.security.auth.x500;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import sun.security.x509.X500Name;
import sun.security.util.*;

@AnnotatedFor({ "interning" })
public final class X500Principal implements Principal, java.io.Serializable {

    @Interned
    public static final String RFC1779;

    @Interned
    public static final String RFC2253;

    @Interned
    public static final String CANONICAL;

    public X500Principal(String name) {
    }

    public X500Principal(String name, Map<String, String> keywordMap) {
    }

    public X500Principal(byte[] name) {
    }

    public X500Principal(InputStream is) {
    }

    public String getName();

    public String getName(String format);

    public String getName(String format, Map<String, String> oidMap);

    public byte[] getEncoded();

    public String toString();

    public boolean equals(Object o);

    public int hashCode();
}
