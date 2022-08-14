package com.hypernite.cs.hibyecraftcore;

import com.hypernite.cs.hibyecraftcore.database.DataSourcesManager;
import com.hypernite.cs.hibyecraftcore.listener.PlayerJoinListener;
import com.hypernite.cs.hibyecraftcore.listener.ServerListPingListener;
import com.hypernite.cs.hibyecraftcore.manager.ConfigManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

public final class HiByeCraftCore extends JavaPlugin {

    @Override
    public void onEnable() {

        // Load Configuration
        ConfigManager.getInstance(this).loadConfig();

        // Check if database connectable
        Connection connection = DataSourcesManager.getConnection();

        if(connection != null) {
            if(ConfigManager.isWhitelist) {this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);}
            if(ConfigManager.isMotd) {this.getServer().getPluginManager().registerEvents(new ServerListPingListener(), this);}
        } else {
            onDisable();
            return;
        }

        this.getLogger().info("HiByeCraft Core loaded successfully.");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("HiByeCraft Core unloaded successfully.");
    }
}
