package ski.mashiro.file;

import org.apache.commons.io.FileUtils;
import ski.mashiro.AccountBookRebuild;
import ski.mashiro.pojo.Deal;
import ski.mashiro.pojo.SelDateDeal;
import ski.mashiro.pojo.SelIdDeal;
import ski.mashiro.sql.Sql;
import ski.mashiro.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author FeczIne
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Balance {

    private static final File BALANCE_FILE = new File(AccountBookRebuild.INSTANCE.getDataFolder(), Config.config.getOwner() + ".yml");

    public static void loadBalance() {
        try {
            if (!BALANCE_FILE.exists()) {
                BALANCE_FILE.createNewFile();
                ski.mashiro.pojo.Balance balance = getBalance(-1);
                Utils.balance = balance;
                FileUtils.writeStringToFile(BALANCE_FILE, Utils.YAML_MAPPER.writeValueAsString(balance), "utf-8");
                return;
            }
            Utils.balance = Utils.YAML_MAPPER.readValue(FileUtils.readFileToString(BALANCE_FILE, "utf-8"), ski.mashiro.pojo.Balance.class);
            ski.mashiro.pojo.Balance tempBalance = Utils.balance;
            ski.mashiro.pojo.Balance balance = getBalance(tempBalance.getId());
            balance.setBalance(balance.getBalance() + tempBalance.getBalance());
            Utils.balance = balance;
        } catch (Exception e) {
            BALANCE_FILE.delete();
            e.printStackTrace();
        }
    }

    public static void saveBalance() {
        try {
            if (!BALANCE_FILE.exists()) {
                BALANCE_FILE.createNewFile();
                ski.mashiro.pojo.Balance balance = getBalance(-1);
                Utils.balance = balance;
                FileUtils.writeStringToFile(BALANCE_FILE, Utils.YAML_MAPPER.writeValueAsString(balance), "utf-8");
                return;
            }
            ski.mashiro.pojo.Balance balance = getBalance(Utils.balance.getId());
            balance.setBalance(Utils.balance.getBalance());
            FileUtils.writeStringToFile(BALANCE_FILE, Utils.YAML_MAPPER.writeValueAsString(balance), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SelDateDeal getBalancePast(Date date) throws IOException {
        List<Deal> deals = Sql.selectDate(date);
        SelDateDeal rs = new SelDateDeal();
        for (Deal deal : deals) {
            if (deal.getMoney() > 0) {
                rs.setInput(rs.getInput() + deal.getMoney());
            } else {
                rs.setOutput(rs.getOutput() + deal.getMoney());
            }
        }
        rs.setTotal(rs.getInput() + rs.getOutput());
        return rs;
    }

    private static ski.mashiro.pojo.Balance getBalance(int lastId) throws IOException {
        List<SelIdDeal> selIdDeals = lastId == -1 ? Sql.selectAllId() : Sql.selectId(lastId);
        double balance = 0;
        for (SelIdDeal selIdDeal : selIdDeals) {
            balance += selIdDeal.getMoney();
        }
        int id = !selIdDeals.isEmpty() ? selIdDeals.get(selIdDeals.size() - 1).getId() : lastId == -1 ? 0 : lastId;
        return new ski.mashiro.pojo.Balance(id, balance, new Date());
    }
}
