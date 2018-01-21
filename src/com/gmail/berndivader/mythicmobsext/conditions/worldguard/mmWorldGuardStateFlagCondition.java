package com.gmail.berndivader.mythicmobsext.conditions.worldguard;

import com.gmail.berndivader.mythicmobsext.Main;
import com.gmail.berndivader.mythicmobsext.abstractcode.AbstractCustomCondition;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;

public class mmWorldGuardStateFlagCondition
extends
AbstractCustomCondition
implements
ILocationCondition {

	private WorldGuardFlags wgf = Main.wgf;
	private String flagName;
	private boolean debug;

	public mmWorldGuardStateFlagCondition(String line, MythicLineConfig mlc) {
		super(line,mlc);
		this.flagName = mlc.getString(new String[] { "flagname", "flag", "f" }, "mob-spawning");
		this.debug=mlc.getBoolean("debug",false);
	}

	@Override
	public boolean check(AbstractLocation location) {
		boolean b=wgf.checkRegionStateFlagAtLocation(BukkitAdapter.adapt(location), flagName);
		if (this.debug) Main.logger.info("wgstateflag outcome: "+b);
		return b;
	}
}
