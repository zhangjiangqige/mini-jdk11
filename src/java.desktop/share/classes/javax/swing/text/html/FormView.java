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
    public static final String SUBMIT = new String("Submit Query");

    @Deprecated
    @Interned
    public static final String RESET = new String("Reset");

    static final String PostDataProperty = "javax.swing.JEditorPane.postdata";

    private short maxIsPreferred;

    public FormView(Element elem) {
        super(elem);
    }

    protected Component createComponent();

    private JComponent createInputComponent(AttributeSet attr, Object model);

    private void removeStaleListenerForModel(Object model);

    public float getMaximumSpan(int axis);

    public void actionPerformed(ActionEvent evt);

    protected void submitData(String data);

    private void storePostData(HTMLDocument doc, String target, String data);

    protected class MouseEventListener extends MouseAdapter {

        public void mouseReleased(MouseEvent evt);
    }

    protected void imageSubmit(String imageData);

    @SuppressWarnings("deprecation")
    private String getImageData(Point point);

    private Element getFormElement();

    private void getFormData(StringBuilder buffer);

    private void loadElementDataIntoBuffer(Element elem, StringBuilder buffer);

    private String getInputElementData(AttributeSet attr);

    private String getTextAreaData(AttributeSet attr);

    private void loadSelectData(AttributeSet attr, StringBuilder buffer);

    @SuppressWarnings("deprecation")
    private void appendBuffer(StringBuilder buffer, String name, String value);

    private boolean isControl(Element elem);

    boolean isLastTextOrPasswordField();

    void resetForm();

    private class BrowseFileAction implements ActionListener {

        private AttributeSet attrs;

        private Document model;

        BrowseFileAction(AttributeSet attrs, Document model) {
            this.attrs = attrs;
            this.model = model;
        }

        public void actionPerformed(ActionEvent ae);
    }
}
