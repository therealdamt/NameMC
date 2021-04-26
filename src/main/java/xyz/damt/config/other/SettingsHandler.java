package xyz.damt.config.other;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;

public class SettingsHandler {

    private final FileConfiguration fileConfiguration;

    public final String SERVER_IP;
    public final boolean RANDOMIZE_REWARDS;
    public final boolean IS_MONGO_ENABLED;
    public final int AMOUNT_OF_REWARDS;

    public final String VERIFY_COMMAND_PERMISSION;
    public final String ADMIN_VERIFY_COMMAND_PERMISSION;

    public SettingsHandler() {
        this.fileConfiguration = JavaPlugin.getPlugin(NameMC.class).getConfig();

        this.SERVER_IP = fileConfiguration.getString("settings.server-ip");
        this.RANDOMIZE_REWARDS = fileConfiguration.getBoolean("settings.randomize-rewards");
        this.IS_MONGO_ENABLED = fileConfiguration.getBoolean("settings.storage.mongo");
        this.AMOUNT_OF_REWARDS = fileConfiguration.getInt("settings.amount-of-rewards");

        this.VERIFY_COMMAND_PERMISSION = fileConfiguration.getString("settings.verify-command-permission");
        this.ADMIN_VERIFY_COMMAND_PERMISSION = fileConfiguration.getString("settings.namemc-command-permission");
    }

}
