package com.flushmc.simplecommandlib;

import com.flushmc.simplecommandlib.shared.ReflectUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.function.Function;

public class CommandRegistry {

    private JavaPlugin plugin;
    private CommandMap commandMap;
    @Getter private MessageConfig messageConfig;

    public CommandRegistry(JavaPlugin plugin) {
        this.plugin = plugin;
        this.messageConfig = new MessageConfig(
                MessageConfig.simpleTranslate("&c&lError &cYou do not have permission to run this command.")
        );
        setup();
    }

    private void setup() {
        if (commandMap == null) {
            var server = Bukkit.getServer();
            try {
                Method getCommandMap = server.getClass().getDeclaredMethod("getCommandMap");
                this.commandMap = (CommandMap) getCommandMap.invoke(server);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void editMessages(Function<MessageConfig, MessageConfig> messageConfig) {
        this.messageConfig = messageConfig.apply(this.messageConfig);
    }

    public void register(Object commandClass) {
        var handlers = ReflectUtil.getExecutorHandlers(commandClass);
        if (handlers != null && !handlers.isEmpty()) {
            handlers.forEach(handler -> {
                var command = new ReflectCommand(commandClass, handler, this);
                commandMap.register(plugin.getName(), command);
            });
        }
    }

}
