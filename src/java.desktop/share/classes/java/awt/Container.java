/*
 * Copyright (c) 1995, 2017, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.guieffect.qual.SafeEffect;
import org.checkerframework.checker.guieffect.qual.UIType;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.dnd.DropTarget;
import java.awt.event.*;
import java.awt.peer.ContainerPeer;
import java.awt.peer.ComponentPeer;
import java.awt.peer.LightweightPeer;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;
import javax.accessibility.*;
import sun.util.logging.PlatformLogger;
import sun.awt.AppContext;
import sun.awt.AWTAccessor;
import sun.awt.AWTAccessor.MouseEventAccessor;
import sun.awt.PeerEvent;
import sun.awt.SunToolkit;
import sun.awt.dnd.SunDropTargetEvent;
import sun.java2d.pipe.Region;
import sun.security.action.GetBooleanAction;

@AnnotatedFor({ "guieffect" })
@UIType
public class Container extends Component {

    public Container() {
    }

    public int getComponentCount();

    @Deprecated
    public int countComponents();

    public Component getComponent(int n);

    public Component[] getComponents();

    public Insets getInsets();

    @Deprecated
    public Insets insets();

    public Component add(Component comp);

    public Component add(String name, Component comp);

    public Component add(Component comp, int index);

    public void setComponentZOrder(Component comp, int index);

    public int getComponentZOrder(Component comp);

    public void add(Component comp, Object constraints);

    public void add(Component comp, Object constraints, int index);

    protected void addImpl(Component comp, Object constraints, int index);

    public void remove(int index);

    public void remove(Component comp);

    public void removeAll();

    public LayoutManager getLayout();

    public void setLayout(LayoutManager mgr);

    public void doLayout();

    @Deprecated
    public void layout();

    public boolean isValidateRoot();

    @SafeEffect
    @Override
    public void invalidate();

    public void validate();

    protected void validateTree();

    public void setFont(Font f);

    public Dimension getPreferredSize();

    @Deprecated
    public Dimension preferredSize();

    public Dimension getMinimumSize();

    @Deprecated
    public Dimension minimumSize();

    public Dimension getMaximumSize();

    public float getAlignmentX();

    public float getAlignmentY();

    public void paint(Graphics g);

    public void update(Graphics g);

    public void print(Graphics g);

    public void paintComponents(Graphics g);

    public void printComponents(Graphics g);

    public synchronized void addContainerListener(ContainerListener l);

    public synchronized void removeContainerListener(ContainerListener l);

    public synchronized ContainerListener[] getContainerListeners();

    public <T extends EventListener> T[] getListeners(Class<T> listenerType);

    protected void processEvent(AWTEvent e);

    protected void processContainerEvent(ContainerEvent e);

    @Deprecated
    public void deliverEvent(Event e);

    public Component getComponentAt(int x, int y);

    @Deprecated
    public Component locate(int x, int y);

    public Component getComponentAt(Point p);

    public Point getMousePosition(boolean allowChildren) throws HeadlessException;

    public Component findComponentAt(int x, int y);

    public Component findComponentAt(Point p);

    public void addNotify();

    public void removeNotify();

    public boolean isAncestorOf(Component c);

    protected String paramString();

    public void list(PrintStream out, int indent);

    public void list(PrintWriter out, int indent);

    public void setFocusTraversalKeys(int id, Set<? extends AWTKeyStroke> keystrokes);

    public Set<AWTKeyStroke> getFocusTraversalKeys(int id);

    public boolean areFocusTraversalKeysSet(int id);

    public boolean isFocusCycleRoot(Container container);

    public void setFocusTraversalPolicy(FocusTraversalPolicy policy);

    public FocusTraversalPolicy getFocusTraversalPolicy();

    public boolean isFocusTraversalPolicySet();

    public void setFocusCycleRoot(boolean focusCycleRoot);

    public boolean isFocusCycleRoot();

    public final void setFocusTraversalPolicyProvider(boolean provider);

    public final boolean isFocusTraversalPolicyProvider();

    public void transferFocusDownCycle();

    public void applyComponentOrientation(ComponentOrientation o);

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    protected class AccessibleAWTContainer extends AccessibleAWTComponent {

        public int getAccessibleChildrenCount();

        public Accessible getAccessibleChild(int i);

        public Accessible getAccessibleAt(Point p);

        protected ContainerListener accessibleContainerHandler;

        protected class AccessibleContainerHandler implements ContainerListener, Serializable {

            public void componentAdded(ContainerEvent e);

            public void componentRemoved(ContainerEvent e);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener);

        public void removePropertyChangeListener(PropertyChangeListener listener);
    }
}
