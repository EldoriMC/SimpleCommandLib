package com.flushmc.simplecommandlib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;

@AllArgsConstructor
public class MessageConfig {

    @Getter @Setter
    private String permissionError;

    public static String simpleTranslate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
