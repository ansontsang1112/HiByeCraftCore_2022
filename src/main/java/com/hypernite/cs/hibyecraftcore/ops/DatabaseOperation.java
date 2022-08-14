package com.hypernite.cs.hibyecraftcore.ops;

import com.hypernite.cs.hibyecraftcore.manager.ConfigManager;
import com.hypernite.cs.hibyecraftcore.utils.MessageLogger;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperation {
    Player player;
    Connection connection;

    public DatabaseOperation(Connection connection, Player player) {
        this.player = player;
        this.connection = connection;
    }

    public boolean isExist() {
        String syntax = syntaxGenerator();
        System.out.println(syntaxGenerator());
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(syntax);

            // Check if the player in database
            while(resultSet.next()) {
                if(resultSet.getString(ConfigManager.table_mc_uuid) == player.getUniqueId().toString().replace("-","")) {
                    return true;
                }
            }

            return false;
        } catch (SQLException e) {
            MessageLogger.adminLog("&c資料庫讀取失敗. 現在轉回白名單模式!");
            // Execute Whitelist Check
            WhitelistOperation ops = new WhitelistOperation(player);
            if(ops.isExist()) return true;
            return false;
        }
    }

    private String syntaxGenerator() {
        String glue = "";
        for(int i = 0; i < ConfigManager.accept_state.size(); i++) {
            if(i != ConfigManager.accept_state.size()-1) {
                glue += ConfigManager.table_player_state + "='" + ConfigManager.accept_state.get(i) + "' AND ";
            } else {
                glue += ConfigManager.table_player_state + "='" + ConfigManager.accept_state.get(i) + "'";
            }
        }

        return "SELECT " + ConfigManager.table_mc_uuid + " FROM " + ConfigManager.table_name + " WHERE " + glue;
    }
}
