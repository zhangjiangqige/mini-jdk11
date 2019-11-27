/*
 * Copyright (c) 1995, 2016, Oracle and/or its affiliates. All rights reserved.
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
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.security.PrivilegedAction;
import java.util.Set;
import java.util.Collections;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public class Socket implements java.io.Closeable {

    public Socket() {
    }

    public Socket(Proxy proxy) {
    }

    protected Socket(SocketImpl impl) throws SocketException {
    }

    public Socket(@Nullable String host, int port) throws UnknownHostException, IOException {
    }

    public Socket(InetAddress address, int port) throws IOException {
    }

    public Socket(@Nullable String host, int port, @Nullable InetAddress localAddr, int localPort) throws IOException {
    }

    public Socket(InetAddress address, int port, @Nullable InetAddress localAddr, int localPort) throws IOException {
    }

    @Deprecated
    public Socket(@Nullable String host, int port, boolean stream) throws IOException {
    }

    @Deprecated
    public Socket(InetAddress host, int port, boolean stream) throws IOException {
    }

    public void connect(SocketAddress endpoint) throws IOException;

    public void connect(SocketAddress endpoint, int timeout) throws IOException;

    public void bind(@Nullable SocketAddress bindpoint) throws IOException;

    @Nullable
    public InetAddress getInetAddress();

    public InetAddress getLocalAddress();

    public int getPort();

    public int getLocalPort();

    @Nullable
    public SocketAddress getRemoteSocketAddress();

    @Nullable
    public SocketAddress getLocalSocketAddress();

    @Nullable
    public SocketChannel getChannel();

    public InputStream getInputStream() throws IOException;

    public OutputStream getOutputStream() throws IOException;

    public void setTcpNoDelay(boolean on) throws SocketException;

    public boolean getTcpNoDelay() throws SocketException;

    public void setSoLinger(boolean on, int linger) throws SocketException;

    public int getSoLinger() throws SocketException;

    public void sendUrgentData(int data) throws IOException;

    public void setOOBInline(boolean on) throws SocketException;

    public boolean getOOBInline() throws SocketException;

    public synchronized void setSoTimeout(int timeout) throws SocketException;

    public synchronized int getSoTimeout() throws SocketException;

    public synchronized void setSendBufferSize(int size) throws SocketException;

    public synchronized int getSendBufferSize() throws SocketException;

    public synchronized void setReceiveBufferSize(int size) throws SocketException;

    public synchronized int getReceiveBufferSize() throws SocketException;

    public void setKeepAlive(boolean on) throws SocketException;

    public boolean getKeepAlive() throws SocketException;

    public void setTrafficClass(int tc) throws SocketException;

    public int getTrafficClass() throws SocketException;

    public void setReuseAddress(boolean on) throws SocketException;

    public boolean getReuseAddress() throws SocketException;

    public synchronized void close() throws IOException;

    public void shutdownInput() throws IOException;

    public void shutdownOutput() throws IOException;

    public String toString();

    public boolean isConnected();

    public boolean isBound();

    public boolean isClosed();

    public boolean isInputShutdown();

    public boolean isOutputShutdown();

    public static synchronized void setSocketImplFactory(@Nullable SocketImplFactory fac) throws IOException;

    public void setPerformancePreferences(int connectionTime, int latency, int bandwidth);

    public <T> Socket setOption(SocketOption<T> name, T value) throws IOException;

    @SuppressWarnings("unchecked")
    public <T> T getOption(SocketOption<T> name) throws IOException;

    public Set<SocketOption<?>> supportedOptions();
}
