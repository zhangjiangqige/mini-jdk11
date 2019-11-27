/*
 * Copyright (c) 1997, 2017, Oracle and/or its affiliates. All rights reserved.
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
import java.util.Enumeration;
import java.util.WeakHashMap;
import java.util.Objects;
import sun.security.jca.GetInstance;
import sun.security.util.Debug;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Policy {

    public static final PermissionCollection UNSUPPORTED_EMPTY_COLLECTION;

    public static Policy getPolicy();

    public static void setPolicy(Policy p);

    public static Policy getInstance(String type, Policy.Parameters params) throws NoSuchAlgorithmException;

    public static Policy getInstance(String type, Policy.Parameters params, String provider) throws NoSuchProviderException, NoSuchAlgorithmException;

    public static Policy getInstance(String type, Policy.Parameters params, Provider provider) throws NoSuchAlgorithmException;

    public Provider getProvider();

    public String getType();

    public Policy.Parameters getParameters();

    public PermissionCollection getPermissions(CodeSource codesource);

    public PermissionCollection getPermissions(ProtectionDomain domain);

    public boolean implies(ProtectionDomain domain, Permission permission);

    public void refresh();

    public static interface Parameters {
    }
}
