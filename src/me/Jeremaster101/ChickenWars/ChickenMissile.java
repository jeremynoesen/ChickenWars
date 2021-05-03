package me.Jeremaster101.ChickenWars;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChickenMissile implements Listener {

	public HashMap<String, Integer> y = new HashMap<String, Integer>();
	
	public static ItemStack ironaxe() {
		ItemStack i1 = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(ChatColor.GOLD + "Right click to use guided chicken missle");
		i1.setItemMeta(m1);
		return i1;
	}

	public static ItemStack woodaxe() {
		ItemStack i1 = new ItemStack(Material.WOOD_AXE, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(ChatColor.GOLD + "Reloading...");
		i1.setItemMeta(m1);
		return i1;
	}

	@SuppressWarnings("deprecation")
	public void missile(final Player player) {
		if (player.getWorld().getName().endsWith("_cw")) {
			final Chicken s = player.getLocation().getWorld()
					.spawn(player.getLocation().add(0.0D, 1.5D, 0.0D), Chicken.class);
			s.setCustomNameVisible(false);
			s.setBaby();
			s.setRemoveWhenFarAway(false);
			s.setVelocity(player.getEyeLocation().getDirection().multiply(0.6D));
			player.playSound(s.getLocation(), Sound.ENTITY_CHICKEN_HURT, 1.0F, 1.5F);
			s.getLocation().setDirection(player.getLocation().getDirection());
			s.setMaxHealth(1000.0D);
			s.setHealth(1000.0D);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					if (!s.isDead()) {
						s.setHealth(0.0D);
						TNTPrimed tnt = (TNTPrimed) s.getWorld().spawn(s.getLocation().add(0.0D, 0.5D, 0.0D),
								TNTPrimed.class);
						tnt.setFuseTicks(0);
						tnt.setYield(2.0F);
					}
				}
			}, 120L);

			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
				public void run() {
					if (!s.isDead()) {
						s.getWorld().playEffect(s.getLocation(), Effect.SMOKE, 2);
						s.setVelocity(player.getEyeLocation().getDirection().multiply(0.6D));
						player.getWorld().playSound(s.getLocation(), Sound.ENTITY_CHICKEN_HURT, 1.0F, 1.5F);
						if ((s.getLocation().subtract(0.0D, 0.5D, 0.0D).getBlock().getType() != Material.AIR)
								|| (s.getLocation().add(0.0D, 0.5D, 0.0D).getBlock().getType() != Material.AIR)
								|| (s.getLocation().subtract(0.5D, 0.0D, 0.0D).getBlock().getType() != Material.AIR)
								|| (s.getLocation().add(0.5D, 0.0D, 0.0D).getBlock().getType() != Material.AIR)
								|| (s.getLocation().subtract(0.0D, 0.0D, 0.5D).getBlock().getType() != Material.AIR)
								|| (s.getLocation().add(0.0D, 0.0D, 0.5D).getBlock().getType() != Material.AIR)) {
							s.setHealth(0.0D);
							TNTPrimed tnt = (TNTPrimed) s.getWorld().spawn(s.getLocation().add(0.0D, 0.5D, 0.0D),
									TNTPrimed.class);
							tnt.setFuseTicks(0);
							tnt.setYield(2.0F);
						}
						for (Entity en : s.getNearbyEntities(1.0D, 1.0D, 1.0D)) {
							if ((en != s) && (en != player) && (en.getType() != EntityType.PRIMED_TNT)
									&& (!Main.notplaying.hasPlayer((OfflinePlayer) en))) {
								s.setHealth(0.0D);
								TNTPrimed tnt = (TNTPrimed) s.getWorld().spawn(s.getLocation().add(0.0D, 0.5D, 0.0D),
										TNTPrimed.class);
								tnt.setFuseTicks(0);
								tnt.setYield(2.0F);
								player.getInventory().removeItem(new ItemStack[] { woodaxe() });
								player.getInventory().addItem(new ItemStack[] { ironaxe() });
								player.sendMessage(
										Messages.starter() + ChatColor.GOLD + "You can now use guided chicken missle.");
								player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
								y.put(player.getName(), Integer.valueOf(1));
							}
						}
					}
					if (s.isDead()) {
						s.getLocation().setY(0.0D);
					}
				}
			}, 0L, 1L);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (p.getWorld().getName().endsWith("_cw")) {
			Action a = e.getAction();
			if (Main.chicken.hasPlayer(p)) {
				if ((a == Action.RIGHT_CLICK_AIR) || (a == Action.RIGHT_CLICK_BLOCK)) {
					if (p.getItemInHand().getType() == Material.IRON_AXE) {
						missile(p);
						p.getInventory().setItem(1, woodaxe());
						p.sendMessage(Messages.starter() + ChatColor.GOLD
								+ "You used guided chicken missle. You can use this again in 6 seconds or when you hit a player.");
						this.y.put(p.getName(), 0);

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								if ((y.get(p.getName()) == 0)
										&& (p.getWorld().getName().endsWith("_cw"))
										&& (!p.getInventory().contains(Material.IRON_AXE))) {
									p.getInventory().setItem(1, ironaxe());
									p.sendMessage(Messages.starter() + ChatColor.GOLD
											+ "You can now use guided chicken missle.");
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
								}

							}
						}, 120L);
					}
				}
			}
		}
	}
}
