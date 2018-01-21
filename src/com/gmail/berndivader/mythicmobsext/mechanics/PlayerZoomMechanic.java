package com.gmail.berndivader.mythicmobsext.mechanics;

import com.gmail.berndivader.mythicmobsext.Main;
import org.bukkit.entity.Player;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;

public class PlayerZoomMechanic 
extends
SkillMechanic 
implements
ITargetedEntitySkill {
	private Float f1;

	public PlayerZoomMechanic(String skill, MythicLineConfig mlc) {
		super(skill, mlc);
		f(mlc.getFloat(new String[] { "value", "v", "amount", "a" }, 0.0F));
	}

	@Override
	public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
		if (target.isPlayer()) {
			Main.getPlugin().getVolatileHandler().setFieldOfViewPacketSend((Player)target.getBukkitEntity(),this.f1);
			return true;
		}
		return false;
	}
	
	private void f(Float f1) {
		this.f1=f1>1.0f?1.0f:f1<0.0f?0.0f:f1;
	}

}
