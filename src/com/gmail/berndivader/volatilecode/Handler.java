package com.gmail.berndivader.volatilecode;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.CommandException;

public interface Handler {
	
    public void aiPathfinderGoal(LivingEntity livingEntity, String goalList, LivingEntity target);
    public boolean inMotion(LivingEntity entity);
    public void teleportEntityPacket(Entity entity);
    public void forceSetPositionRotation(Entity entity,double x,double y,double z,float yaw,float pitch,boolean f,boolean g);
    public void playerConnectionSpin(Entity entity,float s);
    void setItemMotion(Item i, Location ol, Location nl);
	void sendArmorstandEquipPacket(ArmorStand entity);
	void changeHitBox(Entity entity, double a0, double a1, double a2);
	void playerConnectionLookAt(Entity entity, float yaw, float pitch);
	void moveEntityPacket(Entity entity,Location ol, double x, double y, double z);
	void rotateEntityPacket(Entity entity, float y, float p);
	void forceSpectate(Player player, Entity entity);
	void playEndScreenForPlayer(Player player,float f);
	boolean playerIsSleeping(Player p);
	boolean playerIsRunning(Player p);
	boolean playerIsCrouching(Player p);
	boolean playerIsJumping(Player p);
	void forceCancelEndScreenPlayer(Player player);
	void fakeEntityDeath(Entity entity,long d);
	int arrowsOnEntity(Entity entity);
	void modifyArrowsAtEntity(Entity entity, int a, char c);
	void removeSnowmanHead(Entity entity);
	void setDeath(Player p, boolean b);
	boolean testForCondition(Entity e,String ns,char m) throws CommandException;
	float getItemCoolDown(Player p);
	public boolean setItemCooldown(Player p,int j);
	void setFieldOfViewPacketSend(Player player, float f1);
	float getIndicatorPercentage(Player p);
	Parrot spawnCustomParrot(Location l1,boolean b1);
	boolean getNBTValueOf(Entity e1, String s1, boolean b1);
	void moveto(LivingEntity entity);
	void setWBWB(Player p, boolean bl1);
	boolean addNBTTag(Entity e1, String s);
}
