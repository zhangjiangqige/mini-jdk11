package javax.swing.plaf.synth;

import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.util.*;
import java.util.regex.*;
import sun.swing.plaf.synth.*;
import sun.swing.BakedArrayList;

@AnnotatedFor({ "regex" })
class DefaultSynthStyleFactory extends SynthStyleFactory {

    public static final int NAME = 0;

    public static final int REGION = 1;

    private List<StyleAssociation> _styles;

    private BakedArrayList<SynthStyle> _tmpList;

    private Map<BakedArrayList<SynthStyle>, SynthStyle> _resolvedStyles;

    private SynthStyle _defaultStyle;

    DefaultSynthStyleFactory() {
        _tmpList = new BakedArrayList<SynthStyle>(5);
        _styles = new ArrayList<>();
        _resolvedStyles = new HashMap<>();
    }

    public synchronized void addStyle(DefaultSynthStyle style, @Regex String path, int type) throws PatternSyntaxException;

    public synchronized SynthStyle getStyle(JComponent c, Region id);

    private SynthStyle getDefaultStyle();

    private void getMatchingStyles(List<SynthStyle> matches, JComponent c, Region id);

    private void cacheStyle(List<SynthStyle> styles, SynthStyle style);

    private SynthStyle getCachedStyle(List<SynthStyle> styles);

    private SynthStyle mergeStyles(List<SynthStyle> styles);
}
