package xyz.damt.commands.sub;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;
import xyz.damt.api.events.AdminRemoveAllVerifyEvent;

public class AdminRemoveAllSubCommand extends xyz.damt.commands.framework.SubCommand {

    private final NameMC nameMC;

    public AdminRemoveAllSubCommand() {
        super("removeall", JavaPlugin.getPlugin(NameMC.class).getConfigHandler().getSettingsHandler().ADMIN_VERIFY_COMMAND_PERMISSION,
                "/namemc removeall", "");

        this.nameMC = JavaPlugin.getPlugin(NameMC.class);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + getUsage());
            return;
        }

        if (nameMC.getVerificationHandler().isEmpty()) {
            sender.sendMessage(nameMC.getConfigHandler().getMessageHandler().ADMIN_NO_VERIFICATIONS);
            return;
        }

        nameMC.getServer().getPluginManager().callEvent(new AdminRemoveAllVerifyEvent(sender));
        nameMC.getVerificationHandler().removeAll();

        sender.sendMessage(nameMC.getConfigHandler().getMessageHandler().ADMIN_REMOVE_ALL_MESSAGE);
    }
}
