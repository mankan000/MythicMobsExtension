package com.gmail.berndivader.mythicmobsext;

import com.gmail.berndivader.mythicmobsext.conditions.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;

public class CustomConditions implements Listener {

	public CustomConditions(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onMythicMobsConditionsLoadEvent(MythicConditionLoadEvent e) {
		String condition;
		SkillCondition cond;
		condition = e.getConditionName().toLowerCase();

		switch (condition) {
			case "arrowcount": {
				cond = new ArrowOnEntityCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "behind": {
				cond = new IsBehindCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "biomefix": {
				cond = new BiomeFixCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "cmpnbt": {
				cond = new CompareNBTCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "crouching":
			case "running":
			case "disguised":
			case "sleeping":
			case "jumping": {
				cond = new PlayerBooleanConditions(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "damageable":
			case "attackable": {
				cond = new IsAttackableCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "entitiesinradius":
			case "eir":
			case "leir":
			case "livingentitiesinradius":
			case "pir":
			case "playersinradius": {
				cond = new EntitiesInRadiusCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "facingdirection": {
				cond = new FacingDirectionCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "getbowtension":
			case "bowtension":
			case "lastbowtension":
			case "tension": {
				cond = new GetBowtensionCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "getindicator":
			case "damageindicator":
			case "indicator": {
				cond = new GetDamageIndicator(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "lastindicator":
			case "lastdamageindicator":
			case "getlastindicator":
			case "getlastdamageindicator": {
				cond = new GetLastDamageIndicator(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "hasmeta":
			case "hasmetasimple": {
				cond = new HasMetaTagCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "hasspawner": {
				cond = new HasMythicSpawnerCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "hastarget": {
				cond = new HasTargetCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "health": {
				cond = new Healthcondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "infaction": {
				cond = new InFactionCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "infront": {
				cond = new InFrontCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "inmotion": {
				cond = new InMotionCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "isburning": {
				cond = new IsBurningCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "isgoggling": {
				cond = new IsGogglingCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "ispresent": {
				cond = new IsTargetPresentCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "isspinning": {
				cond = new IsSpinningCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "isstunned":
			case "stunned": {
				cond = new IsStunnedCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "isvehicle": {
				cond = new IsVehicleCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "hasvehicle": {
				cond = new HasVehicleCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "lastdamagecause": {
				cond = new LastDamageCauseCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "lookatme":
			case "looksatme": {
				cond = new LookingAtMeCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "mir":
			case "mobsinradius": {
				cond = new MobsInRadiusCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "moveto": {
				cond = new MoveToCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "movespeed": {
				cond = new MovementSpeedCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "oncooldown": {
				cond = new OnCooldownCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "onsolidblock":
			case "insolidblock": {
				cond = new SolidBlockConditions(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "owneralive": {
				cond = new OwnerAliveCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "ownsitem":
			case "ownsitemsimple":
			case "iteminhand": {
				cond = new HasItemCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "parsedstance":
			case "pstance": {
				cond = new ParsedStanceCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "playertime": {
				cond = new PlayerTimeCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "playerweather": {
				cond = new PlayerWeatherCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "relativedirection": {
				cond = new DirectionalDamageCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "samefaction": {
				cond = new SameFactionCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "samespawner": {
				cond = new SameMythicSpawnerCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "sameworld": {
				cond = new SameWorldCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "testfor": {
				cond = new TestForCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			} case "vdistance": {
				cond = new VerticalDistanceCondition(e.getConfig().getLine(),e.getConfig());
				e.register(cond);
				break;
			}
		}
	}
}
