package com.flushmc.simplecommandlib;

import com.flushmc.simplecommandlib.shared.ReflectUtil;
import lombok.NonNull;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;
import java.util.*;

public class ReflectCommand extends Command {

    private Object commandClass;
    private Method handler;

    ReflectCommand(Object commandClass, Method handler, CommandRegistry commandRegistry) {
        super(ReflectUtil.getCommand(handler).name());
        this.commandClass = commandClass;
        this.handler = handler;
        setup(commandRegistry);
    }

    private void setup(CommandRegistry commandRegistry) {
        var command = ReflectUtil.getCommand(handler);
        if (command != null) {
            if (command.alias().length > 0) {
                setAliases(Arrays.asList(command.alias()));
            }
            setPermissionMessage(commandRegistry.getMessageConfig().getPermissionError());
        }

    }

    @Override
    public boolean execute(@NonNull CommandSender sender, @NonNull String label, String[] args) {
        var command = ReflectUtil.getCommand(handler);
        if (!command.permssion().isEmpty()) {
            if (!sender.hasPermission(command.permssion())) {
                sender.sendMessage(getPermissionMessage());
                return true;
            }
        }
        ReflectUtil.runCommand(commandClass.getClass(), handler, sender, args);
        return false;
    }

    @Override
    public List<String> tabComplete(@NonNull CommandSender sender, @NonNull String alias, String[] args) throws IllegalArgumentException {
        var method = ReflectUtil.getTabCompleteHandler(commandClass, ReflectUtil.getCommand(handler).name());
        var command = ReflectUtil.getCommand(handler);
        if (!command.permssion().isEmpty()) {
            if (sender.hasPermission(command.permssion())) {
                return ReflectUtil.runTabComplete(commandClass.getClass(), method, sender, (String[]) ArrayUtils.remove(args, 0));
            }
            return Collections.emptyList();
        }
        return ReflectUtil.runTabComplete(commandClass.getClass(), method, sender, (String[]) ArrayUtils.remove(args, 0));
    }


}
