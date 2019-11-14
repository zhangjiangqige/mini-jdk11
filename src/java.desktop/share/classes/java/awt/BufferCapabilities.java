package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class BufferCapabilities implements Cloneable {

    private ImageCapabilities frontCaps;

    private ImageCapabilities backCaps;

    private FlipContents flipContents;

    public BufferCapabilities(ImageCapabilities frontCaps, ImageCapabilities backCaps, FlipContents flipContents) {
        if (frontCaps == null || backCaps == null) {
            throw new IllegalArgumentException("Image capabilities specified cannot be null");
        }
        this.frontCaps = frontCaps;
        this.backCaps = backCaps;
        this.flipContents = flipContents;
    }

    public ImageCapabilities getFrontBufferCapabilities();

    public ImageCapabilities getBackBufferCapabilities();

    public boolean isPageFlipping();

    public FlipContents getFlipContents();

    public boolean isFullScreenRequired();

    public boolean isMultiBufferAvailable();

    public Object clone();

    public static final class FlipContents extends AttributeValue {

        private static int I_UNDEFINED = 0;

        private static int I_BACKGROUND = 1;

        private static int I_PRIOR = 2;

        private static int I_COPIED = 3;

        private static final String[] NAMES = { "undefined", "background", "prior", "copied" };

        public static final FlipContents UNDEFINED = new FlipContents(I_UNDEFINED);

        public static final FlipContents BACKGROUND = new FlipContents(I_BACKGROUND);

        public static final FlipContents PRIOR = new FlipContents(I_PRIOR);

        public static final FlipContents COPIED = new FlipContents(I_COPIED);

        private FlipContents(int type) {
            super(type, NAMES);
        }
    }
}
