package ski.mashiro.pojo;

import java.util.Date;

/**
 * @author FeczIne
 */
public class SelIdDeal extends Deal {
    private final int id;

    public SelIdDeal(int id, double money) {
        super(money);
        this.id = id;
    }

    public SelIdDeal(int id, double money, Date date) {
        super(money, date);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
