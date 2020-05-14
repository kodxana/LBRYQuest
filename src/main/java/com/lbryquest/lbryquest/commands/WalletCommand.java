package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
//import com.lbryquest.lbryquest.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.text.*;
import java.math.BigDecimal;

public class WalletCommand extends CommandAction {
  private LBRYQuest lbryQuest;

  public WalletCommand(LBRYQuest plugin) {
    lbryQuest = plugin;
  }

  public boolean run(
      CommandSender sender, Command cmd, String label, String[] args, Player player) {
try {
if (args[0].equalsIgnoreCase("help") || !(args.length >= 1)) {
	      player.sendMessage(ChatColor.GREEN + "/wallet - Displays your wallet info.");
              player.sendMessage(ChatColor.GREEN + "/wallet <set> <address> - will set your own win address to an address of your choosing instead of the ingame wallet. ");
	      player.sendMessage(ChatColor.GREEN + "/tip <amount> <playername> - Tip is used for player to player transactions.");
	      player.sendMessage(ChatColor.GREEN + "/withdraw <amount> <address> - withdraw is used for External transactions to an address.");

	      player.sendMessage(ChatColor.GREEN + "Your Deposit address on this server: " + lbryQuest.getAccountAddress(player.getUniqueId().toString()));
String url = lbryQuest.ADDRESS_URL + lbryQuest.REDIS.get("nodeAddress"+ player.getUniqueId().toString());
      	      player.sendMessage(ChatColor.WHITE + "" + ChatColor.UNDERLINE + url);
	}
} catch (Exception e) {
      //e.printStackTrace();
      player.sendMessage(ChatColor.GREEN + "/wallet - Displays your wallet info.");
              player.sendMessage(ChatColor.GREEN + "/wallet <set> <address> - will set your own win address to an address of your choosing instead of the ingame wallet. ");
	      player.sendMessage(ChatColor.GREEN + "/tip <amount> <playername> - Tip is used for player to player transactions.");
	      player.sendMessage(ChatColor.GREEN + "/withdraw <amount> <address> - withdraw is used for External transactions to an address.");
    }

    try {
      //User user = new User(player);
      //lbryQuest.getWalletInfo(player.getUniqueId().toString());
player.sendMessage(ChatColor.GREEN + "Your Deposit address on this server: " + lbryQuest.getAccountAddress(player.getUniqueId().toString()));
String url = lbryQuest.ADDRESS_URL + lbryQuest.REDIS.get("nodeAddress"+ player.getUniqueId().toString());
      player.sendMessage(ChatColor.WHITE + "" + ChatColor.UNDERLINE + url);
      //Long balance1 = lbryQuest.getBalance(player.getUniqueId().toString(),1);
     //Double playerCoinBalance1 = ((Double)(BigDecimal.valueOf(lbryQuest.getBalance(player.getUniqueId().toString(),1)).doubleValue() * lbryQuest.baseSat));
      //Long balance6 = lbryQuest.getBalance(player.getUniqueId().toString(),6);
		Double playerCoinBalance6 = (Double)(BigDecimal.valueOf(lbryQuest.getBalance(player.getUniqueId().toString(),lbryQuest.CONFS_TARGET)).doubleValue() * lbryQuest.baseSat);
      
      
      //Long unconfirmedBalance = lbryQuest.getUnconfirmedBalance(player.getUniqueId().toString());
      Double playerCoinBalanceUnconfirmed = (Double)(BigDecimal.valueOf(lbryQuest.getUnconfirmedBalance(player.getUniqueId().toString())).doubleValue() * lbryQuest.baseSat);





		player.sendMessage(ChatColor.GREEN + "wallet balance with "+lbryQuest.CONFS_TARGET+"-conf+: " + lbryQuest.globalDecimalFormat.format(playerCoinBalance6));
		//player.sendMessage(ChatColor.GREEN + "wallet balance with 1-conf+: " + satoshiQuest.globalDecimalFormat.format(playerCoinBalance1));
      player.sendMessage(ChatColor.DARK_GREEN + "wallet unconfirmed: " + lbryQuest.globalDecimalFormat.format(playerCoinBalanceUnconfirmed));
	if (LBRYQuest.REDIS.exists("txFee" + player.getUniqueId().toString())) {
		player.sendMessage(ChatColor.GREEN + "player fee is set to " + LBRYQuest.REDIS.get("txFee" + player.getUniqueId().toString()) + ""+ lbryQuest.DENOMINATION_NAME +"/byte.");
	}
		DecimalFormat df = new DecimalFormat(LBRYQuest.USD_DECIMALS); df = new DecimalFormat("0.00");
	        	//System.out.print(df.format(exRate));
	player.sendMessage(ChatColor.GREEN + "1 " + lbryQuest.COINGECKO_CRYPTO + " = $" + df.format(lbryQuest.exRate));
	if (lbryQuest.REDIS.exists("ExternalAddress" +player.getUniqueId().toString())) {
	player.sendMessage(ChatColor.GREEN + "On win address set to: " + lbryQuest.REDIS.get("ExternalAddress" +player.getUniqueId().toString()));
	} else {
	player.sendMessage(ChatColor.YELLOW + "On win address not set, default is ingame wallet.");
	player.sendMessage(ChatColor.YELLOW + "to set your On Win address to send funds to if won instead of your ingame wallet use command: (/wallet set <wallet address>) ");
	}

      	if (args.length > 0) {
	if (args[0].equalsIgnoreCase("del")) {
	lbryQuest.REDIS.del("ExternalAddress" +player.getUniqueId().toString());
	player.sendMessage(ChatColor.RED + "Your On Win address has been deleted");
	} else if ((args[0].equalsIgnoreCase("set")) && (args.length > 1)) {
	lbryQuest.REDIS.set("ExternalAddress" +player.getUniqueId().toString(), args[1]);
	player.sendMessage(ChatColor.GREEN + "Set your On Win address to: " + lbryQuest.REDIS.get("ExternalAddress" +player.getUniqueId().toString()));
	
	} else {
	player.sendMessage(ChatColor.YELLOW + "to set your On Win address to send funds to if won instead of your ingame wallet use command: (/wallet set <wallet address>) ");
	}
	}

      lbryQuest.updateScoreboard(player);
    } catch (Exception e) {
      e.printStackTrace();
      player.sendMessage(ChatColor.RED + "There was a problem reading your wallet.");
    }

    return true;
  }
}
