package com.gmail.berndivader.mythicmobsext.mechanics;

import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.gmail.berndivader.utils.Utils;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;

public class CustomDamage
extends
SkillMechanic
implements
ITargetedEntitySkill {
	boolean pk;
	boolean pi;
	boolean ia;
	boolean iabs;
	boolean ip;
	boolean p;
	boolean uc;
	boolean pcur;
	boolean debug;
	double dbd;
	DamageCause cause;
	String amount;
	String ca;

	public CustomDamage(String skill, MythicLineConfig mlc) {
		super(skill, mlc);

		this.ASYNC_SAFE = false;
		this.pk = mlc.getBoolean(new String[] { "preventknockback", "pkb", "pk" }, false);
 		this.amount = mlc.getString(new String[] { "amount", "a" }, "1");
		if (this.amount.startsWith("-")) { this.amount = "1"; }
		this.ia = mlc.getBoolean(new String[] { "ignorearmor", "ignorearmour", "ia", "i" }, false);
		this.pi = mlc.getBoolean(new String[] { "preventimmunity", "pi" }, false);
		this.iabs = mlc.getBoolean(new String[] { "ignoreabsorbtion", "ignoreabs", "iabs" }, false);
		this.ip = mlc.getBoolean(new String[] { "ignorepower", "ip" }, false);
		this.p = mlc.getBoolean(new String[] { "percentage", "p" }, false);
		this.uc = mlc.getBoolean(new String[] { "usecaster", "uc" }, false);
		this.pcur = mlc.getBoolean(new String[] { "percentcurrent", "pcur", "pc" }, false);
		ca = mlc.getString(new String[] { "damagecause", "cause", "dc" }, "CUSTOM").toUpperCase();
		this.dbd = mlc.getDouble(new String[] { "rangedamagebydistance", "rdbd" }, -7331D);
		cause=DamageCause.CUSTOM;
		for (DamageCause dc : DamageCause.values()) {
			if (dc.toString().equals(ca)) {
				this.cause=dc;
				break;
			}
		}
		this.debug=mlc.getBoolean("debug",false);
	}

	@Override
	public boolean castAtEntity(SkillMetadata data, AbstractEntity t) {
		if (t.isDead() || t.getHealth() <= 0.0 || data.getCaster().isUsingDamageSkill())
			return false;
		AbstractEntity c = data.getCaster().getEntity();
		double dmg = Utils.randomRangeDouble(this.amount);
		if (this.p) {
			if (this.uc) {
				dmg = this.pcur ? c.getHealth() * dmg : c.getMaxHealth() * dmg;
			} else {
				dmg = this.pcur ? t.getHealth() * dmg : t.getMaxHealth() * dmg;
			}
		}
		if (!this.ip)
			dmg = dmg * data.getPower();
		if (dbd>-7331D) {
			int dd=(int)Math.sqrt(Utils.distance3D(data.getCaster().getEntity().getLocation().toVector(), t.getBukkitEntity().getLocation().toVector()));
			dmg-=(dmg*(dd*dbd));
		}
		Utils.doDamage(data.getCaster(), t, dmg, this.ia, this.pk, this.pi, this.iabs, this.debug, this.cause);
		return true;
	}
}
