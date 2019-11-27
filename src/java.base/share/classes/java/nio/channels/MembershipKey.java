/*
 * Copyright (c) 2007, 2013, Oracle and/or its affiliates. All rights reserved.
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
package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class MembershipKey {

    protected MembershipKey() {
    }

    public abstract boolean isValid();

    public abstract void drop();

    public abstract MembershipKey block(InetAddress source) throws IOException;

    public abstract MembershipKey unblock(InetAddress source);

    public abstract MulticastChannel channel();

    public abstract InetAddress group();

    public abstract NetworkInterface networkInterface();

    public abstract InetAddress sourceAddress();
}
