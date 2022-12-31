package ski.mashiro.pojo;

import java.util.Date;

/**
 * @author FeczIne
 */
public class Balance {

    private int id;
    private double balance;
    private Date date;

    public Balance() {
    }

    public Balance(int id, double balance, Date date) {
        this.id = id;
        this.balance = balance;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }
}
