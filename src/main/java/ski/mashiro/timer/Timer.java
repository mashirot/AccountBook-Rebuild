package ski.mashiro.timer;

import ski.mashiro.file.Config;
import ski.mashiro.sql.Sql;
import ski.mashiro.util.Utils;

import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author FeczIne
 */
public class Timer {

    private static final ScheduledThreadPoolExecutor POOL = new ScheduledThreadPoolExecutor(2);

    public static void autoSubmit() {
        Calendar nextDay = Calendar.getInstance();
        nextDay.set(Calendar.DAY_OF_MONTH, nextDay.get(Calendar.DAY_OF_MONTH) + 1);
        nextDay.set(Calendar.HOUR, 0);
        nextDay.set(Calendar.MINUTE, 0);
        nextDay.set(Calendar.SECOND, 0);
        nextDay.set(Calendar.MILLISECOND, 0);
        POOL.scheduleAtFixedRate(() -> {
            try {
                Sql.insert(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, nextDay.getTime().getTime() - System.currentTimeMillis(), 24 * 60 * 60 * 1000L, TimeUnit.MILLISECONDS);
    }

    public static void checkout() {
        Calendar nextCheckoutDay = Calendar.getInstance();
        nextCheckoutDay.set(Calendar.DAY_OF_MONTH, Config.config.getCheckoutDay());
        nextCheckoutDay.set(Calendar.HOUR, 0);
        nextCheckoutDay.set(Calendar.MINUTE, 0);
        nextCheckoutDay.set(Calendar.SECOND, 0);
        nextCheckoutDay.set(Calendar.MILLISECOND, 0);
        if (nextCheckoutDay.getTimeInMillis() - System.currentTimeMillis() < 0) {
            nextCheckoutDay.set(Calendar.MONTH, nextCheckoutDay.get(Calendar.MONTH) + 1);
        }
        POOL.scheduleAtFixedRate(() -> {
            try {
                nextCheckoutDay.set(Calendar.MONTH, nextCheckoutDay.get(Calendar.MONTH) - 1);
                Sql.selectDate(nextCheckoutDay.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, nextCheckoutDay.getTimeInMillis() - System.currentTimeMillis(), 30 * 24 * 60 * 60 * 1000L, TimeUnit.MILLISECONDS);
    }
}
