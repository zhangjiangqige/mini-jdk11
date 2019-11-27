/*
 * Copyright (c) 1997, 2017, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.guieffect.qual.UIType;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.beans.*;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;
import java.util.EventListener;
import java.util.Set;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectInputValidation;
import java.io.InvalidObjectException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import static javax.swing.ClientPropertyKey.*;
import javax.accessibility.*;
import sun.awt.AWTAccessor;
import sun.awt.SunToolkit;
import sun.swing.SwingAccessor;
import sun.swing.SwingUtilities2;

@AnnotatedFor({ "interning", "guieffect" })
@UIType
@JavaBean(defaultProperty = "UIClassID")
@SuppressWarnings("serial")
public abstract class JComponent extends Container implements Serializable, TransferHandler.HasGetTransferHandler {

    protected transient ComponentUI ui;

    protected EventListenerList listenerList;

    public static final int WHEN_FOCUSED;

    public static final int WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;

    public static final int WHEN_IN_FOCUSED_WINDOW;

    public static final int UNDEFINED_CONDITION;

    @Interned
    public static final String TOOL_TIP_TEXT_KEY;

    @BeanProperty(description = "Whether or not the JPopupMenu is inherited")
    public void setInheritsPopupMenu(boolean value);

    public boolean getInheritsPopupMenu();

    @BeanProperty(preferred = true, description = "Popup to show")
    public void setComponentPopupMenu(JPopupMenu popup);

    @SuppressWarnings("deprecation")
    public JPopupMenu getComponentPopupMenu();

    public JComponent() {
    }

    public void updateUI();

    @Transient
    public ComponentUI getUI();

    @BeanProperty(hidden = true, visualUpdate = true, description = "The component's look and feel delegate.")
    protected void setUI(ComponentUI newUI);

    @BeanProperty(bound = false, expert = true, description = "UIClassID")
    public String getUIClassID();

    protected Graphics getComponentGraphics(Graphics g);

    protected void paintComponent(Graphics g);

    protected void paintChildren(Graphics g);

    protected void paintBorder(Graphics g);

    public void update(Graphics g);

    public void paint(Graphics g);

    public void printAll(Graphics g);

    public void print(Graphics g);

    protected void printComponent(Graphics g);

    protected void printChildren(Graphics g);

    protected void printBorder(Graphics g);

    @BeanProperty(bound = false)
    public boolean isPaintingTile();

    @BeanProperty(bound = false)
    public final boolean isPaintingForPrint();

    @Deprecated
    @BeanProperty(bound = false)
    public boolean isManagingFocus();

    @Deprecated
    public void setNextFocusableComponent(Component aComponent);

    @Deprecated
    public Component getNextFocusableComponent();

    public void setRequestFocusEnabled(boolean requestFocusEnabled);

    public boolean isRequestFocusEnabled();

    public void requestFocus();

    public boolean requestFocus(boolean temporary);

    public boolean requestFocusInWindow();

    protected boolean requestFocusInWindow(boolean temporary);

    public void grabFocus();

    @BeanProperty(description = "Whether the Component verifies input before accepting focus.")
    public void setVerifyInputWhenFocusTarget(boolean verifyInputWhenFocusTarget);

    public boolean getVerifyInputWhenFocusTarget();

    public FontMetrics getFontMetrics(Font font);

    @BeanProperty(preferred = true, description = "The preferred size of the component.")
    public void setPreferredSize(Dimension preferredSize);

    @Transient
    public Dimension getPreferredSize();

    @BeanProperty(description = "The maximum size of the component.")
    public void setMaximumSize(Dimension maximumSize);

    @Transient
    public Dimension getMaximumSize();

    @BeanProperty(description = "The minimum size of the component.")
    public void setMinimumSize(Dimension minimumSize);

    @Transient
    public Dimension getMinimumSize();

    public boolean contains(int x, int y);

    @BeanProperty(preferred = true, visualUpdate = true, description = "The component's border.")
    public void setBorder(Border border);

    public Border getBorder();

    @BeanProperty(expert = true)
    public Insets getInsets();

    public Insets getInsets(Insets insets);

    public float getAlignmentY();

    @BeanProperty(description = "The preferred vertical alignment of the component.")
    public void setAlignmentY(float alignmentY);

    public float getAlignmentX();

    @BeanProperty(description = "The preferred horizontal alignment of the component.")
    public void setAlignmentX(float alignmentX);

    @BeanProperty(description = "The component's input verifier.")
    public void setInputVerifier(InputVerifier inputVerifier);

    public InputVerifier getInputVerifier();

    @BeanProperty(bound = false)
    public Graphics getGraphics();

    @BeanProperty(bound = false, preferred = true, enumerationValues = { "DebugGraphics.NONE_OPTION", "DebugGraphics.LOG_OPTION", "DebugGraphics.FLASH_OPTION", "DebugGraphics.BUFFERED_OPTION" }, description = "Diagnostic options for graphics operations.")
    public void setDebugGraphicsOptions(int debugOptions);

    public int getDebugGraphicsOptions();

    public void registerKeyboardAction(ActionListener anAction, String aCommand, KeyStroke aKeyStroke, int aCondition);

    public void registerKeyboardAction(ActionListener anAction, KeyStroke aKeyStroke, int aCondition);

    public void unregisterKeyboardAction(KeyStroke aKeyStroke);

    @BeanProperty(bound = false)
    public KeyStroke[] getRegisteredKeyStrokes();

    public int getConditionForKeyStroke(KeyStroke aKeyStroke);

    public ActionListener getActionForKeyStroke(KeyStroke aKeyStroke);

    public void resetKeyboardActions();

    public final void setInputMap(int condition, InputMap map);

    public final InputMap getInputMap(int condition);

    public final InputMap getInputMap();

    public final void setActionMap(ActionMap am);

    public final ActionMap getActionMap();

    public int getBaseline(int width, int height);

    @BeanProperty(bound = false)
    public BaselineResizeBehavior getBaselineResizeBehavior();

    @Deprecated
    public boolean requestDefaultFocus();

    @BeanProperty(hidden = true, visualUpdate = true)
    public void setVisible(boolean aFlag);

    @BeanProperty(expert = true, preferred = true, visualUpdate = true, description = "The enabled state of the component.")
    public void setEnabled(boolean enabled);

    @BeanProperty(preferred = true, visualUpdate = true, description = "The foreground color of the component.")
    public void setForeground(Color fg);

    @BeanProperty(preferred = true, visualUpdate = true, description = "The background color of the component.")
    public void setBackground(Color bg);

    @BeanProperty(preferred = true, visualUpdate = true, description = "The font for the component.")
    public void setFont(Font font);

    public static Locale getDefaultLocale();

    public static void setDefaultLocale(Locale l);

    protected void processComponentKeyEvent(KeyEvent e);

    protected void processKeyEvent(KeyEvent e);

    @SuppressWarnings("deprecation")
    protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed);

    @BeanProperty(bound = false, preferred = true, description = "The text to display in a tool tip.")
    public void setToolTipText(String text);

    public String getToolTipText();

    public String getToolTipText(MouseEvent event);

    public Point getToolTipLocation(MouseEvent event);

    public Point getPopupLocation(MouseEvent event);

    public JToolTip createToolTip();

    public void scrollRectToVisible(Rectangle aRect);

    @BeanProperty(bound = false, expert = true, description = "Determines if this component automatically scrolls its contents when dragged.")
    public void setAutoscrolls(boolean autoscrolls);

    public boolean getAutoscrolls();

    @BeanProperty(hidden = true, description = "Mechanism for transfer of data to and from the component")
    public void setTransferHandler(TransferHandler newHandler);

    public TransferHandler getTransferHandler();

    protected void processMouseEvent(MouseEvent e);

    protected void processMouseMotionEvent(MouseEvent e);

    @Deprecated
    public void enable();

    @Deprecated
    public void disable();

    @SuppressWarnings("serial")
    public abstract class AccessibleJComponent extends AccessibleAWTContainer implements AccessibleExtendedComponent {

        protected AccessibleJComponent() {
        }

        @Deprecated
        protected FocusListener accessibleFocusHandler;

        protected class AccessibleContainerHandler implements ContainerListener {

            public void componentAdded(ContainerEvent e);

            public void componentRemoved(ContainerEvent e);
        }

        @Deprecated
        protected class AccessibleFocusHandler implements FocusListener {

            public void focusGained(FocusEvent event);

            public void focusLost(FocusEvent event);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener);

        public void removePropertyChangeListener(PropertyChangeListener listener);

        protected String getBorderTitle(Border b);

        public String getAccessibleName();

        public String getAccessibleDescription();

        public AccessibleRole getAccessibleRole();

        public AccessibleStateSet getAccessibleStateSet();

        public int getAccessibleChildrenCount();

        public Accessible getAccessibleChild(int i);

        public String getToolTipText();

        public String getTitledBorderText();

        public AccessibleKeyBinding getAccessibleKeyBinding();
    }

    public final Object getClientProperty(Object key);

    public final void putClientProperty(Object key, Object value);

    public void setFocusTraversalKeys(int id, Set<? extends AWTKeyStroke> keystrokes);

    public static boolean isLightweightComponent(Component c);

    @Deprecated
    public void reshape(int x, int y, int w, int h);

    public Rectangle getBounds(Rectangle rv);

    public Dimension getSize(Dimension rv);

    public Point getLocation(Point rv);

    @BeanProperty(bound = false)
    public int getX();

    @BeanProperty(bound = false)
    public int getY();

    @BeanProperty(bound = false)
    public int getWidth();

    @BeanProperty(bound = false)
    public int getHeight();

    public boolean isOpaque();

    @BeanProperty(expert = true, description = "The component's opacity")
    public void setOpaque(boolean isOpaque);

    public void computeVisibleRect(Rectangle visibleRect);

    @BeanProperty(bound = false)
    public Rectangle getVisibleRect();

    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue);

    public void firePropertyChange(String propertyName, int oldValue, int newValue);

    public void firePropertyChange(String propertyName, char oldValue, char newValue);

    protected void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws java.beans.PropertyVetoException;

    public synchronized void addVetoableChangeListener(VetoableChangeListener listener);

    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener);

    @BeanProperty(bound = false)
    public synchronized VetoableChangeListener[] getVetoableChangeListeners();

    @BeanProperty(bound = false)
    @SuppressWarnings("deprecation")
    public Container getTopLevelAncestor();

    public void addAncestorListener(AncestorListener listener);

    public void removeAncestorListener(AncestorListener listener);

    @BeanProperty(bound = false)
    public AncestorListener[] getAncestorListeners();

    @SuppressWarnings("unchecked")
    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    public void addNotify();

    public void removeNotify();

    public void repaint(long tm, int x, int y, int width, int height);

    public void repaint(Rectangle r);

    @SafeEffect
    public void revalidate();

    @Override
    public boolean isValidateRoot();

    @BeanProperty(bound = false)
    public boolean isOptimizedDrawingEnabled();

    protected boolean isPaintingOrigin();

    public void paintImmediately(int x, int y, int w, int h);

    public void paintImmediately(Rectangle r);

    public void setDoubleBuffered(boolean aFlag);

    public boolean isDoubleBuffered();

    @BeanProperty(bound = false)
    public JRootPane getRootPane();

    protected String paramString();

    @Override
    @Deprecated
    public void hide();
}
