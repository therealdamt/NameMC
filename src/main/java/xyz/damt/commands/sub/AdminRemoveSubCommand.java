package xyz.damt.commands.sub;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;
import xyz.damt.api.events.AdminAddVerifyEvent;
import xyz.damt.api.events.AdminRemoveVerifyEvent;

import java.util.Objects;

public class AdminRemoveSubCommand extends xyz.damt.commands.framework.SubCommand {

    private final NameMC nameMC;

    public AdminRemoveSubCommand() {
        super("remove", JavaPlugin.getPlugin(NameMC.class).getConfigHandler().getSettingsHandler().ADMIN_VERIFY_COMMAND_PERMISSION,
                "/namemc remove <user>", "");

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

        if (!nameMC.getVerificationHandler().containsUser(player.getUniqueId())) {
            sender.sendMessage(nameMC.getConfigHandler().getMessageHandler().ADMIN_IS_NOT_VERIFIED.replace("{user}", Objects.requireNonNull(player.getName())));
            return;
        }

        nameMC.getServer().getPluginManager().callEvent(new AdminRemoveVerifyEvent(sender, player.getUniqueId()));
        nameMC.getVerificationHandler().removeUser(player.getUniqueId());

        sender.sendMessage(nameMC.getConfigHandler().getMessageHandler().ADMIN_REMOVE_MESSAGE.replace("{user}", Objects.requireNonNull(player.getName())));
    }

}
