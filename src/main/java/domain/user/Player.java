package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private double bettingMoney;

    public Player(String name) {
        super(name);
    }
    public Player(String name, double bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }
}
