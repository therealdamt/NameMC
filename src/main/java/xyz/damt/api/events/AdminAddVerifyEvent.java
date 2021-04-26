package xyz.damt.api.events;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class AdminAddVerifyEvent extends Event implements Cancellable {

    private final HandlerList handlerList = new HandlerList();
    private final CommandSender commandSender;
    private final UUID uuid;

    private boolean cancel;

    public AdminAddVerifyEvent(CommandSender commandSender, UUID uuid) {
        this.commandSender = commandSender;
        this.uuid = uuid;

        this.cancel = false;
    }

    public CommandSender getSender() {
        return this.commandSender;
    }

    public UUID getTargetUUID() {
        return this.uuid;
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
