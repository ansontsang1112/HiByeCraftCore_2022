package com.hypernite.cs.hibyecraftcore.ops;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WhitelistOperation {
    Player player;

    public WhitelistOperation(Player player) {
        this.player = player;
    }

    public boolean isExist() {
        for(OfflinePlayer p : Bukkit.getServer().getWhitelistedPlayers()) {
            if(p.getPlayer().getUniqueId() == player.getUniqueId()) {
                return true;
            }
        }
        return false;
    }
}
