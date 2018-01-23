package com.gmail.berndivader.utils;

public class Vec2D
implements
Cloneable {
	private double x;
	private double y;
	
	public Vec2D(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x=x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y=y;
	}
	public void set(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public Vec2D clone() throws CloneNotSupportedException {
		return (Vec2D)super.clone();
	}
}
