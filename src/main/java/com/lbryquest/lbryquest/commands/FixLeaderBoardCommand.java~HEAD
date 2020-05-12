package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.text.*;


public class FixLeaderBoardCommand extends CommandAction {
    private LBRYQuest lbryQuest;

    public FixLeaderBoardCommand(LBRYQuest plugin) {
        this.lbryQuest = plugin;
    }

    public boolean run(CommandSender sender, Command cmd, String label, String[] args, Player player) {
	int ArgsLength = args.length;
		if (args[0].equalsIgnoreCase("List")){
			Set<String> ownerList = LBRYQuest.REDIS.keys("LeaderBoard *");
			int iter=1;
				for (String tempOwnerList : ownerList) {
		if ((LBRYQuest.REDIS.get("LeaderBoard "+iter)) != null) {					
			String tempString =  LBRYQuest.REDIS.get("LeaderBoard "+iter);
			String lastWord = tempString.substring(tempString.lastIndexOf(" ")+1);
		double amtUSD = (double)(lbryQuest.exRate * (Long.parseLong(lastWord) * 0.00000001));
		DecimalFormat df = new DecimalFormat("#.##");
					sender.sendMessage(ChatColor.GREEN +" "+iter+") "+ LBRYQuest.REDIS.get("LeaderBoard "+iter) + " now $" + df.format(amtUSD));
		}
					iter++;
				}
		}
		if (args[0].equalsIgnoreCase("Del")){
			Set<String> ownerList = LBRYQuest.REDIS.keys("LeaderBoard *");
			int iter=1;
			for (String tempOwnerList : ownerList) {
				if (iter == Integer.parseInt(args[1])) {
			 		if ((LBRYQuest.REDIS.get("LeaderBoard "+iter)) != null) {
						sender.sendMessage(ChatColor.YELLOW + "Removing: " + "LeaderBoard "+LBRYQuest.REDIS.get("LeaderBoard "+iter));
          					LBRYQuest.REDIS.del("LeaderBoard "+iter);
          					sender.sendMessage(ChatColor.GREEN + "Removed: " + "LeaderBoard "+args[1]);
					}
				}
				iter++;
			}
		}
		if (args[0].equalsIgnoreCase("DelAll")){
			Set<String> ownerList = LBRYQuest.REDIS.keys("LeaderBoard *");
			int iter=1;
			for (String tempOwnerList : ownerList) {
			 		if ((LBRYQuest.REDIS.get(tempOwnerList)) != null) {
						sender.sendMessage(ChatColor.YELLOW + "Removing: " + "LeaderBoard "+LBRYQuest.REDIS.get(tempOwnerList));
          					LBRYQuest.REDIS.del(tempOwnerList);
          					sender.sendMessage(ChatColor.GREEN + "Removed: " + tempOwnerList);
					}
				iter++;
			}
		}
		if (args[0].equalsIgnoreCase("fix")){
			Set<String> ownerList = LBRYQuest.REDIS.keys("LeaderBoard *");
			int iter=1;
			for (String tempOwnerList : ownerList) {
				if (iter == Integer.parseInt(args[1])) {
			 		if ((LBRYQuest.REDIS.get("LeaderBoard "+iter)) != null) {
						sender.sendMessage(ChatColor.YELLOW + "Changing: " + "LeaderBoard "+LBRYQuest.REDIS.get("LeaderBoard "+iter));
						String toChange = "";
        					for (int x = 2; x<= args.length-1; x++) {
							toChange = toChange + " " + args[x];
						}
//REDIS.set("LeaderBoard " + iter,dateFormat.format(date) + " " + player.getName() + " $" + df.format(amtUSD) + " Satoshis: " + sendLoot);
       					LBRYQuest.REDIS.del("LeaderBoard "+ args[2]);
					LBRYQuest.REDIS.set("LeaderBoard "+ args[2], toChange);
        				sender.sendMessage(ChatColor.GREEN + "Changed: " + "LeaderBoard "+toChange);
					}
				}
			iter++;
			}
		}
		if (args[0].equalsIgnoreCase("add")){
			String toChange = "";
			for (int x = 1; x<= args.length-1; x++) {
				toChange = toChange + " " + args[x];
			}
			Set<String> ownerList = LBRYQuest.REDIS.keys("LeaderBoard *");
			int iter=1;
			for (String tempOwnerList : ownerList) {
				if ((LBRYQuest.REDIS.get("LeaderBoard "+iter)) != null) {
				iter++;
				} else {break;}
			}
			sender.sendMessage(ChatColor.YELLOW + "adding: " + "LeaderBoard " +iter + " " + toChange);
			LBRYQuest.REDIS.set("LeaderBoard " +iter, toChange);
			sender.sendMessage(ChatColor.GREEN + "added: " + "LeaderBoard " +iter + " " +toChange);
		}
		consolidateLeaderBoard();
		return true;
 }
 public void consolidateLeaderBoard() {
Set<String> ownerList = LBRYQuest.REDIS.keys("LeaderBoard *");
			int iter=1;
			int tempIter=iter+1;
				for (String tempOwnerList : ownerList) {
		if ((LBRYQuest.REDIS.get("LeaderBoard "+tempIter)) != null && ((LBRYQuest.REDIS.get("LeaderBoard "+iter)) == null)) {
					LBRYQuest.REDIS.set("LeaderBoard " +iter, LBRYQuest.REDIS.get("LeaderBoard " +tempIter));
					LBRYQuest.REDIS.del("LeaderBoard "+tempIter);
		}
					iter++;
					tempIter++;
				}
	}
}
