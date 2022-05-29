package aoc.y2021.d19;

import java.util.Objects;

public class Vec3i {

	public int x, y, z;
	public Vec3i() {
		this(0, 0, 0);
	}
	
	public Vec3i(int x, int y, int z) {
		this.x = x; 
		this.y = y;
		this.z = z;
	}
	
	public Vec3i(String s) {
		String[] parts = s.split(",");
		
		this.x = Integer.parseInt(parts[0]);
		this.y = Integer.parseInt(parts[1]);
		this.z = Integer.parseInt(parts[2]);
	}
	
	public Vec3i add(Vec3i other) {
		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
		
		return this;
	}
	
	public Vec3i sub(Vec3i other) {
		this.x -= other.x;
		this.y -= other.y;
		this.z -= other.z;
		
		return this;
	}
	
	public long manhattan(Vec3i other) {
		long out = 0;
		out += Math.abs(other.x - x);
		out += Math.abs(other.y - y);
		out += Math.abs(other.z - z);
		return out;
	}
	
	public Vec3i rotate(double rx, double ry, double rz) {
		return this.rotateX(rx).rotateY(ry).rotateZ(rz);
	}
	
	public Vec3i rotateX(double angle) {
		angle = Math.toRadians(angle);
		
		double nx = x;
		double ny = y * Math.cos(angle) - z * Math.sin(angle);
		double nz = y * Math.sin(angle) + z * Math.cos(angle);
				
		x = (int) Math.round(nx);
		y = (int) Math.round(ny);
		z = (int) Math.round(nz);
				
		return this;
	}
	
	public Vec3i rotateY(double angle) {
		angle = Math.toRadians(angle);
		
		double nx = z * Math.sin(angle) + x * Math.cos(angle);
		double ny = y;
		double nz = z * Math.cos(angle) - x * Math.sin(angle);
				
		x = (int) Math.round(nx);
		y = (int) Math.round(ny);
		z = (int) Math.round(nz);
				
		return this;
	}

	public Vec3i rotateZ(double angle) {
		angle = Math.toRadians(angle);
		
		double nx = x * Math.cos(angle) - y * Math.sin(angle);
		double ny = x * Math.sin(angle) + y * Math.cos(angle);
		double nz = z;
		
		
		x = (int) Math.round(nx);
		y = (int) Math.round(ny);
		z = (int) Math.round(nz);
				
		return this;
	}
	
	@Override
	public boolean equals(Object o2) {
		if(o2 instanceof Vec3i v) {
			return v.x == x && v.y == y && v.z == z;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
	@Override
	public Vec3i clone() {
		return new Vec3i(x, y, z);
	}
	
	@Override
	public String toString() {
		return "(" + x  + " | " + y + " | " + z + ")";
	}
	
}
