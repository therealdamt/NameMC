package xyz.damt.api.events;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AdminRemoveAllVerifyEvent extends Event implements Cancellable {

    private final HandlerList handlerList = new HandlerList();
    private final CommandSender commandSender;

    private boolean cancel;

    public AdminRemoveAllVerifyEvent(CommandSender sender) {
        this.commandSender = sender;
        this.cancel = false;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
