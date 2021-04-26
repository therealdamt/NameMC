package xyz.damt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.api.NameMCAPI;
import xyz.damt.api.events.AdminAddVerifyEvent;
import xyz.damt.api.events.AdminRemoveAllVerifyEvent;
import xyz.damt.api.events.AdminRemoveVerifyEvent;
import xyz.damt.api.events.PlayerVerifyEvent;

public class APIUsage implements Listener {

    private final NameMCAPI nameMCAPI = JavaPlugin.getPlugin(NameMC.class).getNameMCAPI();

    @EventHandler
    public void onPlayerVerifyEvent(PlayerVerifyEvent e) {
        Player player = e.getPlayer();

        if (!player.hasPermission("no.verify")) return;

        player.sendMessage(ChatColor.RED + "You may not verify yourself!");
        e.setCancelled(true);
    }

    @EventHandler
    public void onAdminVerifyEvent(AdminAddVerifyEvent e) {
        Player target = Bukkit.getPlayer(e.getTargetUUID());

        if (target == null) return;
        target.sendMessage("You have been verified!");
    }

    @EventHandler
    public void onAdminRemoveAllVerifyEvent(AdminRemoveAllVerifyEvent e) {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage("All verifications were removed!"));
    }

    @EventHandler
    public void onAdminRemoveVerifyEvent(AdminRemoveVerifyEvent e) {
        Player player = Bukkit.getPlayer(e.getTargetUUID());
        if (player == null) return;

        player.sendMessage("You're verification has been removed!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage("Due to a special event we verify everyone of which joins right away!");
        if (!nameMCAPI.isVerified(e.getPlayer().getUniqueId()))
        nameMCAPI.addVerify(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Bukkit.getConsoleSender().sendMessage("The verification of the player " + e.getPlayer().getName() + " has been removed!");
        nameMCAPI.removeVerify(e.getPlayer().getUniqueId());
    }



}
