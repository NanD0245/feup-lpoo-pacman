package g50.model.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreditsMenu extends Menu {
    List<String> text;
    public CreditsMenu() {
        super("CREDITS", Collections.singletonList(ENTRIES.RETURN_ENTER));

        this.text = Arrays.asList("\t\t LPOO 20/21", "", "", "", "",
                "  PROGRAMMERS & PRODUCERS:", "", "", "",
                "\t\tBRUNO MENDES", "", "",
                "\t\t NUNO COSTA", "", "",
                "\t   FERNANDO REGO");
    }

    public String getText(int i) {
        return text.get(i);
    }

    public int getLinesNumber() {
        return text.size();
    }
}
