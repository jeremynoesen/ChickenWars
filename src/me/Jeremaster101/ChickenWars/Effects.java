package me.Jeremaster101.ChickenWars;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Effects {
	@SuppressWarnings("deprecation")
	static void blood(Player p) {
		ItemStack is = new ItemStack(Material.INK_SACK, 1, (short) 0, (byte) 14);
		ItemMeta id = is.getItemMeta();
		id.setDisplayName("1");
		is.setItemMeta(id);

		ItemStack is1 = new ItemStack(Material.INK_SACK, 1, (short) 0, (byte) 14);
		ItemMeta id1 = is1.getItemMeta();
		id1.setDisplayName("2");
		is1.setItemMeta(id1);

		ItemStack is2 = new ItemStack(Material.INK_SACK, 1, (short) 0, (byte) 14);
		ItemMeta id2 = is2.getItemMeta();
		id2.setDisplayName("3");
		is2.setItemMeta(id2);

		ItemStack is3 = new ItemStack(Material.INK_SACK, 1, (short) 0, (byte) 14);
		ItemMeta id3 = is3.getItemMeta();
		id3.setDisplayName("4");
		is3.setItemMeta(id3);

		ItemStack is4 = new ItemStack(Material.FEATHER, 64);
		ItemMeta id4 = is4.getItemMeta();
		id4.setDisplayName("5");
		is4.setItemMeta(id4);

		ItemStack is5 = new ItemStack(Material.FEATHER, 64);
		ItemMeta id5 = is5.getItemMeta();
		id5.setDisplayName("6");
		is5.setItemMeta(id5);

		p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
		final Item item = p.getWorld().dropItemNaturally(p.getLocation(), is);
		final Item item1 = p.getWorld().dropItemNaturally(p.getLocation(), is1);
		final Item item2 = p.getWorld().dropItemNaturally(p.getLocation(), is2);
		final Item item3 = p.getWorld().dropItemNaturally(p.getLocation(), is3);
		final Item item4 = p.getWorld().dropItemNaturally(p.getLocation(), is4);
		final Item item5 = p.getWorld().dropItemNaturally(p.getLocation(), is5);
		item.setVelocity(new Vector(0.0D, 0.2D, 0.2D));
		item1.setVelocity(new Vector(0.2D, 0.2D, 0.0D));
		item2.setVelocity(new Vector(0.0D, 0.2D, -0.2D));
		item3.setVelocity(new Vector(-0.2D, 0.2D, 0.0D));
		item4.setVelocity(new Vector(0, 0, 0));
		item5.setVelocity(new Vector(0, 0, 0));
		item.setPickupDelay(999999999);
		item1.setPickupDelay(999999999);
		item2.setPickupDelay(999999999);
		item3.setPickupDelay(999999999);
		item4.setPickupDelay(999999999);
		item5.setPickupDelay(999999999);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				item.remove();
				item1.remove();
				item2.remove();
				item3.remove();
				item4.remove();
				item5.remove();
			}
		}, 10L);
	}
}
