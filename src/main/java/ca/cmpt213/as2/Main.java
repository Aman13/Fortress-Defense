package ca.cmpt213.as2;

import ca.cmpt213.as2.logic.GameLogic;
import ca.cmpt213.as2.ui.TextUi;

public class Main {

    public static void main(String[] args) {
        GameLogic logic = new GameLogic();
        TextUi textUi = new TextUi(logic);
        textUi.runGame();
    }
}
