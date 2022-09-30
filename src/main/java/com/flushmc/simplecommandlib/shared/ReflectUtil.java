package com.flushmc.simplecommandlib.shared;

import com.flushmc.simplecommandlib.CommandAuthor;
import com.flushmc.simplecommandlib.annotations.Command;
import com.flushmc.simplecommandlib.annotations.TabComplete;
import org.bukkit.command.CommandSender;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectUtil {

    /*
    Return Command Interface
     */

    public static Command getCommand(Method handler) {
        if (handler == null) return null;
        try {
            return handler.getAnnotation(Command.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Command> getAllCommands(Object commandClass) {
        List<Method> method = getExecutorHandlers(commandClass);
        if (method == null || method.isEmpty()) return null;
        try {
            return method.stream().map(m -> m.getAnnotation(Command.class)).toList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*
    TAB
     */

    public static Method getTabCompleteHandler(Object rawCommand, String target) {
        var cls = rawCommand.getClass();
        try {
            return Arrays.stream(cls.getMethods())
                    .filter(method -> method.getAnnotation(TabComplete.class) != null)
                    .filter(method -> method.getAnnotation(TabComplete.class).commandTarget().equalsIgnoreCase(target))
                    .findFirst().orElse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<String> runTabComplete(Object object, Method method, CommandAuthor author, String[] args) {
        if (method != null) {
            try {
                var result = method.invoke(object, author, args);
                if (result instanceof List list) {
                    return (List<String>) list;
                }
                return Collections.emptyList();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    public static List<Method> getExecutorHandlers(Object rawCommand) {
        var cls = rawCommand.getClass();
        try {
            return Arrays.stream(cls.getMethods()).filter(method -> method.getAnnotation(Command.class) != null).toList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*
    Command
     */

    public static void runCommand(Object object, Method method, CommandAuthor author, String[] args) {
        if (method != null) {
            try {
                method.invoke(object, author, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
