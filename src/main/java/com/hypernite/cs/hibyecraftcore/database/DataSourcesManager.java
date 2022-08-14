package com.hypernite.cs.hibyecraftcore.database;

import com.hypernite.cs.hibyecraftcore.HiByeCraftCore;
import com.hypernite.cs.hibyecraftcore.manager.ConfigManager;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSourcesManager {
    static String jdbcURL = "jdbc:mysql://" + ConfigManager.db_host +  ":" + ConfigManager.db_port +
            "/" + ConfigManager.db_database + "?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, ConfigManager.db_username, ConfigManager.db_password);
            return connection;
        } catch (Exception e) {
            HiByeCraftCore.getPlugin(HiByeCraftCore.class).getLogger().info(ConfigManager.prefix + " | " + ChatColor.RED + e.getMessage());
            return null;
        }
    }

}
