package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;


public class ModCommand extends CommandAction {
    private LBRYQuest lbryQuest;

    public ModCommand(LBRYQuest plugin) {
        this.lbryQuest = plugin;
    }
    public boolean run(CommandSender sender, Command cmd, String label, String[] args, Player player) {
        if(args[0].equalsIgnoreCase("add")) {
            // Sub-command: /mod add

            if(LBRYQuest.REDIS.exists("uuid:"+args[1])) {
                UUID uuid=UUID.fromString(LBRYQuest.REDIS.get("uuid:"+args[1]));
                LBRYQuest.REDIS.sadd("moderators",uuid.toString());
                sender.sendMessage(ChatColor.GREEN+LBRYQuest.REDIS.get("name:"+uuid)+" added to moderators group");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED+"Cannot find player "+args[1]);
                return true;
            }
        } else if(args[0].equalsIgnoreCase("remove")) {
            // Sub-command: /mod del
            if(LBRYQuest.REDIS.exists("uuid:"+args[1])) {
                UUID uuid=UUID.fromString(LBRYQuest.REDIS.get("uuid:"+args[1]));
                LBRYQuest.REDIS.srem("moderators",uuid.toString());
                return true;
            }
            return false;
        } else if(args[0].equalsIgnoreCase("list")) {
            // Sub-command: /mod list
            Set<String> moderators=LBRYQuest.REDIS.smembers("moderators");
            for(String uuid:moderators) {
                sender.sendMessage(ChatColor.YELLOW+LBRYQuest.REDIS.get("name:"+uuid));
            }
            return true;
        } else if(args[0].equalsIgnoreCase("flag")) {
	try{
	if (!(LBRYQuest.REDIS.exists("ModFlag"))){
		LBRYQuest.REDIS.set("ModFlag","true");
		player.sendMessage(ChatColor.RED + "ModFlag is ON");
	 }         
	else if (LBRYQuest.REDIS.get("ModFlag").equals("false")){
		LBRYQuest.REDIS.set("ModFlag","true");
		player.sendMessage(ChatColor.RED + "ModFlag is ON");
           }
	 else {
		LBRYQuest.REDIS.set("ModFlag","false");
		player.sendMessage(ChatColor.RED + "ModFlag is OFF");
           }
		} catch (NullPointerException nullPointer)
		{
                	System.out.println("modflag: "+nullPointer);
		}

	return true;	
	} else if(args[0].equalsIgnoreCase("beta")) {
	try{
	if (!(LBRYQuest.REDIS.exists("BetaTest"))){
		LBRYQuest.REDIS.set("BetaTest","true");
		player.sendMessage(ChatColor.RED + "BetaTest is ON");
	 } else {
		LBRYQuest.REDIS.del("BetaTest");
		player.sendMessage(ChatColor.RED + "BetaTest is OFF");
           }
		} catch (NullPointerException nullPointer)
		{
                	System.out.println("BetaTest: "+nullPointer);
		}

	return true;	
	} else if(args[0].equalsIgnoreCase("expandingloot")) {
	try{
	if (!(LBRYQuest.REDIS.exists("expandingloot"))){
		LBRYQuest.REDIS.set("expandingloot","true");
		player.sendMessage(ChatColor.RED + "expandingloot is ON");
	 } else {
		LBRYQuest.REDIS.del("expandingloot");
		player.sendMessage(ChatColor.RED + "expandingloot is OFF");
           }
		} catch (NullPointerException nullPointer)
		{
                	System.out.println("expandingloot: "+nullPointer);
		}

	return true;	
	} else {
            return false;
        }
    }
}
