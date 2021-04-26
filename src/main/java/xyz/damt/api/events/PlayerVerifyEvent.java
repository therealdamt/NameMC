package xyz.damt.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerVerifyEvent extends Event implements Cancellable {

    private final HandlerList handlerList = new HandlerList();
    private final Player player;

    private boolean cancel;

    public PlayerVerifyEvent(Player who) {
        this.player = who;
        this.cancel = false;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
