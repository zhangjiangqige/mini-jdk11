package java.lang;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Locale;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "index", "interning", "nullness" })
public final class Character implements java.io.Serializable, Comparable<Character> {

    @Positive
    public static final int MIN_RADIX = 2;

    @Positive
    public static final int MAX_RADIX = 36;

    public static final char MIN_VALUE = '\u0000';

    public static final char MAX_VALUE = '\uFFFF';

    @SuppressWarnings("unchecked")
    public static final Class<Character> TYPE = (Class<Character>) Class.getPrimitiveClass("char");

    public static final byte UNASSIGNED = 0;

    public static final byte UPPERCASE_LETTER = 1;

    public static final byte LOWERCASE_LETTER = 2;

    public static final byte TITLECASE_LETTER = 3;

    public static final byte MODIFIER_LETTER = 4;

    public static final byte OTHER_LETTER = 5;

    public static final byte NON_SPACING_MARK = 6;

    public static final byte ENCLOSING_MARK = 7;

    public static final byte COMBINING_SPACING_MARK = 8;

    public static final byte DECIMAL_DIGIT_NUMBER = 9;

    public static final byte LETTER_NUMBER = 10;

    public static final byte OTHER_NUMBER = 11;

    public static final byte SPACE_SEPARATOR = 12;

    public static final byte LINE_SEPARATOR = 13;

    public static final byte PARAGRAPH_SEPARATOR = 14;

    public static final byte CONTROL = 15;

    public static final byte FORMAT = 16;

    public static final byte PRIVATE_USE = 18;

    public static final byte SURROGATE = 19;

    public static final byte DASH_PUNCTUATION = 20;

    public static final byte START_PUNCTUATION = 21;

    public static final byte END_PUNCTUATION = 22;

    public static final byte CONNECTOR_PUNCTUATION = 23;

    public static final byte OTHER_PUNCTUATION = 24;

    public static final byte MATH_SYMBOL = 25;

    public static final byte CURRENCY_SYMBOL = 26;

    public static final byte MODIFIER_SYMBOL = 27;

    public static final byte OTHER_SYMBOL = 28;

    public static final byte INITIAL_QUOTE_PUNCTUATION = 29;

    public static final byte FINAL_QUOTE_PUNCTUATION = 30;

    static final int ERROR = 0xFFFFFFFF;

    public static final byte DIRECTIONALITY_UNDEFINED = -1;

    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT = 0;

    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT = 1;

    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = 2;

    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER = 3;

    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR = 4;

    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR = 5;

    public static final byte DIRECTIONALITY_ARABIC_NUMBER = 6;

    public static final byte DIRECTIONALITY_COMMON_NUMBER_SEPARATOR = 7;

    public static final byte DIRECTIONALITY_NONSPACING_MARK = 8;

    public static final byte DIRECTIONALITY_BOUNDARY_NEUTRAL = 9;

    public static final byte DIRECTIONALITY_PARAGRAPH_SEPARATOR = 10;

    public static final byte DIRECTIONALITY_SEGMENT_SEPARATOR = 11;

    public static final byte DIRECTIONALITY_WHITESPACE = 12;

    public static final byte DIRECTIONALITY_OTHER_NEUTRALS = 13;

    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = 14;

    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = 15;

    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = 16;

    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = 17;

    public static final byte DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = 18;

    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_ISOLATE = 19;

    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_ISOLATE = 20;

    public static final byte DIRECTIONALITY_FIRST_STRONG_ISOLATE = 21;

    public static final byte DIRECTIONALITY_POP_DIRECTIONAL_ISOLATE = 22;

    public static final char MIN_HIGH_SURROGATE = '\uD800';

    public static final char MAX_HIGH_SURROGATE = '\uDBFF';

    public static final char MIN_LOW_SURROGATE = '\uDC00';

    public static final char MAX_LOW_SURROGATE = '\uDFFF';

    public static final char MIN_SURROGATE = MIN_HIGH_SURROGATE;

    public static final char MAX_SURROGATE = MAX_LOW_SURROGATE;

    public static final int MIN_SUPPLEMENTARY_CODE_POINT = 0x010000;

    public static final int MIN_CODE_POINT = 0x000000;

    public static final int MAX_CODE_POINT = 0X10FFFF;

    public static class Subset {

        private String name;

        protected Subset(String name) {
            if (name == null) {
                throw new NullPointerException("name");
            }
            this.name = name;
        }

        @Pure
        public final boolean equals(@Nullable Object obj);

        @Pure
        public final int hashCode();

        @SideEffectFree
        public final String toString();
    }

    @Interned
    public static final class UnicodeBlock extends Subset {

        private static Map<String, UnicodeBlock> map = new HashMap<>((int) (638 / 0.75f + 1.0f));

        private UnicodeBlock(String idName) {
            super(idName);
            map.put(idName, this);
        }

        private UnicodeBlock(String idName, String alias) {
            this(idName);
            map.put(alias, this);
        }

        private UnicodeBlock(String idName, String... aliases) {
            this(idName);
            for (String alias : aliases) map.put(alias, this);
        }

        public static final UnicodeBlock BASIC_LATIN = new UnicodeBlock("BASIC_LATIN", "BASIC LATIN", "BASICLATIN");

        public static final UnicodeBlock LATIN_1_SUPPLEMENT = new UnicodeBlock("LATIN_1_SUPPLEMENT", "LATIN-1 SUPPLEMENT", "LATIN-1SUPPLEMENT");

        public static final UnicodeBlock LATIN_EXTENDED_A = new UnicodeBlock("LATIN_EXTENDED_A", "LATIN EXTENDED-A", "LATINEXTENDED-A");

        public static final UnicodeBlock LATIN_EXTENDED_B = new UnicodeBlock("LATIN_EXTENDED_B", "LATIN EXTENDED-B", "LATINEXTENDED-B");

        public static final UnicodeBlock IPA_EXTENSIONS = new UnicodeBlock("IPA_EXTENSIONS", "IPA EXTENSIONS", "IPAEXTENSIONS");

        public static final UnicodeBlock SPACING_MODIFIER_LETTERS = new UnicodeBlock("SPACING_MODIFIER_LETTERS", "SPACING MODIFIER LETTERS", "SPACINGMODIFIERLETTERS");

        public static final UnicodeBlock COMBINING_DIACRITICAL_MARKS = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS", "COMBINING DIACRITICAL MARKS", "COMBININGDIACRITICALMARKS");

        public static final UnicodeBlock GREEK = new UnicodeBlock("GREEK", "GREEK AND COPTIC", "GREEKANDCOPTIC");

        public static final UnicodeBlock CYRILLIC = new UnicodeBlock("CYRILLIC");

        public static final UnicodeBlock ARMENIAN = new UnicodeBlock("ARMENIAN");

        public static final UnicodeBlock HEBREW = new UnicodeBlock("HEBREW");

        public static final UnicodeBlock ARABIC = new UnicodeBlock("ARABIC");

        public static final UnicodeBlock DEVANAGARI = new UnicodeBlock("DEVANAGARI");

        public static final UnicodeBlock BENGALI = new UnicodeBlock("BENGALI");

        public static final UnicodeBlock GURMUKHI = new UnicodeBlock("GURMUKHI");

        public static final UnicodeBlock GUJARATI = new UnicodeBlock("GUJARATI");

        public static final UnicodeBlock ORIYA = new UnicodeBlock("ORIYA");

        public static final UnicodeBlock TAMIL = new UnicodeBlock("TAMIL");

        public static final UnicodeBlock TELUGU = new UnicodeBlock("TELUGU");

        public static final UnicodeBlock KANNADA = new UnicodeBlock("KANNADA");

        public static final UnicodeBlock MALAYALAM = new UnicodeBlock("MALAYALAM");

        public static final UnicodeBlock THAI = new UnicodeBlock("THAI");

        public static final UnicodeBlock LAO = new UnicodeBlock("LAO");

        public static final UnicodeBlock TIBETAN = new UnicodeBlock("TIBETAN");

        public static final UnicodeBlock GEORGIAN = new UnicodeBlock("GEORGIAN");

        public static final UnicodeBlock HANGUL_JAMO = new UnicodeBlock("HANGUL_JAMO", "HANGUL JAMO", "HANGULJAMO");

        public static final UnicodeBlock LATIN_EXTENDED_ADDITIONAL = new UnicodeBlock("LATIN_EXTENDED_ADDITIONAL", "LATIN EXTENDED ADDITIONAL", "LATINEXTENDEDADDITIONAL");

        public static final UnicodeBlock GREEK_EXTENDED = new UnicodeBlock("GREEK_EXTENDED", "GREEK EXTENDED", "GREEKEXTENDED");

        public static final UnicodeBlock GENERAL_PUNCTUATION = new UnicodeBlock("GENERAL_PUNCTUATION", "GENERAL PUNCTUATION", "GENERALPUNCTUATION");

        public static final UnicodeBlock SUPERSCRIPTS_AND_SUBSCRIPTS = new UnicodeBlock("SUPERSCRIPTS_AND_SUBSCRIPTS", "SUPERSCRIPTS AND SUBSCRIPTS", "SUPERSCRIPTSANDSUBSCRIPTS");

        public static final UnicodeBlock CURRENCY_SYMBOLS = new UnicodeBlock("CURRENCY_SYMBOLS", "CURRENCY SYMBOLS", "CURRENCYSYMBOLS");

        public static final UnicodeBlock COMBINING_MARKS_FOR_SYMBOLS = new UnicodeBlock("COMBINING_MARKS_FOR_SYMBOLS", "COMBINING DIACRITICAL MARKS FOR SYMBOLS", "COMBININGDIACRITICALMARKSFORSYMBOLS", "COMBINING MARKS FOR SYMBOLS", "COMBININGMARKSFORSYMBOLS");

        public static final UnicodeBlock LETTERLIKE_SYMBOLS = new UnicodeBlock("LETTERLIKE_SYMBOLS", "LETTERLIKE SYMBOLS", "LETTERLIKESYMBOLS");

        public static final UnicodeBlock NUMBER_FORMS = new UnicodeBlock("NUMBER_FORMS", "NUMBER FORMS", "NUMBERFORMS");

        public static final UnicodeBlock ARROWS = new UnicodeBlock("ARROWS");

        public static final UnicodeBlock MATHEMATICAL_OPERATORS = new UnicodeBlock("MATHEMATICAL_OPERATORS", "MATHEMATICAL OPERATORS", "MATHEMATICALOPERATORS");

        public static final UnicodeBlock MISCELLANEOUS_TECHNICAL = new UnicodeBlock("MISCELLANEOUS_TECHNICAL", "MISCELLANEOUS TECHNICAL", "MISCELLANEOUSTECHNICAL");

        public static final UnicodeBlock CONTROL_PICTURES = new UnicodeBlock("CONTROL_PICTURES", "CONTROL PICTURES", "CONTROLPICTURES");

        public static final UnicodeBlock OPTICAL_CHARACTER_RECOGNITION = new UnicodeBlock("OPTICAL_CHARACTER_RECOGNITION", "OPTICAL CHARACTER RECOGNITION", "OPTICALCHARACTERRECOGNITION");

        public static final UnicodeBlock ENCLOSED_ALPHANUMERICS = new UnicodeBlock("ENCLOSED_ALPHANUMERICS", "ENCLOSED ALPHANUMERICS", "ENCLOSEDALPHANUMERICS");

        public static final UnicodeBlock BOX_DRAWING = new UnicodeBlock("BOX_DRAWING", "BOX DRAWING", "BOXDRAWING");

        public static final UnicodeBlock BLOCK_ELEMENTS = new UnicodeBlock("BLOCK_ELEMENTS", "BLOCK ELEMENTS", "BLOCKELEMENTS");

        public static final UnicodeBlock GEOMETRIC_SHAPES = new UnicodeBlock("GEOMETRIC_SHAPES", "GEOMETRIC SHAPES", "GEOMETRICSHAPES");

        public static final UnicodeBlock MISCELLANEOUS_SYMBOLS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS", "MISCELLANEOUS SYMBOLS", "MISCELLANEOUSSYMBOLS");

        public static final UnicodeBlock DINGBATS = new UnicodeBlock("DINGBATS");

        public static final UnicodeBlock CJK_SYMBOLS_AND_PUNCTUATION = new UnicodeBlock("CJK_SYMBOLS_AND_PUNCTUATION", "CJK SYMBOLS AND PUNCTUATION", "CJKSYMBOLSANDPUNCTUATION");

        public static final UnicodeBlock HIRAGANA = new UnicodeBlock("HIRAGANA");

        public static final UnicodeBlock KATAKANA = new UnicodeBlock("KATAKANA");

        public static final UnicodeBlock BOPOMOFO = new UnicodeBlock("BOPOMOFO");

        public static final UnicodeBlock HANGUL_COMPATIBILITY_JAMO = new UnicodeBlock("HANGUL_COMPATIBILITY_JAMO", "HANGUL COMPATIBILITY JAMO", "HANGULCOMPATIBILITYJAMO");

        public static final UnicodeBlock KANBUN = new UnicodeBlock("KANBUN");

        public static final UnicodeBlock ENCLOSED_CJK_LETTERS_AND_MONTHS = new UnicodeBlock("ENCLOSED_CJK_LETTERS_AND_MONTHS", "ENCLOSED CJK LETTERS AND MONTHS", "ENCLOSEDCJKLETTERSANDMONTHS");

        public static final UnicodeBlock CJK_COMPATIBILITY = new UnicodeBlock("CJK_COMPATIBILITY", "CJK COMPATIBILITY", "CJKCOMPATIBILITY");

        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS", "CJK UNIFIED IDEOGRAPHS", "CJKUNIFIEDIDEOGRAPHS");

        public static final UnicodeBlock HANGUL_SYLLABLES = new UnicodeBlock("HANGUL_SYLLABLES", "HANGUL SYLLABLES", "HANGULSYLLABLES");

        public static final UnicodeBlock PRIVATE_USE_AREA = new UnicodeBlock("PRIVATE_USE_AREA", "PRIVATE USE AREA", "PRIVATEUSEAREA");

        public static final UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS = new UnicodeBlock("CJK_COMPATIBILITY_IDEOGRAPHS", "CJK COMPATIBILITY IDEOGRAPHS", "CJKCOMPATIBILITYIDEOGRAPHS");

        public static final UnicodeBlock ALPHABETIC_PRESENTATION_FORMS = new UnicodeBlock("ALPHABETIC_PRESENTATION_FORMS", "ALPHABETIC PRESENTATION FORMS", "ALPHABETICPRESENTATIONFORMS");

        public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_A = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_A", "ARABIC PRESENTATION FORMS-A", "ARABICPRESENTATIONFORMS-A");

        public static final UnicodeBlock COMBINING_HALF_MARKS = new UnicodeBlock("COMBINING_HALF_MARKS", "COMBINING HALF MARKS", "COMBININGHALFMARKS");

        public static final UnicodeBlock CJK_COMPATIBILITY_FORMS = new UnicodeBlock("CJK_COMPATIBILITY_FORMS", "CJK COMPATIBILITY FORMS", "CJKCOMPATIBILITYFORMS");

        public static final UnicodeBlock SMALL_FORM_VARIANTS = new UnicodeBlock("SMALL_FORM_VARIANTS", "SMALL FORM VARIANTS", "SMALLFORMVARIANTS");

        public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_B = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_B", "ARABIC PRESENTATION FORMS-B", "ARABICPRESENTATIONFORMS-B");

        public static final UnicodeBlock HALFWIDTH_AND_FULLWIDTH_FORMS = new UnicodeBlock("HALFWIDTH_AND_FULLWIDTH_FORMS", "HALFWIDTH AND FULLWIDTH FORMS", "HALFWIDTHANDFULLWIDTHFORMS");

        public static final UnicodeBlock SPECIALS = new UnicodeBlock("SPECIALS");

        @Deprecated(since = "1.5")
        public static final UnicodeBlock SURROGATES_AREA = new UnicodeBlock("SURROGATES_AREA");

        public static final UnicodeBlock SYRIAC = new UnicodeBlock("SYRIAC");

        public static final UnicodeBlock THAANA = new UnicodeBlock("THAANA");

        public static final UnicodeBlock SINHALA = new UnicodeBlock("SINHALA");

        public static final UnicodeBlock MYANMAR = new UnicodeBlock("MYANMAR");

        public static final UnicodeBlock ETHIOPIC = new UnicodeBlock("ETHIOPIC");

        public static final UnicodeBlock CHEROKEE = new UnicodeBlock("CHEROKEE");

        public static final UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS = new UnicodeBlock("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS", "UNIFIED CANADIAN ABORIGINAL SYLLABICS", "UNIFIEDCANADIANABORIGINALSYLLABICS");

        public static final UnicodeBlock OGHAM = new UnicodeBlock("OGHAM");

        public static final UnicodeBlock RUNIC = new UnicodeBlock("RUNIC");

        public static final UnicodeBlock KHMER = new UnicodeBlock("KHMER");

        public static final UnicodeBlock MONGOLIAN = new UnicodeBlock("MONGOLIAN");

        public static final UnicodeBlock BRAILLE_PATTERNS = new UnicodeBlock("BRAILLE_PATTERNS", "BRAILLE PATTERNS", "BRAILLEPATTERNS");

        public static final UnicodeBlock CJK_RADICALS_SUPPLEMENT = new UnicodeBlock("CJK_RADICALS_SUPPLEMENT", "CJK RADICALS SUPPLEMENT", "CJKRADICALSSUPPLEMENT");

        public static final UnicodeBlock KANGXI_RADICALS = new UnicodeBlock("KANGXI_RADICALS", "KANGXI RADICALS", "KANGXIRADICALS");

        public static final UnicodeBlock IDEOGRAPHIC_DESCRIPTION_CHARACTERS = new UnicodeBlock("IDEOGRAPHIC_DESCRIPTION_CHARACTERS", "IDEOGRAPHIC DESCRIPTION CHARACTERS", "IDEOGRAPHICDESCRIPTIONCHARACTERS");

        public static final UnicodeBlock BOPOMOFO_EXTENDED = new UnicodeBlock("BOPOMOFO_EXTENDED", "BOPOMOFO EXTENDED", "BOPOMOFOEXTENDED");

        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A", "CJK UNIFIED IDEOGRAPHS EXTENSION A", "CJKUNIFIEDIDEOGRAPHSEXTENSIONA");

        public static final UnicodeBlock YI_SYLLABLES = new UnicodeBlock("YI_SYLLABLES", "YI SYLLABLES", "YISYLLABLES");

        public static final UnicodeBlock YI_RADICALS = new UnicodeBlock("YI_RADICALS", "YI RADICALS", "YIRADICALS");

        public static final UnicodeBlock CYRILLIC_SUPPLEMENTARY = new UnicodeBlock("CYRILLIC_SUPPLEMENTARY", "CYRILLIC SUPPLEMENTARY", "CYRILLICSUPPLEMENTARY", "CYRILLIC SUPPLEMENT", "CYRILLICSUPPLEMENT");

        public static final UnicodeBlock TAGALOG = new UnicodeBlock("TAGALOG");

        public static final UnicodeBlock HANUNOO = new UnicodeBlock("HANUNOO");

        public static final UnicodeBlock BUHID = new UnicodeBlock("BUHID");

        public static final UnicodeBlock TAGBANWA = new UnicodeBlock("TAGBANWA");

        public static final UnicodeBlock LIMBU = new UnicodeBlock("LIMBU");

        public static final UnicodeBlock TAI_LE = new UnicodeBlock("TAI_LE", "TAI LE", "TAILE");

        public static final UnicodeBlock KHMER_SYMBOLS = new UnicodeBlock("KHMER_SYMBOLS", "KHMER SYMBOLS", "KHMERSYMBOLS");

        public static final UnicodeBlock PHONETIC_EXTENSIONS = new UnicodeBlock("PHONETIC_EXTENSIONS", "PHONETIC EXTENSIONS", "PHONETICEXTENSIONS");

        public static final UnicodeBlock MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A = new UnicodeBlock("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A", "MISCELLANEOUS MATHEMATICAL SYMBOLS-A", "MISCELLANEOUSMATHEMATICALSYMBOLS-A");

        public static final UnicodeBlock SUPPLEMENTAL_ARROWS_A = new UnicodeBlock("SUPPLEMENTAL_ARROWS_A", "SUPPLEMENTAL ARROWS-A", "SUPPLEMENTALARROWS-A");

        public static final UnicodeBlock SUPPLEMENTAL_ARROWS_B = new UnicodeBlock("SUPPLEMENTAL_ARROWS_B", "SUPPLEMENTAL ARROWS-B", "SUPPLEMENTALARROWS-B");

        public static final UnicodeBlock MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B = new UnicodeBlock("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B", "MISCELLANEOUS MATHEMATICAL SYMBOLS-B", "MISCELLANEOUSMATHEMATICALSYMBOLS-B");

        public static final UnicodeBlock SUPPLEMENTAL_MATHEMATICAL_OPERATORS = new UnicodeBlock("SUPPLEMENTAL_MATHEMATICAL_OPERATORS", "SUPPLEMENTAL MATHEMATICAL OPERATORS", "SUPPLEMENTALMATHEMATICALOPERATORS");

        public static final UnicodeBlock MISCELLANEOUS_SYMBOLS_AND_ARROWS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS_AND_ARROWS", "MISCELLANEOUS SYMBOLS AND ARROWS", "MISCELLANEOUSSYMBOLSANDARROWS");

        public static final UnicodeBlock KATAKANA_PHONETIC_EXTENSIONS = new UnicodeBlock("KATAKANA_PHONETIC_EXTENSIONS", "KATAKANA PHONETIC EXTENSIONS", "KATAKANAPHONETICEXTENSIONS");

        public static final UnicodeBlock YIJING_HEXAGRAM_SYMBOLS = new UnicodeBlock("YIJING_HEXAGRAM_SYMBOLS", "YIJING HEXAGRAM SYMBOLS", "YIJINGHEXAGRAMSYMBOLS");

        public static final UnicodeBlock VARIATION_SELECTORS = new UnicodeBlock("VARIATION_SELECTORS", "VARIATION SELECTORS", "VARIATIONSELECTORS");

        public static final UnicodeBlock LINEAR_B_SYLLABARY = new UnicodeBlock("LINEAR_B_SYLLABARY", "LINEAR B SYLLABARY", "LINEARBSYLLABARY");

        public static final UnicodeBlock LINEAR_B_IDEOGRAMS = new UnicodeBlock("LINEAR_B_IDEOGRAMS", "LINEAR B IDEOGRAMS", "LINEARBIDEOGRAMS");

        public static final UnicodeBlock AEGEAN_NUMBERS = new UnicodeBlock("AEGEAN_NUMBERS", "AEGEAN NUMBERS", "AEGEANNUMBERS");

        public static final UnicodeBlock OLD_ITALIC = new UnicodeBlock("OLD_ITALIC", "OLD ITALIC", "OLDITALIC");

        public static final UnicodeBlock GOTHIC = new UnicodeBlock("GOTHIC");

        public static final UnicodeBlock UGARITIC = new UnicodeBlock("UGARITIC");

        public static final UnicodeBlock DESERET = new UnicodeBlock("DESERET");

        public static final UnicodeBlock SHAVIAN = new UnicodeBlock("SHAVIAN");

        public static final UnicodeBlock OSMANYA = new UnicodeBlock("OSMANYA");

        public static final UnicodeBlock CYPRIOT_SYLLABARY = new UnicodeBlock("CYPRIOT_SYLLABARY", "CYPRIOT SYLLABARY", "CYPRIOTSYLLABARY");

        public static final UnicodeBlock BYZANTINE_MUSICAL_SYMBOLS = new UnicodeBlock("BYZANTINE_MUSICAL_SYMBOLS", "BYZANTINE MUSICAL SYMBOLS", "BYZANTINEMUSICALSYMBOLS");

        public static final UnicodeBlock MUSICAL_SYMBOLS = new UnicodeBlock("MUSICAL_SYMBOLS", "MUSICAL SYMBOLS", "MUSICALSYMBOLS");

        public static final UnicodeBlock TAI_XUAN_JING_SYMBOLS = new UnicodeBlock("TAI_XUAN_JING_SYMBOLS", "TAI XUAN JING SYMBOLS", "TAIXUANJINGSYMBOLS");

        public static final UnicodeBlock MATHEMATICAL_ALPHANUMERIC_SYMBOLS = new UnicodeBlock("MATHEMATICAL_ALPHANUMERIC_SYMBOLS", "MATHEMATICAL ALPHANUMERIC SYMBOLS", "MATHEMATICALALPHANUMERICSYMBOLS");

        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B", "CJK UNIFIED IDEOGRAPHS EXTENSION B", "CJKUNIFIEDIDEOGRAPHSEXTENSIONB");

        public static final UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT = new UnicodeBlock("CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT", "CJK COMPATIBILITY IDEOGRAPHS SUPPLEMENT", "CJKCOMPATIBILITYIDEOGRAPHSSUPPLEMENT");

        public static final UnicodeBlock TAGS = new UnicodeBlock("TAGS");

        public static final UnicodeBlock VARIATION_SELECTORS_SUPPLEMENT = new UnicodeBlock("VARIATION_SELECTORS_SUPPLEMENT", "VARIATION SELECTORS SUPPLEMENT", "VARIATIONSELECTORSSUPPLEMENT");

        public static final UnicodeBlock SUPPLEMENTARY_PRIVATE_USE_AREA_A = new UnicodeBlock("SUPPLEMENTARY_PRIVATE_USE_AREA_A", "SUPPLEMENTARY PRIVATE USE AREA-A", "SUPPLEMENTARYPRIVATEUSEAREA-A");

        public static final UnicodeBlock SUPPLEMENTARY_PRIVATE_USE_AREA_B = new UnicodeBlock("SUPPLEMENTARY_PRIVATE_USE_AREA_B", "SUPPLEMENTARY PRIVATE USE AREA-B", "SUPPLEMENTARYPRIVATEUSEAREA-B");

        public static final UnicodeBlock HIGH_SURROGATES = new UnicodeBlock("HIGH_SURROGATES", "HIGH SURROGATES", "HIGHSURROGATES");

        public static final UnicodeBlock HIGH_PRIVATE_USE_SURROGATES = new UnicodeBlock("HIGH_PRIVATE_USE_SURROGATES", "HIGH PRIVATE USE SURROGATES", "HIGHPRIVATEUSESURROGATES");

        public static final UnicodeBlock LOW_SURROGATES = new UnicodeBlock("LOW_SURROGATES", "LOW SURROGATES", "LOWSURROGATES");

        public static final UnicodeBlock ARABIC_SUPPLEMENT = new UnicodeBlock("ARABIC_SUPPLEMENT", "ARABIC SUPPLEMENT", "ARABICSUPPLEMENT");

        public static final UnicodeBlock NKO = new UnicodeBlock("NKO");

        public static final UnicodeBlock SAMARITAN = new UnicodeBlock("SAMARITAN");

        public static final UnicodeBlock MANDAIC = new UnicodeBlock("MANDAIC");

        public static final UnicodeBlock ETHIOPIC_SUPPLEMENT = new UnicodeBlock("ETHIOPIC_SUPPLEMENT", "ETHIOPIC SUPPLEMENT", "ETHIOPICSUPPLEMENT");

        public static final UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED = new UnicodeBlock("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED", "UNIFIED CANADIAN ABORIGINAL SYLLABICS EXTENDED", "UNIFIEDCANADIANABORIGINALSYLLABICSEXTENDED");

        public static final UnicodeBlock NEW_TAI_LUE = new UnicodeBlock("NEW_TAI_LUE", "NEW TAI LUE", "NEWTAILUE");

        public static final UnicodeBlock BUGINESE = new UnicodeBlock("BUGINESE");

        public static final UnicodeBlock TAI_THAM = new UnicodeBlock("TAI_THAM", "TAI THAM", "TAITHAM");

        public static final UnicodeBlock BALINESE = new UnicodeBlock("BALINESE");

        public static final UnicodeBlock SUNDANESE = new UnicodeBlock("SUNDANESE");

        public static final UnicodeBlock BATAK = new UnicodeBlock("BATAK");

        public static final UnicodeBlock LEPCHA = new UnicodeBlock("LEPCHA");

        public static final UnicodeBlock OL_CHIKI = new UnicodeBlock("OL_CHIKI", "OL CHIKI", "OLCHIKI");

        public static final UnicodeBlock VEDIC_EXTENSIONS = new UnicodeBlock("VEDIC_EXTENSIONS", "VEDIC EXTENSIONS", "VEDICEXTENSIONS");

        public static final UnicodeBlock PHONETIC_EXTENSIONS_SUPPLEMENT = new UnicodeBlock("PHONETIC_EXTENSIONS_SUPPLEMENT", "PHONETIC EXTENSIONS SUPPLEMENT", "PHONETICEXTENSIONSSUPPLEMENT");

        public static final UnicodeBlock COMBINING_DIACRITICAL_MARKS_SUPPLEMENT = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS_SUPPLEMENT", "COMBINING DIACRITICAL MARKS SUPPLEMENT", "COMBININGDIACRITICALMARKSSUPPLEMENT");

        public static final UnicodeBlock GLAGOLITIC = new UnicodeBlock("GLAGOLITIC");

        public static final UnicodeBlock LATIN_EXTENDED_C = new UnicodeBlock("LATIN_EXTENDED_C", "LATIN EXTENDED-C", "LATINEXTENDED-C");

        public static final UnicodeBlock COPTIC = new UnicodeBlock("COPTIC");

        public static final UnicodeBlock GEORGIAN_SUPPLEMENT = new UnicodeBlock("GEORGIAN_SUPPLEMENT", "GEORGIAN SUPPLEMENT", "GEORGIANSUPPLEMENT");

        public static final UnicodeBlock TIFINAGH = new UnicodeBlock("TIFINAGH");

        public static final UnicodeBlock ETHIOPIC_EXTENDED = new UnicodeBlock("ETHIOPIC_EXTENDED", "ETHIOPIC EXTENDED", "ETHIOPICEXTENDED");

        public static final UnicodeBlock CYRILLIC_EXTENDED_A = new UnicodeBlock("CYRILLIC_EXTENDED_A", "CYRILLIC EXTENDED-A", "CYRILLICEXTENDED-A");

        public static final UnicodeBlock SUPPLEMENTAL_PUNCTUATION = new UnicodeBlock("SUPPLEMENTAL_PUNCTUATION", "SUPPLEMENTAL PUNCTUATION", "SUPPLEMENTALPUNCTUATION");

        public static final UnicodeBlock CJK_STROKES = new UnicodeBlock("CJK_STROKES", "CJK STROKES", "CJKSTROKES");

        public static final UnicodeBlock LISU = new UnicodeBlock("LISU");

        public static final UnicodeBlock VAI = new UnicodeBlock("VAI");

        public static final UnicodeBlock CYRILLIC_EXTENDED_B = new UnicodeBlock("CYRILLIC_EXTENDED_B", "CYRILLIC EXTENDED-B", "CYRILLICEXTENDED-B");

        public static final UnicodeBlock BAMUM = new UnicodeBlock("BAMUM");

        public static final UnicodeBlock MODIFIER_TONE_LETTERS = new UnicodeBlock("MODIFIER_TONE_LETTERS", "MODIFIER TONE LETTERS", "MODIFIERTONELETTERS");

        public static final UnicodeBlock LATIN_EXTENDED_D = new UnicodeBlock("LATIN_EXTENDED_D", "LATIN EXTENDED-D", "LATINEXTENDED-D");

        public static final UnicodeBlock SYLOTI_NAGRI = new UnicodeBlock("SYLOTI_NAGRI", "SYLOTI NAGRI", "SYLOTINAGRI");

        public static final UnicodeBlock COMMON_INDIC_NUMBER_FORMS = new UnicodeBlock("COMMON_INDIC_NUMBER_FORMS", "COMMON INDIC NUMBER FORMS", "COMMONINDICNUMBERFORMS");

        public static final UnicodeBlock PHAGS_PA = new UnicodeBlock("PHAGS_PA", "PHAGS-PA");

        public static final UnicodeBlock SAURASHTRA = new UnicodeBlock("SAURASHTRA");

        public static final UnicodeBlock DEVANAGARI_EXTENDED = new UnicodeBlock("DEVANAGARI_EXTENDED", "DEVANAGARI EXTENDED", "DEVANAGARIEXTENDED");

        public static final UnicodeBlock KAYAH_LI = new UnicodeBlock("KAYAH_LI", "KAYAH LI", "KAYAHLI");

        public static final UnicodeBlock REJANG = new UnicodeBlock("REJANG");

        public static final UnicodeBlock HANGUL_JAMO_EXTENDED_A = new UnicodeBlock("HANGUL_JAMO_EXTENDED_A", "HANGUL JAMO EXTENDED-A", "HANGULJAMOEXTENDED-A");

        public static final UnicodeBlock JAVANESE = new UnicodeBlock("JAVANESE");

        public static final UnicodeBlock CHAM = new UnicodeBlock("CHAM");

        public static final UnicodeBlock MYANMAR_EXTENDED_A = new UnicodeBlock("MYANMAR_EXTENDED_A", "MYANMAR EXTENDED-A", "MYANMAREXTENDED-A");

        public static final UnicodeBlock TAI_VIET = new UnicodeBlock("TAI_VIET", "TAI VIET", "TAIVIET");

        public static final UnicodeBlock ETHIOPIC_EXTENDED_A = new UnicodeBlock("ETHIOPIC_EXTENDED_A", "ETHIOPIC EXTENDED-A", "ETHIOPICEXTENDED-A");

        public static final UnicodeBlock MEETEI_MAYEK = new UnicodeBlock("MEETEI_MAYEK", "MEETEI MAYEK", "MEETEIMAYEK");

        public static final UnicodeBlock HANGUL_JAMO_EXTENDED_B = new UnicodeBlock("HANGUL_JAMO_EXTENDED_B", "HANGUL JAMO EXTENDED-B", "HANGULJAMOEXTENDED-B");

        public static final UnicodeBlock VERTICAL_FORMS = new UnicodeBlock("VERTICAL_FORMS", "VERTICAL FORMS", "VERTICALFORMS");

        public static final UnicodeBlock ANCIENT_GREEK_NUMBERS = new UnicodeBlock("ANCIENT_GREEK_NUMBERS", "ANCIENT GREEK NUMBERS", "ANCIENTGREEKNUMBERS");

        public static final UnicodeBlock ANCIENT_SYMBOLS = new UnicodeBlock("ANCIENT_SYMBOLS", "ANCIENT SYMBOLS", "ANCIENTSYMBOLS");

        public static final UnicodeBlock PHAISTOS_DISC = new UnicodeBlock("PHAISTOS_DISC", "PHAISTOS DISC", "PHAISTOSDISC");

        public static final UnicodeBlock LYCIAN = new UnicodeBlock("LYCIAN");

        public static final UnicodeBlock CARIAN = new UnicodeBlock("CARIAN");

        public static final UnicodeBlock OLD_PERSIAN = new UnicodeBlock("OLD_PERSIAN", "OLD PERSIAN", "OLDPERSIAN");

        public static final UnicodeBlock IMPERIAL_ARAMAIC = new UnicodeBlock("IMPERIAL_ARAMAIC", "IMPERIAL ARAMAIC", "IMPERIALARAMAIC");

        public static final UnicodeBlock PHOENICIAN = new UnicodeBlock("PHOENICIAN");

        public static final UnicodeBlock LYDIAN = new UnicodeBlock("LYDIAN");

        public static final UnicodeBlock KHAROSHTHI = new UnicodeBlock("KHAROSHTHI");

        public static final UnicodeBlock OLD_SOUTH_ARABIAN = new UnicodeBlock("OLD_SOUTH_ARABIAN", "OLD SOUTH ARABIAN", "OLDSOUTHARABIAN");

        public static final UnicodeBlock AVESTAN = new UnicodeBlock("AVESTAN");

        public static final UnicodeBlock INSCRIPTIONAL_PARTHIAN = new UnicodeBlock("INSCRIPTIONAL_PARTHIAN", "INSCRIPTIONAL PARTHIAN", "INSCRIPTIONALPARTHIAN");

        public static final UnicodeBlock INSCRIPTIONAL_PAHLAVI = new UnicodeBlock("INSCRIPTIONAL_PAHLAVI", "INSCRIPTIONAL PAHLAVI", "INSCRIPTIONALPAHLAVI");

        public static final UnicodeBlock OLD_TURKIC = new UnicodeBlock("OLD_TURKIC", "OLD TURKIC", "OLDTURKIC");

        public static final UnicodeBlock RUMI_NUMERAL_SYMBOLS = new UnicodeBlock("RUMI_NUMERAL_SYMBOLS", "RUMI NUMERAL SYMBOLS", "RUMINUMERALSYMBOLS");

        public static final UnicodeBlock BRAHMI = new UnicodeBlock("BRAHMI");

        public static final UnicodeBlock KAITHI = new UnicodeBlock("KAITHI");

        public static final UnicodeBlock CUNEIFORM = new UnicodeBlock("CUNEIFORM");

        public static final UnicodeBlock CUNEIFORM_NUMBERS_AND_PUNCTUATION = new UnicodeBlock("CUNEIFORM_NUMBERS_AND_PUNCTUATION", "CUNEIFORM NUMBERS AND PUNCTUATION", "CUNEIFORMNUMBERSANDPUNCTUATION");

        public static final UnicodeBlock EGYPTIAN_HIEROGLYPHS = new UnicodeBlock("EGYPTIAN_HIEROGLYPHS", "EGYPTIAN HIEROGLYPHS", "EGYPTIANHIEROGLYPHS");

        public static final UnicodeBlock BAMUM_SUPPLEMENT = new UnicodeBlock("BAMUM_SUPPLEMENT", "BAMUM SUPPLEMENT", "BAMUMSUPPLEMENT");

        public static final UnicodeBlock KANA_SUPPLEMENT = new UnicodeBlock("KANA_SUPPLEMENT", "KANA SUPPLEMENT", "KANASUPPLEMENT");

        public static final UnicodeBlock ANCIENT_GREEK_MUSICAL_NOTATION = new UnicodeBlock("ANCIENT_GREEK_MUSICAL_NOTATION", "ANCIENT GREEK MUSICAL NOTATION", "ANCIENTGREEKMUSICALNOTATION");

        public static final UnicodeBlock COUNTING_ROD_NUMERALS = new UnicodeBlock("COUNTING_ROD_NUMERALS", "COUNTING ROD NUMERALS", "COUNTINGRODNUMERALS");

        public static final UnicodeBlock MAHJONG_TILES = new UnicodeBlock("MAHJONG_TILES", "MAHJONG TILES", "MAHJONGTILES");

        public static final UnicodeBlock DOMINO_TILES = new UnicodeBlock("DOMINO_TILES", "DOMINO TILES", "DOMINOTILES");

        public static final UnicodeBlock PLAYING_CARDS = new UnicodeBlock("PLAYING_CARDS", "PLAYING CARDS", "PLAYINGCARDS");

        public static final UnicodeBlock ENCLOSED_ALPHANUMERIC_SUPPLEMENT = new UnicodeBlock("ENCLOSED_ALPHANUMERIC_SUPPLEMENT", "ENCLOSED ALPHANUMERIC SUPPLEMENT", "ENCLOSEDALPHANUMERICSUPPLEMENT");

        public static final UnicodeBlock ENCLOSED_IDEOGRAPHIC_SUPPLEMENT = new UnicodeBlock("ENCLOSED_IDEOGRAPHIC_SUPPLEMENT", "ENCLOSED IDEOGRAPHIC SUPPLEMENT", "ENCLOSEDIDEOGRAPHICSUPPLEMENT");

        public static final UnicodeBlock MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS", "MISCELLANEOUS SYMBOLS AND PICTOGRAPHS", "MISCELLANEOUSSYMBOLSANDPICTOGRAPHS");

        public static final UnicodeBlock EMOTICONS = new UnicodeBlock("EMOTICONS");

        public static final UnicodeBlock TRANSPORT_AND_MAP_SYMBOLS = new UnicodeBlock("TRANSPORT_AND_MAP_SYMBOLS", "TRANSPORT AND MAP SYMBOLS", "TRANSPORTANDMAPSYMBOLS");

        public static final UnicodeBlock ALCHEMICAL_SYMBOLS = new UnicodeBlock("ALCHEMICAL_SYMBOLS", "ALCHEMICAL SYMBOLS", "ALCHEMICALSYMBOLS");

        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C", "CJK UNIFIED IDEOGRAPHS EXTENSION C", "CJKUNIFIEDIDEOGRAPHSEXTENSIONC");

        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D", "CJK UNIFIED IDEOGRAPHS EXTENSION D", "CJKUNIFIEDIDEOGRAPHSEXTENSIOND");

        public static final UnicodeBlock ARABIC_EXTENDED_A = new UnicodeBlock("ARABIC_EXTENDED_A", "ARABIC EXTENDED-A", "ARABICEXTENDED-A");

        public static final UnicodeBlock SUNDANESE_SUPPLEMENT = new UnicodeBlock("SUNDANESE_SUPPLEMENT", "SUNDANESE SUPPLEMENT", "SUNDANESESUPPLEMENT");

        public static final UnicodeBlock MEETEI_MAYEK_EXTENSIONS = new UnicodeBlock("MEETEI_MAYEK_EXTENSIONS", "MEETEI MAYEK EXTENSIONS", "MEETEIMAYEKEXTENSIONS");

        public static final UnicodeBlock MEROITIC_HIEROGLYPHS = new UnicodeBlock("MEROITIC_HIEROGLYPHS", "MEROITIC HIEROGLYPHS", "MEROITICHIEROGLYPHS");

        public static final UnicodeBlock MEROITIC_CURSIVE = new UnicodeBlock("MEROITIC_CURSIVE", "MEROITIC CURSIVE", "MEROITICCURSIVE");

        public static final UnicodeBlock SORA_SOMPENG = new UnicodeBlock("SORA_SOMPENG", "SORA SOMPENG", "SORASOMPENG");

        public static final UnicodeBlock CHAKMA = new UnicodeBlock("CHAKMA");

        public static final UnicodeBlock SHARADA = new UnicodeBlock("SHARADA");

        public static final UnicodeBlock TAKRI = new UnicodeBlock("TAKRI");

        public static final UnicodeBlock MIAO = new UnicodeBlock("MIAO");

        public static final UnicodeBlock ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS = new UnicodeBlock("ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS", "ARABIC MATHEMATICAL ALPHABETIC SYMBOLS", "ARABICMATHEMATICALALPHABETICSYMBOLS");

        public static final UnicodeBlock COMBINING_DIACRITICAL_MARKS_EXTENDED = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS_EXTENDED", "COMBINING DIACRITICAL MARKS EXTENDED", "COMBININGDIACRITICALMARKSEXTENDED");

        public static final UnicodeBlock MYANMAR_EXTENDED_B = new UnicodeBlock("MYANMAR_EXTENDED_B", "MYANMAR EXTENDED-B", "MYANMAREXTENDED-B");

        public static final UnicodeBlock LATIN_EXTENDED_E = new UnicodeBlock("LATIN_EXTENDED_E", "LATIN EXTENDED-E", "LATINEXTENDED-E");

        public static final UnicodeBlock COPTIC_EPACT_NUMBERS = new UnicodeBlock("COPTIC_EPACT_NUMBERS", "COPTIC EPACT NUMBERS", "COPTICEPACTNUMBERS");

        public static final UnicodeBlock OLD_PERMIC = new UnicodeBlock("OLD_PERMIC", "OLD PERMIC", "OLDPERMIC");

        public static final UnicodeBlock ELBASAN = new UnicodeBlock("ELBASAN");

        public static final UnicodeBlock CAUCASIAN_ALBANIAN = new UnicodeBlock("CAUCASIAN_ALBANIAN", "CAUCASIAN ALBANIAN", "CAUCASIANALBANIAN");

        public static final UnicodeBlock LINEAR_A = new UnicodeBlock("LINEAR_A", "LINEAR A", "LINEARA");

        public static final UnicodeBlock PALMYRENE = new UnicodeBlock("PALMYRENE");

        public static final UnicodeBlock NABATAEAN = new UnicodeBlock("NABATAEAN");

        public static final UnicodeBlock OLD_NORTH_ARABIAN = new UnicodeBlock("OLD_NORTH_ARABIAN", "OLD NORTH ARABIAN", "OLDNORTHARABIAN");

        public static final UnicodeBlock MANICHAEAN = new UnicodeBlock("MANICHAEAN");

        public static final UnicodeBlock PSALTER_PAHLAVI = new UnicodeBlock("PSALTER_PAHLAVI", "PSALTER PAHLAVI", "PSALTERPAHLAVI");

        public static final UnicodeBlock MAHAJANI = new UnicodeBlock("MAHAJANI");

        public static final UnicodeBlock SINHALA_ARCHAIC_NUMBERS = new UnicodeBlock("SINHALA_ARCHAIC_NUMBERS", "SINHALA ARCHAIC NUMBERS", "SINHALAARCHAICNUMBERS");

        public static final UnicodeBlock KHOJKI = new UnicodeBlock("KHOJKI");

        public static final UnicodeBlock KHUDAWADI = new UnicodeBlock("KHUDAWADI");

        public static final UnicodeBlock GRANTHA = new UnicodeBlock("GRANTHA");

        public static final UnicodeBlock TIRHUTA = new UnicodeBlock("TIRHUTA");

        public static final UnicodeBlock SIDDHAM = new UnicodeBlock("SIDDHAM");

        public static final UnicodeBlock MODI = new UnicodeBlock("MODI");

        public static final UnicodeBlock WARANG_CITI = new UnicodeBlock("WARANG_CITI", "WARANG CITI", "WARANGCITI");

        public static final UnicodeBlock PAU_CIN_HAU = new UnicodeBlock("PAU_CIN_HAU", "PAU CIN HAU", "PAUCINHAU");

        public static final UnicodeBlock MRO = new UnicodeBlock("MRO");

        public static final UnicodeBlock BASSA_VAH = new UnicodeBlock("BASSA_VAH", "BASSA VAH", "BASSAVAH");

        public static final UnicodeBlock PAHAWH_HMONG = new UnicodeBlock("PAHAWH_HMONG", "PAHAWH HMONG", "PAHAWHHMONG");

        public static final UnicodeBlock DUPLOYAN = new UnicodeBlock("DUPLOYAN");

        public static final UnicodeBlock SHORTHAND_FORMAT_CONTROLS = new UnicodeBlock("SHORTHAND_FORMAT_CONTROLS", "SHORTHAND FORMAT CONTROLS", "SHORTHANDFORMATCONTROLS");

        public static final UnicodeBlock MENDE_KIKAKUI = new UnicodeBlock("MENDE_KIKAKUI", "MENDE KIKAKUI", "MENDEKIKAKUI");

        public static final UnicodeBlock ORNAMENTAL_DINGBATS = new UnicodeBlock("ORNAMENTAL_DINGBATS", "ORNAMENTAL DINGBATS", "ORNAMENTALDINGBATS");

        public static final UnicodeBlock GEOMETRIC_SHAPES_EXTENDED = new UnicodeBlock("GEOMETRIC_SHAPES_EXTENDED", "GEOMETRIC SHAPES EXTENDED", "GEOMETRICSHAPESEXTENDED");

        public static final UnicodeBlock SUPPLEMENTAL_ARROWS_C = new UnicodeBlock("SUPPLEMENTAL_ARROWS_C", "SUPPLEMENTAL ARROWS-C", "SUPPLEMENTALARROWS-C");

        public static final UnicodeBlock CHEROKEE_SUPPLEMENT = new UnicodeBlock("CHEROKEE_SUPPLEMENT", "CHEROKEE SUPPLEMENT", "CHEROKEESUPPLEMENT");

        public static final UnicodeBlock HATRAN = new UnicodeBlock("HATRAN");

        public static final UnicodeBlock OLD_HUNGARIAN = new UnicodeBlock("OLD_HUNGARIAN", "OLD HUNGARIAN", "OLDHUNGARIAN");

        public static final UnicodeBlock MULTANI = new UnicodeBlock("MULTANI");

        public static final UnicodeBlock AHOM = new UnicodeBlock("AHOM");

        public static final UnicodeBlock EARLY_DYNASTIC_CUNEIFORM = new UnicodeBlock("EARLY_DYNASTIC_CUNEIFORM", "EARLY DYNASTIC CUNEIFORM", "EARLYDYNASTICCUNEIFORM");

        public static final UnicodeBlock ANATOLIAN_HIEROGLYPHS = new UnicodeBlock("ANATOLIAN_HIEROGLYPHS", "ANATOLIAN HIEROGLYPHS", "ANATOLIANHIEROGLYPHS");

        public static final UnicodeBlock SUTTON_SIGNWRITING = new UnicodeBlock("SUTTON_SIGNWRITING", "SUTTON SIGNWRITING", "SUTTONSIGNWRITING");

        public static final UnicodeBlock SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS = new UnicodeBlock("SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS", "SUPPLEMENTAL SYMBOLS AND PICTOGRAPHS", "SUPPLEMENTALSYMBOLSANDPICTOGRAPHS");

        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E", "CJK UNIFIED IDEOGRAPHS EXTENSION E", "CJKUNIFIEDIDEOGRAPHSEXTENSIONE");

        public static final UnicodeBlock SYRIAC_SUPPLEMENT = new UnicodeBlock("SYRIAC_SUPPLEMENT", "SYRIAC SUPPLEMENT", "SYRIACSUPPLEMENT");

        public static final UnicodeBlock CYRILLIC_EXTENDED_C = new UnicodeBlock("CYRILLIC_EXTENDED_C", "CYRILLIC EXTENDED-C", "CYRILLICEXTENDED-C");

        public static final UnicodeBlock OSAGE = new UnicodeBlock("OSAGE");

        public static final UnicodeBlock NEWA = new UnicodeBlock("NEWA");

        public static final UnicodeBlock MONGOLIAN_SUPPLEMENT = new UnicodeBlock("MONGOLIAN_SUPPLEMENT", "MONGOLIAN SUPPLEMENT", "MONGOLIANSUPPLEMENT");

        public static final UnicodeBlock MARCHEN = new UnicodeBlock("MARCHEN");

        public static final UnicodeBlock IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION = new UnicodeBlock("IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION", "IDEOGRAPHIC SYMBOLS AND PUNCTUATION", "IDEOGRAPHICSYMBOLSANDPUNCTUATION");

        public static final UnicodeBlock TANGUT = new UnicodeBlock("TANGUT");

        public static final UnicodeBlock TANGUT_COMPONENTS = new UnicodeBlock("TANGUT_COMPONENTS", "TANGUT COMPONENTS", "TANGUTCOMPONENTS");

        public static final UnicodeBlock KANA_EXTENDED_A = new UnicodeBlock("KANA_EXTENDED_A", "KANA EXTENDED-A", "KANAEXTENDED-A");

        public static final UnicodeBlock GLAGOLITIC_SUPPLEMENT = new UnicodeBlock("GLAGOLITIC_SUPPLEMENT", "GLAGOLITIC SUPPLEMENT", "GLAGOLITICSUPPLEMENT");

        public static final UnicodeBlock ADLAM = new UnicodeBlock("ADLAM");

        public static final UnicodeBlock MASARAM_GONDI = new UnicodeBlock("MASARAM_GONDI", "MASARAM GONDI", "MASARAMGONDI");

        public static final UnicodeBlock ZANABAZAR_SQUARE = new UnicodeBlock("ZANABAZAR_SQUARE", "ZANABAZAR SQUARE", "ZANABAZARSQUARE");

        public static final UnicodeBlock NUSHU = new UnicodeBlock("NUSHU");

        public static final UnicodeBlock SOYOMBO = new UnicodeBlock("SOYOMBO");

        public static final UnicodeBlock BHAIKSUKI = new UnicodeBlock("BHAIKSUKI");

        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F", "CJK UNIFIED IDEOGRAPHS EXTENSION F", "CJKUNIFIEDIDEOGRAPHSEXTENSIONF");

        private static final int[] blockStarts = { 0x0000, 0x0080, 0x0100, 0x0180, 0x0250, 0x02B0, 0x0300, 0x0370, 0x0400, 0x0500, 0x0530, 0x0590, 0x0600, 0x0700, 0x0750, 0x0780, 0x07C0, 0x0800, 0x0840, 0x0860, 0x0870, 0x08A0, 0x0900, 0x0980, 0x0A00, 0x0A80, 0x0B00, 0x0B80, 0x0C00, 0x0C80, 0x0D00, 0x0D80, 0x0E00, 0x0E80, 0x0F00, 0x1000, 0x10A0, 0x1100, 0x1200, 0x1380, 0x13A0, 0x1400, 0x1680, 0x16A0, 0x1700, 0x1720, 0x1740, 0x1760, 0x1780, 0x1800, 0x18B0, 0x1900, 0x1950, 0x1980, 0x19E0, 0x1A00, 0x1A20, 0x1AB0, 0x1B00, 0x1B80, 0x1BC0, 0x1C00, 0x1C50, 0x1C80, 0x1C90, 0x1CC0, 0x1CD0, 0x1D00, 0x1D80, 0x1DC0, 0x1E00, 0x1F00, 0x2000, 0x2070, 0x20A0, 0x20D0, 0x2100, 0x2150, 0x2190, 0x2200, 0x2300, 0x2400, 0x2440, 0x2460, 0x2500, 0x2580, 0x25A0, 0x2600, 0x2700, 0x27C0, 0x27F0, 0x2800, 0x2900, 0x2980, 0x2A00, 0x2B00, 0x2C00, 0x2C60, 0x2C80, 0x2D00, 0x2D30, 0x2D80, 0x2DE0, 0x2E00, 0x2E80, 0x2F00, 0x2FE0, 0x2FF0, 0x3000, 0x3040, 0x30A0, 0x3100, 0x3130, 0x3190, 0x31A0, 0x31C0, 0x31F0, 0x3200, 0x3300, 0x3400, 0x4DC0, 0x4E00, 0xA000, 0xA490, 0xA4D0, 0xA500, 0xA640, 0xA6A0, 0xA700, 0xA720, 0xA800, 0xA830, 0xA840, 0xA880, 0xA8E0, 0xA900, 0xA930, 0xA960, 0xA980, 0xA9E0, 0xAA00, 0xAA60, 0xAA80, 0xAAE0, 0xAB00, 0xAB30, 0xAB70, 0xABC0, 0xAC00, 0xD7B0, 0xD800, 0xDB80, 0xDC00, 0xE000, 0xF900, 0xFB00, 0xFB50, 0xFE00, 0xFE10, 0xFE20, 0xFE30, 0xFE50, 0xFE70, 0xFF00, 0xFFF0, 0x10000, 0x10080, 0x10100, 0x10140, 0x10190, 0x101D0, 0x10200, 0x10280, 0x102A0, 0x102E0, 0x10300, 0x10330, 0x10350, 0x10380, 0x103A0, 0x103E0, 0x10400, 0x10450, 0x10480, 0x104B0, 0x10500, 0x10530, 0x10570, 0x10600, 0x10780, 0x10800, 0x10840, 0x10860, 0x10880, 0x108B0, 0x108E0, 0x10900, 0x10920, 0x10940, 0x10980, 0x109A0, 0x10A00, 0x10A60, 0x10A80, 0x10AA0, 0x10AC0, 0x10B00, 0x10B40, 0x10B60, 0x10B80, 0x10BB0, 0x10C00, 0x10C50, 0x10C80, 0x10D00, 0x10E60, 0x10E80, 0x11000, 0x11080, 0x110D0, 0x11100, 0x11150, 0x11180, 0x111E0, 0x11200, 0x11250, 0x11280, 0x112B0, 0x11300, 0x11380, 0x11400, 0x11480, 0x114E0, 0x11580, 0x11600, 0x11660, 0x11680, 0x116D0, 0x11700, 0x11740, 0x118A0, 0x11900, 0x11A00, 0x11A50, 0x11AB0, 0x11AC0, 0x11B00, 0x11C00, 0x11C70, 0x11CC0, 0x11D00, 0x11D60, 0x12000, 0x12400, 0x12480, 0x12550, 0x13000, 0x13430, 0x14400, 0x14680, 0x16800, 0x16A40, 0x16A70, 0x16AD0, 0x16B00, 0x16B90, 0x16F00, 0x16FA0, 0x16FE0, 0x17000, 0x18800, 0x18B00, 0x1B000, 0x1B100, 0x1B130, 0x1B170, 0x1B300, 0x1BC00, 0x1BCA0, 0x1BCB0, 0x1D000, 0x1D100, 0x1D200, 0x1D250, 0x1D300, 0x1D360, 0x1D380, 0x1D400, 0x1D800, 0x1DAB0, 0x1E000, 0x1E030, 0x1E800, 0x1E8E0, 0x1E900, 0x1E960, 0x1EE00, 0x1EF00, 0x1F000, 0x1F030, 0x1F0A0, 0x1F100, 0x1F200, 0x1F300, 0x1F600, 0x1F650, 0x1F680, 0x1F700, 0x1F780, 0x1F800, 0x1F900, 0x1FA00, 0x20000, 0x2A6E0, 0x2A700, 0x2B740, 0x2B820, 0x2CEB0, 0x2EBF0, 0x2F800, 0x2FA20, 0xE0000, 0xE0080, 0xE0100, 0xE01F0, 0xF0000, 0x100000 };

        private static final UnicodeBlock[] blocks = { BASIC_LATIN, LATIN_1_SUPPLEMENT, LATIN_EXTENDED_A, LATIN_EXTENDED_B, IPA_EXTENSIONS, SPACING_MODIFIER_LETTERS, COMBINING_DIACRITICAL_MARKS, GREEK, CYRILLIC, CYRILLIC_SUPPLEMENTARY, ARMENIAN, HEBREW, ARABIC, SYRIAC, ARABIC_SUPPLEMENT, THAANA, NKO, SAMARITAN, MANDAIC, SYRIAC_SUPPLEMENT, null, ARABIC_EXTENDED_A, DEVANAGARI, BENGALI, GURMUKHI, GUJARATI, ORIYA, TAMIL, TELUGU, KANNADA, MALAYALAM, SINHALA, THAI, LAO, TIBETAN, MYANMAR, GEORGIAN, HANGUL_JAMO, ETHIOPIC, ETHIOPIC_SUPPLEMENT, CHEROKEE, UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, OGHAM, RUNIC, TAGALOG, HANUNOO, BUHID, TAGBANWA, KHMER, MONGOLIAN, UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED, LIMBU, TAI_LE, NEW_TAI_LUE, KHMER_SYMBOLS, BUGINESE, TAI_THAM, COMBINING_DIACRITICAL_MARKS_EXTENDED, BALINESE, SUNDANESE, BATAK, LEPCHA, OL_CHIKI, CYRILLIC_EXTENDED_C, null, SUNDANESE_SUPPLEMENT, VEDIC_EXTENSIONS, PHONETIC_EXTENSIONS, PHONETIC_EXTENSIONS_SUPPLEMENT, COMBINING_DIACRITICAL_MARKS_SUPPLEMENT, LATIN_EXTENDED_ADDITIONAL, GREEK_EXTENDED, GENERAL_PUNCTUATION, SUPERSCRIPTS_AND_SUBSCRIPTS, CURRENCY_SYMBOLS, COMBINING_MARKS_FOR_SYMBOLS, LETTERLIKE_SYMBOLS, NUMBER_FORMS, ARROWS, MATHEMATICAL_OPERATORS, MISCELLANEOUS_TECHNICAL, CONTROL_PICTURES, OPTICAL_CHARACTER_RECOGNITION, ENCLOSED_ALPHANUMERICS, BOX_DRAWING, BLOCK_ELEMENTS, GEOMETRIC_SHAPES, MISCELLANEOUS_SYMBOLS, DINGBATS, MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, SUPPLEMENTAL_ARROWS_A, BRAILLE_PATTERNS, SUPPLEMENTAL_ARROWS_B, MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B, SUPPLEMENTAL_MATHEMATICAL_OPERATORS, MISCELLANEOUS_SYMBOLS_AND_ARROWS, GLAGOLITIC, LATIN_EXTENDED_C, COPTIC, GEORGIAN_SUPPLEMENT, TIFINAGH, ETHIOPIC_EXTENDED, CYRILLIC_EXTENDED_A, SUPPLEMENTAL_PUNCTUATION, CJK_RADICALS_SUPPLEMENT, KANGXI_RADICALS, null, IDEOGRAPHIC_DESCRIPTION_CHARACTERS, CJK_SYMBOLS_AND_PUNCTUATION, HIRAGANA, KATAKANA, BOPOMOFO, HANGUL_COMPATIBILITY_JAMO, KANBUN, BOPOMOFO_EXTENDED, CJK_STROKES, KATAKANA_PHONETIC_EXTENSIONS, ENCLOSED_CJK_LETTERS_AND_MONTHS, CJK_COMPATIBILITY, CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, YIJING_HEXAGRAM_SYMBOLS, CJK_UNIFIED_IDEOGRAPHS, YI_SYLLABLES, YI_RADICALS, LISU, VAI, CYRILLIC_EXTENDED_B, BAMUM, MODIFIER_TONE_LETTERS, LATIN_EXTENDED_D, SYLOTI_NAGRI, COMMON_INDIC_NUMBER_FORMS, PHAGS_PA, SAURASHTRA, DEVANAGARI_EXTENDED, KAYAH_LI, REJANG, HANGUL_JAMO_EXTENDED_A, JAVANESE, MYANMAR_EXTENDED_B, CHAM, MYANMAR_EXTENDED_A, TAI_VIET, MEETEI_MAYEK_EXTENSIONS, ETHIOPIC_EXTENDED_A, LATIN_EXTENDED_E, CHEROKEE_SUPPLEMENT, MEETEI_MAYEK, HANGUL_SYLLABLES, HANGUL_JAMO_EXTENDED_B, HIGH_SURROGATES, HIGH_PRIVATE_USE_SURROGATES, LOW_SURROGATES, PRIVATE_USE_AREA, CJK_COMPATIBILITY_IDEOGRAPHS, ALPHABETIC_PRESENTATION_FORMS, ARABIC_PRESENTATION_FORMS_A, VARIATION_SELECTORS, VERTICAL_FORMS, COMBINING_HALF_MARKS, CJK_COMPATIBILITY_FORMS, SMALL_FORM_VARIANTS, ARABIC_PRESENTATION_FORMS_B, HALFWIDTH_AND_FULLWIDTH_FORMS, SPECIALS, LINEAR_B_SYLLABARY, LINEAR_B_IDEOGRAMS, AEGEAN_NUMBERS, ANCIENT_GREEK_NUMBERS, ANCIENT_SYMBOLS, PHAISTOS_DISC, null, LYCIAN, CARIAN, COPTIC_EPACT_NUMBERS, OLD_ITALIC, GOTHIC, OLD_PERMIC, UGARITIC, OLD_PERSIAN, null, DESERET, SHAVIAN, OSMANYA, OSAGE, ELBASAN, CAUCASIAN_ALBANIAN, null, LINEAR_A, null, CYPRIOT_SYLLABARY, IMPERIAL_ARAMAIC, PALMYRENE, NABATAEAN, null, HATRAN, PHOENICIAN, LYDIAN, null, MEROITIC_HIEROGLYPHS, MEROITIC_CURSIVE, KHAROSHTHI, OLD_SOUTH_ARABIAN, OLD_NORTH_ARABIAN, null, MANICHAEAN, AVESTAN, INSCRIPTIONAL_PARTHIAN, INSCRIPTIONAL_PAHLAVI, PSALTER_PAHLAVI, null, OLD_TURKIC, null, OLD_HUNGARIAN, null, RUMI_NUMERAL_SYMBOLS, null, BRAHMI, KAITHI, SORA_SOMPENG, CHAKMA, MAHAJANI, SHARADA, SINHALA_ARCHAIC_NUMBERS, KHOJKI, null, MULTANI, KHUDAWADI, GRANTHA, null, NEWA, TIRHUTA, null, SIDDHAM, MODI, MONGOLIAN_SUPPLEMENT, TAKRI, null, AHOM, null, WARANG_CITI, null, ZANABAZAR_SQUARE, SOYOMBO, null, PAU_CIN_HAU, null, BHAIKSUKI, MARCHEN, null, MASARAM_GONDI, null, CUNEIFORM, CUNEIFORM_NUMBERS_AND_PUNCTUATION, EARLY_DYNASTIC_CUNEIFORM, null, EGYPTIAN_HIEROGLYPHS, null, ANATOLIAN_HIEROGLYPHS, null, BAMUM_SUPPLEMENT, MRO, null, BASSA_VAH, PAHAWH_HMONG, null, MIAO, null, IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION, TANGUT, TANGUT_COMPONENTS, null, KANA_SUPPLEMENT, KANA_EXTENDED_A, null, NUSHU, null, DUPLOYAN, SHORTHAND_FORMAT_CONTROLS, null, BYZANTINE_MUSICAL_SYMBOLS, MUSICAL_SYMBOLS, ANCIENT_GREEK_MUSICAL_NOTATION, null, TAI_XUAN_JING_SYMBOLS, COUNTING_ROD_NUMERALS, null, MATHEMATICAL_ALPHANUMERIC_SYMBOLS, SUTTON_SIGNWRITING, null, GLAGOLITIC_SUPPLEMENT, null, MENDE_KIKAKUI, null, ADLAM, null, ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS, null, MAHJONG_TILES, DOMINO_TILES, PLAYING_CARDS, ENCLOSED_ALPHANUMERIC_SUPPLEMENT, ENCLOSED_IDEOGRAPHIC_SUPPLEMENT, MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS, EMOTICONS, ORNAMENTAL_DINGBATS, TRANSPORT_AND_MAP_SYMBOLS, ALCHEMICAL_SYMBOLS, GEOMETRIC_SHAPES_EXTENDED, SUPPLEMENTAL_ARROWS_C, SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS, null, CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B, null, CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C, CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D, CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E, CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F, null, CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT, null, TAGS, null, VARIATION_SELECTORS_SUPPLEMENT, null, SUPPLEMENTARY_PRIVATE_USE_AREA_A, SUPPLEMENTARY_PRIVATE_USE_AREA_B };

        @Pure
        @Nullable
        public static UnicodeBlock of(char c);

        @Pure
        @Nullable
        public static UnicodeBlock of(int codePoint);

        @Pure
        public static final UnicodeBlock forName(String blockName);
    }

    public static enum UnicodeScript {

        COMMON,
        LATIN,
        GREEK,
        CYRILLIC,
        ARMENIAN,
        HEBREW,
        ARABIC,
        SYRIAC,
        THAANA,
        DEVANAGARI,
        BENGALI,
        GURMUKHI,
        GUJARATI,
        ORIYA,
        TAMIL,
        TELUGU,
        KANNADA,
        MALAYALAM,
        SINHALA,
        THAI,
        LAO,
        TIBETAN,
        MYANMAR,
        GEORGIAN,
        HANGUL,
        ETHIOPIC,
        CHEROKEE,
        CANADIAN_ABORIGINAL,
        OGHAM,
        RUNIC,
        KHMER,
        MONGOLIAN,
        HIRAGANA,
        KATAKANA,
        BOPOMOFO,
        HAN,
        YI,
        OLD_ITALIC,
        GOTHIC,
        DESERET,
        INHERITED,
        TAGALOG,
        HANUNOO,
        BUHID,
        TAGBANWA,
        LIMBU,
        TAI_LE,
        LINEAR_B,
        UGARITIC,
        SHAVIAN,
        OSMANYA,
        CYPRIOT,
        BRAILLE,
        BUGINESE,
        COPTIC,
        NEW_TAI_LUE,
        GLAGOLITIC,
        TIFINAGH,
        SYLOTI_NAGRI,
        OLD_PERSIAN,
        KHAROSHTHI,
        BALINESE,
        CUNEIFORM,
        PHOENICIAN,
        PHAGS_PA,
        NKO,
        SUNDANESE,
        BATAK,
        LEPCHA,
        OL_CHIKI,
        VAI,
        SAURASHTRA,
        KAYAH_LI,
        REJANG,
        LYCIAN,
        CARIAN,
        LYDIAN,
        CHAM,
        TAI_THAM,
        TAI_VIET,
        AVESTAN,
        EGYPTIAN_HIEROGLYPHS,
        SAMARITAN,
        MANDAIC,
        LISU,
        BAMUM,
        JAVANESE,
        MEETEI_MAYEK,
        IMPERIAL_ARAMAIC,
        OLD_SOUTH_ARABIAN,
        INSCRIPTIONAL_PARTHIAN,
        INSCRIPTIONAL_PAHLAVI,
        OLD_TURKIC,
        BRAHMI,
        KAITHI,
        MEROITIC_HIEROGLYPHS,
        MEROITIC_CURSIVE,
        SORA_SOMPENG,
        CHAKMA,
        SHARADA,
        TAKRI,
        MIAO,
        CAUCASIAN_ALBANIAN,
        BASSA_VAH,
        DUPLOYAN,
        ELBASAN,
        GRANTHA,
        PAHAWH_HMONG,
        KHOJKI,
        LINEAR_A,
        MAHAJANI,
        MANICHAEAN,
        MENDE_KIKAKUI,
        MODI,
        MRO,
        OLD_NORTH_ARABIAN,
        NABATAEAN,
        PALMYRENE,
        PAU_CIN_HAU,
        OLD_PERMIC,
        PSALTER_PAHLAVI,
        SIDDHAM,
        KHUDAWADI,
        TIRHUTA,
        WARANG_CITI,
        AHOM,
        ANATOLIAN_HIEROGLYPHS,
        HATRAN,
        MULTANI,
        OLD_HUNGARIAN,
        SIGNWRITING,
        ADLAM,
        BHAIKSUKI,
        MARCHEN,
        NEWA,
        OSAGE,
        TANGUT,
        MASARAM_GONDI,
        NUSHU,
        SOYOMBO,
        ZANABAZAR_SQUARE,
        UNKNOWN;

        private static final int[] scriptStarts = { 0x0000, 0x0041, 0x005B, 0x0061, 0x007B, 0x00AA, 0x00AB, 0x00BA, 0x00BB, 0x00C0, 0x00D7, 0x00D8, 0x00F7, 0x00F8, 0x02B9, 0x02E0, 0x02E5, 0x02EA, 0x02EC, 0x0300, 0x0370, 0x0374, 0x0375, 0x0378, 0x037A, 0x037E, 0x037F, 0x0380, 0x0384, 0x0385, 0x0386, 0x0387, 0x0388, 0x038B, 0x038C, 0x038D, 0x038E, 0x03A2, 0x03A3, 0x03E2, 0x03F0, 0x0400, 0x0485, 0x0487, 0x0530, 0x0531, 0x0557, 0x0559, 0x0560, 0x0561, 0x0588, 0x0589, 0x058A, 0x058B, 0x058D, 0x0590, 0x0591, 0x05C8, 0x05D0, 0x05EB, 0x05F0, 0x05F5, 0x0600, 0x0605, 0x0606, 0x060C, 0x060D, 0x061B, 0x061C, 0x061D, 0x061E, 0x061F, 0x0620, 0x0640, 0x0641, 0x064B, 0x0656, 0x0670, 0x0671, 0x06DD, 0x06DE, 0x0700, 0x070E, 0x070F, 0x074B, 0x074D, 0x0750, 0x0780, 0x07B2, 0x07C0, 0x07FB, 0x0800, 0x082E, 0x0830, 0x083F, 0x0840, 0x085C, 0x085E, 0x085F, 0x0860, 0x086B, 0x08A0, 0x08B5, 0x08B6, 0x08BE, 0x08D4, 0x08E2, 0x08E3, 0x0900, 0x0951, 0x0953, 0x0964, 0x0966, 0x0980, 0x0984, 0x0985, 0x098D, 0x098F, 0x0991, 0x0993, 0x09A9, 0x09AA, 0x09B1, 0x09B2, 0x09B3, 0x09B6, 0x09BA, 0x09BC, 0x09C5, 0x09C7, 0x09C9, 0x09CB, 0x09CF, 0x09D7, 0x09D8, 0x09DC, 0x09DE, 0x09DF, 0x09E4, 0x09E6, 0x09FE, 0x0A01, 0x0A04, 0x0A05, 0x0A0B, 0x0A0F, 0x0A11, 0x0A13, 0x0A29, 0x0A2A, 0x0A31, 0x0A32, 0x0A34, 0x0A35, 0x0A37, 0x0A38, 0x0A3A, 0x0A3C, 0x0A3D, 0x0A3E, 0x0A43, 0x0A47, 0x0A49, 0x0A4B, 0x0A4E, 0x0A51, 0x0A52, 0x0A59, 0x0A5D, 0x0A5E, 0x0A5F, 0x0A66, 0x0A76, 0x0A81, 0x0A84, 0x0A85, 0x0A8E, 0x0A8F, 0x0A92, 0x0A93, 0x0AA9, 0x0AAA, 0x0AB1, 0x0AB2, 0x0AB4, 0x0AB5, 0x0ABA, 0x0ABC, 0x0AC6, 0x0AC7, 0x0ACA, 0x0ACB, 0x0ACE, 0x0AD0, 0x0AD1, 0x0AE0, 0x0AE4, 0x0AE6, 0x0AF2, 0x0AF9, 0x0B00, 0x0B01, 0x0B04, 0x0B05, 0x0B0D, 0x0B0F, 0x0B11, 0x0B13, 0x0B29, 0x0B2A, 0x0B31, 0x0B32, 0x0B34, 0x0B35, 0x0B3A, 0x0B3C, 0x0B45, 0x0B47, 0x0B49, 0x0B4B, 0x0B4E, 0x0B56, 0x0B58, 0x0B5C, 0x0B5E, 0x0B5F, 0x0B64, 0x0B66, 0x0B78, 0x0B82, 0x0B84, 0x0B85, 0x0B8B, 0x0B8E, 0x0B91, 0x0B92, 0x0B96, 0x0B99, 0x0B9B, 0x0B9C, 0x0B9D, 0x0B9E, 0x0BA0, 0x0BA3, 0x0BA5, 0x0BA8, 0x0BAB, 0x0BAE, 0x0BBA, 0x0BBE, 0x0BC3, 0x0BC6, 0x0BC9, 0x0BCA, 0x0BCE, 0x0BD0, 0x0BD1, 0x0BD7, 0x0BD8, 0x0BE6, 0x0BFB, 0x0C00, 0x0C04, 0x0C05, 0x0C0D, 0x0C0E, 0x0C11, 0x0C12, 0x0C29, 0x0C2A, 0x0C3A, 0x0C3D, 0x0C45, 0x0C46, 0x0C49, 0x0C4A, 0x0C4E, 0x0C55, 0x0C57, 0x0C58, 0x0C5B, 0x0C60, 0x0C64, 0x0C66, 0x0C70, 0x0C78, 0x0C80, 0x0C84, 0x0C85, 0x0C8D, 0x0C8E, 0x0C91, 0x0C92, 0x0CA9, 0x0CAA, 0x0CB4, 0x0CB5, 0x0CBA, 0x0CBC, 0x0CC5, 0x0CC6, 0x0CC9, 0x0CCA, 0x0CCE, 0x0CD5, 0x0CD7, 0x0CDE, 0x0CDF, 0x0CE0, 0x0CE4, 0x0CE6, 0x0CF0, 0x0CF1, 0x0CF3, 0x0D00, 0x0D04, 0x0D05, 0x0D0D, 0x0D0E, 0x0D11, 0x0D12, 0x0D45, 0x0D46, 0x0D49, 0x0D4A, 0x0D50, 0x0D54, 0x0D64, 0x0D66, 0x0D80, 0x0D82, 0x0D84, 0x0D85, 0x0D97, 0x0D9A, 0x0DB2, 0x0DB3, 0x0DBC, 0x0DBD, 0x0DBE, 0x0DC0, 0x0DC7, 0x0DCA, 0x0DCB, 0x0DCF, 0x0DD5, 0x0DD6, 0x0DD7, 0x0DD8, 0x0DE0, 0x0DE6, 0x0DF0, 0x0DF2, 0x0DF5, 0x0E01, 0x0E3B, 0x0E3F, 0x0E40, 0x0E5C, 0x0E81, 0x0E83, 0x0E84, 0x0E85, 0x0E87, 0x0E89, 0x0E8A, 0x0E8B, 0x0E8D, 0x0E8E, 0x0E94, 0x0E98, 0x0E99, 0x0EA0, 0x0EA1, 0x0EA4, 0x0EA5, 0x0EA6, 0x0EA7, 0x0EA8, 0x0EAA, 0x0EAC, 0x0EAD, 0x0EBA, 0x0EBB, 0x0EBE, 0x0EC0, 0x0EC5, 0x0EC6, 0x0EC7, 0x0EC8, 0x0ECE, 0x0ED0, 0x0EDA, 0x0EDC, 0x0EE0, 0x0F00, 0x0F48, 0x0F49, 0x0F6D, 0x0F71, 0x0F98, 0x0F99, 0x0FBD, 0x0FBE, 0x0FCD, 0x0FCE, 0x0FD5, 0x0FD9, 0x0FDB, 0x1000, 0x10A0, 0x10C6, 0x10C7, 0x10C8, 0x10CD, 0x10CE, 0x10D0, 0x10FB, 0x10FC, 0x1100, 0x1200, 0x1249, 0x124A, 0x124E, 0x1250, 0x1257, 0x1258, 0x1259, 0x125A, 0x125E, 0x1260, 0x1289, 0x128A, 0x128E, 0x1290, 0x12B1, 0x12B2, 0x12B6, 0x12B8, 0x12BF, 0x12C0, 0x12C1, 0x12C2, 0x12C6, 0x12C8, 0x12D7, 0x12D8, 0x1311, 0x1312, 0x1316, 0x1318, 0x135B, 0x135D, 0x137D, 0x1380, 0x139A, 0x13A0, 0x13F6, 0x13F8, 0x13FE, 0x1400, 0x1680, 0x169D, 0x16A0, 0x16EB, 0x16EE, 0x16F9, 0x1700, 0x170D, 0x170E, 0x1715, 0x1720, 0x1735, 0x1737, 0x1740, 0x1754, 0x1760, 0x176D, 0x176E, 0x1771, 0x1772, 0x1774, 0x1780, 0x17DE, 0x17E0, 0x17EA, 0x17F0, 0x17FA, 0x1800, 0x1802, 0x1804, 0x1805, 0x1806, 0x180F, 0x1810, 0x181A, 0x1820, 0x1878, 0x1880, 0x18AB, 0x18B0, 0x18F6, 0x1900, 0x191F, 0x1920, 0x192C, 0x1930, 0x193C, 0x1940, 0x1941, 0x1944, 0x1950, 0x196E, 0x1970, 0x1975, 0x1980, 0x19AC, 0x19B0, 0x19CA, 0x19D0, 0x19DB, 0x19DE, 0x19E0, 0x1A00, 0x1A1C, 0x1A1E, 0x1A20, 0x1A5F, 0x1A60, 0x1A7D, 0x1A7F, 0x1A8A, 0x1A90, 0x1A9A, 0x1AA0, 0x1AAE, 0x1AB0, 0x1ABF, 0x1B00, 0x1B4C, 0x1B50, 0x1B7D, 0x1B80, 0x1BC0, 0x1BF4, 0x1BFC, 0x1C00, 0x1C38, 0x1C3B, 0x1C4A, 0x1C4D, 0x1C50, 0x1C80, 0x1C89, 0x1CC0, 0x1CC8, 0x1CD0, 0x1CD3, 0x1CD4, 0x1CE1, 0x1CE2, 0x1CE9, 0x1CED, 0x1CEE, 0x1CF4, 0x1CF5, 0x1CF8, 0x1CFA, 0x1D00, 0x1D26, 0x1D2B, 0x1D2C, 0x1D5D, 0x1D62, 0x1D66, 0x1D6B, 0x1D78, 0x1D79, 0x1DBF, 0x1DC0, 0x1DFA, 0x1DFB, 0x1E00, 0x1F00, 0x1F16, 0x1F18, 0x1F1E, 0x1F20, 0x1F46, 0x1F48, 0x1F4E, 0x1F50, 0x1F58, 0x1F59, 0x1F5A, 0x1F5B, 0x1F5C, 0x1F5D, 0x1F5E, 0x1F5F, 0x1F7E, 0x1F80, 0x1FB5, 0x1FB6, 0x1FC5, 0x1FC6, 0x1FD4, 0x1FD6, 0x1FDC, 0x1FDD, 0x1FF0, 0x1FF2, 0x1FF5, 0x1FF6, 0x1FFF, 0x2000, 0x200C, 0x200E, 0x2065, 0x2066, 0x2071, 0x2072, 0x2074, 0x207F, 0x2080, 0x208F, 0x2090, 0x209D, 0x20A0, 0x20C0, 0x20D0, 0x20F1, 0x2100, 0x2126, 0x2127, 0x212A, 0x212C, 0x2132, 0x2133, 0x214E, 0x214F, 0x2160, 0x2189, 0x218C, 0x2190, 0x2427, 0x2440, 0x244B, 0x2460, 0x2800, 0x2900, 0x2B74, 0x2B76, 0x2B96, 0x2B98, 0x2BBA, 0x2BBD, 0x2BC9, 0x2BCA, 0x2BD3, 0x2BEC, 0x2BF0, 0x2C00, 0x2C2F, 0x2C30, 0x2C5F, 0x2C60, 0x2C80, 0x2CF4, 0x2CF9, 0x2D00, 0x2D26, 0x2D27, 0x2D28, 0x2D2D, 0x2D2E, 0x2D30, 0x2D68, 0x2D6F, 0x2D71, 0x2D7F, 0x2D80, 0x2D97, 0x2DA0, 0x2DA7, 0x2DA8, 0x2DAF, 0x2DB0, 0x2DB7, 0x2DB8, 0x2DBF, 0x2DC0, 0x2DC7, 0x2DC8, 0x2DCF, 0x2DD0, 0x2DD7, 0x2DD8, 0x2DDF, 0x2DE0, 0x2E00, 0x2E50, 0x2E80, 0x2E9A, 0x2E9B, 0x2EF4, 0x2F00, 0x2FD6, 0x2FF0, 0x2FFC, 0x3000, 0x3005, 0x3006, 0x3007, 0x3008, 0x3021, 0x302A, 0x302E, 0x3030, 0x3038, 0x303C, 0x3040, 0x3041, 0x3097, 0x3099, 0x309B, 0x309D, 0x30A0, 0x30A1, 0x30FB, 0x30FD, 0x3100, 0x3105, 0x312F, 0x3131, 0x318F, 0x3190, 0x31A0, 0x31BB, 0x31C0, 0x31E4, 0x31F0, 0x3200, 0x321F, 0x3220, 0x3260, 0x327F, 0x32D0, 0x32FF, 0x3300, 0x3358, 0x3400, 0x4DB6, 0x4DC0, 0x4E00, 0x9FEB, 0xA000, 0xA48D, 0xA490, 0xA4C7, 0xA4D0, 0xA500, 0xA62C, 0xA640, 0xA6A0, 0xA6F8, 0xA700, 0xA722, 0xA788, 0xA78B, 0xA7AF, 0xA7B0, 0xA7B8, 0xA7F7, 0xA800, 0xA82C, 0xA830, 0xA83A, 0xA840, 0xA878, 0xA880, 0xA8C6, 0xA8CE, 0xA8DA, 0xA8E0, 0xA8FE, 0xA900, 0xA92E, 0xA92F, 0xA930, 0xA954, 0xA95F, 0xA960, 0xA97D, 0xA980, 0xA9CE, 0xA9CF, 0xA9D0, 0xA9DA, 0xA9DE, 0xA9E0, 0xA9FF, 0xAA00, 0xAA37, 0xAA40, 0xAA4E, 0xAA50, 0xAA5A, 0xAA5C, 0xAA60, 0xAA80, 0xAAC3, 0xAADB, 0xAAE0, 0xAAF7, 0xAB01, 0xAB07, 0xAB09, 0xAB0F, 0xAB11, 0xAB17, 0xAB20, 0xAB27, 0xAB28, 0xAB2F, 0xAB30, 0xAB5B, 0xAB5C, 0xAB65, 0xAB66, 0xAB70, 0xABC0, 0xABEE, 0xABF0, 0xABFA, 0xAC00, 0xD7A4, 0xD7B0, 0xD7C7, 0xD7CB, 0xD7FC, 0xF900, 0xFA6E, 0xFA70, 0xFADA, 0xFB00, 0xFB07, 0xFB13, 0xFB18, 0xFB1D, 0xFB37, 0xFB38, 0xFB3D, 0xFB3E, 0xFB3F, 0xFB40, 0xFB42, 0xFB43, 0xFB45, 0xFB46, 0xFB50, 0xFBC2, 0xFBD3, 0xFD3E, 0xFD40, 0xFD50, 0xFD90, 0xFD92, 0xFDC8, 0xFDF0, 0xFDFE, 0xFE00, 0xFE10, 0xFE1A, 0xFE20, 0xFE2E, 0xFE30, 0xFE53, 0xFE54, 0xFE67, 0xFE68, 0xFE6C, 0xFE70, 0xFE75, 0xFE76, 0xFEFD, 0xFEFF, 0xFF00, 0xFF01, 0xFF21, 0xFF3B, 0xFF41, 0xFF5B, 0xFF66, 0xFF70, 0xFF71, 0xFF9E, 0xFFA0, 0xFFBF, 0xFFC2, 0xFFC8, 0xFFCA, 0xFFD0, 0xFFD2, 0xFFD8, 0xFFDA, 0xFFDD, 0xFFE0, 0xFFE7, 0xFFE8, 0xFFEF, 0xFFF9, 0xFFFE, 0x10000, 0x1000C, 0x1000D, 0x10027, 0x10028, 0x1003B, 0x1003C, 0x1003E, 0x1003F, 0x1004E, 0x10050, 0x1005E, 0x10080, 0x100FB, 0x10100, 0x10103, 0x10107, 0x10134, 0x10137, 0x10140, 0x1018F, 0x10190, 0x1019C, 0x101A0, 0x101A1, 0x101D0, 0x101FD, 0x101FE, 0x10280, 0x1029D, 0x102A0, 0x102D1, 0x102E0, 0x102E1, 0x102FC, 0x10300, 0x10324, 0x1032D, 0x10330, 0x1034B, 0x10350, 0x1037B, 0x10380, 0x1039E, 0x1039F, 0x103A0, 0x103C4, 0x103C8, 0x103D6, 0x10400, 0x10450, 0x10480, 0x1049E, 0x104A0, 0x104AA, 0x104B0, 0x104D4, 0x104D8, 0x104FC, 0x10500, 0x10528, 0x10530, 0x10564, 0x1056F, 0x10570, 0x10600, 0x10737, 0x10740, 0x10756, 0x10760, 0x10768, 0x10800, 0x10806, 0x10808, 0x10809, 0x1080A, 0x10836, 0x10837, 0x10839, 0x1083C, 0x1083D, 0x1083F, 0x10840, 0x10856, 0x10857, 0x10860, 0x10880, 0x1089F, 0x108A7, 0x108B0, 0x108E0, 0x108F3, 0x108F4, 0x108F6, 0x108FB, 0x10900, 0x1091C, 0x1091F, 0x10920, 0x1093A, 0x1093F, 0x10940, 0x10980, 0x109A0, 0x109B8, 0x109BC, 0x109D0, 0x109D2, 0x10A00, 0x10A04, 0x10A05, 0x10A07, 0x10A0C, 0x10A14, 0x10A15, 0x10A18, 0x10A19, 0x10A34, 0x10A38, 0x10A3B, 0x10A3F, 0x10A48, 0x10A50, 0x10A59, 0x10A60, 0x10A80, 0x10AA0, 0x10AC0, 0x10AE7, 0x10AEB, 0x10AF7, 0x10B00, 0x10B36, 0x10B39, 0x10B40, 0x10B56, 0x10B58, 0x10B60, 0x10B73, 0x10B78, 0x10B80, 0x10B92, 0x10B99, 0x10B9D, 0x10BA9, 0x10BB0, 0x10C00, 0x10C49, 0x10C80, 0x10CB3, 0x10CC0, 0x10CF3, 0x10CFA, 0x10D00, 0x10E60, 0x10E7F, 0x11000, 0x1104E, 0x11052, 0x11070, 0x1107F, 0x11080, 0x110C2, 0x110D0, 0x110E9, 0x110F0, 0x110FA, 0x11100, 0x11135, 0x11136, 0x11144, 0x11150, 0x11177, 0x11180, 0x111CE, 0x111D0, 0x111E0, 0x111E1, 0x111F5, 0x11200, 0x11212, 0x11213, 0x1123F, 0x11280, 0x11287, 0x11288, 0x11289, 0x1128A, 0x1128E, 0x1128F, 0x1129E, 0x1129F, 0x112AA, 0x112B0, 0x112EB, 0x112F0, 0x112FA, 0x11300, 0x11304, 0x11305, 0x1130D, 0x1130F, 0x11311, 0x11313, 0x11329, 0x1132A, 0x11331, 0x11332, 0x11334, 0x11335, 0x1133A, 0x1133C, 0x11345, 0x11347, 0x11349, 0x1134B, 0x1134E, 0x11350, 0x11351, 0x11357, 0x11358, 0x1135D, 0x11364, 0x11366, 0x1136D, 0x11370, 0x11375, 0x11400, 0x1145A, 0x1145B, 0x1145C, 0x1145D, 0x1145E, 0x11480, 0x114C8, 0x114D0, 0x114DA, 0x11580, 0x115B6, 0x115B8, 0x115DE, 0x11600, 0x11645, 0x11650, 0x1165A, 0x11660, 0X1166D, 0x11680, 0x116B8, 0x116C0, 0x116CA, 0x11700, 0x1171A, 0x1171D, 0x1172C, 0x11730, 0x11740, 0x118A0, 0x118F3, 0x118FF, 0x11900, 0x11A00, 0X11A48, 0x11A50, 0x11A84, 0x11A86, 0x11A9D, 0x11A9E, 0x11AA3, 0x11AC0, 0x11AF9, 0x11C00, 0x11C09, 0x11C0A, 0x11C37, 0x11C38, 0x11C46, 0x11C50, 0x11C6D, 0x11C70, 0x11C90, 0x11C92, 0x11CA8, 0x11CA9, 0x11CB7, 0x11D00, 0x11D07, 0x11D08, 0x11D0A, 0x11D0B, 0x11D37, 0x11D3A, 0x11D3B, 0x11D3C, 0x11D3E, 0x11D3F, 0x11D48, 0x11D50, 0x11D5A, 0x12000, 0x1239A, 0x12400, 0x1246F, 0x12470, 0x12475, 0x12480, 0x12544, 0x13000, 0x1342F, 0x14400, 0x14647, 0x16800, 0x16A39, 0x16A40, 0x16A5F, 0x16A60, 0x16A6A, 0x16A6E, 0x16A70, 0x16AD0, 0x16AEE, 0x16AF0, 0x16AF6, 0x16B00, 0x16B46, 0x16B50, 0x16B5A, 0x16B5B, 0x16B62, 0x16B63, 0x16B78, 0x16B7D, 0x16B90, 0x16F00, 0x16F45, 0x16F50, 0x16F7F, 0x16F8F, 0x16FA0, 0x16FE0, 0x16FE1, 0x16FE2, 0x17000, 0x187ED, 0x18800, 0x18AF3, 0x1B000, 0x1B001, 0x1B11F, 0x1B170, 0x1B2FC, 0x1BC00, 0x1BC6B, 0x1BC70, 0x1BC7D, 0x1BC80, 0x1BC89, 0x1BC90, 0x1BC9A, 0x1BC9C, 0x1BCA0, 0x1BCA4, 0x1D000, 0x1D0F6, 0x1D100, 0x1D127, 0x1D129, 0x1D167, 0x1D16A, 0x1D17B, 0x1D183, 0x1D185, 0x1D18C, 0x1D1AA, 0x1D1AE, 0x1D1E9, 0x1D200, 0x1D246, 0x1D300, 0x1D357, 0x1D360, 0x1D372, 0x1D400, 0x1D455, 0x1D456, 0x1D49D, 0x1D49E, 0x1D4A0, 0x1D4A2, 0x1D4A3, 0x1D4A5, 0x1D4A7, 0x1D4A9, 0x1D4AD, 0x1D4AE, 0x1D4BA, 0x1D4BB, 0x1D4BC, 0x1D4BD, 0x1D4C4, 0x1D4C5, 0x1D506, 0x1D507, 0x1D50B, 0x1D50D, 0x1D515, 0x1D516, 0x1D51D, 0x1D51E, 0x1D53A, 0x1D53B, 0x1D53F, 0x1D540, 0x1D545, 0x1D546, 0x1D547, 0x1D54A, 0x1D551, 0x1D552, 0x1D6A6, 0x1D6A8, 0x1D7CC, 0x1D7CE, 0x1D800, 0x1DA8C, 0x1DA9B, 0x1DAA0, 0x1DAA1, 0x1DAB0, 0x1E000, 0x1E007, 0x1E008, 0x1E019, 0x1E01B, 0x1E022, 0x1E023, 0x1E025, 0x1E026, 0x1E02B, 0x1E800, 0x1E8C5, 0x1E8C7, 0x1E8D7, 0x1E900, 0x1E94B, 0x1E950, 0x1E95A, 0x1E95E, 0x1E960, 0x1EE00, 0x1EE04, 0x1EE05, 0x1EE20, 0x1EE21, 0x1EE23, 0x1EE24, 0x1EE25, 0x1EE27, 0x1EE28, 0x1EE29, 0x1EE33, 0x1EE34, 0x1EE38, 0x1EE39, 0x1EE3A, 0x1EE3B, 0x1EE3C, 0x1EE42, 0x1EE43, 0x1EE47, 0x1EE48, 0x1EE49, 0x1EE4A, 0x1EE4B, 0x1EE4C, 0x1EE4D, 0x1EE50, 0x1EE51, 0x1EE53, 0x1EE54, 0x1EE55, 0x1EE57, 0x1EE58, 0x1EE59, 0x1EE5A, 0x1EE5B, 0x1EE5C, 0x1EE5D, 0x1EE5E, 0x1EE5F, 0x1EE60, 0x1EE61, 0x1EE63, 0x1EE64, 0x1EE65, 0x1EE67, 0x1EE6B, 0x1EE6C, 0x1EE73, 0x1EE74, 0x1EE78, 0x1EE79, 0x1EE7D, 0x1EE7E, 0x1EE7F, 0x1EE80, 0x1EE8A, 0x1EE8B, 0x1EE9C, 0x1EEA1, 0x1EEA4, 0x1EEA5, 0x1EEAA, 0x1EEAB, 0x1EEBC, 0x1EEF0, 0x1EEF2, 0x1F000, 0x1F02C, 0x1F030, 0x1F094, 0x1F0A0, 0x1F0AF, 0x1F0B1, 0x1F0C0, 0x1F0C1, 0x1F0D0, 0x1F0D1, 0x1F0F6, 0x1F100, 0x1F10D, 0x1F110, 0x1F12F, 0x1F130, 0x1F16C, 0x1F170, 0x1F1AD, 0x1F1E6, 0x1F200, 0x1F201, 0x1F203, 0x1F210, 0x1F23C, 0x1F240, 0x1F249, 0x1F250, 0x1F252, 0x1F260, 0x1F266, 0x1F300, 0x1F6D5, 0x1F6E0, 0x1F6ED, 0x1F6F0, 0x1F6F9, 0x1F700, 0x1F774, 0x1F780, 0x1F7D5, 0x1F800, 0x1F80C, 0x1F810, 0x1F848, 0x1F850, 0x1F85A, 0x1F860, 0x1F888, 0x1F890, 0x1F8AE, 0x1F900, 0x1F90C, 0x1F910, 0x1F93F, 0x1F940, 0x1F94D, 0x1F950, 0x1F96C, 0x1F980, 0x1F998, 0x1F9C0, 0x1F9C1, 0x1F9D0, 0x1F9E7, 0x20000, 0x2A6D7, 0x2A700, 0x2B735, 0x2B740, 0x2B81E, 0x2B820, 0x2CEA2, 0x2CEB0, 0x2EBE1, 0x2F800, 0x2FA1E, 0xE0001, 0xE0002, 0xE0020, 0xE0080, 0xE0100, 0xE01F0 };

        private static final UnicodeScript[] scripts = { COMMON, LATIN, COMMON, LATIN, COMMON, LATIN, COMMON, LATIN, COMMON, LATIN, COMMON, LATIN, COMMON, LATIN, COMMON, LATIN, COMMON, BOPOMOFO, COMMON, INHERITED, GREEK, COMMON, GREEK, UNKNOWN, GREEK, COMMON, GREEK, UNKNOWN, GREEK, COMMON, GREEK, COMMON, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, COPTIC, GREEK, CYRILLIC, INHERITED, CYRILLIC, UNKNOWN, ARMENIAN, UNKNOWN, ARMENIAN, UNKNOWN, ARMENIAN, UNKNOWN, COMMON, ARMENIAN, UNKNOWN, ARMENIAN, UNKNOWN, HEBREW, UNKNOWN, HEBREW, UNKNOWN, HEBREW, UNKNOWN, ARABIC, COMMON, ARABIC, COMMON, ARABIC, COMMON, ARABIC, UNKNOWN, ARABIC, COMMON, ARABIC, COMMON, ARABIC, INHERITED, ARABIC, INHERITED, ARABIC, COMMON, ARABIC, SYRIAC, UNKNOWN, SYRIAC, UNKNOWN, SYRIAC, ARABIC, THAANA, UNKNOWN, NKO, UNKNOWN, SAMARITAN, UNKNOWN, SAMARITAN, UNKNOWN, MANDAIC, UNKNOWN, MANDAIC, UNKNOWN, SYRIAC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, COMMON, ARABIC, DEVANAGARI, INHERITED, DEVANAGARI, COMMON, DEVANAGARI, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, BENGALI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GURMUKHI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, GUJARATI, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, ORIYA, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TAMIL, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, UNKNOWN, TELUGU, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, KANNADA, UNKNOWN, MALAYALAM, UNKNOWN, MALAYALAM, UNKNOWN, MALAYALAM, UNKNOWN, MALAYALAM, UNKNOWN, MALAYALAM, UNKNOWN, MALAYALAM, UNKNOWN, MALAYALAM, UNKNOWN, MALAYALAM, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, SINHALA, UNKNOWN, THAI, UNKNOWN, COMMON, THAI, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, LAO, UNKNOWN, TIBETAN, UNKNOWN, TIBETAN, UNKNOWN, TIBETAN, UNKNOWN, TIBETAN, UNKNOWN, TIBETAN, UNKNOWN, TIBETAN, COMMON, TIBETAN, UNKNOWN, MYANMAR, GEORGIAN, UNKNOWN, GEORGIAN, UNKNOWN, GEORGIAN, UNKNOWN, GEORGIAN, COMMON, GEORGIAN, HANGUL, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, CHEROKEE, UNKNOWN, CHEROKEE, UNKNOWN, CANADIAN_ABORIGINAL, OGHAM, UNKNOWN, RUNIC, COMMON, RUNIC, UNKNOWN, TAGALOG, UNKNOWN, TAGALOG, UNKNOWN, HANUNOO, COMMON, UNKNOWN, BUHID, UNKNOWN, TAGBANWA, UNKNOWN, TAGBANWA, UNKNOWN, TAGBANWA, UNKNOWN, KHMER, UNKNOWN, KHMER, UNKNOWN, KHMER, UNKNOWN, MONGOLIAN, COMMON, MONGOLIAN, COMMON, MONGOLIAN, UNKNOWN, MONGOLIAN, UNKNOWN, MONGOLIAN, UNKNOWN, MONGOLIAN, UNKNOWN, CANADIAN_ABORIGINAL, UNKNOWN, LIMBU, UNKNOWN, LIMBU, UNKNOWN, LIMBU, UNKNOWN, LIMBU, UNKNOWN, LIMBU, TAI_LE, UNKNOWN, TAI_LE, UNKNOWN, NEW_TAI_LUE, UNKNOWN, NEW_TAI_LUE, UNKNOWN, NEW_TAI_LUE, UNKNOWN, NEW_TAI_LUE, KHMER, BUGINESE, UNKNOWN, BUGINESE, TAI_THAM, UNKNOWN, TAI_THAM, UNKNOWN, TAI_THAM, UNKNOWN, TAI_THAM, UNKNOWN, TAI_THAM, UNKNOWN, INHERITED, UNKNOWN, BALINESE, UNKNOWN, BALINESE, UNKNOWN, SUNDANESE, BATAK, UNKNOWN, BATAK, LEPCHA, UNKNOWN, LEPCHA, UNKNOWN, LEPCHA, OL_CHIKI, CYRILLIC, UNKNOWN, SUNDANESE, UNKNOWN, INHERITED, COMMON, INHERITED, COMMON, INHERITED, COMMON, INHERITED, COMMON, INHERITED, COMMON, INHERITED, UNKNOWN, LATIN, GREEK, CYRILLIC, LATIN, GREEK, LATIN, GREEK, LATIN, CYRILLIC, LATIN, GREEK, INHERITED, UNKNOWN, INHERITED, LATIN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, GREEK, UNKNOWN, COMMON, INHERITED, COMMON, UNKNOWN, COMMON, LATIN, UNKNOWN, COMMON, LATIN, COMMON, UNKNOWN, LATIN, UNKNOWN, COMMON, UNKNOWN, INHERITED, UNKNOWN, COMMON, GREEK, COMMON, LATIN, COMMON, LATIN, COMMON, LATIN, COMMON, LATIN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, BRAILLE, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, GLAGOLITIC, UNKNOWN, GLAGOLITIC, UNKNOWN, LATIN, COPTIC, UNKNOWN, COPTIC, GEORGIAN, UNKNOWN, GEORGIAN, UNKNOWN, GEORGIAN, UNKNOWN, TIFINAGH, UNKNOWN, TIFINAGH, UNKNOWN, TIFINAGH, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, CYRILLIC, COMMON, UNKNOWN, HAN, UNKNOWN, HAN, UNKNOWN, HAN, UNKNOWN, COMMON, UNKNOWN, COMMON, HAN, COMMON, HAN, COMMON, HAN, INHERITED, HANGUL, COMMON, HAN, COMMON, UNKNOWN, HIRAGANA, UNKNOWN, INHERITED, COMMON, HIRAGANA, COMMON, KATAKANA, COMMON, KATAKANA, UNKNOWN, BOPOMOFO, UNKNOWN, HANGUL, UNKNOWN, COMMON, BOPOMOFO, UNKNOWN, COMMON, UNKNOWN, KATAKANA, HANGUL, UNKNOWN, COMMON, HANGUL, COMMON, KATAKANA, UNKNOWN, KATAKANA, COMMON, HAN, UNKNOWN, COMMON, HAN, UNKNOWN, YI, UNKNOWN, YI, UNKNOWN, LISU, VAI, UNKNOWN, CYRILLIC, BAMUM, UNKNOWN, COMMON, LATIN, COMMON, LATIN, UNKNOWN, LATIN, UNKNOWN, LATIN, SYLOTI_NAGRI, UNKNOWN, COMMON, UNKNOWN, PHAGS_PA, UNKNOWN, SAURASHTRA, UNKNOWN, SAURASHTRA, UNKNOWN, DEVANAGARI, UNKNOWN, KAYAH_LI, COMMON, KAYAH_LI, REJANG, UNKNOWN, REJANG, HANGUL, UNKNOWN, JAVANESE, UNKNOWN, COMMON, JAVANESE, UNKNOWN, JAVANESE, MYANMAR, UNKNOWN, CHAM, UNKNOWN, CHAM, UNKNOWN, CHAM, UNKNOWN, CHAM, MYANMAR, TAI_VIET, UNKNOWN, TAI_VIET, MEETEI_MAYEK, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, ETHIOPIC, UNKNOWN, LATIN, COMMON, LATIN, GREEK, UNKNOWN, CHEROKEE, MEETEI_MAYEK, UNKNOWN, MEETEI_MAYEK, UNKNOWN, HANGUL, UNKNOWN, HANGUL, UNKNOWN, HANGUL, UNKNOWN, HAN, UNKNOWN, HAN, UNKNOWN, LATIN, UNKNOWN, ARMENIAN, UNKNOWN, HEBREW, UNKNOWN, HEBREW, UNKNOWN, HEBREW, UNKNOWN, HEBREW, UNKNOWN, HEBREW, UNKNOWN, HEBREW, ARABIC, UNKNOWN, ARABIC, COMMON, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, INHERITED, COMMON, UNKNOWN, INHERITED, CYRILLIC, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, COMMON, UNKNOWN, COMMON, LATIN, COMMON, LATIN, COMMON, KATAKANA, COMMON, KATAKANA, COMMON, HANGUL, UNKNOWN, HANGUL, UNKNOWN, HANGUL, UNKNOWN, HANGUL, UNKNOWN, HANGUL, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, LINEAR_B, UNKNOWN, LINEAR_B, UNKNOWN, LINEAR_B, UNKNOWN, LINEAR_B, UNKNOWN, LINEAR_B, UNKNOWN, LINEAR_B, UNKNOWN, LINEAR_B, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, GREEK, UNKNOWN, COMMON, UNKNOWN, GREEK, UNKNOWN, COMMON, INHERITED, UNKNOWN, LYCIAN, UNKNOWN, CARIAN, UNKNOWN, INHERITED, COMMON, UNKNOWN, OLD_ITALIC, UNKNOWN, OLD_ITALIC, GOTHIC, UNKNOWN, OLD_PERMIC, UNKNOWN, UGARITIC, UNKNOWN, UGARITIC, OLD_PERSIAN, UNKNOWN, OLD_PERSIAN, UNKNOWN, DESERET, SHAVIAN, OSMANYA, UNKNOWN, OSMANYA, UNKNOWN, OSAGE, UNKNOWN, OSAGE, UNKNOWN, ELBASAN, UNKNOWN, CAUCASIAN_ALBANIAN, UNKNOWN, CAUCASIAN_ALBANIAN, UNKNOWN, LINEAR_A, UNKNOWN, LINEAR_A, UNKNOWN, LINEAR_A, UNKNOWN, CYPRIOT, UNKNOWN, CYPRIOT, UNKNOWN, CYPRIOT, UNKNOWN, CYPRIOT, UNKNOWN, CYPRIOT, UNKNOWN, CYPRIOT, IMPERIAL_ARAMAIC, UNKNOWN, IMPERIAL_ARAMAIC, PALMYRENE, NABATAEAN, UNKNOWN, NABATAEAN, UNKNOWN, HATRAN, UNKNOWN, HATRAN, UNKNOWN, HATRAN, PHOENICIAN, UNKNOWN, PHOENICIAN, LYDIAN, UNKNOWN, LYDIAN, UNKNOWN, MEROITIC_HIEROGLYPHS, MEROITIC_CURSIVE, UNKNOWN, MEROITIC_CURSIVE, UNKNOWN, MEROITIC_CURSIVE, KHAROSHTHI, UNKNOWN, KHAROSHTHI, UNKNOWN, KHAROSHTHI, UNKNOWN, KHAROSHTHI, UNKNOWN, KHAROSHTHI, UNKNOWN, KHAROSHTHI, UNKNOWN, KHAROSHTHI, UNKNOWN, KHAROSHTHI, UNKNOWN, OLD_SOUTH_ARABIAN, OLD_NORTH_ARABIAN, UNKNOWN, MANICHAEAN, UNKNOWN, MANICHAEAN, UNKNOWN, AVESTAN, UNKNOWN, AVESTAN, INSCRIPTIONAL_PARTHIAN, UNKNOWN, INSCRIPTIONAL_PARTHIAN, INSCRIPTIONAL_PAHLAVI, UNKNOWN, INSCRIPTIONAL_PAHLAVI, PSALTER_PAHLAVI, UNKNOWN, PSALTER_PAHLAVI, UNKNOWN, PSALTER_PAHLAVI, UNKNOWN, OLD_TURKIC, UNKNOWN, OLD_HUNGARIAN, UNKNOWN, OLD_HUNGARIAN, UNKNOWN, OLD_HUNGARIAN, UNKNOWN, ARABIC, UNKNOWN, BRAHMI, UNKNOWN, BRAHMI, UNKNOWN, BRAHMI, KAITHI, UNKNOWN, SORA_SOMPENG, UNKNOWN, SORA_SOMPENG, UNKNOWN, CHAKMA, UNKNOWN, CHAKMA, UNKNOWN, MAHAJANI, UNKNOWN, SHARADA, UNKNOWN, SHARADA, UNKNOWN, SINHALA, UNKNOWN, KHOJKI, UNKNOWN, KHOJKI, UNKNOWN, MULTANI, UNKNOWN, MULTANI, UNKNOWN, MULTANI, UNKNOWN, MULTANI, UNKNOWN, MULTANI, UNKNOWN, KHUDAWADI, UNKNOWN, KHUDAWADI, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, GRANTHA, UNKNOWN, NEWA, UNKNOWN, NEWA, UNKNOWN, NEWA, UNKNOWN, TIRHUTA, UNKNOWN, TIRHUTA, UNKNOWN, SIDDHAM, UNKNOWN, SIDDHAM, UNKNOWN, MODI, UNKNOWN, MODI, UNKNOWN, MONGOLIAN, UNKNOWN, TAKRI, UNKNOWN, TAKRI, UNKNOWN, AHOM, UNKNOWN, AHOM, UNKNOWN, AHOM, UNKNOWN, WARANG_CITI, UNKNOWN, WARANG_CITI, UNKNOWN, ZANABAZAR_SQUARE, UNKNOWN, SOYOMBO, UNKNOWN, SOYOMBO, UNKNOWN, SOYOMBO, UNKNOWN, PAU_CIN_HAU, UNKNOWN, BHAIKSUKI, UNKNOWN, BHAIKSUKI, UNKNOWN, BHAIKSUKI, UNKNOWN, BHAIKSUKI, UNKNOWN, MARCHEN, UNKNOWN, MARCHEN, UNKNOWN, MARCHEN, UNKNOWN, MASARAM_GONDI, UNKNOWN, MASARAM_GONDI, UNKNOWN, MASARAM_GONDI, UNKNOWN, MASARAM_GONDI, UNKNOWN, MASARAM_GONDI, UNKNOWN, MASARAM_GONDI, UNKNOWN, MASARAM_GONDI, UNKNOWN, CUNEIFORM, UNKNOWN, CUNEIFORM, UNKNOWN, CUNEIFORM, UNKNOWN, CUNEIFORM, UNKNOWN, EGYPTIAN_HIEROGLYPHS, UNKNOWN, ANATOLIAN_HIEROGLYPHS, UNKNOWN, BAMUM, UNKNOWN, MRO, UNKNOWN, MRO, UNKNOWN, MRO, UNKNOWN, BASSA_VAH, UNKNOWN, BASSA_VAH, UNKNOWN, PAHAWH_HMONG, UNKNOWN, PAHAWH_HMONG, UNKNOWN, PAHAWH_HMONG, UNKNOWN, PAHAWH_HMONG, UNKNOWN, PAHAWH_HMONG, UNKNOWN, MIAO, UNKNOWN, MIAO, UNKNOWN, MIAO, UNKNOWN, TANGUT, NUSHU, UNKNOWN, TANGUT, UNKNOWN, TANGUT, UNKNOWN, KATAKANA, HIRAGANA, UNKNOWN, NUSHU, UNKNOWN, DUPLOYAN, UNKNOWN, DUPLOYAN, UNKNOWN, DUPLOYAN, UNKNOWN, DUPLOYAN, UNKNOWN, DUPLOYAN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, INHERITED, COMMON, INHERITED, COMMON, INHERITED, COMMON, INHERITED, COMMON, UNKNOWN, GREEK, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, SIGNWRITING, UNKNOWN, SIGNWRITING, UNKNOWN, SIGNWRITING, UNKNOWN, GLAGOLITIC, UNKNOWN, GLAGOLITIC, UNKNOWN, GLAGOLITIC, UNKNOWN, GLAGOLITIC, UNKNOWN, GLAGOLITIC, UNKNOWN, MENDE_KIKAKUI, UNKNOWN, MENDE_KIKAKUI, UNKNOWN, ADLAM, UNKNOWN, ADLAM, UNKNOWN, ADLAM, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, ARABIC, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, HIRAGANA, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, HAN, UNKNOWN, HAN, UNKNOWN, HAN, UNKNOWN, HAN, UNKNOWN, HAN, UNKNOWN, HAN, UNKNOWN, COMMON, UNKNOWN, COMMON, UNKNOWN, INHERITED, UNKNOWN };

        private static HashMap<String, Character.UnicodeScript> aliases;

        static {
            aliases = new HashMap<>((int) (142 / 0.75f + 1.0f));
            aliases.put("ADLM", ADLAM);
            aliases.put("AGHB", CAUCASIAN_ALBANIAN);
            aliases.put("AHOM", AHOM);
            aliases.put("ARAB", ARABIC);
            aliases.put("ARMI", IMPERIAL_ARAMAIC);
            aliases.put("ARMN", ARMENIAN);
            aliases.put("AVST", AVESTAN);
            aliases.put("BALI", BALINESE);
            aliases.put("BAMU", BAMUM);
            aliases.put("BASS", BASSA_VAH);
            aliases.put("BATK", BATAK);
            aliases.put("BENG", BENGALI);
            aliases.put("BHKS", BHAIKSUKI);
            aliases.put("BOPO", BOPOMOFO);
            aliases.put("BRAH", BRAHMI);
            aliases.put("BRAI", BRAILLE);
            aliases.put("BUGI", BUGINESE);
            aliases.put("BUHD", BUHID);
            aliases.put("CAKM", CHAKMA);
            aliases.put("CANS", CANADIAN_ABORIGINAL);
            aliases.put("CARI", CARIAN);
            aliases.put("CHAM", CHAM);
            aliases.put("CHER", CHEROKEE);
            aliases.put("COPT", COPTIC);
            aliases.put("CPRT", CYPRIOT);
            aliases.put("CYRL", CYRILLIC);
            aliases.put("DEVA", DEVANAGARI);
            aliases.put("DSRT", DESERET);
            aliases.put("DUPL", DUPLOYAN);
            aliases.put("EGYP", EGYPTIAN_HIEROGLYPHS);
            aliases.put("ELBA", ELBASAN);
            aliases.put("ETHI", ETHIOPIC);
            aliases.put("GEOR", GEORGIAN);
            aliases.put("GLAG", GLAGOLITIC);
            aliases.put("GONM", MASARAM_GONDI);
            aliases.put("GOTH", GOTHIC);
            aliases.put("GRAN", GRANTHA);
            aliases.put("GREK", GREEK);
            aliases.put("GUJR", GUJARATI);
            aliases.put("GURU", GURMUKHI);
            aliases.put("HANG", HANGUL);
            aliases.put("HANI", HAN);
            aliases.put("HANO", HANUNOO);
            aliases.put("HATR", HATRAN);
            aliases.put("HEBR", HEBREW);
            aliases.put("HIRA", HIRAGANA);
            aliases.put("HLUW", ANATOLIAN_HIEROGLYPHS);
            aliases.put("HMNG", PAHAWH_HMONG);
            aliases.put("HUNG", OLD_HUNGARIAN);
            aliases.put("ITAL", OLD_ITALIC);
            aliases.put("JAVA", JAVANESE);
            aliases.put("KALI", KAYAH_LI);
            aliases.put("KANA", KATAKANA);
            aliases.put("KHAR", KHAROSHTHI);
            aliases.put("KHMR", KHMER);
            aliases.put("KHOJ", KHOJKI);
            aliases.put("KNDA", KANNADA);
            aliases.put("KTHI", KAITHI);
            aliases.put("LANA", TAI_THAM);
            aliases.put("LAOO", LAO);
            aliases.put("LATN", LATIN);
            aliases.put("LEPC", LEPCHA);
            aliases.put("LIMB", LIMBU);
            aliases.put("LINA", LINEAR_A);
            aliases.put("LINB", LINEAR_B);
            aliases.put("LISU", LISU);
            aliases.put("LYCI", LYCIAN);
            aliases.put("LYDI", LYDIAN);
            aliases.put("MAHJ", MAHAJANI);
            aliases.put("MARC", MARCHEN);
            aliases.put("MAND", MANDAIC);
            aliases.put("MANI", MANICHAEAN);
            aliases.put("MEND", MENDE_KIKAKUI);
            aliases.put("MERC", MEROITIC_CURSIVE);
            aliases.put("MERO", MEROITIC_HIEROGLYPHS);
            aliases.put("MLYM", MALAYALAM);
            aliases.put("MODI", MODI);
            aliases.put("MONG", MONGOLIAN);
            aliases.put("MROO", MRO);
            aliases.put("MTEI", MEETEI_MAYEK);
            aliases.put("MULT", MULTANI);
            aliases.put("MYMR", MYANMAR);
            aliases.put("NARB", OLD_NORTH_ARABIAN);
            aliases.put("NBAT", NABATAEAN);
            aliases.put("NEWA", NEWA);
            aliases.put("NKOO", NKO);
            aliases.put("NSHU", NUSHU);
            aliases.put("OGAM", OGHAM);
            aliases.put("OLCK", OL_CHIKI);
            aliases.put("ORKH", OLD_TURKIC);
            aliases.put("ORYA", ORIYA);
            aliases.put("OSGE", OSAGE);
            aliases.put("OSMA", OSMANYA);
            aliases.put("PALM", PALMYRENE);
            aliases.put("PAUC", PAU_CIN_HAU);
            aliases.put("PERM", OLD_PERMIC);
            aliases.put("PHAG", PHAGS_PA);
            aliases.put("PHLI", INSCRIPTIONAL_PAHLAVI);
            aliases.put("PHLP", PSALTER_PAHLAVI);
            aliases.put("PHNX", PHOENICIAN);
            aliases.put("PLRD", MIAO);
            aliases.put("PRTI", INSCRIPTIONAL_PARTHIAN);
            aliases.put("RJNG", REJANG);
            aliases.put("RUNR", RUNIC);
            aliases.put("SAMR", SAMARITAN);
            aliases.put("SARB", OLD_SOUTH_ARABIAN);
            aliases.put("SAUR", SAURASHTRA);
            aliases.put("SGNW", SIGNWRITING);
            aliases.put("SHAW", SHAVIAN);
            aliases.put("SHRD", SHARADA);
            aliases.put("SIDD", SIDDHAM);
            aliases.put("SIND", KHUDAWADI);
            aliases.put("SINH", SINHALA);
            aliases.put("SORA", SORA_SOMPENG);
            aliases.put("SOYO", SOYOMBO);
            aliases.put("SUND", SUNDANESE);
            aliases.put("SYLO", SYLOTI_NAGRI);
            aliases.put("SYRC", SYRIAC);
            aliases.put("TAGB", TAGBANWA);
            aliases.put("TAKR", TAKRI);
            aliases.put("TALE", TAI_LE);
            aliases.put("TALU", NEW_TAI_LUE);
            aliases.put("TAML", TAMIL);
            aliases.put("TANG", TANGUT);
            aliases.put("TAVT", TAI_VIET);
            aliases.put("TELU", TELUGU);
            aliases.put("TFNG", TIFINAGH);
            aliases.put("TGLG", TAGALOG);
            aliases.put("THAA", THAANA);
            aliases.put("THAI", THAI);
            aliases.put("TIBT", TIBETAN);
            aliases.put("TIRH", TIRHUTA);
            aliases.put("UGAR", UGARITIC);
            aliases.put("VAII", VAI);
            aliases.put("WARA", WARANG_CITI);
            aliases.put("XPEO", OLD_PERSIAN);
            aliases.put("XSUX", CUNEIFORM);
            aliases.put("YIII", YI);
            aliases.put("ZANB", ZANABAZAR_SQUARE);
            aliases.put("ZINH", INHERITED);
            aliases.put("ZYYY", COMMON);
            aliases.put("ZZZZ", UNKNOWN);
        }

        @Pure
        public static UnicodeScript of(int codePoint) {
            if (!isValidCodePoint(codePoint))
                throw new IllegalArgumentException(String.format("Not a valid Unicode code point: 0x%X", codePoint));
            int type = getType(codePoint);
            if (type == UNASSIGNED)
                return UNKNOWN;
            int index = Arrays.binarySearch(scriptStarts, codePoint);
            if (index < 0)
                index = -index - 2;
            return scripts[index];
        }

        @Pure
        public static final UnicodeScript forName(String scriptName) {
            scriptName = scriptName.toUpperCase(Locale.ENGLISH);
            UnicodeScript sc = aliases.get(scriptName);
            if (sc != null)
                return sc;
            return valueOf(scriptName);
        }
    }

    private final char value;

    private static final long serialVersionUID = 3786198910865385080L;

    @Pure
    @Deprecated(since = "9")
    public Character(char value) {
        this.value = value;
    }

    private static class CharacterCache {

        private CharacterCache() {
        }

        static final Character[] cache = new Character[127 + 1];

        static {
            for (int i = 0; i < cache.length; i++) cache[i] = new Character((char) i);
        }
    }

    @HotSpotIntrinsicCandidate
    public static Character valueOf(char c);

    @Pure
    @HotSpotIntrinsicCandidate
    @NonNegative
    public char charValue();

    @Pure
    @Override
    public int hashCode();

    public static int hashCode(char value);

    @Pure
    public boolean equals(@Nullable Object obj);

    @SideEffectFree
    public String toString();

    @SideEffectFree
    public static String toString(char c);

    public static String toString(int codePoint);

    @Pure
    public static boolean isValidCodePoint(int codePoint);

    @Pure
    public static boolean isBmpCodePoint(int codePoint);

    @Pure
    public static boolean isSupplementaryCodePoint(int codePoint);

    @Pure
    public static boolean isHighSurrogate(char ch);

    @Pure
    public static boolean isLowSurrogate(char ch);

    @Pure
    public static boolean isSurrogate(char ch);

    @Pure
    public static boolean isSurrogatePair(char high, char low);

    @Pure
    @Positive
    public static int charCount(int codePoint);

    @Pure
    public static int toCodePoint(char high, char low);

    @Pure
    public static int codePointAt(CharSequence seq, @IndexFor({ "#1" }) int index);

    @Pure
    public static int codePointAt(char[] a, @IndexFor({ "#1" }) int index);

    @Pure
    public static int codePointAt(char[] a, @IndexFor({ "#1" }) int index, @IndexOrHigh({ "#1" }) int limit);

    static int codePointAtImpl(char[] a, int index, int limit);

    @Pure
    public static int codePointBefore(CharSequence seq, @LTEqLengthOf({ "#1" }) @Positive int index);

    @Pure
    public static int codePointBefore(char[] a, @LTEqLengthOf({ "#1" }) @Positive int index);

    @Pure
    public static int codePointBefore(char[] a, @LTEqLengthOf({ "#1" }) @Positive int index, @IndexOrHigh({ "#1" }) int start);

    static int codePointBeforeImpl(char[] a, int index, int start);

    @Pure
    public static char highSurrogate(int codePoint);

    @Pure
    public static char lowSurrogate(int codePoint);

    @Pure
    public static int toChars(int codePoint, char[] dst, @IndexFor({ "#2" }) int dstIndex);

    @Pure
    public static char[] toChars(int codePoint);

    static void toSurrogates(int codePoint, char[] dst, int index);

    @Pure
    @NonNegative
    public static int codePointCount(CharSequence seq, @IndexOrHigh({ "#1" }) int beginIndex, @IndexOrHigh({ "#1" }) int endIndex);

    @Pure
    @NonNegative
    public static int codePointCount(char[] a, @IndexOrHigh({ "#1" }) int offset, @IndexOrHigh({ "#1" }) int count);

    static int codePointCountImpl(char[] a, int offset, int count);

    @Pure
    public static int offsetByCodePoints(CharSequence seq, @IndexOrHigh({ "#1" }) int index, int codePointOffset);

    @Pure
    @IndexOrHigh({ "#1" })
    public static int offsetByCodePoints(char[] a, @IndexOrHigh({ "#1" }) int start, @IndexOrHigh({ "#1" }) int count, @IndexOrHigh({ "#1" }) int index, int codePointOffset);

    static int offsetByCodePointsImpl(char[] a, int start, int count, int index, int codePointOffset);

    @Pure
    public static boolean isLowerCase(char ch);

    @Pure
    public static boolean isLowerCase(int codePoint);

    @Pure
    public static boolean isUpperCase(char ch);

    @Pure
    public static boolean isUpperCase(int codePoint);

    @Pure
    public static boolean isTitleCase(char ch);

    @Pure
    public static boolean isTitleCase(int codePoint);

    @Pure
    public static boolean isDigit(char ch);

    @Pure
    public static boolean isDigit(int codePoint);

    @Pure
    public static boolean isDefined(char ch);

    @Pure
    public static boolean isDefined(int codePoint);

    @Pure
    public static boolean isLetter(char ch);

    @Pure
    public static boolean isLetter(int codePoint);

    @Pure
    public static boolean isLetterOrDigit(char ch);

    @Pure
    public static boolean isLetterOrDigit(int codePoint);

    @Pure
    @Deprecated(since = "1.1")
    public static boolean isJavaLetter(char ch);

    @Pure
    @Deprecated(since = "1.1")
    public static boolean isJavaLetterOrDigit(char ch);

    @Pure
    public static boolean isAlphabetic(int codePoint);

    @Pure
    public static boolean isIdeographic(int codePoint);

    @Pure
    public static boolean isJavaIdentifierStart(char ch);

    @Pure
    public static boolean isJavaIdentifierStart(int codePoint);

    @Pure
    public static boolean isJavaIdentifierPart(char ch);

    @Pure
    public static boolean isJavaIdentifierPart(int codePoint);

    @Pure
    public static boolean isUnicodeIdentifierStart(char ch);

    @Pure
    public static boolean isUnicodeIdentifierStart(int codePoint);

    @Pure
    public static boolean isUnicodeIdentifierPart(char ch);

    @Pure
    public static boolean isUnicodeIdentifierPart(int codePoint);

    @Pure
    public static boolean isIdentifierIgnorable(char ch);

    @Pure
    public static boolean isIdentifierIgnorable(int codePoint);

    @Pure
    public static char toLowerCase(char ch);

    @Pure
    public static int toLowerCase(int codePoint);

    @Pure
    public static char toUpperCase(char ch);

    @Pure
    public static int toUpperCase(int codePoint);

    @Pure
    public static char toTitleCase(char ch);

    @Pure
    public static int toTitleCase(int codePoint);

    @Pure
    @GTENegativeOne
    public static int digit(char ch, @Positive int radix);

    @Pure
    @GTENegativeOne
    public static int digit(int codePoint, @Positive int radix);

    @Pure
    public static int getNumericValue(char ch);

    @Pure
    public static int getNumericValue(int codePoint);

    @Pure
    @Deprecated(since = "1.1")
    public static boolean isSpace(char ch);

    @Pure
    public static boolean isSpaceChar(char ch);

    @Pure
    public static boolean isSpaceChar(int codePoint);

    @Pure
    public static boolean isWhitespace(char ch);

    @Pure
    public static boolean isWhitespace(int codePoint);

    @Pure
    public static boolean isISOControl(char ch);

    @Pure
    public static boolean isISOControl(int codePoint);

    @Pure
    public static int getType(char ch);

    @Pure
    public static int getType(int codePoint);

    @Pure
    public static char forDigit(int digit, @Positive int radix);

    @Pure
    public static byte getDirectionality(char ch);

    @Pure
    public static byte getDirectionality(int codePoint);

    @Pure
    public static boolean isMirrored(char ch);

    @Pure
    public static boolean isMirrored(int codePoint);

    @Pure
    public int compareTo(Character anotherCharacter);

    @Pure
    public static int compare(char x, char y);

    static int toUpperCaseEx(int codePoint);

    static char[] toUpperCaseCharArray(int codePoint);

    @Positive
    public static final int SIZE = 16;

    public static final int BYTES = SIZE / Byte.SIZE;

    @Pure
    @HotSpotIntrinsicCandidate
    public static char reverseBytes(char ch);

    @Pure
    public static String getName(int codePoint);

    public static int codePointOf(String name);
}
