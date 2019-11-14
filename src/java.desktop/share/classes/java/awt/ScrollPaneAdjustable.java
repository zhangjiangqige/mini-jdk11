package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.awt.AWTAccessor;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.peer.ScrollPanePeer;
import java.io.Serializable;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ScrollPaneAdjustable implements Adjustable, Serializable {

    private ScrollPane sp;

    private int orientation;

    private int value;

    private int minimum;

    private int maximum;

    private int visibleAmount;

    private transient boolean isAdjusting;

    private int unitIncrement = 1;

    private int blockIncrement = 1;

    private AdjustmentListener adjustmentListener;

    private static final String SCROLLPANE_ONLY = "Can be set by scrollpane only";

    private static native void initIDs();

    static {
        Toolkit.loadLibraries();
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
        AWTAccessor.setScrollPaneAdjustableAccessor(new AWTAccessor.ScrollPaneAdjustableAccessor() {

            public void setTypedValue(final ScrollPaneAdjustable adj, final int v, final int type) {
                adj.setTypedValue(v, type);
            }
        });
    }

    private static final long serialVersionUID = -3359745691033257079L;

    ScrollPaneAdjustable(ScrollPane sp, AdjustmentListener l, int orientation) {
        this.sp = sp;
        this.orientation = orientation;
        addAdjustmentListener(l);
    }

    void setSpan(int min, int max, int visible);

    public int getOrientation();

    public void setMinimum(int min);

    public int getMinimum();

    public void setMaximum(int max);

    public int getMaximum();

    public synchronized void setUnitIncrement(int u);

    public int getUnitIncrement();

    public synchronized void setBlockIncrement(int b);

    public int getBlockIncrement();

    public void setVisibleAmount(int v);

    public int getVisibleAmount();

    public void setValueIsAdjusting(boolean b);

    public boolean getValueIsAdjusting();

    public void setValue(int v);

    private void setTypedValue(int v, int type);

    public int getValue();

    public synchronized void addAdjustmentListener(AdjustmentListener l);

    public synchronized void removeAdjustmentListener(AdjustmentListener l);

    public synchronized AdjustmentListener[] getAdjustmentListeners();

    public String toString();

    public String paramString();
}
