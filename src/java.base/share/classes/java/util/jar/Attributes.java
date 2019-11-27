/*
 * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
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
package java.util.jar;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.checkerframework.framework.qual.CFComment;
import sun.util.logging.PlatformLogger;

@CFComment({ "signature: ", "TODO: Attributes does not declare a toString method.", "This declaration then pollutes java.lang.Object.toString, making", "any override illegal.", "public class Attributes implements Map<Object,Object>, Cloneable {", "public @Interned String toString();", "}" })
@AnnotatedFor({ "nullness" })
public class Attributes implements Map<Object, Object>, Cloneable {

    protected Map<Object, Object> map;

    public Attributes() {
    }

    public Attributes(int size) {
    }

    public Attributes(Attributes attr) {
    }

    public Object get(Object name);

    public String getValue(String name);

    public String getValue(Name name);

    public Object put(Object name, Object value);

    public String putValue(String name, String value);

    public Object remove(Object name);

    public boolean containsValue(Object value);

    public boolean containsKey(Object name);

    public void putAll(Map<?, ?> attr);

    public void clear();

    public int size();

    public boolean isEmpty();

    public Set<Object> keySet();

    public Collection<Object> values();

    public Set<Map.Entry<Object, Object>> entrySet();

    public boolean equals(Object o);

    public int hashCode();

    public Object clone();

    public static class Name {

        public Name(String name) {
        }

        public boolean equals(Object o);

        public int hashCode();

        public String toString();

        public static final Name MANIFEST_VERSION;

        public static final Name SIGNATURE_VERSION;

        public static final Name CONTENT_TYPE;

        public static final Name CLASS_PATH;

        public static final Name MAIN_CLASS;

        public static final Name SEALED;

        public static final Name EXTENSION_LIST;

        public static final Name EXTENSION_NAME;

        @Deprecated
        public static final Name EXTENSION_INSTALLATION;

        public static final Name IMPLEMENTATION_TITLE;

        public static final Name IMPLEMENTATION_VERSION;

        public static final Name IMPLEMENTATION_VENDOR;

        @Deprecated
        public static final Name IMPLEMENTATION_VENDOR_ID;

        @Deprecated
        public static final Name IMPLEMENTATION_URL;

        public static final Name SPECIFICATION_TITLE;

        public static final Name SPECIFICATION_VERSION;

        public static final Name SPECIFICATION_VENDOR;

        public static final Name MULTI_RELEASE;
    }
}
