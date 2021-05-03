package me.Jeremaster101.ChickenWars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class ChickenHook implements Listener {

	static Item hook;
	static int idk;

	public static ItemStack fleshhook() {
		ItemStack i1 = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(
				ChatColor.GOLD + "Right click to throw chicken hook, it will make whoever you hit come to you!");
		i1.setItemMeta(m1);
		return i1;
	}

	public static void throwhook(final Entity player, Vector throwVec, final Vector pullVec) {
		idk = 0;
		Location location = player.getLocation().toVector().add(player.getLocation().getDirection().multiply(0.5D))
				.toLocation(player.getWorld());
		hook = player.getWorld().dropItemNaturally(location.add(0.0D, 1.5D, 0.0D),
				new ItemStack(Material.TRIPWIRE_HOOK, 1));
		if (throwVec == null) {
			hook.setVelocity(player.getLocation().getDirection().multiply(2).add(new Vector(0.0D, 0.2D, 0.0D)));
		} else {
			hook.setVelocity(throwVec);
		}
		final int rep = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			public void run() {
				for (Entity en : hook.getNearbyEntities(1.0D, 1.0D, 1.0D)) {
					if ((en != player) && (en != hook) && (!(en instanceof Egg))) {
						if (pullVec == null) {
							en.setVelocity(
									player.getLocation().subtract(hook.getLocation()).multiply(0.16D).toVector());
						} else {
							en.setVelocity(pullVec);
						}
						en.getWorld().playSound(en.getLocation(), Sound.ENTITY_IRONGOLEM_ATTACK, 2.0F, 1.0F);
						if ((player instanceof Player)) {
							Player p = (Player) player;
							p.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.25F);
						}
						((Damageable) en).damage(2.0D);
						hook.remove();
						idk = 1;
					}
				}
			}
		}, 0L, 1L);
		final int rep1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			public void run() {
				hook.getWorld().playSound(hook.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1.4F, 0.8F);
				hook.getWorld().playEffect(hook.getLocation(), Effect.SMOKE, 1);
				if ((hook.getLocation().subtract(0.0D, 0.1D, 0.0D).getBlock().getType() != Material.AIR)
						|| (hook.getLocation().add(0.0D, 0.1D, 0.0D).getBlock().getType() != Material.AIR)
						|| (hook.getLocation().subtract(0.1D, 0.0D, 0.0D).getBlock().getType() != Material.AIR)
						|| (hook.getLocation().add(0.1D, 0.0D, 0.0D).getBlock().getType() != Material.AIR)
						|| (hook.getLocation().subtract(0.0D, 0.0D, 0.1D).getBlock().getType() != Material.AIR)
						|| (hook.getLocation().add(0.0D, 0.0D, 0.1D).getBlock().getType() != Material.AIR)
						|| (hook.isDead())) {
					hook.remove();
					idk = 1;
				}
			}
		}, 0L, 1L);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			public void run() {
				if (idk == 1) {
					Bukkit.getScheduler().cancelTask(rep1);
					Bukkit.getScheduler().cancelTask(rep);
					idk = 0;
				}
			}
		}, 0L, 1L);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (p.getWorld().getName().endsWith("_cw")) {
			Action a = e.getAction();
			if (Main.chicken.hasPlayer(p)) {
				if ((a == Action.RIGHT_CLICK_AIR) || (a == Action.RIGHT_CLICK_BLOCK)) {
					if (p.getItemInHand().getType() == Material.TRIPWIRE_HOOK) {
						ChickenHook.throwhook(p, null, null);
						p.sendMessage(Messages.starter() + ChatColor.GOLD
								+ "You threw chicken hook. You can use it again in 2 seconds.");
						p.getInventory().getItem(2).setAmount(0);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.getInventory().setItem(2, fleshhook());
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
								p.sendMessage(
										Messages.starter() + ChatColor.GOLD + "You can now throw a chicken hook.");
							}
						}, 40L);
					}
				}
			}
		}
	}
}
