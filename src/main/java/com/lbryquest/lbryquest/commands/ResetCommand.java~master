package com.lbryquest.lbryquest.commands;

import com.lbryquest.lbryquest.LBRYQuest;
import org.bukkit.Bukkit;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.attribute.Attribute;


public class ResetCommand extends CommandAction {
    private LBRYQuest lbryQuest;

    public ResetCommand(LBRYQuest plugin) {
        this.lbryQuest = plugin;
    }
    public boolean run(CommandSender sender, Command cmd, String label, String[] args, Player player) {
	if (args.length == 0) {
		lbryQuest.REDIS.del("lootSpawnY");
		lbryQuest.REDIS.del("spawnCreated");
		lbryQuest.REDIS.set("gameRound","1");
		lbryQuest.REDIS.set("winner","true");
	    for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
			lbryQuest.REDIS.set("ClearInv" +offlinePlayer.getUniqueId().toString(), "true");
		}

	for(Player p : Bukkit.getServer().getWorld(lbryQuest.SERVERDISPLAY_NAME).getPlayers()) {
                p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
		lbryQuest.REDIS.del("ClearInv" +p.getUniqueId().toString());
		PlayerInventory pli1= p.getInventory();
		pli1.clear(); //delete player world datas
		pli1.setArmorContents(new ItemStack[4]);
		Inventory pe1  = p.getEnderChest();
		pe1.clear();
		p.setLevel(0);
		p.setExp(0);
		p.setExhaustion(0);
		p.setFoodLevel(20);
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	}
	    for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
		if (lbryQuest.REDIS.exists("LootAnnounced" +offlinePlayer.getUniqueId().toString())) {
			lbryQuest.REDIS.del("LootAnnounced" +offlinePlayer.getUniqueId().toString());
		}
		}
            	Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(lbryQuest.SERVERDISPLAY_NAME), false);
Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(lbryQuest.SERVERDISPLAY_NAME+"_the_end"), false);
Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(lbryQuest.SERVERDISPLAY_NAME+"_nether"), false);
		lbryQuest.deleteLootWorlds();
		lbryQuest.onEnable();
		lbryQuest.REDIS.del("winner");
		return true;	
	} else {
        	return false;
        }
    }
}
