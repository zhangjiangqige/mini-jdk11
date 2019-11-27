/*
 * Copyright (c) 1996, 2018, Oracle and/or its affiliates. All rights reserved.
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
package java.text;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.text.spi.DateFormatSymbolsProvider;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sun.util.locale.provider.CalendarDataUtility;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.LocaleServiceProviderPool;
import sun.util.locale.provider.ResourceBundleBasedAdapter;
import sun.util.locale.provider.TimeZoneNameUtility;

@AnnotatedFor({ "index" })
public class DateFormatSymbols implements Serializable, Cloneable {

    public DateFormatSymbols() {
    }

    public DateFormatSymbols(Locale locale) {
    }

    public static Locale[] getAvailableLocales();

    public static final DateFormatSymbols getInstance();

    public static final DateFormatSymbols getInstance(Locale locale);

    public String[] getEras();

    public void setEras(String[] newEras);

    public String[] getMonths();

    public void setMonths(String[] newMonths);

    public String[] getShortMonths();

    public void setShortMonths(String[] newShortMonths);

    public String[] getWeekdays();

    public void setWeekdays(String[] newWeekdays);

    public String[] getShortWeekdays();

    public void setShortWeekdays(String[] newShortWeekdays);

    public String[] getAmPmStrings();

    public void setAmPmStrings(String[] newAmpms);

    public String[][] getZoneStrings();

    public void setZoneStrings(String[][] newZoneStrings);

    public String getLocalPatternChars();

    public void setLocalPatternChars(String newLocalPatternChars);

    public Object clone();

    @Override
    public int hashCode();

    public boolean equals(Object obj);
}
