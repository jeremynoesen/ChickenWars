package me.Jeremaster101.ChickenWars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EggBlaster implements Listener {
	public static ItemStack ironsword() {
		ItemStack i1 = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(ChatColor.GOLD + "Right click to use egg blaster");
		i1.setItemMeta(m1);
		return i1;
	}

	public static ItemStack woodsword() {
		ItemStack i1 = new ItemStack(Material.WOOD_SWORD, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(ChatColor.GOLD + "Reloading...");
		i1.setItemMeta(m1);
		return i1;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ee) {
		final Player p = ee.getPlayer();
		if (p.getWorld().getName().endsWith("_cw")) {
			Action a = ee.getAction();
			if (Main.chicken.hasPlayer(p)) {
				if ((a == Action.RIGHT_CLICK_AIR) || (a == Action.RIGHT_CLICK_BLOCK)) {
					if (p.getItemInHand().getType() == Material.IRON_SWORD) {
						p.launchProjectile(Egg.class, p.getLocation().getDirection());
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
								p.getInventory().setItem(0, woodsword());
								p.sendMessage(Messages.starter() + ChatColor.GOLD
										+ "You used egg blaster. You can use Main.getPlugin() again in a 4 seconds.");
							}
						}, 1L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 2L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 3L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 4L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 5L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 6L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 7L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 8L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 9L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 10L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 11L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 12L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 13L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.launchProjectile(Egg.class, p.getLocation().getDirection());

								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
							}
						}, 14L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								if (p.getWorld().getName().endsWith("_cw")) {
									p.getInventory().setItem(0, ironsword());
									p.sendMessage(Messages.starter() + ChatColor.GOLD + "You can now use egg blaster.");
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
								}
							}
						}, 74L);
					}
				}
			}
		}
	}
}
