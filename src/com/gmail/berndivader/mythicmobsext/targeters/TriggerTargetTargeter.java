package com.gmail.berndivader.mythicmobsext.targeters;

import java.util.HashSet;

import org.bukkit.entity.Player;

import com.gmail.berndivader.utils.Utils;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;

public class TriggerTargetTargeter
extends
IEntitySelector {
	public TriggerTargetTargeter(MythicLineConfig mlc) {
		super(mlc);
	}

	@Override
	public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
		HashSet<AbstractEntity>targets=new HashSet<>();
		AbstractEntity target;
		if (data.getTrigger().isPlayer()) {
			if ((target=BukkitAdapter.adapt(Utils.getTargetedEntity((Player)data.getTrigger().getBukkitEntity())))!=null) {
				targets.add(target);
			};
		} else if ((target=data.getTrigger().getTarget())!=null) {
			targets.add(target);
		}
		return targets;
	}
}
