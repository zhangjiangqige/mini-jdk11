package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.io.ObjectStreamField;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map.Entry;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class VetoableChangeSupport implements Serializable {

    public VetoableChangeSupport(Object sourceBean) {
    }

    public void addVetoableChangeListener(VetoableChangeListener listener);

    public void removeVetoableChangeListener(VetoableChangeListener listener);

    public VetoableChangeListener[] getVetoableChangeListeners();

    public void addVetoableChangeListener(String propertyName, VetoableChangeListener listener);

    public void removeVetoableChangeListener(String propertyName, VetoableChangeListener listener);

    public VetoableChangeListener[] getVetoableChangeListeners(String propertyName);

    public void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws PropertyVetoException;

    public void fireVetoableChange(String propertyName, int oldValue, int newValue) throws PropertyVetoException;

    public void fireVetoableChange(String propertyName, boolean oldValue, boolean newValue) throws PropertyVetoException;

    public void fireVetoableChange(PropertyChangeEvent event) throws PropertyVetoException;

    public boolean hasListeners(String propertyName);
}
