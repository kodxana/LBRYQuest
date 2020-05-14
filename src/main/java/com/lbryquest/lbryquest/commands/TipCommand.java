package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TipCommand extends CommandAction {
  private LBRYQuest lbryQuest;

  public TipCommand(LBRYQuest plugin) {
    lbryQuest = plugin;
  }

  public boolean run(
      CommandSender sender, Command cmd, String label, String[] args, final Player player) {
	try {
if (args[0].equalsIgnoreCase("help") || !(args.length >= 1)) {
        player.sendMessage(ChatColor.GREEN + "/tip <playername>  <amount> - Tip is used for player to player transactions.");
	}
	} catch (Exception e) {
      //e.printStackTrace();
        player.sendMessage(ChatColor.GREEN + "/tip <playername> <amount> - Tip is used for player to player transactions.");
    }

    //int MAX_SEND = 10000; // to be multiplied by DENOMINATION_FACTOR
    if (args.length == 2) {
        final Long sat = lbryQuest.convertCoinToSats(Double.parseDouble(args[1]));
      for (char c : sat.toString().toCharArray()) {
        if (!Character.isDigit(c)) return false;
      }
        if (args[1].length() > 10) {
        // maximum send is 10 digits
        return false;
      }



      if (sat != 0) {

        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.getName().equalsIgnoreCase(args[0])) {
                if (!args[0].equalsIgnoreCase(player.getDisplayName())) {
              try {


                  Long balance = lbryQuest.getBalance(player.getUniqueId().toString(),lbryQuest.CONFS_TARGET);

                if (balance >= sat) {
                  // TODO: Pay to user address
    		boolean setFee = lbryQuest.setSatByte(player.getUniqueId().toString(), Double.parseDouble(LBRYQuest.REDIS.get("txFee" + player.getUniqueId().toString())));
		  String didSend = lbryQuest.sendToAddress(player.getUniqueId().toString(),lbryQuest.REDIS.get("nodeAddress"+onlinePlayer.getUniqueId().toString()), sat);
                  if (didSend != "failed") {
                    lbryQuest.updateScoreboard(onlinePlayer);
                    lbryQuest.updateScoreboard(player);
                    player.sendMessage(
                        ChatColor.GREEN
                            + "You sent "
                            + ChatColor.LIGHT_PURPLE
                            + lbryQuest.globalDecimalFormat.format(lbryQuest.convertSatsToCoin(sat))
                            + " "
                            + LBRYQuest.CRYPTO_TICKER
                            + ChatColor.GREEN
                            + " to user "
                            + ChatColor.BLUE
                            + onlinePlayer.getName()
			    + ChatColor.BLUE + " "+ lbryQuest.TX_URL + didSend);
                    onlinePlayer.sendMessage(
                        ChatColor.GREEN
                            + "You got "
                            + ChatColor.LIGHT_PURPLE
                            + lbryQuest.globalDecimalFormat.format(lbryQuest.convertSatsToCoin(sat))
                            + " "
                            + LBRYQuest.CRYPTO_TICKER
                            + ChatColor.GREEN
                            + " from user "
                            + ChatColor.BLUE
                            + player.getName()
			    + ChatColor.BLUE + " "+ lbryQuest.TX_URL + didSend);
                  } else {
                    player.sendMessage(ChatColor.RED + "Tip failed.");
                  }
                } else {
                  player.sendMessage(ChatColor.DARK_RED + "Not enough balance");
                }
              } catch (Exception e) {
                player.sendMessage(ChatColor.DARK_RED + "Error. Please try again later.");
                System.out.println(e);
              }
            }
          }
        }
      } else {
        player.sendMessage(
            "error sending that amount.");
      }
    } else {
      return false;
    }
	try {
	      lbryQuest.updateScoreboard(player);
	} catch(Exception e) {
					e.printStackTrace();
				}
    return true;
  }
}

