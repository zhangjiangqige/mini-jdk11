/*
 * Copyright (c) 1998, 2015, Oracle and/or its affiliates. All rights reserved.
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
package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.*;
import java.awt.event.*;
import java.beans.JavaBean;
import java.beans.BeanProperty;
import java.io.*;
import java.util.*;
import javax.swing.colorchooser.*;
import javax.swing.plaf.ColorChooserUI;
import javax.accessibility.*;
import sun.swing.SwingUtilities2;

@AnnotatedFor({ "interning" })
@JavaBean(defaultProperty = "UI", description = "A component that supports selecting a Color.")
@SwingContainer(false)
@SuppressWarnings("serial")
public class JColorChooser extends JComponent implements Accessible {

    @Interned
    public static final String SELECTION_MODEL_PROPERTY;

    @Interned
    public static final String PREVIEW_PANEL_PROPERTY;

    @Interned
    public static final String CHOOSER_PANELS_PROPERTY;

    public static Color showDialog(Component component, String title, Color initialColor) throws HeadlessException;

    @SuppressWarnings("deprecation")
    public static Color showDialog(Component component, String title, Color initialColor, boolean colorTransparencySelectionEnabled) throws HeadlessException;

    public static JDialog createDialog(Component c, String title, boolean modal, JColorChooser chooserPane, ActionListener okListener, ActionListener cancelListener) throws HeadlessException;

    public JColorChooser() {
    }

    public JColorChooser(Color initialColor) {
    }

    public JColorChooser(ColorSelectionModel model) {
    }

    public ColorChooserUI getUI();

    @BeanProperty(hidden = true, description = "The UI object that implements the color chooser's LookAndFeel.")
    public void setUI(ColorChooserUI ui);

    public void updateUI();

    @BeanProperty(bound = false)
    public String getUIClassID();

    public Color getColor();

    @BeanProperty(bound = false, description = "The current color the chooser is to display.")
    public void setColor(Color color);

    public void setColor(int r, int g, int b);

    public void setColor(int c);

    @BeanProperty(bound = false, description = "Determines whether automatic drag handling is enabled.")
    public void setDragEnabled(boolean b);

    public boolean getDragEnabled();

    @BeanProperty(hidden = true, description = "The UI component which displays the current color.")
    public void setPreviewPanel(JComponent preview);

    public JComponent getPreviewPanel();

    public void addChooserPanel(AbstractColorChooserPanel panel);

    public AbstractColorChooserPanel removeChooserPanel(AbstractColorChooserPanel panel);

    @BeanProperty(hidden = true, description = "An array of different chooser types.")
    public void setChooserPanels(AbstractColorChooserPanel[] panels);

    public AbstractColorChooserPanel[] getChooserPanels();

    public ColorSelectionModel getSelectionModel();

    @BeanProperty(hidden = true, description = "The model which contains the currently selected color.")
    public void setSelectionModel(ColorSelectionModel newModel);

    protected String paramString();

    protected AccessibleContext accessibleContext;

    @BeanProperty(bound = false)
    public AccessibleContext getAccessibleContext();

    protected class AccessibleJColorChooser extends AccessibleJComponent {

        public AccessibleRole getAccessibleRole();
    }
}
