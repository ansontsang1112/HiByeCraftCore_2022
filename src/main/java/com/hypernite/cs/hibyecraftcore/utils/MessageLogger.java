package com.hypernite.cs.hibyecraftcore.utils;

import com.hypernite.cs.hibyecraftcore.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageLogger {
    public static void adminLog(String msg) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if(player.isOp() || player.hasPermission("HBC.admin")) {
                player.sendMessage(ColorUtils.replace(ConfigManager.prefix + " " + msg));
            }
        }
    }
}
