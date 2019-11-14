package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.BufferCapabilities;
import java.awt.Graphics;
import java.awt.Image;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class BufferStrategy {

    public abstract BufferCapabilities getCapabilities();

    public abstract Graphics getDrawGraphics();

    public abstract boolean contentsLost();

    public abstract boolean contentsRestored();

    public abstract void show();

    public void dispose();
}
