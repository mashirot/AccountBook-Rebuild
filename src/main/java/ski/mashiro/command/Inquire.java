package ski.mashiro.command;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import ski.mashiro.AccountBookRebuild;
import ski.mashiro.file.Balance;
import ski.mashiro.pojo.SelDateDeal;
import ski.mashiro.util.Utils;

import java.util.Date;
import java.util.Objects;

/**
 * @author FeczIne
 */
public class Inquire extends JSimpleCommand {
    public Inquire() {
        super(AccountBookRebuild.INSTANCE, "=");
        setPrefixOptional(true);
    }

    @Handler
    public void inquire(CommandSender sender) {
        if (!Utils.isOwner(Objects.requireNonNull(sender.getUser()).getId())) {
            return;
        }
        sender.sendMessage("余额 " + Utils.CURRENCY.format(Utils.balance.getBalance()) + " 元");
    }

    @Handler
    public void inquire(CommandSender sender, int day) {
        if (!Utils.isOwner(Objects.requireNonNull(sender.getUser()).getId())) {
            return;
        }
        try {
            SelDateDeal balancePast = Balance.getBalancePast(new Date(System.currentTimeMillis() - day * 24 * 60 * 60 * 1000L));
            sender.sendMessage("过去 " + day + "天，" +
                    " 支出 " + Utils.CURRENCY.format(Math.abs(balancePast.getOutput())) + " 元，" +
                    " 收入 " + Utils.CURRENCY.format(balancePast.getInput()) + " 元，" +
                    " 余额 " + Utils.CURRENCY.format(balancePast.getTotal()) + " 元");
        } catch (Exception e) {
            sender.sendMessage("Err");
            e.printStackTrace();
        }
    }

}
