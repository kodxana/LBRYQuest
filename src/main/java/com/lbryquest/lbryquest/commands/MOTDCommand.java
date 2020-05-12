package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MOTDCommand extends CommandAction {
  private LBRYQuest lbryQuest;

  public MOTDCommand(LBRYQuest plugin) {
    this.lbryQuest = plugin;
  }

  public boolean run(
      CommandSender sender, Command cmd, String label, String[] args, Player player) {
    if (args[0] == null || args[0].isEmpty()) {
      player.sendMessage("Please write a message of the day.");
      return false;
    } else if (args[0].matches("^.*[^a-zA-Z0-9 _].*$")) {
      player.sendMessage("Please use only aplhanumeric characters.");
      return false;
    } else {
      lbryQuest.REDIS.set("lbryquest:motd", args[0]);
      player.sendMessage(ChatColor.GREEN + "Message changed.");
      return true;
    }
  }
}
