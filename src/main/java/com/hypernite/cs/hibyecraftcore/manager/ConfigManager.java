package com.hypernite.cs.hibyecraftcore.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class ConfigManager {
    private static ConfigManager configManager;
    private File configFile;
    private FileConfiguration configuration;

    // Config Vars
    public static String prefix, db_host, db_username, db_password, db_database, motd, not_whitelist_notification;
    public static String table_name, table_mc_uuid, table_player_state;
    public static int db_port, default_gamemode, whitelisted_gamemode;
    public static boolean isMotd, isWhitelist;
    public static List<?> accept_state;

    private ConfigManager(Plugin plugin) {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()) plugin.saveResource("config.yml", true);
        configuration = YamlConfiguration.loadConfiguration(configFile);
    }

    public static ConfigManager getInstance(Plugin plugin) {
        if(configManager == null) configManager = new ConfigManager(plugin);
        return configManager;
    }

    public void loadConfig() {
        prefix = configuration.getString("settings.prefix");

        // Database
        db_host = configuration.getString("settings.database.host");
        db_username = configuration.getString("settings.database.username");
        db_password = configuration.getString("settings.database.password");
        db_database = configuration.getString("settings.database.database");
        db_port = configuration.getInt("settings.database.port");

        // Others
        isMotd = configuration.getBoolean("settings.function.motd");
        motd = configuration.getString("motd.text");

        // Whitelist
        isWhitelist = configuration.getBoolean("settings.function.whitelist");
        not_whitelist_notification = configuration.getString("whitelist.notification.not_whitelisted");
        default_gamemode = configuration.getInt("whitelist.default_gamemode");
        whitelisted_gamemode = configuration.getInt("whitelist.whitelisted_gamemode");

        accept_state = configuration.getList("settings.database.table.accept_state");
        table_name = configuration.getString("settings.database.table.table");
        table_mc_uuid = configuration.getString("settings.database.table.mc_uuid_column");
        table_player_state = configuration.getString("settings.database.table.user_state_column");
    }
}
