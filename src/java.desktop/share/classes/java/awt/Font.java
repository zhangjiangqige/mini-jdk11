/*
 * Copyright (c) 1995, 2017, Oracle and/or its affiliates. All rights reserved.
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
package java.awt;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.peer.FontPeer;
import java.io.*;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.text.AttributedCharacterIterator.Attribute;
import java.text.CharacterIterator;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import sun.awt.ComponentFactory;
import sun.font.StandardGlyphVector;
import sun.font.AttributeMap;
import sun.font.AttributeValues;
import sun.font.CompositeFont;
import sun.font.CreatedFontTracker;
import sun.font.Font2D;
import sun.font.Font2DHandle;
import sun.font.FontAccess;
import sun.font.FontDesignMetrics;
import sun.font.FontManager;
import sun.font.FontManagerFactory;
import sun.font.FontUtilities;
import sun.font.GlyphLayout;
import sun.font.FontLineMetrics;
import sun.font.CoreMetrics;
import static sun.font.EAttribute.*;

@AnnotatedFor({ "interning" })
public class Font implements java.io.Serializable {

    @Interned
    public static final String DIALOG;

    @Interned
    public static final String DIALOG_INPUT;

    @Interned
    public static final String SANS_SERIF;

    @Interned
    public static final String SERIF;

    @Interned
    public static final String MONOSPACED;

    public static final int PLAIN;

    public static final int BOLD;

    public static final int ITALIC;

    public static final int ROMAN_BASELINE;

    public static final int CENTER_BASELINE;

    public static final int HANGING_BASELINE;

    public static final int TRUETYPE_FONT;

    public static final int TYPE1_FONT;

    protected String name;

    protected int style;

    protected int size;

    protected float pointSize;

    public Font(String name, int style, int size) {
    }

    public Font(Map<? extends Attribute, ?> attributes) {
    }

    protected Font(Font font) {
    }

    public static boolean textRequiresLayout(char[] chars, int start, int end);

    public static Font getFont(Map<? extends Attribute, ?> attributes);

    public static Font[] createFonts(InputStream fontStream) throws FontFormatException, IOException;

    public static Font[] createFonts(File fontFile) throws FontFormatException, IOException;

    public static Font createFont(int fontFormat, InputStream fontStream) throws java.awt.FontFormatException, java.io.IOException;

    public static Font createFont(int fontFormat, File fontFile) throws java.awt.FontFormatException, java.io.IOException;

    public AffineTransform getTransform();

    public String getFamily();

    public String getFamily(Locale l);

    public String getPSName();

    public String getName();

    public String getFontName();

    public String getFontName(Locale l);

    public int getStyle();

    public int getSize();

    public float getSize2D();

    public boolean isPlain();

    public boolean isBold();

    public boolean isItalic();

    public boolean isTransformed();

    public boolean hasLayoutAttributes();

    public static Font getFont(String nm);

    public static Font decode(String str);

    public static Font getFont(String nm, Font font);

    public int hashCode();

    public boolean equals(Object obj);

    public String toString();

    public int getNumGlyphs();

    public int getMissingGlyphCode();

    public byte getBaselineFor(char c);

    public Map<TextAttribute, ?> getAttributes();

    public Attribute[] getAvailableAttributes();

    public Font deriveFont(int style, float size);

    public Font deriveFont(int style, AffineTransform trans);

    public Font deriveFont(float size);

    public Font deriveFont(AffineTransform trans);

    public Font deriveFont(int style);

    public Font deriveFont(Map<? extends Attribute, ?> attributes);

    public boolean canDisplay(char c);

    public boolean canDisplay(int codePoint);

    public int canDisplayUpTo(String str);

    public int canDisplayUpTo(char[] text, int start, int limit);

    public int canDisplayUpTo(CharacterIterator iter, int start, int limit);

    public float getItalicAngle();

    public boolean hasUniformLineMetrics();

    public LineMetrics getLineMetrics(String str, FontRenderContext frc);

    public LineMetrics getLineMetrics(String str, int beginIndex, int limit, FontRenderContext frc);

    public LineMetrics getLineMetrics(char[] chars, int beginIndex, int limit, FontRenderContext frc);

    public LineMetrics getLineMetrics(CharacterIterator ci, int beginIndex, int limit, FontRenderContext frc);

    public Rectangle2D getStringBounds(String str, FontRenderContext frc);

    public Rectangle2D getStringBounds(String str, int beginIndex, int limit, FontRenderContext frc);

    public Rectangle2D getStringBounds(char[] chars, int beginIndex, int limit, FontRenderContext frc);

    public Rectangle2D getStringBounds(CharacterIterator ci, int beginIndex, int limit, FontRenderContext frc);

    public Rectangle2D getMaxCharBounds(FontRenderContext frc);

    public GlyphVector createGlyphVector(FontRenderContext frc, String str);

    public GlyphVector createGlyphVector(FontRenderContext frc, char[] chars);

    public GlyphVector createGlyphVector(FontRenderContext frc, CharacterIterator ci);

    public GlyphVector createGlyphVector(FontRenderContext frc, int[] glyphCodes);

    public GlyphVector layoutGlyphVector(FontRenderContext frc, char[] text, int start, int limit, int flags);

    public static final int LAYOUT_LEFT_TO_RIGHT;

    public static final int LAYOUT_RIGHT_TO_LEFT;

    public static final int LAYOUT_NO_START_CONTEXT;

    public static final int LAYOUT_NO_LIMIT_CONTEXT;
}
