package me.Jeremaster101.ChickenWars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;

public class CommandHandler implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
		final Player player = (Player) sender;
		final int x = Main.getRandom(-3, 3);
		final int z = Main.getRandom(-3, 3);
		if (commandLabel.equalsIgnoreCase("cw")) {
			if (player.getWorld().getName().endsWith("_cw")) {
				if (args.length != 1) {
					player.sendMessage(Messages.starter() + ChatColor.GOLD + "Now leaving " + ChatColor.YELLOW
							+ "Chicken" + ChatColor.RED + "Wars" + ChatColor.GOLD + ".");
					//Main.dis.undisguise(player);
					player.performCommand("mvtp " + player.getWorld().getName().replace("_cw", ""));
					return true;
				}
				if ((args[0].equalsIgnoreCase("help")) || (args[0].equalsIgnoreCase("?"))) {
					if (player.hasPermission("cwsetup.allow")) {
						Messages.help(player, true);
					} else if (!player.hasPermission("cwsetup.allow")) {
						Messages.help(player, false);
					}
				}
				int sx = (int) player.getLocation().getX();
				int sy = (int) player.getLocation().getY();
				int sz = (int) player.getLocation().getZ();

				if (args[0].equalsIgnoreCase("setspawn")) {
					if (player.hasPermission("cwsetup.allow")) {
						if (player.getWorld().getName().endsWith("_cw")) {
							player.getWorld().setSpawnLocation(sx, sy, sz);
							player.performCommand("mvm set spawn");
							player.sendMessage(Messages.starter() + ChatColor.GREEN + "Spawnpoint set!");
							return true;
						}
					} else if (!player.hasPermission("cwsetup.allow")) {
						player.sendMessage(ChatColor.WHITE + "Unknown command. Type ''/help'' for help.");
						return true;
					}
				}
				if ((args[0].equalsIgnoreCase("build")) || (args[0].equalsIgnoreCase("b"))) {
					if (player.hasPermission("cwsetup.allow")) {
						if (player.getWorld().getName().endsWith("_cw")) {
							if (!Main.build.hasPlayer(player)) {
								player.getInventory().clear();
								Main.notplaying.removePlayer(player);
								Main.egg.removePlayer(player);
								Main.chicken.removePlayer(player);
								Main.build.addPlayer(player);
								player.sendMessage(Messages.starter() + ChatColor.GOLD
										+ "You entered build mode, /cw build to exit!");
								player.teleport(player.getWorld().getSpawnLocation());
								player.removePotionEffect(PotionEffectType.INVISIBILITY);
								player.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(999999999, 255));
								//Main.dis.undisguise(player);

								player.setGameMode(GameMode.CREATIVE);
								return true;
							}
							if (Main.build.hasPlayer(player)) {
								Main.build.removePlayer(player);
								player.sendMessage(Messages.starter() + ChatColor.GOLD + "You exited build mode!");
								player.performCommand("cw s");
								return true;
							}
						}
					} else if (!player.hasPermission("cwsetup.allow")) {
						player.sendMessage(ChatColor.WHITE + "Unknown command. Type ''/help'' for help.");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("setuphelp")) {
					if (player.hasPermission("cwsetup.allow")) {
						if (player.getWorld().getName().endsWith("_cw")) {
							player.sendMessage(Messages.starter() + ChatColor.GOLD
									+ "First, go to the chickenwars world and type /cw setup");
							player.sendMessage(ChatColor.GOLD + "Next, type /cw setspawn where you want the spawn");
							player.sendMessage(ChatColor.GOLD
									+ "Next, type /cw setmaptop in the top corner of your map (corner where the coordinates are the highest values!)");
							player.sendMessage(ChatColor.GOLD
									+ "Lastly, type /cw setmapbottom in the bottom corner of your map (corner where the coordinates are the lowest values!)");
							return true;
						}
					} else if (!player.hasPermission("cwsetup.allow")) {
						player.sendMessage(ChatColor.WHITE + "Unknown command. Type ''/help'' for help.");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("setmaptop")) {
					if (player.hasPermission("cwsetup.allow")) {
						if (player.getWorld().getName().endsWith("_cw")) {
							String path = "World Bounds.";
							Main.getPlugin().getConfig().set(path + "x-max",
									Double.toString(player.getLocation().getX()));
							Main.getPlugin().getConfig().set(path + "y-max",
									Double.toString(player.getLocation().getY()));
							Main.getPlugin().getConfig().set(path + "z-max",
									Double.toString(player.getLocation().getZ()));
							Main.getPlugin().saveConfig();
							player.sendMessage(Messages.starter() + ChatColor.GREEN + "Top corner of the arena set!");
							return true;
						}
					} else if (!player.hasPermission("cwsetup.allow")) {
						player.sendMessage(ChatColor.WHITE + "Unknown command. Type ''/help'' for help.");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("setmapbottom")) {
					if (player.hasPermission("cwsetup.allow")) {
						if (player.getWorld().getName().endsWith("_cw")) {
							String path = "World Bounds.";
							Main.getPlugin().getConfig().set(path + "x-min",
									Double.toString(player.getLocation().getX()));
							Main.getPlugin().getConfig().set(path + "y-min",
									Double.toString(player.getLocation().getY()));
							Main.getPlugin().getConfig().set(path + "z-min",
									Double.toString(player.getLocation().getZ()));
							Main.getPlugin().saveConfig();
							player.sendMessage(
									Messages.starter() + ChatColor.GREEN + "Bottom corner of the arena set!");
							return true;
						}
					} else if (!player.hasPermission("cwsetup.allow")) {
						player.sendMessage(ChatColor.WHITE + "Unknown command. Type ''/help'' for help.");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("setup")) {
					if (player.hasPermission("cwsetup.allow")) {
						if (player.getWorld().getName().endsWith("_cw")) {
							player.performCommand("mv gamerule keepInventory true " + player.getWorld().getName());
							player.performCommand("mvm set mode creative");
							player.performCommand("mvm set monsters false");
							player.performCommand("mvm set difficulty easy");
							player.performCommand("mvm set animals false");
							player.performCommand("mv gamerule doDaylightCycle false " + player.getWorld().getName());
							player.performCommand("time set 1000");
							player.performCommand("gamerule showDeathMessages false");
							player.performCommand("gamerule doFireTick false");
							player.sendMessage(Messages.starter() + ChatColor.GREEN + "World set up successfully!");
							return true;
						}
					} else if (!player.hasPermission("cwsetup.allow")) {
						player.sendMessage(ChatColor.WHITE + "Unknown command. Type ''/help'' for help.");
						return true;
					}
				}
				if ((args[0].equalsIgnoreCase("spectate")) || (args[0].equalsIgnoreCase("spec"))
						|| (args[0].equalsIgnoreCase("s"))) {
					player.sendMessage(Messages.starter() + ChatColor.GOLD + "You will begin spectating in 5 seconds.");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							player.sendMessage(Messages.starter() + ChatColor.GOLD + "You are now spectating.");
							player.teleport(player.getWorld().getSpawnLocation().add(0.0D, 10.0D, 0.0D));
							//Main.dis.undisguise(player);
							Main.chicken.removePlayer(player);
							player.getInventory().clear();
							player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 0.3F);
							Main.notplaying.addPlayer(player);
							player.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(999999999, 2));
							player.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(999999999, 255));
							player.setGameMode(GameMode.CREATIVE);
							player.setVelocity(new Vector(0.0D, 1.2D, 0.0D));
							player.setFlying(true);
						}
					}, 100L);
					return true;
				}
				if ((args[0].equalsIgnoreCase("play")) || (args[0].equalsIgnoreCase("p"))) {
					player.sendMessage(Messages.starter() + ChatColor.GOLD + "You will begin playing in 5 seconds.");
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							player.sendMessage(Messages.starter() + ChatColor.GOLD + "You are now playing.");
							player.teleport(player.getWorld().getSpawnLocation().add(x, 0.0D, z));
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 2.0F);
							Main.chicken.addPlayer(player);
							//Main.dis.disguise(player, new MobDisguise(DisguiseType.CHICKEN));
							player.getInventory().clear();
							player.getInventory().setItem(0, EggBlaster.ironsword());
							player.getInventory().setItem(1, ChickenMissile.ironaxe());
							player.getInventory().setItem(2, ChickenHook.fleshhook());
							player.getInventory().setItem(3, Bow.bow());
							player.removePotionEffect(PotionEffectType.INVISIBILITY);
							player.setGameMode(GameMode.SURVIVAL);
							player.setFlying(false);
							player.setHealth(20.0D);
							player.setFoodLevel(20);
						}
					}, 100L);
					return true;
				}
			} else if (!player.getWorld().getName().endsWith("_cw")) {
				if (args.length != 1) {
					player.sendMessage(Messages.starter() + ChatColor.GOLD + "Welcome to " + ChatColor.YELLOW
							+ "Chicken" + ChatColor.RED + "Wars" + ChatColor.GOLD + "!");
					player.performCommand("mvtp " + player.getWorld().getName() + "_cw");
					player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_4);
					return true;
				}
				if ((args[0].equalsIgnoreCase("help")) || (args[0].equalsIgnoreCase("?"))) {
					if (player.hasPermission("cwsetup.allow")) {
						if (!player.getWorld().getName().endsWith("_cw")) {
							player.sendMessage(ChatColor.GRAY + "--------------- " + Messages.starter() + ChatColor.GRAY
									+ "---------------");
							player.sendMessage(ChatColor.GOLD + "/cw help" + ChatColor.GRAY + " Help command.");
							player.sendMessage(
									ChatColor.GOLD + "/cw setspawn" + ChatColor.GRAY + " Set the chicken wars spawn.");
							player.sendMessage(
									ChatColor.GOLD + "/cw setuphelp" + ChatColor.GRAY + " Setup instructions.");
							player.sendMessage(
									ChatColor.GOLD + "/cw setmaptop" + ChatColor.GRAY + " Set map top corner bounds.");
							player.sendMessage(ChatColor.GOLD + "/cw setmapbottom" + ChatColor.GRAY
									+ " Set map bottom corner bounds.");
							player.sendMessage(ChatColor.GOLD + "/cw setup" + ChatColor.GRAY
									+ " Make the world optimal for the game.");
							player.sendMessage(ChatColor.GOLD + "/cw spectate" + ChatColor.GRAY
									+ " Spectate a game of ChickenWars.");
							player.sendMessage(
									ChatColor.GOLD + "/cw play" + ChatColor.GRAY + " Play a game of ChickenWars.");
							player.sendMessage(ChatColor.GOLD + "/cw build" + ChatColor.GRAY + " Enter build mode.");
							return true;
						}
					} else if (!player.hasPermission("cwsetup.allow")) {
						player.sendMessage(ChatColor.GRAY + "--------------- " + Messages.starter() + ChatColor.GRAY
								+ "---------------");
						player.sendMessage(
								ChatColor.GOLD + "/cw spectate" + ChatColor.GRAY + " Spectate a game of ChickenWars.");
						player.sendMessage(
								ChatColor.GOLD + "/cw play" + ChatColor.GRAY + " Play a game of ChickenWars.");
						return true;
					}
				}
				if (player.hasPermission("cwsetup.allow")) {
					if (((args.length == 1) && (args[0].equalsIgnoreCase("setup")))
							|| (args[0].equalsIgnoreCase("setmapbottom")) || (args[0].equalsIgnoreCase("setmaptop"))
							|| (args[0].equalsIgnoreCase("setspawn")) || (args[0].equalsIgnoreCase("setuphelp"))) {
						player.sendMessage(Messages.starter() + ChatColor.RED
								+ "You aren't in the world whose name ends with _cw! Type " + ChatColor.YELLOW + "/cw "
								+ ChatColor.RED + "to teleport there.");
						return true;
					}
				} else if (!player.hasPermission("cwsetup.allow")) {
					player.sendMessage(ChatColor.WHITE + "Unknown command. Type ''/help'' for help.");
					return true;
				}
			}
			return true;
		}
		return true;
	}
}
