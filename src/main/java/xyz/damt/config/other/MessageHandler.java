package xyz.damt.config.other;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.NameMC;
import xyz.damt.util.CC;

import java.util.List;

public class MessageHandler {

    private final FileConfiguration fileConfiguration;

    public final String NO_PERMISSION;
    public final String INVALID_VALUE;

    public final String USER_HAS_LIKED;
    public final String USER_DID_NOT_LIKE;
    public final String USER_ALREADY_LIKED;

    public final List<String> ADMIN_WRONG_USAGE;
    public final String ADMIN_STATUS_MESSAGE;
    public final String ADMIN_REMOVE_MESSAGE;
    public final String ADMIN_ADD_MESSAGE;
    public final String ADMIN_REMOVE_ALL_MESSAGE;
    public final String ADMIN_LIST_MESSAGE;
    public final String ADMIN_ALREADY_VERIFIED;
    public final String ADMIN_IS_NOT_VERIFIED;
    public final String ADMIN_NO_VERIFICATIONS;

    public MessageHandler() {
        this.fileConfiguration = JavaPlugin.getPlugin(NameMC.class).getConfig();

        this.NO_PERMISSION = CC.translate(fileConfiguration.getString("messages.no-permission"));
        this.INVALID_VALUE = CC.translate(fileConfiguration.getString("messages.invalid-value"));

        this.USER_HAS_LIKED = CC.translate(fileConfiguration.getString("messages.user.has-liked"));
        this.USER_DID_NOT_LIKE = CC.translate(fileConfiguration.getString("messages.user.did-not-liked"));
        this.USER_ALREADY_LIKED = CC.translate(fileConfiguration.getString("messages.user.already-liked"));

        this.ADMIN_WRONG_USAGE = fileConfiguration.getStringList("messages.admin.wrong-usage");
        this.ADMIN_STATUS_MESSAGE = CC.translate(fileConfiguration.getString("messages.admin.status-message"));
        this.ADMIN_REMOVE_MESSAGE = CC.translate(fileConfiguration.getString("messages.admin.remove-message"));
        this.ADMIN_ADD_MESSAGE = CC.translate(fileConfiguration.getString("messages.admin.add-message"));
        this.ADMIN_REMOVE_ALL_MESSAGE = CC.translate(fileConfiguration.getString("messages.admin.remove-all-message"));
        this.ADMIN_LIST_MESSAGE = CC.translate(fileConfiguration.getString("messages.admin.list-message"));
        this.ADMIN_ALREADY_VERIFIED = CC.translate(fileConfiguration.getString("messages.admin.already-verified"));
        this.ADMIN_IS_NOT_VERIFIED = CC.translate(fileConfiguration.getString("messages.admin.is-not-verified"));
        this.ADMIN_NO_VERIFICATIONS = CC.translate(fileConfiguration.getString("messages.admin.no-verifications"));
    }

}
