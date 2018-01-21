package com.gmail.berndivader.mythicmobsext.targeters;

import java.util.HashSet;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;

public class LastDamagerTargeter
extends
IEntitySelector {

	public LastDamagerTargeter(MythicLineConfig mlc) {
		super(mlc);
	}

	@Override
	public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
		HashSet<AbstractEntity>targets=new HashSet<>();
		EntityDamageEvent e=data.getCaster().getEntity().getBukkitEntity().getLastDamageCause();
		if (e instanceof EntityDamageByEntityEvent) {
			targets.add(BukkitAdapter.adapt(((EntityDamageByEntityEvent)e).getDamager()));
		}
		return targets;
	}

}
