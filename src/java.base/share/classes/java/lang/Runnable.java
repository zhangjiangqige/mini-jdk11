package java.lang;

import org.checkerframework.checker.guieffect.qual.PolyUI;
import org.checkerframework.checker.guieffect.qual.PolyUIEffect;
import org.checkerframework.checker.guieffect.qual.PolyUIType;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "guieffect", "nullness" })
@PolyUIType
@FunctionalInterface
public interface Runnable {

    @PolyUIEffect
    public abstract void run(@PolyUI Runnable this);
}
