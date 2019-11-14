package javax.swing.text;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.Icon;

@AnnotatedFor({ "interning" })
public class StyleConstants {

    @Interned
    public static final String ComponentElementName = "component";

    @Interned
    public static final String IconElementName = "icon";

    public static final Object NameAttribute = new StyleConstants("name");

    public static final Object ResolveAttribute = new StyleConstants("resolver");

    public static final Object ModelAttribute = new StyleConstants("model");

    public String toString();

    public static final Object BidiLevel = new CharacterConstants("bidiLevel");

    public static final Object FontFamily = new FontConstants("family");

    public static final Object Family = FontFamily;

    public static final Object FontSize = new FontConstants("size");

    public static final Object Size = FontSize;

    public static final Object Bold = new FontConstants("bold");

    public static final Object Italic = new FontConstants("italic");

    public static final Object Underline = new CharacterConstants("underline");

    public static final Object StrikeThrough = new CharacterConstants("strikethrough");

    public static final Object Superscript = new CharacterConstants("superscript");

    public static final Object Subscript = new CharacterConstants("subscript");

    public static final Object Foreground = new ColorConstants("foreground");

    public static final Object Background = new ColorConstants("background");

    public static final Object ComponentAttribute = new CharacterConstants("component");

    public static final Object IconAttribute = new CharacterConstants("icon");

    public static final Object ComposedTextAttribute = new StyleConstants("composed text");

    public static final Object FirstLineIndent = new ParagraphConstants("FirstLineIndent");

    public static final Object LeftIndent = new ParagraphConstants("LeftIndent");

    public static final Object RightIndent = new ParagraphConstants("RightIndent");

    public static final Object LineSpacing = new ParagraphConstants("LineSpacing");

    public static final Object SpaceAbove = new ParagraphConstants("SpaceAbove");

    public static final Object SpaceBelow = new ParagraphConstants("SpaceBelow");

    public static final Object Alignment = new ParagraphConstants("Alignment");

    public static final Object TabSet = new ParagraphConstants("TabSet");

    public static final Object Orientation = new ParagraphConstants("Orientation");

    public static final int ALIGN_LEFT = 0;

    public static final int ALIGN_CENTER = 1;

    public static final int ALIGN_RIGHT = 2;

    public static final int ALIGN_JUSTIFIED = 3;

    public static int getBidiLevel(AttributeSet a);

    public static void setBidiLevel(MutableAttributeSet a, int o);

    public static Component getComponent(AttributeSet a);

    public static void setComponent(MutableAttributeSet a, Component c);

    public static Icon getIcon(AttributeSet a);

    public static void setIcon(MutableAttributeSet a, Icon c);

    public static String getFontFamily(AttributeSet a);

    public static void setFontFamily(MutableAttributeSet a, String fam);

    public static int getFontSize(AttributeSet a);

    public static void setFontSize(MutableAttributeSet a, int s);

    public static boolean isBold(AttributeSet a);

    public static void setBold(MutableAttributeSet a, boolean b);

    public static boolean isItalic(AttributeSet a);

    public static void setItalic(MutableAttributeSet a, boolean b);

    public static boolean isUnderline(AttributeSet a);

    public static boolean isStrikeThrough(AttributeSet a);

    public static boolean isSuperscript(AttributeSet a);

    public static boolean isSubscript(AttributeSet a);

    public static void setUnderline(MutableAttributeSet a, boolean b);

    public static void setStrikeThrough(MutableAttributeSet a, boolean b);

    public static void setSuperscript(MutableAttributeSet a, boolean b);

    public static void setSubscript(MutableAttributeSet a, boolean b);

    public static Color getForeground(AttributeSet a);

    public static void setForeground(MutableAttributeSet a, Color fg);

    public static Color getBackground(AttributeSet a);

    public static void setBackground(MutableAttributeSet a, Color fg);

    public static float getFirstLineIndent(AttributeSet a);

    public static void setFirstLineIndent(MutableAttributeSet a, float i);

    public static float getRightIndent(AttributeSet a);

    public static void setRightIndent(MutableAttributeSet a, float i);

    public static float getLeftIndent(AttributeSet a);

    public static void setLeftIndent(MutableAttributeSet a, float i);

    public static float getLineSpacing(AttributeSet a);

    public static void setLineSpacing(MutableAttributeSet a, float i);

    public static float getSpaceAbove(AttributeSet a);

    public static void setSpaceAbove(MutableAttributeSet a, float i);

    public static float getSpaceBelow(AttributeSet a);

    public static void setSpaceBelow(MutableAttributeSet a, float i);

    public static int getAlignment(AttributeSet a);

    public static void setAlignment(MutableAttributeSet a, int align);

    public static TabSet getTabSet(AttributeSet a);

    public static void setTabSet(MutableAttributeSet a, TabSet tabs);

    static Object[] keys = { NameAttribute, ResolveAttribute, BidiLevel, FontFamily, FontSize, Bold, Italic, Underline, StrikeThrough, Superscript, Subscript, Foreground, Background, ComponentAttribute, IconAttribute, FirstLineIndent, LeftIndent, RightIndent, LineSpacing, SpaceAbove, SpaceBelow, Alignment, TabSet, Orientation, ModelAttribute, ComposedTextAttribute };

    StyleConstants(String representation) {
        this.representation = representation;
    }

    private String representation;

    public static class ParagraphConstants extends StyleConstants implements AttributeSet.ParagraphAttribute {

        private ParagraphConstants(String representation) {
            super(representation);
        }
    }

    public static class CharacterConstants extends StyleConstants implements AttributeSet.CharacterAttribute {

        private CharacterConstants(String representation) {
            super(representation);
        }
    }

    public static class ColorConstants extends StyleConstants implements AttributeSet.ColorAttribute, AttributeSet.CharacterAttribute {

        private ColorConstants(String representation) {
            super(representation);
        }
    }

    public static class FontConstants extends StyleConstants implements AttributeSet.FontAttribute, AttributeSet.CharacterAttribute {

        private FontConstants(String representation) {
            super(representation);
        }
    }
}
