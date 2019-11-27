/*
 * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.VolatileImage;
import java.awt.image.WritableRaster;
import sun.awt.image.SunVolatileImage;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class GraphicsConfiguration {

    protected GraphicsConfiguration() {
    }

    public abstract GraphicsDevice getDevice();

    public BufferedImage createCompatibleImage(int width, int height);

    public BufferedImage createCompatibleImage(int width, int height, int transparency);

    public VolatileImage createCompatibleVolatileImage(int width, int height);

    public VolatileImage createCompatibleVolatileImage(int width, int height, int transparency);

    public VolatileImage createCompatibleVolatileImage(int width, int height, ImageCapabilities caps) throws AWTException;

    public VolatileImage createCompatibleVolatileImage(int width, int height, ImageCapabilities caps, int transparency) throws AWTException;

    public abstract ColorModel getColorModel();

    public abstract ColorModel getColorModel(int transparency);

    public abstract AffineTransform getDefaultTransform();

    public abstract AffineTransform getNormalizingTransform();

    public abstract Rectangle getBounds();

    public BufferCapabilities getBufferCapabilities();

    public ImageCapabilities getImageCapabilities();

    public boolean isTranslucencyCapable();
}
