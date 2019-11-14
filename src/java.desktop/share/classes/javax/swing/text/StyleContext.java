package javax.swing.text;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.ChangeEvent;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import sun.font.FontUtilities;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class StyleContext implements Serializable, AbstractDocument.AttributeContext {

    public static final StyleContext getDefaultStyleContext();

    private static StyleContext defaultContext;

    public StyleContext() {
        styles = new NamedStyle(null);
        addStyle(DEFAULT_STYLE, null);
    }

    public Style addStyle(String nm, Style parent);

    public void removeStyle(String nm);

    public Style getStyle(String nm);

    public Enumeration<?> getStyleNames();

    public void addChangeListener(ChangeListener l);

    public void removeChangeListener(ChangeListener l);

    public ChangeListener[] getChangeListeners();

    public Font getFont(AttributeSet attr);

    public Color getForeground(AttributeSet attr);

    public Color getBackground(AttributeSet attr);

    public Font getFont(String family, int style, int size);

    @SuppressWarnings("deprecation")
    public FontMetrics getFontMetrics(Font f);

    public synchronized AttributeSet addAttribute(AttributeSet old, Object name, Object value);

    public synchronized AttributeSet addAttributes(AttributeSet old, AttributeSet attr);

    public synchronized AttributeSet removeAttribute(AttributeSet old, Object name);

    public synchronized AttributeSet removeAttributes(AttributeSet old, Enumeration<?> names);

    public synchronized AttributeSet removeAttributes(AttributeSet old, AttributeSet attrs);

    public AttributeSet getEmptySet();

    public void reclaim(AttributeSet a);

    protected int getCompressionThreshold();

    protected SmallAttributeSet createSmallAttributeSet(AttributeSet a);

    protected MutableAttributeSet createLargeAttributeSet(AttributeSet a);

    synchronized void removeUnusedSets();

    AttributeSet getImmutableUniqueSet();

    MutableAttributeSet getMutableAttributeSet(AttributeSet a);

    public String toString();

    public void writeAttributes(ObjectOutputStream out, AttributeSet a) throws IOException;

    public void readAttributes(ObjectInputStream in, MutableAttributeSet a) throws ClassNotFoundException, IOException;

    public static void writeAttributeSet(ObjectOutputStream out, AttributeSet a) throws IOException;

    public static void readAttributeSet(ObjectInputStream in, MutableAttributeSet a) throws ClassNotFoundException, IOException;

    public static void registerStaticAttributeKey(Object key);

    public static Object getStaticAttribute(Object key);

    public static Object getStaticAttributeKey(Object key);

    private void writeObject(java.io.ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

    @Interned
    public static final String DEFAULT_STYLE = "default";

    private static Hashtable<Object, String> freezeKeyMap;

    private static Hashtable<String, Object> thawKeyMap;

    private Style styles;

    private transient FontKey fontSearch = new FontKey(null, 0, 0);

    private transient Hashtable<FontKey, Font> fontTable = new Hashtable<>();

    private transient Map<SmallAttributeSet, WeakReference<SmallAttributeSet>> attributesPool = Collections.synchronizedMap(new WeakHashMap<SmallAttributeSet, WeakReference<SmallAttributeSet>>());

    private transient MutableAttributeSet search = new SimpleAttributeSet();

    private int unusedSets;

    static final int THRESHOLD = 9;

    public class SmallAttributeSet implements AttributeSet {

        public SmallAttributeSet(Object[] attributes) {
            this.attributes = Arrays.copyOf(attributes, attributes.length);
            updateResolveParent();
        }

        public SmallAttributeSet(AttributeSet attrs) {
            int n = attrs.getAttributeCount();
            Object[] tbl = new Object[2 * n];
            Enumeration<?> names = attrs.getAttributeNames();
            int i = 0;
            while (names.hasMoreElements()) {
                tbl[i] = names.nextElement();
                tbl[i + 1] = attrs.getAttribute(tbl[i]);
                i += 2;
            }
            attributes = tbl;
            updateResolveParent();
        }

        private void updateResolveParent();

        Object getLocalAttribute(Object nm);

        public String toString();

        public int hashCode();

        public boolean equals(Object obj);

        public Object clone();

        public int getAttributeCount();

        public boolean isDefined(Object key);

        public boolean isEqual(AttributeSet attr);

        public AttributeSet copyAttributes();

        public Object getAttribute(Object key);

        public Enumeration<?> getAttributeNames();

        public boolean containsAttribute(Object name, Object value);

        public boolean containsAttributes(AttributeSet attrs);

        public AttributeSet getResolveParent();

        Object[] attributes;

        AttributeSet resolveParent;
    }

    class KeyEnumeration implements Enumeration<Object> {

        KeyEnumeration(Object[] attr) {
            this.attr = attr;
            i = 0;
        }

        public boolean hasMoreElements();

        public Object nextElement();

        Object[] attr;

        int i;
    }

    class KeyBuilder {

        public void initialize(AttributeSet a);

        private void initialize(Object[] sorted);

        public Object[] createTable();

        int getCount();

        public void addAttribute(Object key, Object value);

        public void addAttributes(AttributeSet attr);

        public void removeAttribute(Object key);

        public void removeAttributes(Enumeration<?> names);

        public void removeAttributes(AttributeSet attr);

        private void removeSearchAttribute(Object ikey, Object value);

        private Vector<Object> keys = new Vector<Object>();

        private Vector<Object> data = new Vector<Object>();
    }

    static class FontKey {

        private String family;

        private int style;

        private int size;

        public FontKey(String family, int style, int size) {
            setValue(family, style, size);
        }

        public void setValue(String family, int style, int size);

        public int hashCode();

        public boolean equals(Object obj);
    }

    @SuppressWarnings("serial")
    public class NamedStyle implements Style, Serializable {

        public NamedStyle(String name, Style parent) {
            attributes = getEmptySet();
            if (name != null) {
                setName(name);
            }
            if (parent != null) {
                setResolveParent(parent);
            }
        }

        public NamedStyle(Style parent) {
            this(null, parent);
        }

        public NamedStyle() {
            attributes = getEmptySet();
        }

        public String toString();

        public String getName();

        public void setName(String name);

        public void addChangeListener(ChangeListener l);

        public void removeChangeListener(ChangeListener l);

        public ChangeListener[] getChangeListeners();

        protected void fireStateChanged();

        public <T extends EventListener> T[] getListeners(Class<T> listenerType);

        public int getAttributeCount();

        public boolean isDefined(Object attrName);

        public boolean isEqual(AttributeSet attr);

        public AttributeSet copyAttributes();

        public Object getAttribute(Object attrName);

        public Enumeration<?> getAttributeNames();

        public boolean containsAttribute(Object name, Object value);

        public boolean containsAttributes(AttributeSet attrs);

        public AttributeSet getResolveParent();

        public void addAttribute(Object name, Object value);

        public void addAttributes(AttributeSet attr);

        public void removeAttribute(Object name);

        public void removeAttributes(Enumeration<?> names);

        public void removeAttributes(AttributeSet attrs);

        public void setResolveParent(AttributeSet parent);

        private void writeObject(ObjectOutputStream s) throws IOException;

        private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

        protected EventListenerList listenerList = new EventListenerList();

        protected transient ChangeEvent changeEvent = null;

        private transient AttributeSet attributes;
    }

    static {
        try {
            int n = StyleConstants.keys.length;
            for (int i = 0; i < n; i++) {
                StyleContext.registerStaticAttributeKey(StyleConstants.keys[i]);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
