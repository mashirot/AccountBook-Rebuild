package ski.mashiro.command;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JCompositeCommand;
import ski.mashiro.AccountBookRebuild;
import ski.mashiro.file.Config;
import ski.mashiro.sql.Sql;
import ski.mashiro.util.Utils;

import java.util.Objects;

/**
 * @author FeczIne
 */
public class Admin extends JCompositeCommand {
    public Admin() {
        super(AccountBookRebuild.INSTANCE, "ab");
        setPrefixOptional(true);
    }

    @SubCommand("submit")
    public void submit(CommandSender sender) {
        if (!Utils.isOwner(Objects.requireNonNull(sender.getUser()).getId())) {
            return;
        }
        try {
            Sql.insert(sender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubCommand("setCheckoutDay")
    public void setCheckoutDay(CommandSender sender, int day) {
        if (!Utils.isOwner(Objects.requireNonNull(sender.getUser()).getId())) {
            return;
        }
        Config.config.setCheckoutDay(day);
        sender.sendMessage("Modify Success");
    }

}
