package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class CharacterIteratorFieldDelegate implements Format.FieldDelegate {

    private ArrayList<AttributedString> attributedStrings;

    private int size;

    CharacterIteratorFieldDelegate() {
        attributedStrings = new ArrayList<>();
    }

    public void formatted(Format.Field attr, Object value, int start, int end, StringBuffer buffer);

    public void formatted(int fieldID, Format.Field attr, Object value, int start, int end, StringBuffer buffer);

    public AttributedCharacterIterator getIterator(String string);
}
