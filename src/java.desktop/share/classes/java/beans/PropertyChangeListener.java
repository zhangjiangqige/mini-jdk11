package java.beans;

import org.checkerframework.checker.guieffect.qual.PolyUI;
import org.checkerframework.checker.guieffect.qual.PolyUIEffect;
import org.checkerframework.checker.guieffect.qual.PolyUIType;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "guieffect" })
@PolyUIType
public interface PropertyChangeListener extends java.util.EventListener {
}
