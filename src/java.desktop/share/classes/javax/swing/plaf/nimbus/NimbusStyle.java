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
    public static final String LARGE_KEY = "large";

    @Interned
    public static final String SMALL_KEY = "small";

    @Interned
    public static final String MINI_KEY = "mini";

    public static final double LARGE_SCALE = 1.15;

    public static final double SMALL_SCALE = 0.857;

    public static final double MINI_SCALE = 0.714;

    private static final Object NULL = '\0';

    private static final Color DEFAULT_COLOR = new ColorUIResource(Color.BLACK);

    private static final Comparator<RuntimeState> STATE_COMPARATOR = new Comparator<RuntimeState>() {

        @Override
        public int compare(RuntimeState a, RuntimeState b) {
            return a.state - b.state;
        }
    };

    private String prefix;

    private SynthPainter painter;

    private Values values;

    private CacheKey tmpKey = new CacheKey("", 0);

    private WeakReference<JComponent> component;

    NimbusStyle(String prefix, JComponent c) {
        if (c != null) {
            this.component = new WeakReference<JComponent>(c);
        }
        this.prefix = prefix;
        this.painter = new SynthPainterImpl(this);
    }

    @Override
    public void installDefaults(SynthContext ctx);

    private void validate();

    private Painter<Object> getPainter(Map<String, Object> defaults, String key);

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

    @SuppressWarnings("unchecked")
    private static Painter<Object> paintFilter(@SuppressWarnings("rawtypes") Painter painter);

    public Painter<Object> getBackgroundPainter(SynthContext ctx);

    public Painter<Object> getForegroundPainter(SynthContext ctx);

    public Painter<Object> getBorderPainter(SynthContext ctx);

    private Values getValues(SynthContext ctx);

    private boolean contains(String[] names, String name);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private int getExtendedState(SynthContext ctx, Values v);

    private RuntimeState getNextState(RuntimeState[] states, int[] lastState, int xstate);

    private final class RuntimeState implements Cloneable {

        int state;

        Painter<Object> backgroundPainter;

        Painter<Object> foregroundPainter;

        Painter<Object> borderPainter;

        String stateName;

        UIDefaults defaults = new UIDefaults(10, .7f);

        private RuntimeState(int state, String stateName) {
            this.state = state;
            this.stateName = stateName;
        }

        @Override
        public String toString();

        @Override
        public RuntimeState clone();
    }

    private static final class Values {

        State<?>[] stateTypes = null;

        RuntimeState[] states = null;

        Insets contentMargins;

        UIDefaults defaults = new UIDefaults(10, .7f);

        Map<CacheKey, Object> cache = new HashMap<CacheKey, Object>();
    }

    private static final class CacheKey {

        private String key;

        private int xstate;

        CacheKey(Object key, int xstate) {
            init(key, xstate);
        }

        void init(Object key, int xstate);

        @Override
        public boolean equals(Object obj);

        @Override
        public int hashCode();
    }
}
