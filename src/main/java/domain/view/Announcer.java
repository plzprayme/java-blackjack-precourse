package domain.view;

import domain.tools.ToolBox;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class Announcer {
    ToolBox tool;

    public static final String PLAYER_INPUT_MENT = "플레이어 이름을 입력해주세요. (쉼표 기준으로 분리)";
    public static final String CARD_STATUS_MENT = "======== 카드 현황 ========";
    public static final String MONEY_INPUT_MENT = "의 베팅금액: ";
    public static final String DONE_USER_CREATE = "딜러에게 한 장의 카드, %s에게 두 장의 카드를 나눠줬습니다.\n";
    public static final String DONE_GIVE_CARD_TO_USER = "%s에게 한 장의 카드를 나눠줬습니다.\n\n";
    public static final String ASK_ONE_MORE_CARD = "%s는 카드를 더 받으시겠습니까? [Y/N]: ";
    public static final String BLACKJACK = "%s이 블랙잭을 달성 했습니다!";

    public Announcer() {
        tool = new ToolBox();
    }

    public void announcePlayerInput() {
        System.out.println(PLAYER_INPUT_MENT);
    }

    public void announceMoneyInput(String name) {
        System.out.print(name+MONEY_INPUT_MENT);
    }

    public void announceDoneUserCreate(Player[] players) {
        System.out.printf(DONE_USER_CREATE,
                tool.joinString(players));
        System.out.println();
    }

    public void announceCardStatusStart() {
        System.out.println(CARD_STATUS_MENT);
    }

    public void announceDoneGiveCard(User user) {
        System.out.printf(DONE_GIVE_CARD_TO_USER, user.getName());
    }

    public void announceCardStatus(User[] users) {
        for (User user : users) {
            System.out.print(user.getName() + ": ");
            System.out.println(
                    tool.joinString(user.getCards()));
        }
    }

    public void announceCardStatus(User user) {
        System.out.print(user.getName() + ": ");
        System.out.println(
                tool.joinString(user.getCards()));
    }

    public void announceOneMoreCard(User user) {
        System.out.printf(ASK_ONE_MORE_CARD, user.getName());
    }

    public void announceEndByBlackJack(User user) {
        System.out.printf(BLACKJACK, user.getName());
    }
}
