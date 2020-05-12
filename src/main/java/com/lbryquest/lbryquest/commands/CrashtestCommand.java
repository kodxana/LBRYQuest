package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrashtestCommand extends CommandAction {
  private LBRYQuest lbryQuest;

  public CrashtestCommand(LBRYQuest plugin) {
    this.lbryQuest = plugin;
  }

  public boolean run(
      CommandSender sender, Command cmd, String label, String[] args, Player player) {
    lbryQuest.crashtest();
    return true;
  }
}
