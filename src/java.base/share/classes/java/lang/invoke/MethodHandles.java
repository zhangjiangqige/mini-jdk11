/*
 * Copyright (c) 2008, 2018, Oracle and/or its affiliates. All rights reserved.
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
package java.lang.invoke;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.module.IllegalAccessLogger;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import jdk.internal.vm.annotation.ForceInline;
import sun.invoke.util.ValueConversions;
import sun.invoke.util.VerifyAccess;
import sun.invoke.util.Wrapper;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstants;
import java.lang.invoke.LambdaForm.BasicType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ReflectPermission;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.lang.invoke.MethodHandleImpl.Intrinsic;
import static java.lang.invoke.MethodHandleNatives.Constants.*;
import static java.lang.invoke.MethodHandleStatics.newIllegalArgumentException;
import static java.lang.invoke.MethodType.methodType;

public class MethodHandles {

    @CallerSensitive
    @ForceInline
    public static Lookup lookup();

    public static Lookup publicLookup();

    public static Lookup privateLookupIn(Class<?> targetClass, Lookup lookup) throws IllegalAccessException;

    public static <T extends Member> T reflectAs(Class<T> expected, MethodHandle target);

    public static final class Lookup {

        public static final int PUBLIC;

        public static final int PRIVATE;

        public static final int PROTECTED;

        public static final int PACKAGE;

        public static final int MODULE;

        public static final int UNCONDITIONAL;

        public Class<?> lookupClass();

        public int lookupModes();

        public Lookup in(Class<?> requestedLookupClass);

        public Lookup dropLookupMode(int modeToDrop);

        public Class<?> defineClass(byte[] bytes) throws IllegalAccessException;

        @Override
        public String toString();

        public MethodHandle findStatic(Class<?> refc, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException;

        public MethodHandle findVirtual(Class<?> refc, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException;

        public MethodHandle findConstructor(Class<?> refc, MethodType type) throws NoSuchMethodException, IllegalAccessException;

        public Class<?> findClass(String targetName) throws ClassNotFoundException, IllegalAccessException;

        public Class<?> accessClass(Class<?> targetClass) throws IllegalAccessException;

        public MethodHandle findSpecial(Class<?> refc, String name, MethodType type, Class<?> specialCaller) throws NoSuchMethodException, IllegalAccessException;

        public MethodHandle findGetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public MethodHandle findSetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public VarHandle findVarHandle(Class<?> recv, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public MethodHandle findStaticGetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public MethodHandle findStaticSetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public VarHandle findStaticVarHandle(Class<?> decl, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException;

        public MethodHandle bind(Object receiver, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException;

        public MethodHandle unreflect(Method m) throws IllegalAccessException;

        public MethodHandle unreflectSpecial(Method m, Class<?> specialCaller) throws IllegalAccessException;

        public MethodHandle unreflectConstructor(Constructor<?> c) throws IllegalAccessException;

        public MethodHandle unreflectGetter(Field f) throws IllegalAccessException;

        public MethodHandle unreflectSetter(Field f) throws IllegalAccessException;

        public VarHandle unreflectVarHandle(Field f) throws IllegalAccessException;

        public MethodHandleInfo revealDirect(MethodHandle target);

        public boolean hasPrivateAccess();
    }

    public static MethodHandle arrayConstructor(Class<?> arrayClass) throws IllegalArgumentException;

    public static MethodHandle arrayLength(Class<?> arrayClass) throws IllegalArgumentException;

    public static MethodHandle arrayElementGetter(Class<?> arrayClass) throws IllegalArgumentException;

    public static MethodHandle arrayElementSetter(Class<?> arrayClass) throws IllegalArgumentException;

    public static VarHandle arrayElementVarHandle(Class<?> arrayClass) throws IllegalArgumentException;

    public static VarHandle byteArrayViewVarHandle(Class<?> viewArrayClass, ByteOrder byteOrder) throws IllegalArgumentException;

    public static VarHandle byteBufferViewVarHandle(Class<?> viewArrayClass, ByteOrder byteOrder) throws IllegalArgumentException;

    public static MethodHandle spreadInvoker(MethodType type, int leadingArgCount);

    public static MethodHandle exactInvoker(MethodType type);

    public static MethodHandle invoker(MethodType type);

    static public MethodHandle varHandleExactInvoker(VarHandle.AccessMode accessMode, MethodType type);

    static public MethodHandle varHandleInvoker(VarHandle.AccessMode accessMode, MethodType type);

    public static MethodHandle explicitCastArguments(MethodHandle target, MethodType newType);

    public static MethodHandle permuteArguments(MethodHandle target, MethodType newType, int... reorder);

    public static MethodHandle constant(Class<?> type, Object value);

    public static MethodHandle identity(Class<?> type);

    public static MethodHandle zero(Class<?> type);

    public static MethodHandle empty(MethodType type);

    public static MethodHandle insertArguments(MethodHandle target, int pos, Object... values);

    public static MethodHandle dropArguments(MethodHandle target, int pos, List<Class<?>> valueTypes);

    public static MethodHandle dropArguments(MethodHandle target, int pos, Class<?>... valueTypes);

    public static MethodHandle dropArgumentsToMatch(MethodHandle target, int skip, List<Class<?>> newTypes, int pos);

    public static MethodHandle filterArguments(MethodHandle target, int pos, MethodHandle... filters);

    public static MethodHandle collectArguments(MethodHandle target, int pos, MethodHandle filter);

    public static MethodHandle filterReturnValue(MethodHandle target, MethodHandle filter);

    public static MethodHandle foldArguments(MethodHandle target, MethodHandle combiner);

    public static MethodHandle foldArguments(MethodHandle target, int pos, MethodHandle combiner);

    public static MethodHandle guardWithTest(MethodHandle test, MethodHandle target, MethodHandle fallback);

    public static MethodHandle catchException(MethodHandle target, Class<? extends Throwable> exType, MethodHandle handler);

    public static MethodHandle throwException(Class<?> returnType, Class<? extends Throwable> exType);

    public static MethodHandle loop(MethodHandle[]... clauses);

    public static MethodHandle whileLoop(MethodHandle init, MethodHandle pred, MethodHandle body);

    public static MethodHandle doWhileLoop(MethodHandle init, MethodHandle body, MethodHandle pred);

    public static MethodHandle countedLoop(MethodHandle iterations, MethodHandle init, MethodHandle body);

    public static MethodHandle countedLoop(MethodHandle start, MethodHandle end, MethodHandle init, MethodHandle body);

    public static MethodHandle iteratedLoop(MethodHandle iterator, MethodHandle init, MethodHandle body);

    public static MethodHandle tryFinally(MethodHandle target, MethodHandle cleanup);
}
