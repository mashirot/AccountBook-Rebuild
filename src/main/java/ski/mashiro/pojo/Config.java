package ski.mashiro.pojo;

/**
 * @author FeczIne
 */
public class Config {
    private Long owner = 12345L;
    private int checkoutDay = 15;

    public Long getOwner() {
        return owner;
    }

    public int getCheckoutDay() {
        return checkoutDay;
    }

    public void setCheckoutDay(int checkoutDay) {
        this.checkoutDay = checkoutDay;
    }
}
