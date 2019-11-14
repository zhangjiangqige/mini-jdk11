package javax.swing.text.rtf;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.lang.*;

@AnnotatedFor("signedness")
abstract class AbstractFilter extends OutputStream {

    protected char[] translationTable;

    protected boolean[] specialsTable;

    static final char[] latin1TranslationTable;

    static final boolean[] noSpecialsTable;

    static final boolean[] allSpecialsTable;

    static {
        int i;
        noSpecialsTable = new boolean[256];
        for (i = 0; i < 256; i++) noSpecialsTable[i] = false;
        allSpecialsTable = new boolean[256];
        for (i = 0; i < 256; i++) allSpecialsTable[i] = true;
        latin1TranslationTable = new char[256];
        for (i = 0; i < 256; i++) latin1TranslationTable[i] = (char) i;
    }

    public void readFromStream(InputStream in) throws IOException;

    public void readFromReader(Reader in) throws IOException;

    public AbstractFilter() {
        translationTable = latin1TranslationTable;
        specialsTable = noSpecialsTable;
    }

    public void write(int b) throws IOException;

    public void write(@PolySigned byte[] buf, int off, int len) throws IOException;

    public void write(String s) throws IOException;

    protected abstract void write(char ch) throws IOException;

    protected abstract void writeSpecial(int b) throws IOException;
}
