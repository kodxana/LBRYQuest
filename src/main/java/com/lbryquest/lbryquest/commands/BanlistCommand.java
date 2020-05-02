package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanlistCommand extends CommandAction {
  public boolean run(
      CommandSender sender, Command cmd, String label, String[] args, Player player) {
    Set<String> banlist = LBRYQuest.REDIS.smembers("banlist");
    for (String uuid : banlist) {
      sender.sendMessage(ChatColor.YELLOW + LBRYQuest.REDIS.get("name:" + uuid));
    }
    return true;
  }
}
