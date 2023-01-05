package ski.mashiro.command;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.SimpleCommand;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import ski.mashiro.AccountBookRebuild;
import ski.mashiro.util.Utils;

import java.util.Objects;

/**
 * @author FeczIne
 */
public class Undo extends JSimpleCommand {
    public Undo() {
        super(AccountBookRebuild.INSTANCE, "undo");
        setPrefixOptional(true);
    }

    @SimpleCommand.Handler
    public void undo(CommandSender sender) {
        if (!Utils.isOwner(Objects.requireNonNull(sender.getUser()).getId())) {
            return;
        }
        if (Utils.DEAL_LIST.isEmpty()) {
            sender.sendMessage("Undo Failed, cause by list is empty");
            return;
        }
        Utils.balance.setBalance(Utils.balance.getBalance() - Utils.DEAL_LIST.get(Utils.DEAL_LIST.size() - 1).getMoney());
        Utils.DEAL_LIST.remove(Utils.DEAL_LIST.size() - 1);
        sender.sendMessage("Undo Success");
    }
}
