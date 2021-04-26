package xyz.damt.commands.sub;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;
import xyz.damt.api.events.AdminRemoveAllVerifyEvent;

public class AdminListSubCommand extends xyz.damt.commands.framework.SubCommand {

    private final NameMC nameMC;

    public AdminListSubCommand() {
        super("list", JavaPlugin.getPlugin(NameMC.class).getConfigHandler().getSettingsHandler().ADMIN_VERIFY_COMMAND_PERMISSION,
                "/namemc list", "");

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

        StringBuilder builder = new StringBuilder();
        nameMC.getVerificationHandler().getVerifiedUsers().forEach(uuid -> {
            builder.append(nameMC.getServer().getOfflinePlayer(uuid).getName()).append(",");
        });

        sender.sendMessage(nameMC.getConfigHandler().getMessageHandler().ADMIN_LIST_MESSAGE.replace("{users}", builder.toString()));
    }
}
