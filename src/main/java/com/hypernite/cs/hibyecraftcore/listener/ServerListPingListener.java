package com.hypernite.cs.hibyecraftcore.listener;

import com.hypernite.cs.hibyecraftcore.manager.ConfigManager;
import com.hypernite.cs.hibyecraftcore.utils.ColorUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {
    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMotd(ColorUtils.replace(ConfigManager.motd));
    }
}
