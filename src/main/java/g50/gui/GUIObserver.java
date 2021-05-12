package g50.gui;

import java.io.IOException;

public interface GUIObserver {
    void addPendingAction(GUI.ACTION action) throws IOException;
}
