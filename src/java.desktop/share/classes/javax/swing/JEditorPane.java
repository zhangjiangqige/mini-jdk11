/*
 * Copyright (c) 1997, 2015, Oracle and/or its affiliates. All rights reserved.
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
import java.beans.JavaBean;
import java.beans.BeanProperty;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.plaf.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import javax.accessibility.*;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "interning" })
@JavaBean(defaultProperty = "UIClassID", description = "A text component to edit various types of content.")
@SwingContainer(false)
@SuppressWarnings("serial")
public class JEditorPane extends JTextComponent {

    public JEditorPane() {
    }

    public JEditorPane(URL initialPage) throws IOException {
    }

    public JEditorPane(String url) throws IOException {
    }

    public JEditorPane(String type, String text) {
    }

    public synchronized void addHyperlinkListener(HyperlinkListener listener);

    public synchronized void removeHyperlinkListener(HyperlinkListener listener);

    @BeanProperty(bound = false)
    public synchronized HyperlinkListener[] getHyperlinkListeners();

    public void fireHyperlinkUpdate(HyperlinkEvent e);

    @BeanProperty(expert = true, description = "the URL used to set content")
    public void setPage(URL page) throws IOException;

    public void read(InputStream in, Object desc) throws IOException;

    protected InputStream getStream(URL page) throws IOException;

    @SuppressWarnings("deprecation")
    public void scrollToReference(String reference);

    public URL getPage();

    public void setPage(String url) throws IOException;

    @BeanProperty(bound = false)
    public String getUIClassID();

    protected EditorKit createDefaultEditorKit();

    public EditorKit getEditorKit();

    public final String getContentType();

    @BeanProperty(bound = false, description = "the type of content")
    public final void setContentType(String type);

    @BeanProperty(expert = true, description = "the currently installed kit for handling content")
    public void setEditorKit(EditorKit kit);

    public EditorKit getEditorKitForContentType(String type);

    public void setEditorKitForContentType(String type, EditorKit k);

    @Override
    public void replaceSelection(String content);

    @SuppressWarnings("deprecation")
    public static EditorKit createEditorKitForContentType(String type);

    public static void registerEditorKitForContentType(String type, String classname);

    public static void registerEditorKitForContentType(String type, String classname, ClassLoader loader);

    public static String getEditorKitClassNameForContentType(String type);

    public Dimension getPreferredSize();

    @BeanProperty(bound = false, description = "the text of this component")
    public void setText(String t);

    public String getText();

    @BeanProperty(bound = false)
    public boolean getScrollableTracksViewportWidth();

    @BeanProperty(bound = false)
    public boolean getScrollableTracksViewportHeight();

    @Interned
    public static final String W3C_LENGTH_UNITS;

    @Interned
    public static final String HONOR_DISPLAY_PROPERTIES;

    protected String paramString();

    @BeanProperty(bound = false)
    public AccessibleContext getAccessibleContext();

    @SuppressWarnings("serial")
    protected class AccessibleJEditorPane extends AccessibleJTextComponent {

        public String getAccessibleDescription();

        public AccessibleStateSet getAccessibleStateSet();
    }

    @SuppressWarnings("serial")
    protected class AccessibleJEditorPaneHTML extends AccessibleJEditorPane {

        public AccessibleText getAccessibleText();

        protected AccessibleJEditorPaneHTML() {
        }

        public int getAccessibleChildrenCount();

        public Accessible getAccessibleChild(int i);

        public Accessible getAccessibleAt(Point p);
    }

    protected class JEditorPaneAccessibleHypertextSupport extends AccessibleJEditorPane implements AccessibleHypertext {

        public class HTMLLink extends AccessibleHyperlink {

            public HTMLLink(Element e) {
            }

            public boolean isValid();

            public int getAccessibleActionCount();

            public boolean doAccessibleAction(int i);

            public String getAccessibleActionDescription(int i);

            public Object getAccessibleActionObject(int i);

            public Object getAccessibleActionAnchor(int i);

            public int getStartIndex();

            public int getEndIndex();
        }

        public JEditorPaneAccessibleHypertextSupport() {
        }

        public int getLinkCount();

        public int getLinkIndex(int charIndex);

        public AccessibleHyperlink getLink(int linkIndex);

        public String getLinkText(int linkIndex);
    }
}
