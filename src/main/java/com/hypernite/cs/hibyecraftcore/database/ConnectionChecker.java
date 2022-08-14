package com.hypernite.cs.hibyecraftcore.database;

import java.sql.Connection;

public class ConnectionChecker {
    public static boolean check(Connection connection) {
        if(connection == null) return false;
        return true;
    }
}
