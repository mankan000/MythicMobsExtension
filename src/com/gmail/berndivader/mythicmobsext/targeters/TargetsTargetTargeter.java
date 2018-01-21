package com.gmail.berndivader.mythicmobsext.targeters;

import java.util.HashSet;
import java.util.Iterator;

import org.bukkit.entity.Player;

import com.gmail.berndivader.utils.Utils;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;

public class TargetsTargetTargeter 
extends
IEntitySelector {

	public TargetsTargetTargeter(MythicLineConfig mlc) {
		super(mlc);
	}

	@Override
	public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
		HashSet<AbstractEntity>targets=new HashSet<>();
		HashSet<AbstractEntity>tt=data.getEntityTargets();
		Iterator<AbstractEntity>it=tt.iterator();
		while (it.hasNext()) {
			AbstractEntity target=it.next();
			if (target!=null) {
				if (target.isPlayer()) {
					AbstractEntity pt=BukkitAdapter.adapt(Utils.getTargetedEntity((Player)target.getBukkitEntity()));
					if (pt!=null) targets.add(pt);
				} else if (target.getTarget()!=null){
					targets.add(target.getTarget());
				}
			}
		}
		return targets;
	}
}
