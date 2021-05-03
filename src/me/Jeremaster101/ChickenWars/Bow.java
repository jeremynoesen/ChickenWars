package me.Jeremaster101.ChickenWars;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Bow implements Listener {
	
	static int Task;
	
	public static HashMap<String, Integer> grasped = new HashMap<String, Integer>();

	public static ItemStack bow() {
		ItemStack i1 = new ItemStack(Material.BOW, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(ChatColor.GOLD + "Right click to fire, left to use deadly chicken grasp.");
		i1.setItemMeta(m1);
		return i1;
	}

	public static ItemStack bowreload() {
		ItemStack i1 = new ItemStack(Material.STICK, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(ChatColor.GOLD + "Reloading...");
		i1.setItemMeta(m1);
		return i1;
	}

	public static ItemStack arrow() {
		ItemStack i1 = new ItemStack(Material.ARROW, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(ChatColor.GOLD + "Throwable drumstick");
		i1.setItemMeta(m1);
		return i1;
	}
	
	@SuppressWarnings("deprecation")
	static void grasp(final Player p) {
		grasped.put(p.getName(), Integer.valueOf(0));
		if (p.getItemInHand().getType() == Material.BOW) {
			p.sendMessage(Messages.starter() + ChatColor.GOLD
					+ "You used Deadly chicken grasp, you can use it again in 4 seconds or when you hit a player.");
			p.getInventory().setItem(3, bowreload());
			p.setVelocity(p.getLocation().getDirection().multiply(2.5D));
			p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_HURT, 1.0F, 1.4F);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					if (grasped.get(p.getName()) == 0) {
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
						p.sendMessage(Messages.starter() + ChatColor.GOLD + "You can now use deadly chicken grasp.");
					}
					grasped.put(p.getName(), Integer.valueOf(1));
					if (!p.getInventory().contains(bow())) {
						p.getInventory().setItem(3, bow());
					}
				}
			}, 80L);
			Task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
				public void run() {
					if (grasped.get(p.getName()) == 0) {
						for (Entity en : p.getNearbyEntities(1.0D, 1.0D, 1.0D)) {
							if (((en != p) && (p.getItemInHand().getType() == Material.BOW))
									|| (p.getItemInHand().getType() == Material.STICK)) {
								((LivingEntity) en).setMaximumNoDamageTicks(1);
								((Damageable) en).damage(6.0D);
								en.setVelocity(p.getLocation().subtract(en.getLocation()).toVector().multiply(2));
								p.setVelocity(new Vector(0, 0, 0));
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_HURT, 1.4F, 0.8F);
								grasped.put(p.getName(), 1);
								if (!p.getInventory().contains(bow())) {
									p.getInventory().setItem(3, bow());
								}
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
								p.sendMessage(Messages.starter() + ChatColor.GOLD
										+ "You can now use deadly chicken grasp.");
							}
						}
					}
				}
			}, 0L, 1L);
			if (grasped.get(p.getName()) == 1) {
				Bukkit.getScheduler().cancelTask(Task);
				grasped.put(p.getName(), 0);
			}
		}
	}
	
	public HashMap<String, Integer> arrows = new HashMap<String, Integer>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (p.getWorld().getName().endsWith("_cw")) {
			Action a = e.getAction();
			if (Main.chicken.hasPlayer(p)) {
				if ((a == Action.RIGHT_CLICK_AIR) || (a == Action.RIGHT_CLICK_BLOCK)) {
					if (p.getItemInHand().getType() == Material.BOW) {
						if (!p.getInventory().contains(arrow())) {
							arrows.put(p.getName(), 0);
						} else {
							arrows.put(p.getName(), 1);
						}
						if (arrows.get(p.getName()) == 0) {
							p.getInventory().setItem(8, arrow());
							arrows.put(p.getName(), 1);
						}
					}
				}
				if ((a == Action.LEFT_CLICK_AIR) || (a == Action.LEFT_CLICK_BLOCK)) {
					if (p.getItemInHand().getType() == Material.BOW) {
						Bow.grasp(p);
					} else if ((p.getItemInHand().getType() != Material.BOW)
							&& (p.getItemInHand().getType() != Material.STICK)) {
						Bukkit.getScheduler().cancelTask(Task);
						for (Entity en : p.getNearbyEntities(6.0D, 6.0D, 6.0D)) {
							((LivingEntity) en).setMaximumNoDamageTicks(20);
						}
					}
				}
			}
		}
	}
}
