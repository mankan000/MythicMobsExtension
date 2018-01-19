package com.gmail.berndivader.config;

public enum ConfigValue {

	VERSION("Configuration.Version", 1),
	DEBUG("Configuration.Debug", false),
	UPDATE("Configuration.Update_Notification", true),
	NAN_PATCH("Configuration.Patches.NaN_Patch", true),
	M_PLAYERS("Configuration.Modules.Mythic_Players", true),
	M_THIEFS("Configuration.Modules.Mythic_Thiefs", true),
	C_OWNERS("Configuration.Modules.Cached_Owners", true),
	M_PARROT("Configuration.Entities.Mythic_Parrot", true),
	WGUARD("Configuration.Compatibility.Worldguard", true),
	H_DISPLAYS("Configuration.Compatibility.Holographic_Displays", true);

	private final String path;
	private final Object value;

	private ConfigValue(String path, Object defaultValue) {
		this.path = path;
		value = defaultValue;
	}

	public String getPath() {
		return path;
	}

	public Object getDefaultValue() {
		return value;
	}
}
