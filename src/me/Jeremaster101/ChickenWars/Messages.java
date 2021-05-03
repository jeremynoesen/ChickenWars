package me.Jeremaster101.ChickenWars;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {
	public static String starter() {
		return ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW.toString() + ChatColor.BOLD + "C" + ChatColor.RED.toString()
				+ ChatColor.BOLD + "W" + ChatColor.RESET + ChatColor.DARK_GRAY + "] ";
	}

	public static boolean help(Player player, boolean hasPerms) {
		if (hasPerms == true) {
			player.sendMessage(
					ChatColor.GRAY + "--------------- " + starter() + ChatColor.GRAY + "---------------");
			player.sendMessage(ChatColor.GOLD + "/cw help" + ChatColor.GRAY + " Help command.");
			player.sendMessage(ChatColor.GOLD + "/cw setspawn" + ChatColor.GRAY + " Set the chicken wars spawn.");
			player.sendMessage(ChatColor.GOLD + "/cw setuphelp" + ChatColor.GRAY + " Setup instructions.");
			player.sendMessage(ChatColor.GOLD + "/cw setmaptop" + ChatColor.GRAY + " Set map top corner bounds.");
			player.sendMessage(ChatColor.GOLD + "/cw setmapbottom" + ChatColor.GRAY + " Set map bottom corner bounds.");
			player.sendMessage(ChatColor.GOLD + "/cw setup" + ChatColor.GRAY + " Make the world optimal for the game.");
			player.sendMessage(ChatColor.GOLD + "/cw spectate" + ChatColor.GRAY + " Spectate a game of ChickenWars.");
			player.sendMessage(ChatColor.GOLD + "/cw play" + ChatColor.GRAY + " Play a game of ChickenWars.");
			player.sendMessage(ChatColor.GOLD + "/cw build" + ChatColor.GRAY + " Enter build mode.");
		}
		if (hasPerms == false) {
			player.sendMessage(
					ChatColor.GRAY + "--------------- " + starter() + ChatColor.GRAY + "---------------");
			player.sendMessage(ChatColor.GOLD + "/cw spectate" + ChatColor.GRAY + " Spectate a game of ChickenWars.");
			player.sendMessage(ChatColor.GOLD + "/cw play" + ChatColor.GRAY + " Play a game of ChickenWars.");
		}
		return true;
	}
}
