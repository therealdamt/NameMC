package xyz.damt.commands.sub;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;

public class AdminReloadSubCommand extends xyz.damt.commands.framework.SubCommand {

    private final NameMC nameMC;

    public AdminReloadSubCommand() {
        super("reload", JavaPlugin.getPlugin(NameMC.class).getConfigHandler().getSettingsHandler().ADMIN_VERIFY_COMMAND_PERMISSION,
                "/namemc reload", "");

        this.nameMC = JavaPlugin.getPlugin(NameMC.class);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + getUsage());
            return;
        }

        nameMC.reloadConfig();
        nameMC.getConfigHandler().reload();

        sender.sendMessage(nameMC.getConfigHandler().getMessageHandler().ADMIN_RELOAD_MESSAGE);
    }
}
