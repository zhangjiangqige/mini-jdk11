package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class BufferCapabilities implements Cloneable {

    public BufferCapabilities(ImageCapabilities frontCaps, ImageCapabilities backCaps, FlipContents flipContents) {
    }

    public ImageCapabilities getFrontBufferCapabilities();

    public ImageCapabilities getBackBufferCapabilities();

    public boolean isPageFlipping();

    public FlipContents getFlipContents();

    public boolean isFullScreenRequired();

    public boolean isMultiBufferAvailable();

    public Object clone();

    public static final class FlipContents extends AttributeValue {

        public static final FlipContents UNDEFINED;

        public static final FlipContents BACKGROUND;

        public static final FlipContents PRIOR;

        public static final FlipContents COPIED;
    }
}
