package java.beans;

import org.checkerframework.checker.fenum.qual.FenumTop;
import org.checkerframework.checker.guieffect.qual.PolyUI;
import org.checkerframework.checker.guieffect.qual.PolyUIEffect;
import org.checkerframework.checker.guieffect.qual.PolyUIType;
import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.io.ObjectStreamField;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map.Entry;

@AnnotatedFor({ "fenum", "guieffect", "interning" })
@PolyUIType
@UsesObjectEquals
public class PropertyChangeSupport implements Serializable {

    @SafeEffect
    public PropertyChangeSupport(@PolyUI Object sourceBean) {
    }

    @PolyUIEffect
    public void addPropertyChangeListener(@PolyUI PropertyChangeSupport this, @PolyUI PropertyChangeListener listener);

    @PolyUIEffect
    public void removePropertyChangeListener(@PolyUI PropertyChangeSupport this, PropertyChangeListener listener);

    @PolyUIEffect
    @PolyUI
    public PropertyChangeListener[] getPropertyChangeListeners(@PolyUI PropertyChangeSupport this);

    @PolyUIEffect
    public void addPropertyChangeListener(@PolyUI PropertyChangeSupport this, String propertyName, @PolyUI PropertyChangeListener listener);

    @PolyUIEffect
    public void removePropertyChangeListener(@PolyUI PropertyChangeSupport this, String propertyName, PropertyChangeListener listener);

    @PolyUIEffect
    @PolyUI
    public PropertyChangeListener[] getPropertyChangeListeners(@PolyUI PropertyChangeSupport this, String propertyName);

    @PolyUIEffect
    public void firePropertyChange(@PolyUI PropertyChangeSupport this, String propertyName, @FenumTop Object oldValue, @FenumTop Object newValue);

    @PolyUIEffect
    public void firePropertyChange(@PolyUI PropertyChangeSupport this, String propertyName, @FenumTop int oldValue, @FenumTop int newValue);

    @PolyUIEffect
    public void firePropertyChange(@PolyUI PropertyChangeSupport this, String propertyName, boolean oldValue, boolean newValue);

    @PolyUIEffect
    public void firePropertyChange(@PolyUI PropertyChangeSupport this, PropertyChangeEvent event);

    @PolyUIEffect
    public void fireIndexedPropertyChange(@PolyUI PropertyChangeSupport this, String propertyName, int index, Object oldValue, Object newValue);

    @PolyUIEffect
    public void fireIndexedPropertyChange(@PolyUI PropertyChangeSupport this, String propertyName, int index, int oldValue, int newValue);

    @PolyUIEffect
    public void fireIndexedPropertyChange(@PolyUI PropertyChangeSupport this, String propertyName, int index, boolean oldValue, boolean newValue);

    @PolyUIEffect
    public boolean hasListeners(@PolyUI PropertyChangeSupport this, String propertyName);
}
