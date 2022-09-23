package com.flushmc.simplecommandlib;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class CommandAuthor implements CommandSender {

    private CommandSender sender;

    public CommandAuthor(CommandSender sender) {
        this.sender = sender;
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public Player getPlayer() {
        if (isPlayer()) {
            return (Player) sender;
        }
        return null;
    }

    @Override
    public void sendMessage(String s) {
        sender.sendMessage(MessageConfig.simpleTranslate(s));
    }

    @Override
    public void sendMessage(String[] strings) {
        Arrays.stream(strings).forEach(this::sendMessage);
    }

    @Override
    public void sendMessage(UUID uuid, String s) {
        sendMessage(s);
    }

    @Override
    public void sendMessage(UUID uuid, String[] strings) {
        sendMessage(strings);
    }

    @Override
    public Server getServer() {
        return sender.getServer();
    }

    @Override
    public String getName() {
        return sender.getName();
    }

    @Override
    public Spigot spigot() {
        return sender.spigot();
    }

    @Override
    public boolean isPermissionSet(String s) {
        return sender.isPermissionSet(s);
    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        return sender.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(String s) {
        return sender.hasPermission(s);
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return sender.hasPermission(permission);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        return sender.addAttachment(plugin, s, b);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return sender.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        return sender.addAttachment(plugin, s, b, i);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return sender.addAttachment(plugin, i);
    }

    @Override
    public void removeAttachment(PermissionAttachment permissionAttachment) {
        sender.removeAttachment(permissionAttachment);
    }

    @Override
    public void recalculatePermissions() {
        sender.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return sender.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return sender.isOp();
    }

    @Override
    public void setOp(boolean b) {
        sender.setOp(b);
    }
}
