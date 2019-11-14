package java.awt.event;

import org.checkerframework.checker.guieffect.qual.UIEffect;
import org.checkerframework.checker.guieffect.qual.UIType;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.EventListener;

@AnnotatedFor({ "guieffect" })
@UIType
public interface ActionListener extends EventListener {

    @UIEffect
    public void actionPerformed(ActionEvent e);
}
