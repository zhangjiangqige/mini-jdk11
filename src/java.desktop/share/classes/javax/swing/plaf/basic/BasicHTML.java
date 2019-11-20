package javax.swing.plaf.basic;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import sun.swing.SwingUtilities2;

@AnnotatedFor({ "interning" })
public class BasicHTML {

    public static View createHTMLView(JComponent c, String html);

    public static int getHTMLBaseline(View view, int w, int h);

    public static boolean isHTMLString(String s);

    public static void updateRenderer(JComponent c, String text);

    @Interned
    public static final String propertyKey;

    @Interned
    public static final String documentBaseKey;
}
