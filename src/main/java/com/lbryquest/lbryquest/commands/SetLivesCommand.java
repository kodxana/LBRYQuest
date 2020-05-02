package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;

import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLivesCommand extends CommandAction {
  private LBRYQuest lbryQuest;

  public SetLivesCommand(LBRYQuest plugin) {
	  lbryQuest = plugin;
  }
  public boolean run(
      CommandSender sender, Command cmd, String label, String[] args, Player player) {
	if (args.length > 0) {
		if (args[0].equals("set")) {
		        if (args.length > 1) {
				if (LBRYQuest.REDIS.exists("uuid:" + args[1])) {
					UUID uuid = UUID.fromString(LBRYQuest.REDIS.get("uuid:" + args[1]));
					if (lbryQuest.isStringInt(args[2])) {
						LBRYQuest.REDIS.set("LivesLeft" +uuid.toString(), args[2]);
						sender.sendMessage(ChatColor.GREEN + " given " + args[2] + " lives");
					} else {
						sender.sendMessage(ChatColor.GREEN + " needs to be a number of lives.");
						sender.sendMessage(ChatColor.RED + "Usage: /setlives set <player> <amount>");
					}

					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Cannot find player " + args[1]);
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Usage: /setlives set <player> <amount>");
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Usage: /setlives set <player> <amount>");
			return true;
		}
	} else {
		sender.sendMessage(ChatColor.RED + "Usage: /setlives set <player> <amount>");
		return true;
    }
  }
}
