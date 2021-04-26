package xyz.damt.api;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;

import java.util.UUID;

public class NameMCAPI {

    private final NameMC nameMC;

    public NameMCAPI() {
        this.nameMC = JavaPlugin.getPlugin(NameMC.class);
    }

    public void addVerify(UUID uuid) {
        this.nameMC.getVerificationHandler().addUser(uuid);
    }

    public void removeVerify(UUID uuid) {
        this.nameMC.getVerificationHandler().removeUser(uuid);
    }

    public boolean isVerified(UUID uuid) {
        return this.nameMC.getVerificationHandler().containsUser(uuid);
    }

}
