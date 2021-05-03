package me.Jeremaster101.ChickenWars;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import de.robingrether.idisguise.api.DisguiseAPI;

public class Main extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener {
	public static Main getPlugin() {
		return JavaPlugin.getPlugin(Main.class);
	}

	ScoreboardManager manager;
	Scoreboard board;
	public static Team chicken;
	public static Team notplaying;
	public static Team egg;
	public static Team build;
	World world;
	Objective objective;
	static DisguiseAPI dis;

	
	public static int getRandom(int lower, int upper) {
		Random random = new Random();
		return random.nextInt(upper - lower + 1) + lower;
	}
	
	public Permission playerPermission = new Permission("cwsetup.allow");

	public void loadConfiguration() {
		String path = "World Bounds";
		getConfig().addDefault(path + ".x-max", "-150");
		getConfig().addDefault(path + ".y-max", "70");
		getConfig().addDefault(path + ".z-max", "530");
		getConfig().addDefault(path + ".x-min", "-265");
		getConfig().addDefault(path + ".y-min", "5");
		getConfig().addDefault(path + ".z-min", "450");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void onEnable() {
		getLogger().info("Chicken Wars v2.0 made by Jeremaster101 has been enabled!");
		
		//dis = Bukkit.getServicesManager().getRegistration(DisguiseAPI.class).getProvider();

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new Bow(), this);
		pm.registerEvents(new ChickenHook(), this);
		pm.registerEvents(new ChickenMissile(), this);
		pm.registerEvents(new EggBlaster(), this);
		pm.registerEvents(new EggBomb(), this);
		pm.registerEvents(new GameListener(), this);

		pm.addPermission(this.playerPermission);
		
		getCommand("cw").setExecutor(new CommandHandler());

		this.manager = Bukkit.getScoreboardManager();
		this.board = this.manager.getNewScoreboard();
		Main.chicken = this.board.registerNewTeam("chicken");
		Main.notplaying = this.board.registerNewTeam("none");
		Main.egg = this.board.registerNewTeam("egg");
		Main.build = this.board.registerNewTeam("build");

		loadConfiguration();
	}

	public void onDisable() {
		getLogger().info("Chicken Wars v2.0 made by Jeremaster101 has been disabled!");
	}
}
