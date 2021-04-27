package xyz.damt.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;
import xyz.damt.api.events.PlayerVerifyEvent;
import xyz.damt.commands.framework.BaseCommand;
import xyz.damt.request.Request;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VerifyCommand extends BaseCommand {

    private final NameMC nameMC;

    public VerifyCommand() {
        super("verify", JavaPlugin.getPlugin(NameMC.class).getConfigHandler().getSettingsHandler().VERIFY_COMMAND_PERMISSION,
                "/verify");

        this.playerOnly = true;
        this.nameMC = JavaPlugin.getPlugin(NameMC.class);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Request request = new Request(player.getUniqueId(), nameMC.getConfigHandler().getSettingsHandler().SERVER_IP);

        if (nameMC.getVerificationHandler().containsUser(player.getUniqueId())) {
            player.sendMessage(nameMC.getConfigHandler().getMessageHandler().USER_ALREADY_LIKED);
            return;
        }

        nameMC.getLogger().log(Level.SEVERE, String.valueOf(request.hasLiked()));
        nameMC.getLogger().log(Level.SEVERE, nameMC.getConfigHandler().getSettingsHandler().SERVER_IP);

        if (!request.hasLiked()) {
            player.sendMessage(nameMC.getConfigHandler().getMessageHandler().USER_DID_NOT_LIKE);
            return;
        }

        nameMC.getServer().getPluginManager().callEvent(new PlayerVerifyEvent(player));
        nameMC.getVerificationHandler().addUser(player.getUniqueId());

        player.sendMessage(nameMC.getConfigHandler().getMessageHandler().USER_HAS_LIKED);
        nameMC.getVerificationHandler().giveRewards(player);
    }
}
