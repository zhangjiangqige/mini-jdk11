package com.sun.imageio.plugins.png;

import org.checkerframework.checker.signedness.qual.PolySigned;
import java.awt.Rectangle;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.ImageOutputStreamImpl;

public final class PNGImageWriter extends ImageWriter {

    public PNGImageWriter(ImageWriterSpi originatingProvider) {
    }

    @Override
    public void setOutput(Object output);

    @Override
    public ImageWriteParam getDefaultWriteParam();

    @Override
    public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param);

    @Override
    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param);

    @Override
    public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param);

    @Override
    public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param);

    @Override
    public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IIOException;
}
