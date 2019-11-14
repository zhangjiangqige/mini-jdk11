package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class FocusTraversalPolicy {

    public abstract Component getComponentAfter(Container aContainer, Component aComponent);

    public abstract Component getComponentBefore(Container aContainer, Component aComponent);

    public abstract Component getFirstComponent(Container aContainer);

    public abstract Component getLastComponent(Container aContainer);

    public abstract Component getDefaultComponent(Container aContainer);

    public Component getInitialComponent(Window window);
}
