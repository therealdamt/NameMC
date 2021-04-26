package xyz.damt.config;

import lombok.Getter;
import xyz.damt.config.other.MessageHandler;
import xyz.damt.config.other.SettingsHandler;

public class ConfigHandler {

    @Getter private MessageHandler messageHandler;
    @Getter private SettingsHandler settingsHandler;

    public ConfigHandler() {
        this.registerConfigs();
    }

    public void reload() {
        this.registerConfigs();
    }

    private void registerConfigs() {
        this.messageHandler = new MessageHandler();
        this.settingsHandler = new SettingsHandler();
    }


}
