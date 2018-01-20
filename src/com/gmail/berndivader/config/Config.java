package com.gmail.berndivader.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class Config {

    public static boolean debug;
	public static boolean update;
	public static boolean nan;
	public static boolean m_players;
	public static boolean m_thiefs;
	public static boolean m_parrot;
	public static boolean c_owners;
	public static boolean wguard;
	public static boolean factions;
	public static boolean rpgitems;
	public static boolean mobarena;
	public static boolean h_displays;

	public static void load(Plugin plugin) {
		File configFile = new File(plugin.getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			plugin.getLogger().info("Generating config.yml...");
			plugin.saveDefaultConfig();
		}

		YamlConfiguration config = new YamlConfiguration();
		try {
			config.load(configFile);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			plugin.getLogger().warning("The configuration is not a valid YAML file! Please check it with a tool like http://yaml-online-parser.appspot.com/");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			plugin.getLogger().warning("Error while reading the file. Is it in use?");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			plugin.getLogger().warning("Unhandled exception while reading the configuration!");
			return;
		}

		// updates
        int version = config.getInt(ConfigValue.VERSION.getPath());

		if (version <= 1) {
			for (ConfigValue value : ConfigValue.values()) {
				if (!config.isSet(value.getPath())) {
					config.set(value.getPath(), value.getDefaultValue());
				}
			}

			// check for old entry's
			List<String> entryToRemove = Arrays.asList(
					"Old_Entry1",
					"Old_Entry2"
			);

			for (String oldEntry : entryToRemove) {
				if (config.isSet(oldEntry)) {
					config.set(oldEntry, null);
				}
			}

			try {
				config.save(configFile);
			} catch (IOException e) {
				e.printStackTrace();
				plugin.getLogger().warning("I/O error while saving the configuration. Was the file in use?");
			}
		}

		debug = config.getBoolean(ConfigValue.DEBUG.getPath());
		update = config.getBoolean(ConfigValue.UPDATE.getPath());
		nan = config.getBoolean(ConfigValue.NAN_PATCH.getPath());
		m_players = config.getBoolean(ConfigValue.M_PLAYERS.getPath());
		m_thiefs = config.getBoolean(ConfigValue.M_THIEFS.getPath());
		c_owners = config.getBoolean(ConfigValue.C_OWNERS.getPath());
		m_parrot = config.getBoolean(ConfigValue.M_PARROT.getPath());
		wguard = config.getBoolean(ConfigValue.WGUARD.getPath());
		factions = config.getBoolean(ConfigValue.FACTIONS.getPath());
		rpgitems = config.getBoolean(ConfigValue.RPGITEMS.getPath());
		mobarena = config.getBoolean(ConfigValue.MOBARENA.getPath());
		h_displays = config.getBoolean(ConfigValue.H_DISPLAYS.getPath());
	}

}
