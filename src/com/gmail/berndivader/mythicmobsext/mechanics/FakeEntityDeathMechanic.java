package com.gmail.berndivader.mythicmobsext.mechanics;

import com.gmail.berndivader.mythicmobsext.Main;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;

public class FakeEntityDeathMechanic
extends
SkillMechanic
implements
ITargetedEntitySkill {
	private long d;

	public FakeEntityDeathMechanic(String skill, MythicLineConfig mlc) {
		super(skill, mlc);
		this.d=(long)mlc.getInteger(new String[] { "duration", "dur" }, 60);
	}

	@Override
	public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
		if (target.isLiving()) {
			Main.getPlugin().getVolatileHandler().fakeEntityDeath(target.getBukkitEntity(),d);
		}
		return false;
	}
}
