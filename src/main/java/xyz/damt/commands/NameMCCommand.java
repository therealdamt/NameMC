package xyz.damt.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;
import xyz.damt.commands.framework.BaseCommand;
import xyz.damt.commands.sub.*;
import xyz.damt.util.CC;

public class NameMCCommand extends BaseCommand {

    private final NameMC nameMC;

    public NameMCCommand() {
        super("namemc", JavaPlugin.getPlugin(NameMC.class).getConfigHandler().getSettingsHandler().ADMIN_VERIFY_COMMAND_PERMISSION, "/namemc");

        this.getSubCommands().add(new AdminStatusSubCommand());
        this.getSubCommands().add(new AdminAddSubCommand());
        this.getSubCommands().add(new AdminRemoveSubCommand());
        this.getSubCommands().add(new AdminListSubCommand());
        this.getSubCommands().add(new AdminRemoveAllSubCommand());

        this.nameMC = JavaPlugin.getPlugin(NameMC.class);
        this.playerOnly = false;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        nameMC.getConfigHandler().getMessageHandler().ADMIN_WRONG_USAGE.forEach(s -> {
            sender.sendMessage(CC.translate(s));
        });
    }
}
