package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.*;
import java.beans.JavaBean;
import java.beans.BeanProperty;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.plaf.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import javax.accessibility.*;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "interning" })
@JavaBean(defaultProperty = "UIClassID", description = "A text component to edit various types of content.")
@SwingContainer(false)
@SuppressWarnings("serial")
public class JEditorPane extends JTextComponent {

    public JEditorPane() {
        super();
        setFocusCycleRoot(true);
        setFocusTraversalPolicy(new LayoutFocusTraversalPolicy() {

            public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
                if (focusCycleRoot != JEditorPane.this || (!isEditable() && getComponentCount() > 0)) {
                    return super.getComponentAfter(focusCycleRoot, aComponent);
                } else {
                    Container rootAncestor = getFocusCycleRootAncestor();
                    return (rootAncestor != null) ? rootAncestor.getFocusTraversalPolicy().getComponentAfter(rootAncestor, JEditorPane.this) : null;
                }
            }

            public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
                if (focusCycleRoot != JEditorPane.this || (!isEditable() && getComponentCount() > 0)) {
                    return super.getComponentBefore(focusCycleRoot, aComponent);
                } else {
                    Container rootAncestor = getFocusCycleRootAncestor();
                    return (rootAncestor != null) ? rootAncestor.getFocusTraversalPolicy().getComponentBefore(rootAncestor, JEditorPane.this) : null;
                }
            }

            public Component getDefaultComponent(Container focusCycleRoot) {
                return (focusCycleRoot != JEditorPane.this || (!isEditable() && getComponentCount() > 0)) ? super.getDefaultComponent(focusCycleRoot) : null;
            }

            protected boolean accept(Component aComponent) {
                return (aComponent != JEditorPane.this) ? super.accept(aComponent) : false;
            }
        });
        LookAndFeel.installProperty(this, "focusTraversalKeysForward", JComponent.getManagingFocusForwardTraversalKeys());
        LookAndFeel.installProperty(this, "focusTraversalKeysBackward", JComponent.getManagingFocusBackwardTraversalKeys());
    }

    public JEditorPane(URL initialPage) throws IOException {
        this();
        setPage(initialPage);
    }

    public JEditorPane(String url) throws IOException {
        this();
        setPage(url);
    }

    public JEditorPane(String type, String text) {
        this();
        setContentType(type);
        setText(text);
    }

    public synchronized void addHyperlinkListener(HyperlinkListener listener);

    public synchronized void removeHyperlinkListener(HyperlinkListener listener);

    @BeanProperty(bound = false)
    public synchronized HyperlinkListener[] getHyperlinkListeners();

    public void fireHyperlinkUpdate(HyperlinkEvent e);

    @BeanProperty(expert = true, description = "the URL used to set content")
    public void setPage(URL page) throws IOException;

    private Document initializeModel(EditorKit kit, URL page);

    private int getAsynchronousLoadPriority(Document doc);

    public void read(InputStream in, Object desc) throws IOException;

    void read(InputStream in, Document doc) throws IOException;

    class PageLoader extends SwingWorker<URL, Object> {

        PageLoader(Document doc, InputStream in, URL old, URL page) {
            this.in = in;
            this.old = old;
            this.page = page;
            this.doc = doc;
        }

        protected URL doInBackground();

        InputStream in;

        URL old;

        URL page;

        Document doc;
    }

    protected InputStream getStream(URL page) throws IOException;

    private void handleConnectionProperties(URLConnection conn);

    private Object getPostData();

    private void handlePostData(HttpURLConnection conn, Object postData) throws IOException;

    @SuppressWarnings("deprecation")
    public void scrollToReference(String reference);

    public URL getPage();

    public void setPage(String url) throws IOException;

    @BeanProperty(bound = false)
    public String getUIClassID();

    protected EditorKit createDefaultEditorKit();

    public EditorKit getEditorKit();

    public final String getContentType();

    @BeanProperty(bound = false, description = "the type of content")
    public final void setContentType(String type);

    private void setCharsetFromContentTypeParameters(String paramlist);

    @BeanProperty(expert = true, description = "the currently installed kit for handling content")
    public void setEditorKit(EditorKit kit);

    public EditorKit getEditorKitForContentType(String type);

    public void setEditorKitForContentType(String type, EditorKit k);

    @Override
    public void replaceSelection(String content);

    @SuppressWarnings("deprecation")
    public static EditorKit createEditorKitForContentType(String type);

    public static void registerEditorKitForContentType(String type, String classname);

    public static void registerEditorKitForContentType(String type, String classname, ClassLoader loader);

    public static String getEditorKitClassNameForContentType(String type);

    private static Hashtable<String, String> getKitTypeRegistry();

    private static Hashtable<String, ClassLoader> getKitLoaderRegistry();

    private static Hashtable<String, EditorKit> getKitRegisty();

    private static void loadDefaultKitsIfNecessary();

    public Dimension getPreferredSize();

    @BeanProperty(bound = false, description = "the text of this component")
    public void setText(String t);

    public String getText();

    @BeanProperty(bound = false)
    public boolean getScrollableTracksViewportWidth();

    @BeanProperty(bound = false)
    public boolean getScrollableTracksViewportHeight();

    private void writeObject(ObjectOutputStream s) throws IOException;

    private SwingWorker<URL, Object> pageLoader;

    private EditorKit kit;

    private boolean isUserSetEditorKit;

    private Hashtable<String, Object> pageProperties;

    static final String PostDataProperty = "javax.swing.JEditorPane.postdata";

    private Hashtable<String, EditorKit> typeHandlers;

    private static final Object kitRegistryKey = new StringBuffer("JEditorPane.kitRegistry");

    private static final Object kitTypeRegistryKey = new StringBuffer("JEditorPane.kitTypeRegistry");

    private static final Object kitLoaderRegistryKey = new StringBuffer("JEditorPane.kitLoaderRegistry");

    private static final String uiClassID = "EditorPaneUI";

    @Interned
    public static final String W3C_LENGTH_UNITS = "JEditorPane.w3cLengthUnits";

    @Interned
    public static final String HONOR_DISPLAY_PROPERTIES = "JEditorPane.honorDisplayProperties";

    static final Map<String, String> defaultEditorKitMap = new HashMap<String, String>(0);

    protected String paramString();

    @BeanProperty(bound = false)
    public AccessibleContext getAccessibleContext();

    @SuppressWarnings("serial")
    protected class AccessibleJEditorPane extends AccessibleJTextComponent {

        public String getAccessibleDescription();

        public AccessibleStateSet getAccessibleStateSet();
    }

    @SuppressWarnings("serial")
    protected class AccessibleJEditorPaneHTML extends AccessibleJEditorPane {

        private AccessibleContext accessibleContext;

        public AccessibleText getAccessibleText();

        protected AccessibleJEditorPaneHTML() {
            HTMLEditorKit kit = (HTMLEditorKit) JEditorPane.this.getEditorKit();
            accessibleContext = kit.getAccessibleContext();
        }

        public int getAccessibleChildrenCount();

        public Accessible getAccessibleChild(int i);

        public Accessible getAccessibleAt(Point p);
    }

    protected class JEditorPaneAccessibleHypertextSupport extends AccessibleJEditorPane implements AccessibleHypertext {

        public class HTMLLink extends AccessibleHyperlink {

            Element element;

            public HTMLLink(Element e) {
                element = e;
            }

            public boolean isValid();

            public int getAccessibleActionCount();

            public boolean doAccessibleAction(int i);

            public String getAccessibleActionDescription(int i);

            public Object getAccessibleActionObject(int i);

            public Object getAccessibleActionAnchor(int i);

            public int getStartIndex();

            public int getEndIndex();
        }

        private class LinkVector extends Vector<HTMLLink> {

            public int baseElementIndex(Element e);
        }

        LinkVector hyperlinks;

        boolean linksValid = false;

        private void buildLinkTable();

        public JEditorPaneAccessibleHypertextSupport() {
            hyperlinks = new LinkVector();
            Document d = JEditorPane.this.getDocument();
            if (d != null) {
                d.addDocumentListener(new DocumentListener() {

                    public void changedUpdate(DocumentEvent theEvent) {
                        linksValid = false;
                    }

                    public void insertUpdate(DocumentEvent theEvent) {
                        linksValid = false;
                    }

                    public void removeUpdate(DocumentEvent theEvent) {
                        linksValid = false;
                    }
                });
            }
        }

        public int getLinkCount();

        public int getLinkIndex(int charIndex);

        public AccessibleHyperlink getLink(int linkIndex);

        public String getLinkText(int linkIndex);
    }

    static class PlainEditorKit extends DefaultEditorKit implements ViewFactory {

        public ViewFactory getViewFactory();

        public View create(Element elem);

        View createI18N(Element elem);

        static class PlainParagraph extends javax.swing.text.ParagraphView {

            PlainParagraph(Element elem) {
                super(elem);
                layoutPool = new LogicalView(elem);
                layoutPool.setParent(this);
            }

            protected void setPropertiesFromAttributes();

            public int getFlowSpan(int index);

            protected SizeRequirements calculateMinorAxisRequirements(int axis, SizeRequirements r);

            static class LogicalView extends CompositeView {

                LogicalView(Element elem) {
                    super(elem);
                }

                protected int getViewIndexAtPosition(int pos);

                protected boolean updateChildren(DocumentEvent.ElementChange ec, DocumentEvent e, ViewFactory f);

                protected void loadChildren(ViewFactory f);

                public float getPreferredSpan(int axis);

                protected void forwardUpdateToView(View v, DocumentEvent e, Shape a, ViewFactory f);

                public void paint(Graphics g, Shape allocation);

                protected boolean isBefore(int x, int y, Rectangle alloc);

                protected boolean isAfter(int x, int y, Rectangle alloc);

                protected View getViewAtPoint(int x, int y, Rectangle alloc);

                protected void childAllocation(int index, Rectangle a);
            }
        }
    }

    static class HeaderParser {

        String raw;

        String[][] tab;

        public HeaderParser(String raw) {
            this.raw = raw;
            tab = new String[10][2];
            parse();
        }

        private void parse();

        public String findKey(int i);

        public String findValue(int i);

        public String findValue(String key);

        public String findValue(String k, String Default);

        public int findInt(String k, int Default);
    }
}
