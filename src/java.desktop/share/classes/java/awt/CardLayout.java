package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CardLayout implements LayoutManager2, Serializable {

    private static final long serialVersionUID = -4328196481005934313L;

    Vector<Card> vector = new Vector<>();

    class Card implements Serializable {

        static final long serialVersionUID = 6640330810709497518L;

        public String name;

        public Component comp;

        public Card(String cardName, Component cardComponent) {
            name = cardName;
            comp = cardComponent;
        }
    }

    int currentCard = 0;

    int hgap;

    int vgap;

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("tab", Hashtable.class), new ObjectStreamField("hgap", Integer.TYPE), new ObjectStreamField("vgap", Integer.TYPE), new ObjectStreamField("vector", Vector.class), new ObjectStreamField("currentCard", Integer.TYPE) };

    public CardLayout() {
        this(0, 0);
    }

    public CardLayout(int hgap, int vgap) {
        this.hgap = hgap;
        this.vgap = vgap;
    }

    public int getHgap();

    public void setHgap(int hgap);

    public int getVgap();

    public void setVgap(int vgap);

    public void addLayoutComponent(Component comp, Object constraints);

    @Deprecated
    public void addLayoutComponent(String name, Component comp);

    public void removeLayoutComponent(Component comp);

    public Dimension preferredLayoutSize(Container parent);

    public Dimension minimumLayoutSize(Container parent);

    public Dimension maximumLayoutSize(Container target);

    public float getLayoutAlignmentX(Container parent);

    public float getLayoutAlignmentY(Container parent);

    public void invalidateLayout(Container target);

    public void layoutContainer(Container parent);

    void checkLayout(Container parent);

    public void first(Container parent);

    public void next(Container parent);

    public void previous(Container parent);

    void showDefaultComponent(Container parent);

    public void last(Container parent);

    public void show(Container parent, String name);

    public String toString();

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException;

    private void writeObject(ObjectOutputStream s) throws IOException;
}
