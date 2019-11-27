/*
 * Copyright (c) 2000, 2018, Oracle and/or its affiliates. All rights reserved.
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
package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.Enumeration;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Inet6Address extends InetAddress {

    public static Inet6Address getByAddress(String host, byte[] addr, NetworkInterface nif) throws UnknownHostException;

    public static Inet6Address getByAddress(String host, byte[] addr, int scope_id) throws UnknownHostException;

    @Override
    public boolean isMulticastAddress();

    @Override
    public boolean isAnyLocalAddress();

    @Override
    public boolean isLoopbackAddress();

    @Override
    public boolean isLinkLocalAddress();

    @Override
    public boolean isSiteLocalAddress();

    @Override
    public boolean isMCGlobal();

    @Override
    public boolean isMCNodeLocal();

    @Override
    public boolean isMCLinkLocal();

    @Override
    public boolean isMCSiteLocal();

    @Override
    public boolean isMCOrgLocal();

    @Override
    public byte[] getAddress();

    public int getScopeId();

    public NetworkInterface getScopedInterface();

    @Override
    public String getHostAddress();

    @Override
    public int hashCode();

    @Override
    public boolean equals(Object obj);

    public boolean isIPv4CompatibleAddress();
}
