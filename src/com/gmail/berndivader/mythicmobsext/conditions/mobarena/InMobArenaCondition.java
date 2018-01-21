package com.gmail.berndivader.mythicmobsext.conditions.mobarena;

import com.garbagemule.MobArena.MobArenaHandler;
import com.gmail.berndivader.mythicmobsext.Main;
import com.gmail.berndivader.mythicmobsext.abstractcode.AbstractCustomCondition;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;

public class InMobArenaCondition 
extends
AbstractCustomCondition
implements 
ILocationCondition {
	protected MobArenaHandler maHandler;

	public InMobArenaCondition(String line, MythicLineConfig mlc) {
		super(line, mlc);
		this.maHandler = Main.getPlugin().getMobArenaHandler();
	}

	@Override
	public boolean check(AbstractLocation location) {
		return maHandler.inRegion(BukkitAdapter.adapt(location));
	}
}
