package me.Jeremaster101.ChickenWars;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EggBomb implements Listener {
	
	public static HashMap<String, Integer> bomb = new HashMap<String, Integer>();
	
	Projectile proj;
	int th;
	
	public static ItemStack ironpick() {
		ItemStack i1 = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(ChatColor.GOLD + "Right click to use egg bomb (one time use!)");
		i1.setItemMeta(m1);
		return i1;
	}

	public static ItemStack woodpick() {
		ItemStack i1 = new ItemStack(Material.WOOD_PICKAXE, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName(ChatColor.GOLD + "Right click to stop the bomb where it is");
		i1.setItemMeta(m1);
		return i1;
	}
	
	private void eggs(Entity e) {
		int ran = Main.getRandom(-2, 2);
		int ran1 = Main.getRandom(-2, 2);
		int ran2 = Main.getRandom(-2, 2);
		int ran3 = Main.getRandom(-2, 2);
		int ran4 = Main.getRandom(-2, 2);
		int ran5 = Main.getRandom(-2, 2);
		int ran6 = Main.getRandom(-2, 2);
		int ran7 = Main.getRandom(-2, 2);
		int ran10 = Main.getRandom(0, 3);

		Location loc = e.getLocation();
		final Entity pro = e.getWorld().spawnEntity(loc, EntityType.EGG);
		pro.setVelocity(new Vector(ran, 6, ran1).multiply(new Vector(2.0E-4D, 2.0E-4D, 2.0E-4D))
				.multiply(new Vector(ran10, ran10, ran10)).normalize());
		final Entity pro1 = e.getWorld().spawnEntity(loc, EntityType.EGG);
		pro1.setVelocity(new Vector(ran2, 5, ran3).multiply(new Vector(2.0E-4D, 2.0E-4D, 2.0E-4D))
				.multiply(new Vector(ran10, ran10, ran10)).normalize());
		final Entity pro2 = e.getWorld().spawnEntity(loc, EntityType.EGG);
		pro2.setVelocity(new Vector(ran4, 4, ran5).multiply(new Vector(2.0E-4D, 2.0E-4D, 2.0E-4D))
				.multiply(new Vector(ran10, ran10, ran10)).normalize());
		final Entity pro3 = e.getWorld().spawnEntity(loc, EntityType.EGG);
		pro3.setVelocity(new Vector(ran6, 3, ran7).multiply(new Vector(2.0E-4D, 2.0E-4D, 2.0E-4D))
				.multiply(new Vector(ran10, ran10, ran10)).normalize());
		final Entity pro4 = e.getWorld().spawnEntity(loc, EntityType.EGG);
		pro4.setVelocity(new Vector(ran, 2, ran1).multiply(new Vector(2.0E-4D, 2.0E-4D, 2.0E-4D))
				.multiply(new Vector(ran10, ran10, ran10)).normalize());
		final Entity pro5 = e.getWorld().spawnEntity(loc, EntityType.EGG);
		pro5.setVelocity(new Vector(ran2, 1, ran3).multiply(new Vector(2.0E-4D, 2.0E-4D, 2.0E-4D))
				.multiply(new Vector(ran10, ran10, ran10)).normalize());
		final Entity pro6 = e.getWorld().spawnEntity(loc, EntityType.EGG);
		pro6.setVelocity(new Vector(ran4, 0, ran5).multiply(new Vector(2.0E-4D, 2.0E-4D, 2.0E-4D))
				.multiply(new Vector(ran10, ran10, ran10)).normalize());
		final Entity pro7 = e.getWorld().spawnEntity(loc, EntityType.EGG);
		pro7.setVelocity(new Vector(ran6, -1, ran7).multiply(new Vector(2.0E-4D, 2.0E-4D, 2.0E-4D))
				.multiply(new Vector(ran10, ran10, ran10)).normalize());
		e.getWorld().playSound(e.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				pro.remove();
				pro1.remove();
				pro2.remove();
				pro3.remove();
				pro4.remove();
				pro5.remove();
				pro6.remove();
				pro7.remove();
			}
		}, 200L);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ee) {
		final Player p = ee.getPlayer();
		if (p.getWorld().getName().endsWith("_cw")) {
			Action a = ee.getAction();
			if (Main.chicken.hasPlayer(p)) {
				if ((a == Action.RIGHT_CLICK_AIR) || (a == Action.RIGHT_CLICK_BLOCK)) {
					if (p.getItemInHand().getType() == Material.WOOD_PICKAXE) {
						p.getInventory().getItemInMainHand().setAmount(0);
						final int ID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							public void run() {
								if ((!proj.isDead()) && ((proj.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock()
										.getType() == Material.AIR)
										|| (proj.getLocation().add(0.0D, 1.0D, 0.0D).getBlock()
												.getType() == Material.AIR)
										|| (proj.getLocation().subtract(1.0D, 0.0D, 0.0D).getBlock()
												.getType() == Material.AIR)
										|| (proj.getLocation().add(1.0D, 0.0D, 0.0D).getBlock()
												.getType() == Material.AIR)
										|| (proj.getLocation().subtract(0.0D, 0.0D, 1.0D).getBlock()
												.getType() == Material.AIR)
										|| (proj.getLocation().add(0.0D, 0.0D, 1.0D).getBlock()
												.getType() == Material.AIR))) {
									proj.setVelocity(new Vector(0, 0, 0));
									eggs(proj);
									int j = 0;
									for (Iterator<Entity> localIterator = proj.getNearbyEntities(150.0D, 150.0D, 150.0D)
											.iterator(); localIterator.hasNext(); j++) {
										Entity en = (Entity) localIterator.next();
										if ((!(en instanceof Player)) || (en == p))
											break;
										Vector pull = proj.getLocation().subtract(en.getLocation()).toVector()
												.multiply(0.03D);
										en.setVelocity(pull);
										((Player) en).playSound(en.getLocation(), Sound.ENTITY_IRONGOLEM_DEATH, 0.1F,
												0.2F);
										Vector loca = proj.getLocation().add(en.getLocation().add(0.0D, 1.5D, 0.0D))
												.toVector().divide(new Vector(j / 3, j / 3, j / 3));
										((Player) en).getWorld().playEffect(loca.toLocation(en.getWorld()),
												Effect.SMOKE, 1);
										j++;
										j = 0;
										continue;

									}

									if (th == 1) {
										for (Entity en : proj.getNearbyEntities(150.0D, 150.0D, 150.0D)) {
											if ((en instanceof Player)) {
												proj.getWorld().playSound(proj.getLocation(),
														Sound.ENTITY_GENERIC_EXPLODE, 3.0F, 1.0F);
											}
										}
									}
									th += 1;
								}

							}
						}, 0L, 1L);
						final int ID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							public void run() {
								if (!proj.isDead()) {
									if ((proj.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock()
											.getType() == Material.AIR)
											|| (proj.getLocation().add(0.0D, 1.0D, 0.0D).getBlock()
													.getType() == Material.AIR)
											|| (proj.getLocation().subtract(1.0D, 0.0D, 0.0D).getBlock()
													.getType() == Material.AIR)
											|| (proj.getLocation().add(1.0D, 0.0D, 0.0D).getBlock()
													.getType() == Material.AIR)
											|| (proj.getLocation().subtract(0.0D, 0.0D, 1.0D).getBlock()
													.getType() == Material.AIR)
											|| (proj.getLocation().add(0.0D, 0.0D, 1.0D).getBlock()
													.getType() == Material.AIR)) {
										proj.getWorld().playEffect(proj.getLocation(), Effect.STEP_SOUND,
												Material.SAND);
									}

								} else {
								}
							}

						}, 0L, 3L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								proj.remove();
								Bukkit.getScheduler().cancelTask(ID1);
								Bukkit.getScheduler().cancelTask(ID2);
							}
						}, 280L);
						Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							public void run() {
								if ((proj.isDead())
										|| (proj.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().add(0.0D, 1.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().subtract(1.0D, 0.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().add(1.0D, 0.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().subtract(0.0D, 0.0D, 1.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().add(0.0D, 0.0D, 1.0D).getBlock()
												.getType() != Material.AIR)) {
									Bukkit.getScheduler().cancelTask(ID1);
									Bukkit.getScheduler().cancelTask(ID2);
								}
							}
						}, 0L, 1L);
					}
					if (p.getItemInHand().getType() == Material.IRON_PICKAXE) {
						bomb.put(p.getName(), Integer.valueOf(1));
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								bomb.put(p.getName(), Integer.valueOf(0));
							}
						}, 6000L);
						th = 0;
						p.addPotionEffect(PotionEffectType.SATURATION.createEffect(20, 255));
						p.addPotionEffect(PotionEffectType.REGENERATION.createEffect(20, 255));
						p.getInventory().remove(ironpick());
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.getInventory().addItem(woodpick());
							}
						}, 1L);
						proj = p.launchProjectile(Egg.class);
						proj.teleport(new Location(proj.getWorld(), proj.getLocation().getX(),
								proj.getLocation().add(0.0D, 1.0D, 0.0D).getY(), proj.getLocation().getZ()));
						proj.setVelocity(proj.getVelocity().multiply(0.9D));
						final int ID3 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							public void run() {
								if ((!proj.isDead()) && ((proj.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock()
										.getType() != Material.AIR)
										|| (proj.getLocation().add(0.0D, 1.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().subtract(1.0D, 0.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().add(1.0D, 0.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().subtract(0.0D, 0.0D, 1.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().add(0.0D, 0.0D, 1.0D).getBlock()
												.getType() != Material.AIR))) {
									proj.setVelocity(new Vector(0, 0, 0));
									eggs(proj);
									int j = 0;
									for (Iterator<Entity> localIterator = proj.getNearbyEntities(150.0D, 150.0D, 150.0D)
											.iterator(); localIterator.hasNext(); j++) {
										Entity en = (Entity) localIterator.next();
										if ((!(en instanceof Player)) || (en == p))
											break;
										Vector pull = proj.getLocation().subtract(en.getLocation()).toVector()
												.multiply(0.03D);
										en.setVelocity(pull);
										((Player) en).playSound(en.getLocation(), Sound.ENTITY_IRONGOLEM_DEATH, 0.1F,
												0.2F);
										Vector loca = proj.getLocation().add(en.getLocation().add(0.0D, 1.5D, 0.0D))
												.toVector().divide(new Vector(j / 3, j / 3, j / 3));
										((Player) en).getWorld().playEffect(loca.toLocation(en.getWorld()),
												Effect.SMOKE, 1);
										j++;
										j = 0;
										continue;
									}

									if (th == 1) {
										for (Entity en : proj.getNearbyEntities(150.0D, 150.0D, 150.0D)) {
											if ((en instanceof Player)) {
												proj.getWorld().playSound(proj.getLocation(),
														Sound.ENTITY_GENERIC_EXPLODE, 3.0F, 1.0F);
											}
										}
									}
									th += 1;
								}

							}
						}, 0L, 1L);
						final int ID4 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							public void run() {
								if ((!proj.isDead()) && ((proj.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock()
										.getType() != Material.AIR)
										|| (proj.getLocation().add(0.0D, 1.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().subtract(1.0D, 0.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().add(1.0D, 0.0D, 0.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().subtract(0.0D, 0.0D, 1.0D).getBlock()
												.getType() != Material.AIR)
										|| (proj.getLocation().add(0.0D, 0.0D, 1.0D).getBlock()
												.getType() != Material.AIR))) {
									proj.getWorld().playEffect(proj.getLocation(), Effect.STEP_SOUND, Material.SAND);
								}

							}
						}, 0L, 3L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								proj.remove();
								Bukkit.getScheduler().cancelTask(ID3);
								Bukkit.getScheduler().cancelTask(ID4);
								p.getInventory().remove(woodpick());
							}
						}, 280L);
						Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							public void run() {
								if (proj.isDead()) {
									Bukkit.getScheduler().cancelTask(ID3);
									Bukkit.getScheduler().cancelTask(ID4);
								}
							}
						}, 0L, 1L);
					}
				}
			}
		}
	}
}
