/*
 * Copyright (c) 1999, 2015, Oracle and/or its affiliates. All rights reserved.
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
package javax.naming.spi;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.net.MalformedURLException;
import java.util.*;
import javax.naming.*;
import com.sun.naming.internal.VersionHelper;
import com.sun.naming.internal.ResourceManager;
import com.sun.naming.internal.FactoryEnumeration;

@AnnotatedFor({ "interning" })
public class NamingManager {

    public static synchronized void setObjectFactoryBuilder(ObjectFactoryBuilder builder) throws NamingException;

    public static Object getObjectInstance(Object refInfo, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception;

    public static Context getURLContext(String scheme, Hashtable<?, ?> environment) throws NamingException;

    public static Context getInitialContext(Hashtable<?, ?> env) throws NamingException;

    public static synchronized void setInitialContextFactoryBuilder(InitialContextFactoryBuilder builder) throws NamingException;

    public static boolean hasInitialContextFactoryBuilder();

    @Interned
    public static final String CPE;

    @SuppressWarnings("unchecked")
    public static Context getContinuationContext(CannotProceedException cpe) throws NamingException;

    public static Object getStateToBind(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException;
}
