package com.g50.gui;

import java.io.IOException;

public interface GUIObserver {
    void addPendingKBDAction(GUI.KBD_ACTION action) throws IOException;
}
