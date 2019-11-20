package javax.swing.text.html;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

@AnnotatedFor({ "interning" })
public class FormView extends ComponentView implements ActionListener {

    @Deprecated
    @Interned
    public static final String SUBMIT;

    @Deprecated
    @Interned
    public static final String RESET;

    public FormView(Element elem) {
    }

    protected Component createComponent();

    public float getMaximumSpan(int axis);

    public void actionPerformed(ActionEvent evt);

    protected void submitData(String data);

    protected class MouseEventListener extends MouseAdapter {

        public void mouseReleased(MouseEvent evt);
    }

    protected void imageSubmit(String imageData);
}
