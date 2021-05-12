package g50.model.menu;

import java.util.Arrays;
import java.util.List;

public abstract class Menu {
    protected final List<String> entries;
    protected int currentEntry;

    public Menu(List<String> entries) {
        this.entries = entries;
        this.currentEntry = 0;
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry >= this.entries.size()) currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0) currentEntry = this.entries.size() - 1;
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public int getCurrentEntry() {
        return currentEntry;
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
