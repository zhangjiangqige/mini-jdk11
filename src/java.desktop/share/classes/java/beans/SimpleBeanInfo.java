package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class SimpleBeanInfo implements BeanInfo {

    @Override
    public BeanDescriptor getBeanDescriptor();

    @Override
    public PropertyDescriptor[] getPropertyDescriptors();

    @Override
    public int getDefaultPropertyIndex();

    @Override
    public EventSetDescriptor[] getEventSetDescriptors();

    @Override
    public int getDefaultEventIndex();

    @Override
    public MethodDescriptor[] getMethodDescriptors();

    @Override
    public BeanInfo[] getAdditionalBeanInfo();

    @Override
    public Image getIcon(final int iconKind);

    private Image loadStandardImage(final String resourceName);

    private Image loadImage(final String resourceName, final String suffix);

    public Image loadImage(final String resourceName);
}
