/*
 * Copyright (c) 1996, 2015, Oracle and/or its affiliates. All rights reserved.
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
package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import com.sun.beans.TypeResolver;
import com.sun.beans.WeakCache;
import com.sun.beans.finder.ClassFinder;
import com.sun.beans.introspect.ClassInfo;
import com.sun.beans.introspect.EventSetInfo;
import com.sun.beans.introspect.PropertyInfo;
import java.awt.Component;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.EventObject;
import java.util.List;
import java.util.TreeMap;
import jdk.internal.misc.JavaBeansAccess;
import jdk.internal.misc.SharedSecrets;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Introspector {

    public static final int USE_ALL_BEANINFO;

    public static final int IGNORE_IMMEDIATE_BEANINFO;

    public static final int IGNORE_ALL_BEANINFO;

    public static BeanInfo getBeanInfo(Class<?> beanClass) throws IntrospectionException;

    public static BeanInfo getBeanInfo(Class<?> beanClass, int flags) throws IntrospectionException;

    public static BeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass) throws IntrospectionException;

    public static BeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass, int flags) throws IntrospectionException;

    public static String decapitalize(String name);

    public static String[] getBeanInfoSearchPath();

    public static void setBeanInfoSearchPath(String[] path);

    public static void flushCaches();

    public static void flushFromCaches(Class<?> clz);
}
