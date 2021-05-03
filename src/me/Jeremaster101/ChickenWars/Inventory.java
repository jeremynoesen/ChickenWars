package me.Jeremaster101.ChickenWars;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Inventory {
	public static void loadInv(Player p, String area) {
		File playerInvConfigFile = new File(Main.getPlugin().getDataFolder() + File.separator + "data",
				p.getUniqueId().toString() + ".yml");
		FileConfiguration pInv = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(playerInvConfigFile);
		PlayerInventory inv = p.getInventory();
		String start = area + ".inventory";
		if (pInv.get(area + ".experience", null) != null) {
			p.getPlayer().giveExpLevels(-1000000000);
			p.getPlayer().giveExp(Integer.parseInt(pInv.get(area + ".experience", null).toString()));
		} else
			p.getPlayer().giveExpLevels(-1000000000);
		if (pInv.get(area + ".health", null) != null) {
			p.getPlayer().setHealth(Double.parseDouble(pInv.get(area + ".health", null).toString()));
		} else
			p.getPlayer().setHealth(20);
		if (pInv.get(area + ".hunger", null) != null) {
			p.getPlayer().setFoodLevel(Integer.parseInt(pInv.get(area + ".hunger", null).toString()));
		} else
			p.getPlayer().setFoodLevel(20);
		for (PotionEffect allEffects : p.getActivePotionEffects()) {
			p.removePotionEffect(allEffects.getType());
		}
		if (pInv.get(area + ".effects", null) != null) {
			for (int effect = 0; effect < pInv.getConfigurationSection(area + ".effects").getKeys(false)
					.size(); effect++) {
				String type = pInv.getString(area + ".effects." + effect + ".type", null);
				int duration = Integer.parseInt(pInv.get(area + ".effects." + effect + ".duration", 0).toString());
				int amplifier = Integer.parseInt(pInv.get(area + ".effects." + effect + ".amplifier", 0).toString());
				boolean visibility = Boolean
						.parseBoolean(pInv.get(area + ".effects." + effect + ".visible").toString());
				if ((type != null) && (duration != 0)) {
					PotionEffect peffect = new PotionEffect(PotionEffectType.getByName(type), duration, amplifier,
							visibility);
					p.addPotionEffect(peffect);
				} else
					continue;
			}
		}
		for (int slotcounter = 0; slotcounter < inv.getSize(); slotcounter++) {
			String startInventory = start + "." + slotcounter;
			ItemStack slot = new ItemStack(Material.AIR, 0);
			if (pInv.get(startInventory, null) != null) {
				slot.setType(Material.getMaterial(pInv.getString(startInventory + ".type", "AIR")));
				slot.setAmount(pInv.getInt(startInventory + ".amount", 0));
				slot.setDurability(Short.parseShort(pInv.getString(startInventory + ".durability", "0")));
				slot.setItemMeta((ItemMeta) pInv.get(startInventory + ".meta", null));
				if (pInv.get(startInventory + ".enchantments", null) != null) {
					for (int ench = 0; ench < pInv.getConfigurationSection(startInventory + ".enchantments")
							.getKeys(false).size(); ench++) {
						String enchantmentname = pInv.getString(startInventory + ".enchantments." + ench + ".name",
								null);
						int enchantmentlevel = pInv.getInt(startInventory + ".enchantments." + ench + ".level", 0);
						if ((enchantmentname != null) && (enchantmentlevel != 0)) {
							Enchantment enchant = Enchantment.getByName(enchantmentname);
							slot.addEnchantment(enchant, enchantmentlevel);
						} else
							continue;
					}
				}
				inv.setItem(slotcounter, slot);
			} else {
				inv.setItem(slotcounter, slot);
				continue;
			}
		}
	}

	public static void saveInv(Player p, String area) {
		File playerInvConfigFile = new File(Main.getPlugin().getDataFolder() + File.separator + "data",
				p.getUniqueId().toString() + ".yml");
		FileConfiguration pInv = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(playerInvConfigFile);
		if (p.getPlayer().getTotalExperience() > 0) {
			pInv.set(area + ".experience", Integer.valueOf(p.getPlayer().getTotalExperience()));
		} else
			pInv.set(area + ".experience", null);
		if (p.getPlayer().getHealth() < 20) {
			pInv.set(area + ".health", Double.valueOf(p.getPlayer().getHealth()));
		} else
			pInv.set(area + ".health", null);
		if (p.getPlayer().getFoodLevel() < 20) {
			pInv.set(area + ".hunger", Integer.valueOf(p.getPlayer().getFoodLevel()));
		} else
			pInv.set(area + ".hunger", null);
		pInv.set(area + ".effects", null);
		int currenteffect = 0;
		for (PotionEffect effect : p.getActivePotionEffects()) {
			if (effect == null)
				break;
			pInv.set(area + ".effects." + currenteffect + ".type", effect.getType().getName().toString());
			pInv.set(area + ".effects." + currenteffect + ".duration", Integer.valueOf(effect.getDuration()));
			pInv.set(area + ".effects." + currenteffect + ".amplifier", Integer.valueOf(effect.getAmplifier()));
			if (!(effect.isAmbient()))
				pInv.set(area + ".effects." + currenteffect + ".visible", "false");
			if (effect.isAmbient())
				pInv.set(area + ".effects." + currenteffect + ".visible", "true");
			currenteffect++;
		}
		pInv.set(area + ".inventory", null);
		int currentslot = 0;
		for (ItemStack stack : p.getInventory().getContents()) {
			String startInventory = area + ".inventory." + currentslot;
			if (stack == null) {
				currentslot++;
				continue;
			}
			if (stack.getType().toString() != null) {
				pInv.set(startInventory + ".amount", Integer.valueOf(stack.getAmount()));
				pInv.set(startInventory + ".durability", Integer.valueOf(stack.getDurability()));
				pInv.set(startInventory + ".type", stack.getType().toString());
				pInv.set(startInventory + ".meta", stack.getItemMeta());
				int currentenchant = 0;
				for (Map.Entry<Enchantment, Integer> enchant : stack.getEnchantments().entrySet()) {
					if (stack.getEnchantments() == null)
						continue;
					pInv.set(startInventory + ".enchantments." + currentenchant + ".name",
							((Enchantment) enchant.getKey()).getName());
					pInv.set(startInventory + ".enchantments." + currentenchant + ".level", enchant.getValue());
					currentenchant++;
				}
				currentslot++;
			}
		}
		try {
			pInv.save(playerInvConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
