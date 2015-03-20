package fr.esgi.demo;

/**
 * Created by Pro on 16/03/2015.
 */
public class Account {

    private int money;
    private boolean blocked = false;

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
