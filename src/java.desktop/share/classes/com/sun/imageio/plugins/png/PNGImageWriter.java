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

final class CRC {

    private static final int[] crcTable = new int[256];

    private int crc = 0xffffffff;

    static {
        for (int n = 0; n < 256; n++) {
            int c = n;
            for (int k = 0; k < 8; k++) {
                if ((c & 1) == 1) {
                    c = 0xedb88320 ^ (c >>> 1);
                } else {
                    c >>>= 1;
                }
                crcTable[n] = c;
            }
        }
    }

    CRC() {
    }

    void reset();

    void update(byte[] data, int off, int len);

    void update(int data);

    int getValue();
}

final class ChunkStream extends ImageOutputStreamImpl {

    private final ImageOutputStream stream;

    private final long startPos;

    private final CRC crc = new CRC();

    ChunkStream(int type, ImageOutputStream stream) throws IOException {
        this.stream = stream;
        this.startPos = stream.getStreamPosition();
        stream.writeInt(-1);
        writeInt(type);
    }

    @Override
    public int read() throws IOException;

    @Override
    public int read(byte[] b, int off, int len) throws IOException;

    @Override
    public void write(@PolySigned byte[] b, int off, int len) throws IOException;

    @Override
    public void write(int b) throws IOException;

    void finish() throws IOException;

    @Override
    @SuppressWarnings("deprecation")
    protected void finalize() throws Throwable;
}

final class IDATOutputStream extends ImageOutputStreamImpl {

    private static final byte[] chunkType = { (byte) 'I', (byte) 'D', (byte) 'A', (byte) 'T' };

    private final ImageOutputStream stream;

    private final int chunkLength;

    private long startPos;

    private final CRC crc = new CRC();

    private final Deflater def;

    private final byte[] buf = new byte[512];

    private final byte[] wbuf1 = new byte[1];

    private int bytesRemaining;

    IDATOutputStream(ImageOutputStream stream, int chunkLength, int deflaterLevel) throws IOException {
        this.stream = stream;
        this.chunkLength = chunkLength;
        this.def = new Deflater(deflaterLevel);
        startChunk();
    }

    private void startChunk() throws IOException;

    private void finishChunk() throws IOException;

    @Override
    public int read() throws IOException;

    @Override
    public int read(byte[] b, int off, int len) throws IOException;

    @Override
    public void write(@PolySigned byte[] b, int off, int len) throws IOException;

    void deflate() throws IOException;

    @Override
    public void write(int b) throws IOException;

    void finish() throws IOException;

    @Override
    @SuppressWarnings("deprecation")
    protected void finalize() throws Throwable;
}

final class PNGImageWriteParam extends ImageWriteParam {

    private static final float DEFAULT_QUALITY = 0.5f;

    private static final String[] compressionNames = { "Deflate" };

    private static final float[] qualityVals = { 0.00F, 0.30F, 0.75F, 1.00F };

    private static final String[] qualityDescs = { "High compression", "Medium compression", "Low compression" };

    PNGImageWriteParam(Locale locale) {
        super();
        this.canWriteProgressive = true;
        this.locale = locale;
        this.canWriteCompressed = true;
        this.compressionTypes = compressionNames;
        this.compressionType = compressionTypes[0];
        this.compressionMode = MODE_DEFAULT;
        this.compressionQuality = DEFAULT_QUALITY;
    }

    @Override
    public void unsetCompression();

    @Override
    public boolean isCompressionLossless();

    @Override
    public String[] getCompressionQualityDescriptions();

    @Override
    public float[] getCompressionQualityValues();
}

public final class PNGImageWriter extends ImageWriter {

    private static final int DEFAULT_COMPRESSION_LEVEL = 4;

    ImageOutputStream stream = null;

    PNGMetadata metadata = null;

    int sourceXOffset = 0;

    int sourceYOffset = 0;

    int sourceWidth = 0;

    int sourceHeight = 0;

    int[] sourceBands = null;

    int periodX = 1;

    int periodY = 1;

    int numBands;

    int bpp;

    RowFilter rowFilter = new RowFilter();

    byte[] prevRow = null;

    byte[] currRow = null;

    byte[][] filteredRows = null;

    int[] sampleSize = null;

    int scalingBitDepth = -1;

    byte[][] scale = null;

    byte[] scale0 = null;

    byte[][] scaleh = null;

    byte[][] scalel = null;

    int totalPixels;

    int pixelsDone;

    public PNGImageWriter(ImageWriterSpi originatingProvider) {
        super(originatingProvider);
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

    private void write_magic() throws IOException;

    private void write_IHDR() throws IOException;

    private void write_cHRM() throws IOException;

    private void write_gAMA() throws IOException;

    private void write_iCCP() throws IOException;

    private void write_sBIT() throws IOException;

    private void write_sRGB() throws IOException;

    private void write_PLTE() throws IOException;

    private void write_hIST() throws IOException, IIOException;

    private void write_tRNS() throws IOException, IIOException;

    private void write_bKGD() throws IOException;

    private void write_pHYs() throws IOException;

    private void write_sPLT() throws IOException;

    private void write_tIME() throws IOException;

    private void write_tEXt() throws IOException;

    private byte[] deflate(byte[] b) throws IOException;

    private void write_iTXt() throws IOException;

    private void write_zTXt() throws IOException;

    private void writeUnknownChunks() throws IOException;

    private static int chunkType(String typeString);

    private void encodePass(ImageOutputStream os, RenderedImage image, int xOffset, int yOffset, int xSkip, int ySkip) throws IOException;

    private void write_IDAT(RenderedImage image, int deflaterLevel) throws IOException;

    private void writeIEND() throws IOException;

    private boolean equals(int[] s0, int[] s1);

    private void initializeScaleTables(int[] sampleSize);

    @Override
    public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IIOException;
}
