
package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.math.BigDecimal;

public class LivesCommand extends CommandAction {
  private LBRYQuest lbryQuest;

  public LivesCommand(LBRYQuest plugin) {
    lbryQuest = plugin;
  }

  public boolean run(
      CommandSender sender, Command cmd, String label, String[] args, Player player) {
	//lbryQuest.REDIS.get("LivesLeft" + player.getUniqueId().toString())
	Long balance = 0L;
   if (args.length > 0) {
      if ((args[0].equalsIgnoreCase("help"))||(args.length == 0)) {
	try {
      		lbryQuest.getWalletInfo(player.getUniqueId().toString());
		balance = lbryQuest.getBalance(player.getUniqueId().toString(),lbryQuest.CONFS_TARGET);
		player.sendMessage(ChatColor.GREEN + "wallet balance: " + balance);
	} catch (Exception e) {
		e.printStackTrace();
		player.sendMessage(ChatColor.RED + "There was a problem reading your wallet.");
    	}
	if (lbryQuest.didVote(player.getName()) == 0) {
	player.sendMessage(ChatColor.GREEN + "Lives are $" + lbryQuest.BUYIN_AMOUNT + " USD for " + lbryQuest.LIVES_PERBUYIN + " in "+LBRYQuest.CRYPTO_TICKER+". Most goes into Loot wallet which everyone searches for the treasure, once found that player will recive funds to their player wallet if no external wallet set and the world will reset for a new hunt. A little bit is set aside for further developent and hosting.");
	player.sendMessage(ChatColor.AQUA + "You can also get 10% off lives here: " + lbryQuest.VOTE_URL);
	} else if (lbryQuest.didVote(player.getName()) == 1) {
		player.sendMessage(ChatColor.AQUA + "THANK YOU FOR VOTING!");
player.sendMessage(ChatColor.GREEN + "Lives are $" + (lbryQuest.BUYIN_AMOUNT*0.9) + " USD for " + lbryQuest.LIVES_PERBUYIN + " in "+LBRYQuest.CRYPTO_TICKER+". Most goes into Loot wallet which everyone searches for the treasure, once found that player will recive funds to their player wallet if no external wallet set and the world will reset for a new hunt. A little bit is set aside for further developent and hosting.");
	}
	if (balance == 0) {
		player.sendMessage(ChatColor.RED + "Looks like you dont have enough funds, try the (/wallet) command to check your balance and deposit address.");
	}
	player.sendMessage(ChatColor.YELLOW + "You can use the command (/Lives [Number]) like (/Lives 1) or (/Lives 2)");
	if (lbryQuest.didVote(player.getName()) == 0) {
	player.sendMessage(ChatColor.YELLOW + "But to complete it you need to say buy to confirm so (/Lives 3 buy) will get you "+(lbryQuest.LIVES_PERBUYIN * 3)+" lives for " + (3 * lbryQuest.totalLifeRate));
	} else if (lbryQuest.didVote(player.getName()) == 1) {
	player.sendMessage(ChatColor.AQUA + "THANK YOU FOR VOTING!");
	player.sendMessage(ChatColor.YELLOW + "But to complete it you need to say buy to confirm so (/Lives 3 buy) will get you "+(lbryQuest.LIVES_PERBUYIN * 3)+" lives for " + (3 * (lbryQuest.totalLifeRate*0.9)));
	}
	player.sendMessage(ChatColor.GREEN + "Lives are transferable between players with (/Lives [Number] send [playername])");
     } else if ((lbryQuest.isStringInt(args[0])) && (args.length <= 3)) {  //end help
	if (Integer.parseInt(args[0]) > 0) {
		try {
			balance = lbryQuest.getBalance(player.getUniqueId().toString(),lbryQuest.CONFS_TARGET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int livesAmount = Integer.valueOf(args[0]);
		Long sendLoot = lbryQuest.livesRate * livesAmount;
		Long sendAdmin = lbryQuest.adminRate * livesAmount;
		Long sendAdmin1 = sendAdmin / 2;
		Long sendAdmin2 = sendAdmin - sendAdmin1;
		Long totalBuyingBTC = lbryQuest.totalLifeRate * livesAmount;
		if (lbryQuest.didVote(player.getName()) == 1) {
		sendLoot = (long)(lbryQuest.livesRate*0.9) * livesAmount;
		sendAdmin = (long)(lbryQuest.adminRate*0.9) * livesAmount;
		sendAdmin1 = sendAdmin / 2;
		sendAdmin2 = sendAdmin - sendAdmin1;
		totalBuyingBTC = (long)(lbryQuest.totalLifeRate*0.9) * livesAmount;
		}
		if (args.length == 1) {
		player.sendMessage(ChatColor.YELLOW + "Buy " + (lbryQuest.LIVES_PERBUYIN * livesAmount) + " Lives for " + lbryQuest.globalDecimalFormat.format(((Double)(BigDecimal.valueOf(totalBuyingBTC).doubleValue() * lbryQuest.baseSat))) + " with " + lbryQuest.globalDecimalFormat.format(((Double)(BigDecimal.valueOf(sendLoot).doubleValue() * lbryQuest.baseSat))) + " going into the loot treasure and " + lbryQuest.globalDecimalFormat.format(((Double)(BigDecimal.valueOf(sendAdmin).doubleValue() * lbryQuest.baseSat))) + " going to the admin");
		}
		if (args.length == 2) {
			if (args[1].equalsIgnoreCase("buy")) {
				String result = "failed";
				try {
					if (lbryQuest.ADMIN2_ADDRESS != "noSet") {
						result = lbryQuest.sendMany2(player.getUniqueId().toString(), 							lbryQuest.REDIS.get("nodeAddress"+lbryQuest.SERVERDISPLAY_NAME), lbryQuest.ADMIN_ADDRESS, lbryQuest.ADMIN2_ADDRESS, sendLoot, sendAdmin1, sendAdmin2);				
					} else {
						result = lbryQuest.sendMany(player.getUniqueId().toString(), 							lbryQuest.REDIS.get("nodeAddress"+lbryQuest.SERVERDISPLAY_NAME), lbryQuest.ADMIN_ADDRESS, sendLoot, sendAdmin);
					}

					//end if multidev
					Long newBalance = lbryQuest.getBalance(player.getUniqueId().toString(),lbryQuest.CONFS_TARGET);
					boolean setFee = lbryQuest.setSatByte(player.getUniqueId().toString(), Double.parseDouble(LBRYQuest.REDIS.get("txFee" + player.getUniqueId().toString())));
					if ((result != "failed") && (balance > newBalance)) {
						String setLives = Integer.toString(((Integer.valueOf(lbryQuest.REDIS.get("LivesLeft" +player.getUniqueId().toString()))) + (lbryQuest.LIVES_PERBUYIN * livesAmount)));
						lbryQuest.REDIS.set("LivesLeft" +player.getUniqueId().toString(), setLives);
					player.sendMessage(ChatColor.GREEN + "You just got " + (lbryQuest.LIVES_PERBUYIN * livesAmount) + " lives! "+ ChatColor.BLUE + lbryQuest.TX_URL + result);
					} else if (result == "failed") {
						player.sendMessage(ChatColor.RED + "Buy lives failed, may be due to not enough confirmed balance. try /wallet to check.");
					}
					System.out.println("[LivesBuy] " + result);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}//end true
		}//end args.length == 2
	
			if (args.length == 3) {

			if (args[1].equalsIgnoreCase("send")) {

				String sendWho = args[2];
				for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
          			if (onlinePlayer.getName().equalsIgnoreCase(sendWho)) {

	            			if (!sendWho.equalsIgnoreCase(player.getDisplayName())) {
						if (Integer.valueOf(lbryQuest.REDIS.get("LivesLeft" +player.getUniqueId().toString())) >= 2) {

							if (Integer.valueOf(args[0]) <= Integer.valueOf(lbryQuest.REDIS.get("LivesLeft" +player.getUniqueId().toString()))) {
						       
														player.sendMessage(ChatColor.GREEN + "Sending " + args[0] + " lives to " + sendWho);
							String minusLives = Integer.toString((Integer.valueOf(lbryQuest.REDIS.get("LivesLeft" +player.getUniqueId().toString())) - Integer.valueOf(args[0])));
							String plusLives = Integer.toString((Integer.valueOf(lbryQuest.REDIS.get("LivesLeft" +Bukkit.getServer().getPlayer(sendWho).getUniqueId())) + Integer.valueOf(args[0])));
							lbryQuest.REDIS.set("LivesLeft" +player.getUniqueId().toString(), minusLives);
							lbryQuest.REDIS.set("LivesLeft" + Bukkit.getServer().getPlayer(sendWho).getUniqueId(),plusLives);
							onlinePlayer.sendMessage(ChatColor.GREEN +""+player.getDisplayName()+ " Sent " + args[0] + " lives to you!");
							}
						} else {
							player.sendMessage(ChatColor.RED + "You need 2 or more lives to be able to send to another player");
						}
					}
				}

				}//end for
			}//if send
		}//end (args.length == 3)
		}//end is args[0] > 0
	 } //end isStringInt
	}//end args length >0
	try {
	lbryQuest.updateScoreboard(player);
	} catch(Exception e) {
		e.printStackTrace();
	}
    return true;
  }


}
