package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class VoteCommand extends CommandAction {
  private LBRYQuest lbryQuest;

  public VoteCommand(LBRYQuest plugin) {
	  lbryQuest = plugin;
  }
    public boolean run(CommandSender sender, Command cmd, String label, String[] args, Player player) {
	String playerName = player.getName(); //(player.getName()
	if (lbryQuest.didVote(playerName) == 0) {
		player.sendMessage(ChatColor.GREEN + "Please Vote here for 10% off lives! " + lbryQuest.VOTE_URL);
		//player.sendMessage(ChatColor.AQUA + "Run command again after you vote for reward!");
	} else if (lbryQuest.didVote(playerName) == 1) {
		player.sendMessage(ChatColor.GREEN + "You've already voted, try again later please!");

	} else if (lbryQuest.didVote(playerName) == 2) {
		player.sendMessage(ChatColor.GREEN + "You've already voted, try again later please!");
	} 
        return true;
    }
}
