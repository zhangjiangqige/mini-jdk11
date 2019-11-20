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

    public int getValue();

    public synchronized void addAdjustmentListener(AdjustmentListener l);

    public synchronized void removeAdjustmentListener(AdjustmentListener l);

    public synchronized AdjustmentListener[] getAdjustmentListeners();

    public String toString();

    public String paramString();
}
