package domain.controller;

import domain.exception.InputException;
import domain.input.MoneyInput;
import domain.input.PlayerInput;
import domain.tools.ToolBox;
import domain.user.Dealer;
import domain.user.Player;
import domain.view.Announcer;

import java.util.List;

public class PlayerCreateManager {
    Dealer dealer;
    CardManager cardManager;
    PlayerInput input;
    ToolBox toolbox;
    Announcer announce;
    InputException inputException;
    MoneyInput moneyinput;
    Player[] players;

    final static int MAKE_TWO_CARD = 2;

    public PlayerCreateManager() {
        cardManager = new CardManager();
        input = new PlayerInput();
        toolbox = new ToolBox();
        announce = new Announcer();
        inputException = new InputException();
        moneyinput = new MoneyInput();
        dealer = new Dealer();
    }

    public void playerStandBy(){
        createPlayer(createPlayerName());
    }

    private void createPlayer(List<String> nameList) {
        int i = 0;
        for (String name : nameList) {
            announce.announceMoneyInput(name);
            players[i] = new Player(
                    name, moneyinput.inputBettingMoney());
            cardManager.giveCard(players[i], MAKE_TWO_CARD);
            i++;
        }
        cardManager.giveCard(dealer, MAKE_TWO_CARD);
    }

    private List<String> createPlayerName() {
        List<String> nameList;

        do {
            announce.announcePlayerInput();
            nameList = input.inputPlayerNames();
        } while (inputException.isZeroLength(nameList));

        players = new Player[nameList.size()];
        return nameList;
    }

}
