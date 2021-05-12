package g50.model.menu;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<String> entries;
    private int currentEntry;

    public Menu() {
        this.entries = Arrays.asList("START", "CONTROLS", "CREDITS", "HIGH-SCORE", "EXIT");
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

    public boolean isSelectedStart() {
        return (currentEntry == 0);
    }

    public boolean isSelectedExit() {
        return (currentEntry == 4);
    }

    public int getCurrentEntry() {
        return currentEntry;
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
