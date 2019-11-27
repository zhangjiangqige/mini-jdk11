/*
 * Copyright (c) 1996, 2017, Oracle and/or its affiliates. All rights reserved.
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
package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;
import java.net.URL;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.util.StaticProperty;
import sun.security.util.Debug;
import sun.security.util.PropertyExpander;
import sun.security.jca.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Security {

    @Deprecated
    public static String getAlgorithmProperty(String algName, String propName);

    public static synchronized int insertProviderAt(Provider provider, int position);

    public static int addProvider(Provider provider);

    public static synchronized void removeProvider(String name);

    public static Provider[] getProviders();

    public static Provider getProvider(String name);

    public static Provider[] getProviders(String filter);

    public static Provider[] getProviders(Map<String, String> filter);

    public static String getProperty(String key);

    public static void setProperty(String key, String datum);

    public static Set<String> getAlgorithms(String serviceName);
}
