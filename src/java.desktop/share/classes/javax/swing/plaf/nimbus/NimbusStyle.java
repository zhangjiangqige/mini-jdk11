package javax.swing.plaf.nimbus;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.swing.Painter;
import javax.swing.JComponent;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.synth.ColorType;
import static javax.swing.plaf.synth.SynthConstants.*;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthPainter;
import javax.swing.plaf.synth.SynthStyle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@AnnotatedFor({ "interning" })
public final class NimbusStyle extends SynthStyle {

    @Interned
    public static final String LARGE_KEY;

    @Interned
    public static final String SMALL_KEY;

    @Interned
    public static final String MINI_KEY;

    public static final double LARGE_SCALE;

    public static final double SMALL_SCALE;

    public static final double MINI_SCALE;

    @Override
    public void installDefaults(SynthContext ctx);

    @Override
    public Insets getInsets(SynthContext ctx, Insets in);

    @Override
    protected Color getColorForState(SynthContext ctx, ColorType type);

    @Override
    protected Font getFontForState(SynthContext ctx);

    @Override
    public SynthPainter getPainter(SynthContext ctx);

    @Override
    public boolean isOpaque(SynthContext ctx);

    @Override
    public Object get(SynthContext ctx, Object key);

    public Painter<Object> getBackgroundPainter(SynthContext ctx);

    public Painter<Object> getForegroundPainter(SynthContext ctx);

    public Painter<Object> getBorderPainter(SynthContext ctx);
}
