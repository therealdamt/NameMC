package xyz.damt.commands.sub;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;

public class AdminStatusSubCommand extends xyz.damt.commands.framework.SubCommand {

    private final NameMC nameMC;

    public AdminStatusSubCommand() {
        super("status", JavaPlugin.getPlugin(NameMC.class).getConfigHandler().getSettingsHandler().ADMIN_VERIFY_COMMAND_PERMISSION,
                "/namemc status <user>", "");

        this.nameMC = JavaPlugin.getPlugin(NameMC.class);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + getUsage());
            return;
        }

        if (args[1].isEmpty()) {
            sender.sendMessage(nameMC.getConfigHandler().getMessageHandler().INVALID_VALUE);
            return;
        }

        OfflinePlayer player = nameMC.getServer().getOfflinePlayer(args[1]);
        String status = nameMC.getVerificationHandler().containsUser(player.getUniqueId()) ? "Verified" : "Not Verified";

        sender.sendMessage(nameMC.getConfigHandler().getMessageHandler().ADMIN_STATUS_MESSAGE.replace("{user}", player.getName())
        .replace("{status}", status));
    }
}
