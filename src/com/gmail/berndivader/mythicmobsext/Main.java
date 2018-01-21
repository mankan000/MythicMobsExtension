package com.gmail.berndivader.mythicmobsext;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;


import com.gmail.berndivader.config.Config;
import com.gmail.berndivader.mythicmobsext.mythicthiefs.ThiefHandler;
import com.gmail.filoghost.holographicdisplays.util.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.garbagemule.MobArena.MobArenaHandler;
import com.gmail.berndivader.mythicmobsext.mythicplayers.MythicPlayers;
import com.gmail.berndivader.NMS.NMSUtils;
import com.gmail.berndivader.mythicmobsext.cachedowners.CachedOwnerHandler;
import com.gmail.berndivader.mythicmobsext.mechanics.healthbar.HealthbarHandler;
import com.gmail.berndivader.mythicmobsext.conditions.factions.FactionsFlags;
import com.gmail.berndivader.mythicmobsext.conditions.factions.FactionsFlagConditions;
import com.gmail.berndivader.mythicmobsext.conditions.mobarena.MobArenaConditions;
import com.gmail.berndivader.mythicmobsext.conditions.worldguard.WorldGuardFlags;
import com.gmail.berndivader.mythicmobsext.conditions.worldguard.WorldGuardFlag;
import com.gmail.berndivader.nanpatch.NaNpatch;
import com.gmail.berndivader.utils.Utils;
import com.gmail.berndivader.volatilecode.VolatileHandler;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.MobManager;

public class Main extends JavaPlugin {
	private static Main plugin;
	public static HealthbarHandler healthbarhandler;
	public static CachedOwnerHandler cachedOwnerHandler;
	public static Random random;
	public static Integer wgVer;
	public static WorldGuardFlags wgf;
	public static FactionsFlags fflags;
	public static final String mpNameVar = "mythicprojectile";
	public static final String noTargetVar = "nottargetable";
	public static boolean hasRpgItems = false;
	public static Logger logger;
	public static PluginManager pluginmanager;
	public static boolean slappyNewBorn = true;
	public static MythicMobs mythicmobs;
	public WorldGuardPlugin wg;
	private ThiefHandler thiefhandler;
	private static MobManager mobmanager;
	private static MythicPlayers mythicplayers;
	private MobArenaHandler maHandler;
	private VolatileHandler volatilehandler;
	public static HashSet<Entity>entityCache=new HashSet<Entity>();
	public static boolean disguisepresent;

	public void onEnable() {

		if (plugin != null  || System.getProperty("MythicMobsExtensionLoaded") != null) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[MythicMobsExtension] Please do not use /reload or plugin reloaders. You will not recieve support for doing this operation.");
		}

		System.setProperty("MythicMobsExtensionLoaded", "true");

		plugin = this;
		random = new Random();
		pluginmanager = plugin.getServer().getPluginManager();
		logger = plugin.getLogger();
/**
 *TODO: implement helper if needed
		if (!pluginmanager.isPluginEnabled("Helper")) {
			Plugin helper;
			logger.info("Helper not found. Try to register");
			helper=RegisterHelper.init();
			if (helper==null) {
				logger.info("There was a problem registering helper classes!");
				pluginmanager.disablePlugin(this);
				return;
			}
		}
 */

		//need to figure out which way to check for updates, guess reading the bukkitpage for the version number might be the best way
		//if (Config.update) {
		//}

		String version = VersionUtils.getBukkitVersion();

		if ("v1_10_R1".equals(version)) {
			return;

		} else if ("v1_11_R1".equals(version)) {
			return;

		} else if ("v1_12_R1".equals(version)) {
			return;

		} else {
			logger.warning("******************************************************");
			logger.warning("     This version of MythicMobsExtension is only");
			logger.warning("     supported on server versions 1.10 to 1.12.");
			logger.warning("     We cant garantie that it runs properly.");
			logger.warning("******************************************************");
		}

		Config.load(this);

		this.volatilehandler = this.getVolatileHandler();
		if (pluginmanager.isPluginEnabled("MythicMobs")) {
			logger.info("Found MythicMobs...");
			Main.mythicmobs = MythicMobs.inst();
			Main.mobmanager = Main.mythicmobs.getMobManager();
			pluginmanager.registerEvents(new UndoBlockListener(), this);

			new Utils(this);
			new CustomMechanics(this);
			logger.info("registered CustomSkills!");
			new CustomConditions(); //this?
			logger.info("registered CustomConditions!");

			if (Config.nan) {
				new NaNpatch(this);
				logger.info("NaN patch applied!");
			}

			if (Config.m_players) {
				Main.mythicplayers = new MythicPlayers(this);
				logger.info("registered mythicplayers!");
			}

			if (Config.m_thiefs) {
				this.thiefhandler = new ThiefHandler();
				logger.info("registered ThiefHandlers!");
			}

			if (Config.m_parrot) {
				return;
			}

			if (pluginmanager.isPluginEnabled("LibsDisguise") && Config.c_owners) {
				cachedOwnerHandler = new CachedOwnerHandler(plugin);
				logger.info("CachedOwner support enabled!");
			}

			if (pluginmanager.isPluginEnabled("WorldGuard") && Config.wguard) {
				wg = getWorldGuard();
				wgf = new WorldGuardFlags();
				new WorldGuardFlag();
				logger.info("Worldguard support enabled!");
			}
			if (pluginmanager.isPluginEnabled("Factions") && pluginmanager.isPluginEnabled("MassiveCore") && Config.factions) {
				fflags = new FactionsFlags();
				new FactionsFlagConditions();
				logger.info("Faction support enabled!");
			}
			if (pluginmanager.getPlugin("RPGItems") != null && Config.rpgitems) {
				hasRpgItems = true;
				logger.info("RPGItems support enabled!");
			}
			if (pluginmanager.isPluginEnabled("MobArena") && Config.mobarena) {
				maHandler = new MobArenaHandler();
				new MobArenaConditions();
				logger.info("MobArena support enabled!");
			}
			if (pluginmanager.isPluginEnabled("HolographicDisplays") && Config.h_displays) {
				Main.healthbarhandler = new HealthbarHandler(this);
				logger.info("HolographicDisplays support enabled!");
			}

			
			new BukkitRunnable() {
				@Override
				public void run() {
					Main.mythicmobs.getRandomSpawningManager().reload();
				}
			}.runTask(this);
			
			new BukkitRunnable() {
				@Override
				public void run() {
					for (Iterator<Entity>it=Main.entityCache.iterator();it.hasNext();) {
						Entity entity=it.next();
						if (entity!=null) {
							if (NMSUtils.getEntity(entity.getWorld(),entity.getUniqueId())==null) it.remove();
						} else {
							it.remove();
						}
					}
				}
			}.runTaskTimerAsynchronously(this,600L,600L);
		}
	}

	@Override
	public void onDisable() {
		for (Entity e : entityCache) {
			if (e != null) e.remove();
		}
		if (healthbarhandler!=null) {
			Main.healthbarhandler.removeHealthbars();
			Main.healthbarhandler.removeSpeechBubbles();
		}
		if (this.thiefhandler!=null) Bukkit.getServer().getScheduler().cancelTask(this.thiefhandler.taskid.getTaskId());
		if (Main.cachedOwnerHandler!=null) CachedOwnerHandler.saveCachedOwners();
		this.thiefhandler = null;
		Main.mythicplayers = null;
		Main.mythicmobs = null;
		this.maHandler = null;
		this.volatilehandler = null;
		this.wg = null;
		Main.cachedOwnerHandler = null;
		Main.wgf = null;
		Main.fflags = null;
		pluginmanager.disablePlugin(this);
	}

	public static Main getPlugin() {
		return plugin;
	}

	public MythicMobs getMythicMobs() {
		return Main.mythicmobs;
	}

	public MythicPlayers getMythicPlayers() {
		return Main.mythicplayers;
	}

	public MobManager getMobManager() {
		return Main.mobmanager;
	}

	public ThiefHandler getThiefHandler() {
		return this.thiefhandler;
	}

	private static WorldGuardPlugin getWorldGuard() {
		return (WorldGuardPlugin) pluginmanager.getPlugin("WorldGuard");
	}

	public MobArenaHandler getMobArenaHandler() {
		return this.maHandler;
	}

	public VolatileHandler getVolatileHandler() {
		if (this.volatilehandler != null) return this.volatilehandler;
		String v, n;
		VolatileHandler vh=null;
		n = Bukkit.getServer().getClass().getPackage().getName();
		v = n.substring(n.lastIndexOf(46) + 1);
		try {
			Class<?> c = Class.forName("com.gmail.berndivader.volatilecode.Volatile_"+v);
			if (VolatileHandler.class.isAssignableFrom(c)) {
				vh = (VolatileHandler)c.getConstructor(new Class[0]).newInstance(new Object[0]);
			}
		} catch (Exception ex) {
			if (ex instanceof ClassNotFoundException) {
				logger.warning("Server version not supported!");
			}
			ex.printStackTrace();
		}
		return vh;
	}

}
