package ski.mashiro;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.permission.AbstractPermitteeId;
import net.mamoe.mirai.console.permission.PermissionService;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import ski.mashiro.command.*;
import ski.mashiro.file.Balance;
import ski.mashiro.file.Config;
import ski.mashiro.timer.Timer;

/**
 * @author FeczIne
 */
public final class AccountBookRebuild extends JavaPlugin {
    public static final AccountBookRebuild INSTANCE = new AccountBookRebuild();

    private AccountBookRebuild() {
        super(new JvmPluginDescriptionBuilder("ski.mashiro.accountbook-rebuild", "1.0.0")
                .name("AccountBook-Rebuild")
                .author("FeczIne")
                .build());
    }

    @Override
    public void onEnable() {
        Config.loadConfig();
        Balance.loadBalance();
        Timer.autoSubmit();
        Timer.checkout();
        CommandManager.INSTANCE.registerCommand(new Insert(), true);
        CommandManager.INSTANCE.registerCommand(new Disburse(), true);
        CommandManager.INSTANCE.registerCommand(new Undo(), true);
        CommandManager.INSTANCE.registerCommand(new Inquire(), true);
        CommandManager.INSTANCE.registerCommand(new Admin(), true);
        PermissionService.permit0(AbstractPermitteeId.AnyContact.INSTANCE, INSTANCE.getParentPermission());
        getLogger().info("[AccountBook] 加载成功");
    }

    @Override
    public void onDisable() {
        Config.saveConfig();
        Balance.saveBalance();
        getLogger().info("[AccountBook] 已卸载");
    }
}