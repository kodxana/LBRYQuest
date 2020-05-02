package com.lbryquest.lbryquest.events;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerEvents implements Listener {
  LBRYQuest lbryQuest;

  public ServerEvents(LBRYQuest plugin) {
    lbryQuest = plugin;
  }

  @EventHandler
  public void onServerListPing(ServerListPingEvent event) {

    event.setMotd(
        ChatColor.GOLD
            + ChatColor.BOLD.toString()
            + LBRYQuest.SERVERDISPLAY_NAME
            + ChatColor.RESET
            + " - The server that runs on "
            + LBRYQuest.DENOMINATION_NAME);
  }
}
