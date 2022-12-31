package ski.mashiro.command;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.SimpleCommand;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import ski.mashiro.AccountBookRebuild;
import ski.mashiro.pojo.Deal;
import ski.mashiro.util.Utils;

import java.util.Objects;

/**
 * @author FeczIne
 */
public class Disburse extends JSimpleCommand {
    public Disburse() {
        super(AccountBookRebuild.INSTANCE, "-");
        setPrefixOptional(true);
    }

    @SimpleCommand.Handler
    public void disburse(CommandSender sender, double money) {
        if (!Utils.isOwner(Objects.requireNonNull(sender.getUser()).getId())) {
            return;
        }
        Utils.DEAL_LIST.add(new Deal(-money));
        Utils.balance.setBalance(Utils.balance.getBalance() - money);
        sender.sendMessage("Disburse Success");
    }

    @SimpleCommand.Handler
    public void disburse(CommandSender sender, double money, String reason) {
        if (!Utils.isOwner(Objects.requireNonNull(sender.getUser()).getId())) {
            return;
        }
        Utils.DEAL_LIST.add(new Deal(-money, reason));
        Utils.balance.setBalance(Utils.balance.getBalance() - money);
        sender.sendMessage("Disburse Success");
    }
}
