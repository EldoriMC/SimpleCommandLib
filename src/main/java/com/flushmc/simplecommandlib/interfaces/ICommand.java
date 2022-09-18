package com.flushmc.simplecommandlib.interfaces;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public interface ICommand {

    void onExecute(CommandSender sender, String[] args);

    default List<String> onComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

}
