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
package sun.nio.ch;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.ref.Cleaner.Cleanable;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.FileLockInterruptionException;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.WritableByteChannel;
import jdk.internal.misc.JavaIOFileDescriptorAccess;
import jdk.internal.misc.JavaNioAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.ref.Cleaner;
import jdk.internal.ref.CleanerFactory;

@AnnotatedFor({ "index" })
public class FileChannelImpl extends FileChannel {

    public static FileChannel open(FileDescriptor fd, String path, boolean readable, boolean writable, boolean direct, Object parent);

    public void setUninterruptible();

    protected void implCloseChannel() throws IOException;

    public int read(ByteBuffer dst) throws IOException;

    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException;

    public int write(ByteBuffer src) throws IOException;

    public long write(ByteBuffer[] srcs, int offset, int length) throws IOException;

    public long position() throws IOException;

    public FileChannel position(long newPosition) throws IOException;

    public long size() throws IOException;

    public FileChannel truncate(long newSize) throws IOException;

    public void force(boolean metaData) throws IOException;

    public long transferTo(long position, long count, WritableByteChannel target) throws IOException;

    public long transferFrom(ReadableByteChannel src, long position, long count) throws IOException;

    public int read(ByteBuffer dst, long position) throws IOException;

    public int write(ByteBuffer src, long position) throws IOException;

    public MappedByteBuffer map(MapMode mode, long position, long size) throws IOException;

    public static JavaNioAccess.BufferPool getMappedBufferPool();

    public FileLock lock(long position, long size, boolean shared) throws IOException;

    public FileLock tryLock(long position, long size, boolean shared) throws IOException;
}
