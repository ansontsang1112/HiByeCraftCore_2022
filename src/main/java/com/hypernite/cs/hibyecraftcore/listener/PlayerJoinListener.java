package com.hypernite.cs.hibyecraftcore.listener;

import com.hypernite.cs.hibyecraftcore.database.ConnectionChecker;
import com.hypernite.cs.hibyecraftcore.database.DataSourcesManager;
import com.hypernite.cs.hibyecraftcore.manager.ConfigManager;
import com.hypernite.cs.hibyecraftcore.ops.DatabaseOperation;
import com.hypernite.cs.hibyecraftcore.ops.WhitelistOperation;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // Get UUID and compare to the database
        Player p = e.getPlayer();
        DatabaseOperation databaseOperation = new DatabaseOperation(DataSourcesManager.getConnection(), p);
        WhitelistOperation whitelistOperation = new WhitelistOperation(p);

        Boolean isExist;

        if(!ConnectionChecker.check(DataSourcesManager.getConnection())) {
            isExist = whitelistOperation.isExist();
        } else {
            isExist = databaseOperation.isExist();
        }

        if(isExist) {
            changeGameMode(p, ConfigManager.whitelisted_gamemode);
        } else {
            p.sendMessage(ConfigManager.prefix + " " + ConfigManager.not_whitelist_notification);
            changeGameMode(p, ConfigManager.default_gamemode);
        }
    }

    public void changeGameMode(Player player, int gameMode) {
        switch (gameMode) {
            case 0:
                player.setGameMode(GameMode.SURVIVAL);
                break;

            case 1:
                player.setGameMode(GameMode.CREATIVE);
                break;

            case 2:
                player.setGameMode(GameMode.ADVENTURE);
                break;
        }
    }
}
