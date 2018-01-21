package com.gmail.berndivader.mythicmobsext.mechanics;

import com.gmail.berndivader.mythicmobsext.Main;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.IMetaSkill;
import io.lumine.xikage.mythicmobs.skills.Skill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;

public class StoreCooldownMechanic
extends
SkillMechanic
implements
IMetaSkill {
	private String name;

	public StoreCooldownMechanic(String skill, MythicLineConfig mlc) {
		super(skill, mlc);
		this.name=mlc.getString("skill");
	}

	@Override
	public boolean cast(SkillMetadata data) {
		Skill skill= Main.mythicmobs.getSkillManager().getSkill(this.name).get();
		if (skill!=null) {
		}
		return false;
		
	}

}
