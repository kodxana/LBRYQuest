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
			sender.sendMessage(ChatColor.GREEN + "please use a satoshi amount between 0.0001 - 15 LBC/byte.");
			sender.sendMessage(ChatColor.GREEN + "/setfee <#>");
			return false;
		}
		try {
		if ((lbryQuest.isStringDouble(args[0])) || (lbryQuest.isStringInt(args[0]))) {
			if ((Double.parseDouble(args[0]) <= 15) && (Double.parseDouble(args[0]) >= 0.0001)) {
				boolean setFee = lbryQuest.setSatByte(player.getUniqueId().toString(), Double.parseDouble(args[0]));
				System.out.println("set to " + args[0] + "LBC/byte: "+setFee);
				LBRYQuest.REDIS.set("txFee" + player.getUniqueId().toString(),args[0]);
				sender.sendMessage(ChatColor.GREEN + "Your wallet fee has been set to " + args[0] + "lbc/b");
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "failed, please use a LBC amount between 0.0001 - 15 LBC/byte.");
				sender.sendMessage(ChatColor.GREEN + "/setfee <#>");
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "failed, please use a LBC amount between 0.0001 - 15 LBC/byte.");
			sender.sendMessage(ChatColor.GREEN + "/setfee <#>");
			return false;
		}
		} catch (Exception e) {
		//e.printStackTrace();
		player.sendMessage(ChatColor.RED + "There was a problem updating your fee.");
    	}
	} else if (args.length == 0) {
		sender.sendMessage(ChatColor.GREEN + "please use a satoshi amount between 0.0001 - 15 LBC/byte.");
		sender.sendMessage(ChatColor.GREEN + "/setfee <#>");
		return false;
	}

				return true;	
    }
}
