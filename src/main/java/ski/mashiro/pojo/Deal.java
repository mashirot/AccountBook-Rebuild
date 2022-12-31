package ski.mashiro.pojo;

import java.util.Date;

/**
 * @author FeczIne
 */
public class Deal {
    private double money;
    private String reason = "No reason";
    private Date date = new Date();

    public Deal(double money) {
        this.money = money;
    }

    public Deal(double money, String reason) {
        this.money = money;
        this.reason = reason;
    }

    public Deal(double money, Date date) {
        this.money = money;
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
