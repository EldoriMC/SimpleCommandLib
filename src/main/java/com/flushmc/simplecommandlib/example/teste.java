package com.flushmc.simplecommandlib.example;


import com.flushmc.simplecommandlib.CommandAuthor;
import com.flushmc.simplecommandlib.MessageConfig;
import com.flushmc.simplecommandlib.annotations.Command;
import com.flushmc.simplecommandlib.annotations.TabComplete;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class teste {

    private List<String> availableKits = List.of("noob", "vip", "fome", "inicio");

    @Command(name = "kit", alias = {"kits"})
    public void onExecute(CommandAuthor author, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("list")) {
            author.sendMessage(
                    MessageConfig.simpleTranslate("&eKits: &7" + String.join(", ", availableKits))
            );
            return;
        }
    }

    @Command(name = "batata", alias = {"bb"})
    public void onExecute2(CommandAuthor author, String[] args) {
        author.sendMessage("bbbbb");
    }

    @TabComplete(commandTarget = "kit")
    public List<String> onComplete(CommandAuthor author, String[] args) {
        if (args.length == 0) {
            return availableKits;
        } else {
            return Collections.emptyList();
        }
    }

    @TabComplete(commandTarget = "batata")
    public List<String> onComplete2(CommandAuthor author, String[] args) {
        return List.of("...");
    }
}
