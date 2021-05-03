package me.Jeremaster101.ChickenWars;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Iterator;


@SuppressWarnings("deprecation")
public class GameListener implements Listener {

    public HashMap<String, Integer> i = new HashMap<String, Integer>();
    public HashMap<String, Integer> leavecw = new HashMap<String, Integer>();
    public HashMap<String, Integer> eb = new HashMap<String, Integer>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (e.getPlayer().getWorld().getName().endsWith("_cw")) {
            player.performCommand("mvtp " + player.getWorld().getName().replace("_cw", ""));
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if ((Main.chicken.hasPlayer(player)) && (player.getWorld().getName().endsWith("_cw"))
                    && ((event.getEntity() instanceof Player))) {
                player = (Player) event.getEntity();
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_CHICKEN_HURT, 1.0F, 1.0F);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(EntityDamageByEntityEvent ff) {
        final Entity p = ff.getEntity();
        if (p.getWorld().getName().endsWith("_cw")) {
            if (ff.getDamager().getType() == EntityType.ARROW) {
                Player damager = (Player) ((Projectile) ff.getDamager()).getShooter();
                damager.playSound(damager.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.4F, 1.0F);
                p.setVelocity(ff.getDamager().getLocation().getDirection().setY(1));
            }
            if (ff.getDamager().getType() == EntityType.EGG) {
                Main.chicken.removePlayer((OfflinePlayer) p);
                Main.egg.addPlayer((OfflinePlayer) p);
                ((Damageable) p).damage(0.5D);
                p.setVelocity(new Vector(0.0D, 0.075D, 0.0D));
                ff.setCancelled(true);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                    public void run() {
                        Main.egg.removePlayer((OfflinePlayer) p);
                        Main.chicken.addPlayer((OfflinePlayer) p);
                    }
                }, 16L);
            } else {
                Main.chicken.addPlayer((OfflinePlayer) p);
                Main.egg.removePlayer((OfflinePlayer) p);
            }
        }
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent ee) {
        Player pp = ee.getPlayer();
        if (pp.getWorld().getName().endsWith("_cw")) {
            if (!Main.chicken.hasPlayer(pp)) {
                if (pp.getGameMode() == GameMode.CREATIVE) {
                    return;
                }
                ee.setCancelled(true);
            }
            if (Main.chicken.hasPlayer(pp)) {
                if (pp.getGameMode() == GameMode.CREATIVE)
                    return;
                i.put(pp.getName(), i.get(pp.getName()) + 1);
                ee.setCancelled(true);
                pp.setAllowFlight(false);
                pp.setFlying(false);
                if (1 == i.get(pp.getName())) {
                    pp.setVelocity(pp.getLocation().getDirection().multiply(0.6).setY(0.7D));
                    pp.setExp(0.835f);
                    pp.getWorld().playSound(pp.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1.0F, 1.0F);
                }
                if (i.get(pp.getName()) == 2) {
                    pp.setVelocity(pp.getLocation().getDirection().multiply(0.55).setY(0.65D));
                    pp.setExp(0.67f);
                    pp.getWorld().playSound(pp.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1.0F, 1.02F);
                }
                if (i.get(pp.getName()) == 3) {
                    pp.setVelocity(pp.getLocation().getDirection().multiply(0.5).setY(0.6D));
                    pp.setExp(0.5f);
                    pp.getWorld().playSound(pp.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1.0F, 1.04F);
                }
                if (i.get(pp.getName()) == 4) {
                    pp.setVelocity(pp.getLocation().getDirection().multiply(0.45).setY(0.55D));
                    pp.setExp(0.33f);
                    pp.getWorld().playSound(pp.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1.0F, 1.06F);
                }
                if (i.get(pp.getName()) == 5) {
                    pp.setVelocity(pp.getLocation().getDirection().multiply(0.38).setY(0.46D));
                    pp.setExp(0.167f);
                    pp.getWorld().playSound(pp.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1.0F, 1.08F);
                }
                if (i.get(pp.getName()) == 6) {
                    pp.setVelocity(pp.getLocation().getDirection().multiply(0.31).setY(0.38D));
                    pp.setExp(0.0f);
                    pp.getWorld().playSound(pp.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1.0F, 1.1F);
                }
            } else if (!Main.chicken.hasPlayer(pp)) {
                pp.setAllowFlight(false);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        String path = "World Bounds";
        String xmax1 = Main.getPlugin().getConfig().getString(path + ".x-max");
        String xmin1 = Main.getPlugin().getConfig().getString(path + ".x-min");
        String ymax1 = Main.getPlugin().getConfig().getString(path + ".y-max");
        String ymin1 = Main.getPlugin().getConfig().getString(path + ".y-min");
        String zmax1 = Main.getPlugin().getConfig().getString(path + ".z-max");
        String zmin1 = Main.getPlugin().getConfig().getString(path + ".z-min");

        float xmax = Float.parseFloat(xmax1);
        float xmin = Float.parseFloat(xmin1);
        float ymax = Float.parseFloat(ymax1);
        float ymin = Float.parseFloat(ymin1);
        float zmax = Float.parseFloat(zmax1);
        float zmin = Float.parseFloat(zmin1);
        if (p.getWorld().getName().endsWith("_cw")) {
            if (Main.egg.hasPlayer(p)) {
                p.setNoDamageTicks(0);
                if ((p.getLocation().getX() < xmin) || (p.getLocation().getX() > xmax)
                        || (p.getLocation().getY() < ymin) || (p.getLocation().getY() > ymax)
                        || (p.getLocation().getZ() < zmin) || (p.getLocation().getZ() > zmax)) {
                    Location locate = p.getWorld().getSpawnLocation().add(0.0D, 10.0D, 0.0D);
                    p.getWorld().strikeLightning(p.getLocation());
                    p.damage(100.0D);
                    p.teleport(locate);
                }
            }
            if (Main.notplaying.hasPlayer(p)) {
                p.getInventory().clear();
                if ((p.isFlying()) && (p.isSprinting())) {
                    p.setVelocity(p.getLocation().getDirection().multiply(2));
                }
            }

            if (Main.chicken.hasPlayer(p)) {
                if ((p.getLocation().getX() < xmin) || (p.getLocation().getX() > xmax)
                        || (p.getLocation().getY() < ymin) || (p.getLocation().getY() > ymax)
                        || (p.getLocation().getZ() < zmin) || (p.getLocation().getZ() > zmax)) {
                    Location locate = p.getWorld().getSpawnLocation().add(0.0D, 10.0D, 0.0D);
                    p.getWorld().strikeLightning(p.getLocation());
                    p.damage(100.0D);
                    p.teleport(locate);
                }
                if (p.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR) {
                    p.setAllowFlight(true);
                    p.setExp(1.0F);
                    i.put(p.getName(), 0);
                }
                if ((p.getGameMode() != GameMode.CREATIVE) && (!p.isFlying())
                        && (i.get(p.getName()) < 6)) {
                    p.setAllowFlight(true);
                }

                p.removePotionEffect(PotionEffectType.WEAKNESS);

                if (e.getPlayer().getInventory().getItem(0) != null)
                    e.getPlayer().getInventory().getItem(0).setDurability((short) 0);
                if (e.getPlayer().getInventory().getItem(1) != null)
                    e.getPlayer().getInventory().getItem(1).setDurability((short) 0);
                if(e.getPlayer().getInventory().getItem(2) != null)
                    e.getPlayer().getInventory().getItem(2).setDurability((short) 0);
                if(e.getPlayer().getInventory().getItem(3) != null)
                    e.getPlayer().getInventory().getItem(3).setDurability((short) 0);
                if(e.getPlayer().getInventory().getItem(4) != null)
                    e.getPlayer().getInventory().getItem(4).setDurability((short) 0);
                if(e.getPlayer().getInventory().getItem(5) != null)
                    e.getPlayer().getInventory().getItem(5).setDurability((short) 0);
                if(e.getPlayer().getInventory().getItem(6) != null)
                    e.getPlayer().getInventory().getItem(6).setDurability((short) 0);
                if(e.getPlayer().getInventory().getItem(7) != null)
                    e.getPlayer().getInventory().getItem(7).setDurability((short) 0);
                if(e.getPlayer().getInventory().getItem(8) != null)
                    e.getPlayer().getInventory().getItem(8).setDurability((short) 0);
                p.setFallDistance(0.0F);
            }
        } else if ((!p.getWorld().getName().endsWith("_cw")) && (p.getGameMode() != GameMode.CREATIVE)
                && (!p.isFlying())) {
            p.setAllowFlight(false);
        }
    }

    @EventHandler
    public void onPlayerEggThrow(PlayerEggThrowEvent f) {
        if (f.getEgg().getWorld().getName().endsWith("_cw")) {
            f.setHatching(false);
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();
        if (!e.getPlayer().getWorld().getName().endsWith("_cw")) {
            player.getScoreboard().resetScores(player);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            player.removePotionEffect(PotionEffectType.WEAKNESS);
            Inventory.loadInv(player, player.getServer().getWorld(player.getWorld().getName() + "_cw").toString());
            if (leavecw.get(player.getName()) == 1) {
                for (Player pp : player.getServer().getWorld(player.getWorld().getName() + "_cw").getPlayers()) {
                    pp.sendMessage(Messages.starter() + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " has left "
                            + ChatColor.YELLOW + "Chicken" + ChatColor.RED + "Wars");
                }
                leavecw.put(player.getName(), 0);
            }
        }
        if (e.getPlayer().getWorld().getName().endsWith("_cw")) {
            EggBomb.bomb.put(player.getName(), 0);
            leavecw.put(player.getName(), 1);
            for (Player pp : player.getWorld().getPlayers()) {
                pp.sendMessage(Messages.starter() + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " has joined "
                        + ChatColor.YELLOW + "Chicken" + ChatColor.RED + "Wars");
            }
            Inventory.saveInv(player, player.getWorld().getName());
            player.setScoreboard(Main.getPlugin().board);
            //Main.dis.undisguise(player);
            //Main.chicken.removePlayer(player);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1.0F, 1.0F);
            player.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(999999999, 2));
            player.setGameMode(GameMode.CREATIVE);
            player.setVelocity(new Vector(0.0D, 1.2D, 0.0D));
            player.setFlying(true);
            Main.notplaying.addPlayer(e.getPlayer());
            e.getPlayer()
                    .sendMessage(Messages.starter() + ChatColor.GOLD + "Type " + ChatColor.YELLOW + "/cw play " + ChatColor.GOLD
                            + "to begin playing " + ChatColor.YELLOW + "Chicken" + ChatColor.RED + "Wars"
                            + ChatColor.GOLD + ".");
        }
    }

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        Player killer = e.getEntity().getKiller();
        final int x = Main.getRandom(-3, 3);
        final int z = Main.getRandom(-3, 3);
        if (p.getWorld().getName().endsWith("_cw")) {
            p.sendMessage(Messages.starter() + ChatColor.GOLD + "You have died! You will respawn in 2 seconds.");
            Effects.blood(p);
            p.setHealth(20.0D);
            p.setFoodLevel(20);
            p.setGameMode(GameMode.CREATIVE);
            p.setVelocity(new Vector(0.0D, 1.2D, 0.0D));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 2));
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 255));
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_HURT, 1.0F, 1.0F);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                public void run() {
                    p.setFlying(true);
                }
            }, 10L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                public void run() {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.setFlying(false);
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                    p.teleport(p.getWorld().getSpawnLocation().add(x, 0.0D, z));
                    p.sendMessage(Messages.starter() + ChatColor.GOLD + "You have respawned.");
                }
            }, 40L);
            if (!p.getInventory().contains(EggBomb.ironpick())) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                    public void run() {
                        if (!p.getInventory().contains(EggBomb.ironpick())) {
                            eb.put(p.getName(), Integer.valueOf(0));
                        } else {
                            eb.put(p.getName(), Integer.valueOf(1));
                        }
                        if ((((Integer) eb.get(p.getName())).intValue() == 0)
                                && (((Integer) EggBomb.bomb.get(p.getName())).intValue() == 0)
                                && (p.getWorld().getName().endsWith("_cw"))) {
                            p.getInventory().addItem(new ItemStack[]{EggBomb.ironpick()});
                            for (Player pp : p.getWorld().getPlayers()) {
                                pp.sendMessage(Messages.starter() + ChatColor.YELLOW + e.getEntity().getName()
                                        + ChatColor.GRAY + " has obtained the " + ChatColor.YELLOW + "Egg bomb"
                                        + ChatColor.GRAY + "!");
                                pp.playSound(pp.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 2.0F, 1.0F);
                            }
                            eb.put(p.getName(), Integer.valueOf(1));
                        }

                    }
                }, 4840L);
            }
            for (Player pp : p.getWorld().getPlayers()) {
                pp.sendMessage(Messages.starter() + ChatColor.YELLOW + e.getEntity().getName() + ChatColor.GRAY
                        + " has been killed by " + ChatColor.YELLOW + killer.getName() + ChatColor.GRAY + ".");
            }
        } else if (!p.getWorld().getName().endsWith("_cw")) {
            p.teleport(p.getBedSpawnLocation());
        }
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if ((e.getBlock().getWorld().getName().endsWith("_cw")) && (!Main.build.hasPlayer(e.getPlayer()))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        if ((e.getEntity().getWorld().getName().endsWith("_cw")) && (e.getEntityType().equals(EntityType.PRIMED_TNT))) {
            Iterator<Block> bi = e.blockList().iterator();
            while (bi.hasNext())
                if (((Block) bi.next()).getType() != Material.TNT)
                    bi.remove();
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (e.getPlayer().getWorld().getName().endsWith("_cw")) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if (e.getPlayer().getWorld().getName().endsWith("_cw")) {
            e.getItem().remove();
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e) {
        if (e.getEntity().getWorld().getName().endsWith("_cw")) {
            e.getEntity().remove();
        }
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if ((e.getBlock().getWorld().getName().endsWith("_cw")) && (!Main.build.hasPlayer(e.getPlayer()))) {
            e.getBlock().setType(Material.AIR);
        }
    }
}
