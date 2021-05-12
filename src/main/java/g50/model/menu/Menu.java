package g50.model.menu;

import java.util.List;

public abstract class Menu {
    protected final List<ENTRIES> entries;
    protected int currentEntry;
    protected String title;

    public enum ENTRIES {START, CONTROLS, CREDITS, HIGH_SCORE, EXIT, RESUME, RETURN_ENTER}

    public Menu(String title, List<ENTRIES> entries) {
        this.entries = entries;
        this.currentEntry = 0;
        this.title = title;
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry >= this.entries.size()) currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0) currentEntry = this.entries.size() - 1;
    }

    public ENTRIES getEntry(int i) {
        return entries.get(i);
    }

    public int getCurrentEntry() {
        return currentEntry;
    }

    public int getNumberEntries() {
        return this.entries.size();
    }

    public String getTitle() {
        return title;
    }
}
