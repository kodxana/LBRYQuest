package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetFeeCommand extends CommandAction {
    private LBRYQuest lbryQuest;

    public SetFeeCommand(LBRYQuest plugin) {
        this.lbryQuest = plugin;
    }

    public boolean run(CommandSender sender, Command cmd, String label, String[] args, Player player) {
	if (args.length == 1) {
		if (args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(ChatColor.GREEN + "please use a "+lbryQuest.DENOMINATION_NAME+" amount between "+lbryQuest.MIN_FEE+" - "+lbryQuest.MAX_FEE+" "+lbryQuest.DENOMINATION_NAME+"s/byte.");
			sender.sendMessage(ChatColor.GREEN + "/setfee <#>");
			return false;
		}
		try {
		if ((lbryQuest.isStringDouble(args[0])) || (lbryQuest.isStringInt(args[0]))) {
			if ((Double.parseDouble(args[0]) <= lbryQuest.MAX_FEE) && (Double.parseDouble(args[0]) >= 1.2)) {
				boolean setFee = lbryQuest.setSatByte(player.getUniqueId().toString(), Double.parseDouble(args[0]));
				System.out.println("set to " + args[0] + ""+lbryQuest.DENOMINATION_NAME+"s/byte: "+setFee);
				LBRYQuest.REDIS.set("txFee" + player.getUniqueId().toString(),args[0]);
				sender.sendMessage(ChatColor.GREEN + "Your wallet fee has been set to " + args[0] + ""+lbryQuest.DENOMINATION_NAME+"s/b");
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "failed, please use a "+lbryQuest.DENOMINATION_NAME+" amount between "+lbryQuest.MIN_FEE+" - "+lbryQuest.MAX_FEE+" "+lbryQuest.DENOMINATION_NAME+"s/byte.");
				sender.sendMessage(ChatColor.GREEN + "/setfee <#>");
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "failed, please use a "+lbryQuest.DENOMINATION_NAME+" amount between "+lbryQuest.MIN_FEE+" - "+lbryQuest.MAX_FEE+" "+lbryQuest.DENOMINATION_NAME+"s/byte.");
			sender.sendMessage(ChatColor.GREEN + "/setfee <#>");
			return false;
		}
		} catch (Exception e) {
		//e.printStackTrace();
		player.sendMessage(ChatColor.RED + "There was a problem updating your fee.");
    	}
	} else if (args.length == 0) {
		sender.sendMessage(ChatColor.GREEN + "please use a "+lbryQuest.DENOMINATION_NAME+" amount between "+lbryQuest.MIN_FEE+" - "+lbryQuest.MAX_FEE+" "+lbryQuest.DENOMINATION_NAME+"s/byte.");
		sender.sendMessage(ChatColor.GREEN + "/setfee <#>");
		return false;
	}

				return true;	
    }
}
