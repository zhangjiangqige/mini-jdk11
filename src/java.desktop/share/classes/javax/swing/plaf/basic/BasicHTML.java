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

    static int getBaseline(JComponent c, int y, int ascent, int w, int h);

    static int getBaseline(View view, int w, int h);

    private static int getBaseline(View view, Shape bounds);

    private static boolean hasParagraph(View view);

    public static boolean isHTMLString(String s);

    public static void updateRenderer(JComponent c, String text);

    private static final String htmlDisable = "html.disable";

    @Interned
    public static final String propertyKey = "html";

    @Interned
    public static final String documentBaseKey = "html.base";

    static BasicEditorKit getFactory();

    private static BasicEditorKit basicHTMLFactory;

    private static ViewFactory basicHTMLViewFactory;

    private static final String styleChanges = "p { margin-top: 0; margin-bottom: 0; margin-left: 0; margin-right: 0 }" + "body { margin-top: 0; margin-bottom: 0; margin-left: 0; margin-right: 0 }";

    @SuppressWarnings("serial")
    static class BasicEditorKit extends HTMLEditorKit {

        private static StyleSheet defaultStyles;

        public StyleSheet getStyleSheet();

        public Document createDefaultDocument(Font defaultFont, Color foreground);

        public ViewFactory getViewFactory();
    }

    static class BasicHTMLViewFactory extends HTMLEditorKit.HTMLFactory {

        public View create(Element elem);
    }

    @SuppressWarnings("serial")
    static class BasicDocument extends HTMLDocument {

        BasicDocument(StyleSheet s, Font defaultFont, Color foreground) {
            super(s);
            setPreservesUnknownTags(false);
            setFontAndColor(defaultFont, foreground);
        }

        private void setFontAndColor(Font font, Color fg);
    }

    static class Renderer extends View {

        Renderer(JComponent c, ViewFactory f, View v) {
            super(null);
            host = c;
            factory = f;
            view = v;
            view.setParent(this);
            setSize(view.getPreferredSpan(X_AXIS), view.getPreferredSpan(Y_AXIS));
        }

        public AttributeSet getAttributes();

        public float getPreferredSpan(int axis);

        public float getMinimumSpan(int axis);

        public float getMaximumSpan(int axis);

        public void preferenceChanged(View child, boolean width, boolean height);

        public float getAlignment(int axis);

        public void paint(Graphics g, Shape allocation);

        public void setParent(View parent);

        public int getViewCount();

        public View getView(int n);

        public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException;

        public Shape modelToView(int p0, Position.Bias b0, int p1, Position.Bias b1, Shape a) throws BadLocationException;

        public int viewToModel(float x, float y, Shape a, Position.Bias[] bias);

        public Document getDocument();

        public int getStartOffset();

        public int getEndOffset();

        public Element getElement();

        public void setSize(float width, float height);

        public Container getContainer();

        public ViewFactory getViewFactory();

        private int width;

        private View view;

        private ViewFactory factory;

        private JComponent host;
    }
}
