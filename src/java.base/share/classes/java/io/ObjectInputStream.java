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
package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectStreamClass.WeakClassKey;
import java.lang.System.Logger;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import static java.io.ObjectStreamClass.processQueue;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.Unsafe;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "nullness", "index" })
public class ObjectInputStream extends InputStream implements ObjectInput, ObjectStreamConstants {

    public ObjectInputStream(InputStream in) throws IOException {
    }

    protected ObjectInputStream() throws IOException, SecurityException {
    }

    public final Object readObject() throws IOException, ClassNotFoundException;

    protected Object readObjectOverride() throws IOException, ClassNotFoundException;

    public Object readUnshared() throws IOException, ClassNotFoundException;

    public void defaultReadObject() throws IOException, ClassNotFoundException;

    public ObjectInputStream.GetField readFields() throws IOException, ClassNotFoundException;

    public void registerValidation(ObjectInputValidation obj, int prio) throws NotActiveException, InvalidObjectException;

    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException;

    protected Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException;

    protected Object resolveObject(Object obj) throws IOException;

    protected boolean enableResolveObject(boolean enable) throws SecurityException;

    protected void readStreamHeader() throws IOException, StreamCorruptedException;

    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException;

    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public int available() throws IOException;

    public void close() throws IOException;

    public boolean readBoolean() throws IOException;

    public byte readByte() throws IOException;

    @NonNegative
    public int readUnsignedByte() throws IOException;

    public char readChar() throws IOException;

    public short readShort() throws IOException;

    @NonNegative
    public int readUnsignedShort() throws IOException;

    public int readInt() throws IOException;

    public long readLong() throws IOException;

    public float readFloat() throws IOException;

    public double readDouble() throws IOException;

    public void readFully(byte[] buf) throws IOException;

    public void readFully(byte[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public int skipBytes(@NonNegative int len) throws IOException;

    @Deprecated
    @Nullable
    public String readLine() throws IOException;

    public String readUTF() throws IOException;

    public final ObjectInputFilter getObjectInputFilter();

    public final void setObjectInputFilter(ObjectInputFilter filter);

    public abstract static class GetField {

        public abstract ObjectStreamClass getObjectStreamClass();

        public abstract boolean defaulted(String name) throws IOException;

        public abstract boolean get(String name, boolean val) throws IOException;

        public abstract byte get(String name, byte val) throws IOException;

        public abstract char get(String name, char val) throws IOException;

        public abstract short get(String name, short val) throws IOException;

        public abstract int get(String name, int val) throws IOException;

        public abstract long get(String name, long val) throws IOException;

        public abstract float get(String name, float val) throws IOException;

        public abstract double get(String name, double val) throws IOException;

        @Nullable
        public abstract Object get(String name, @Nullable Object val) throws IOException;
    }
}
