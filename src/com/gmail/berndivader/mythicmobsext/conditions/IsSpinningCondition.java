package com.gmail.berndivader.mythicmobsext.conditions;

import com.gmail.berndivader.mythicmobsext.mechanics.PlayerSpinMechanic;
import com.gmail.berndivader.mythicmobsext.abstractcode.AbstractCustomCondition;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;

public class IsSpinningCondition 
extends
AbstractCustomCondition
implements
IEntityCondition {

	public IsSpinningCondition(String line, MythicLineConfig mlc) {
		super(line, mlc);
	}

	@Override
	public boolean check(AbstractEntity entity) {
		return entity.getBukkitEntity().hasMetadata(PlayerSpinMechanic.str);
	}

}
