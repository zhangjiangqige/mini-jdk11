package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import sun.awt.image.IntegerComponentRaster;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class ColorPaintContext implements PaintContext {

    int color;

    WritableRaster savedTile;

    protected ColorPaintContext(int color, ColorModel cm) {
        this.color = color;
    }

    public void dispose();

    int getRGB();

    public ColorModel getColorModel();

    public synchronized Raster getRaster(int x, int y, int w, int h);
}
