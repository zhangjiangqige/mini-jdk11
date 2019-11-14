package java.beans.beancontext;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class BeanContextChildSupport implements BeanContextChild, BeanContextServicesListener, Serializable {

    static final long serialVersionUID = 6328947014421475877L;

    public BeanContextChildSupport() {
        super();
        beanContextChildPeer = this;
        pcSupport = new PropertyChangeSupport(beanContextChildPeer);
        vcSupport = new VetoableChangeSupport(beanContextChildPeer);
    }

    public BeanContextChildSupport(BeanContextChild bcc) {
        super();
        beanContextChildPeer = (bcc != null) ? bcc : this;
        pcSupport = new PropertyChangeSupport(beanContextChildPeer);
        vcSupport = new VetoableChangeSupport(beanContextChildPeer);
    }

    public synchronized void setBeanContext(BeanContext bc) throws PropertyVetoException;

    public synchronized BeanContext getBeanContext();

    public void addPropertyChangeListener(String name, PropertyChangeListener pcl);

    public void removePropertyChangeListener(String name, PropertyChangeListener pcl);

    public void addVetoableChangeListener(String name, VetoableChangeListener vcl);

    public void removeVetoableChangeListener(String name, VetoableChangeListener vcl);

    public void serviceRevoked(BeanContextServiceRevokedEvent bcsre);

    public void serviceAvailable(BeanContextServiceAvailableEvent bcsae);

    public BeanContextChild getBeanContextChildPeer();

    public boolean isDelegated();

    public void firePropertyChange(String name, Object oldValue, Object newValue);

    public void fireVetoableChange(String name, Object oldValue, Object newValue) throws PropertyVetoException;

    public boolean validatePendingSetBeanContext(BeanContext newValue);

    protected void releaseBeanContextResources();

    protected void initializeBeanContextResources();

    private void writeObject(ObjectOutputStream oos) throws IOException;

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException;

    public BeanContextChild beanContextChildPeer;

    protected PropertyChangeSupport pcSupport;

    protected VetoableChangeSupport vcSupport;

    protected transient BeanContext beanContext;

    protected transient boolean rejectedSetBCOnce;
}
