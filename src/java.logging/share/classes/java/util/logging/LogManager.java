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
package java.util.logging;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.util.*;
import java.security.*;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jdk.internal.misc.JavaAWTAccess;
import jdk.internal.misc.SharedSecrets;
import sun.util.logging.internal.LoggingProviderImpl;
import static jdk.internal.logger.DefaultLoggerFinder.isSystem;

@AnnotatedFor({ "index", "interning", "signature" })
@UsesObjectEquals
public class LogManager {

    protected LogManager() {
    }

    public static LogManager getLogManager();

    public boolean addLogger(Logger logger);

    public Logger getLogger(String name);

    public Enumeration<String> getLoggerNames();

    public void readConfiguration() throws IOException, SecurityException;

    public void reset() throws SecurityException;

    public void readConfiguration(InputStream ins) throws IOException, SecurityException;

    public void updateConfiguration(Function<String, BiFunction<String, String, String>> mapper) throws IOException;

    public void updateConfiguration(InputStream ins, Function<String, BiFunction<String, String, String>> mapper) throws IOException;

    public String getProperty(String name);

    public void checkAccess() throws SecurityException;

    public final static String LOGGING_MXBEAN_NAME;

    @Deprecated()
    public static synchronized LoggingMXBean getLoggingMXBean();

    public LogManager addConfigurationListener(Runnable listener);

    public void removeConfigurationListener(Runnable listener);
}
