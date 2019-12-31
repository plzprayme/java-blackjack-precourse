package domain.controller;

import domain.exception.InputException;
import domain.input.MoneyInput;
import domain.input.PlayerInput;
import domain.tools.ToolBox;
import domain.user.Dealer;
import domain.user.Player;
import domain.view.Announcer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class UserCreateManager {
    CardManager cardManager;
    PlayerInput input;
    ToolBox toolbox;
    Announcer announce;
    InputException inputException;
    MoneyInput moneyinput;
    Player[] players;
    Dealer dealer;

    final static int MAKE_TWO_CARD = 2;

    public UserCreateManager(Dealer dealer) {
        cardManager = new CardManager();
        input = new PlayerInput();
        toolbox = new ToolBox();
        announce = new Announcer();
        inputException = new InputException();
        moneyinput = new MoneyInput();
        this.dealer = dealer;
    }

    public Player[] playerStandBy() {
        createPlayer(createPlayerName());
        createDealer();
        return players;
    }

//    private void createPlayer(List<String> nameList) {
//        int i = 0;
//        for (String name : nameList) {
//            announce.announceMoneyInput(name);
//            players[i] = new Player(
//                    name, moneyinput.inputBettingMoney());
//            System.out.println();
//            cardManager.giveCard(players[i], MAKE_TWO_CARD);
//            i++;
//        }
//    }

    private void createPlayer(HashMap<String, Double> nameList) {
        List<Player> player = map(nameList, Player::new);
    }

    private List<Player> map(HashMap<String, Double> player, BiFunction<String, Double, Player> f) {
        List<Player> result = new ArrayList<>();
        player.entrySet().stream().forEach((k,v) -> result.add(f.apply(k,v)));
        for (Map.Entry a : player.entrySet()) {
            result.add(f.apply(a.getKey(), a.getValue()));
        }
        return result;
    }

//    private List<Player> map(List<String> nameList, Function<String, Player> f) {
//        List<Player> result = new ArrayList<>();
//
//        // version lambda
//        nameList.forEach(x -> result.add(f.apply(x)));
//
//        // version forEach
//        for (String name : nameList) {
//            result.add(f.apply(name));
//        }
//
//        return result;
//    }


    private void createDealer() {
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
