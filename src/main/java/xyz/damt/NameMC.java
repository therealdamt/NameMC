package xyz.damt;

import lombok.Getter;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.api.NameMCAPI;
import xyz.damt.commands.NameMCCommand;
import xyz.damt.commands.VerifyCommand;
import xyz.damt.commands.framework.BaseCommand;
import xyz.damt.config.ConfigHandler;
import xyz.damt.handlers.VerificationHandler;
import xyz.damt.util.CC;

import java.util.Arrays;

public final class NameMC extends JavaPlugin {

    @Getter private NameMCAPI nameMCAPI;

    @Getter private VerificationHandler verificationHandler;
    @Getter private ConfigHandler configHandler;

    @Override
    public void onLoad() {
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        this.configHandler = new ConfigHandler();
        this.verificationHandler = new VerificationHandler();
        this.nameMCAPI = new NameMCAPI();

        this.getServer().getScheduler().runTaskAsynchronously(this, () -> this.verificationHandler.load());

        Arrays.asList(
                new NameMCCommand(),
                new VerifyCommand()
        ).forEach(BaseCommand::register);

        this.getServer().getConsoleSender().sendMessage(CC.translate("&6NameMC | Verification plugin loaded"));
        this.getServer().getConsoleSender().sendMessage(CC.translate("&aCoded by damt"));
    }

    @Override
    public void onDisable() {
        this.verificationHandler.save();
    }

}
