package com.gmail.berndivader.mythicmobsext.mythicplayers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.berndivader.mythicmobsext.mythicplayers.mechanics.mmCreateActivePlayer;
import com.gmail.berndivader.mythicmobsext.mythicplayers.mechanics.mmNormalPlayer;
import com.gmail.berndivader.mythicmobsext.mythicplayers.mechanics.mmSetTarget;
import com.gmail.berndivader.mythicmobsext.targeters.CrosshairTargeter;
import com.gmail.berndivader.mythicmobsext.targeters.EyeDirectionTargeter;
import com.gmail.berndivader.mythicmobsext.targeters.LastDamagerTargeter;
import com.gmail.berndivader.mythicmobsext.targeters.OwnerTargetTargeter;
import com.gmail.berndivader.mythicmobsext.targeters.TargetsTargetTargeter;
import com.gmail.berndivader.mythicmobsext.targeters.TriggerDirectionTargeter;
import com.gmail.berndivader.mythicmobsext.targeters.TriggerTargetTargeter;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicTargeterLoadEvent;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillTargeter;

public class MythicPlayerMythicMobsLoadEvent implements Listener {

	@EventHandler
	public void onMMSkillLoad(MythicMechanicLoadEvent e) {
		SkillMechanic skill;
		String mech = e.getMechanicName().toLowerCase();
		switch (mech) {
		case "activeplayer": {
			skill = new mmCreateActivePlayer(e.getContainer().getConfigLine(), e.getConfig());
			e.register(skill);
			break;
		} case "normalplayer": {
			skill = new mmNormalPlayer(e.getContainer().getConfigLine(), e.getConfig());
			e.register(skill);
			break;
		} case "settarget": {
			skill = new mmSetTarget(e.getContainer().getConfigLine(), e.getConfig());
			e.register(skill);
			break;
		}}
	}

	@EventHandler
	public void onMythicMobsTargetersLoad(MythicTargeterLoadEvent e) {
		String TargeterName = e.getTargeterName().toLowerCase();
		switch (TargeterName) {
		case "crosshair": {
			SkillTargeter targeter = new CrosshairTargeter(e.getConfig());
			e.register(targeter);
			break;
		}
		case "ownertarget": {
			SkillTargeter targeter=new OwnerTargetTargeter(e.getConfig());
			e.register(targeter);
			break;
		}
		case "lastdamager": {
			SkillTargeter targeter=new LastDamagerTargeter(e.getConfig());
			e.register(targeter);
			break;
		}case "triggerstarget": {
			SkillTargeter targeter=new TriggerTargetTargeter(e.getConfig());
			e.register(targeter);
			break;
		}case "targetstarget": {
			SkillTargeter targeter=new TargetsTargetTargeter(e.getConfig());
			e.register(targeter);
			break;
		}case "eyedirection": {
			SkillTargeter targeter=new EyeDirectionTargeter(e.getConfig());
			e.register(targeter);
			break;
		}case "triggerdirection": {
			SkillTargeter targeter=new TriggerDirectionTargeter(e.getConfig());
			e.register(targeter);
			break;
		}
		}
	}
}
