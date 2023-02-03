package com.flushmc.simplecommandlib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;

@AllArgsConstructor
public class MessageConfig {

    @Getter
    private String permissionError;

    public void setPermissionError(String permissionError) {
        this.permissionError = simpleTranslate(permissionError);
    }

    public static String simpleTranslate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
