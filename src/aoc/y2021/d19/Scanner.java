package aoc.y2021.d19;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scanner {
		
	private Scanner instance;
		
	private Vec3i position;
	private List<Vec3i> scanPoints;
	private List<Vec3i> otherScanner;
	
	public Scanner() {
		instance = this;
		scanPoints = new ArrayList<>();
		otherScanner = new ArrayList<>();
	}
	
	public void addPoint(String s) {
		scanPoints.add(new Vec3i(s));
	}
	
	public void setPosition(Vec3i pos) {
		Vec3i sub = new Vec3i();
		if(this.position != null) {
			sub = this.position;
		}
		
		Vec3i add = new Vec3i();
		if(pos != null) add = pos;
		
		for(Vec3i v: scanPoints) {
			v.sub(sub).add(add);
		}
		
		this.position = pos;
	}
	
	public boolean matches(Scanner s) {
		for(Vec3i potentialPosition: s.scanPoints) {
			for(Vec3i potentialMatchingPos: scanPoints) {
				
				Vec3i pos = potentialPosition.clone().sub(potentialMatchingPos.clone());
				
				this.setPosition(pos);
				
				int matches = 0;
				for(Vec3i v: this.scanPoints) {
					if(s.scanPoints.contains(v)) matches++;
				}
				
				if(matches >= 12) {
					s.setScanner(pos);
					this.setScanner(s.position);
					return true;
				}
				
				this.setPosition(null);

			}
		}
		
		return false;
	}
	
	public List<Vec3i> getScanPoints() {
		return scanPoints;
	}
	
	public Vec3i getPosition() {
		return position;
	}
	
	private void setScanner(Vec3i scanner) {
		scanPoints.remove(scanner);
		otherScanner.add(scanner);
	}
	
	public Iterator<Scanner> getOrientationIterator() {
		return new Iterator<Scanner>() {

			private int rx = 0, ry = 0, rz = 0;
			
			@Override
			public boolean hasNext() {
				return !(rx == 3 && ry == 3 && rz == 3);
			}

			@Override
			public Scanner next() {
				for(Vec3i v: scanPoints) v.rotate(-(rx * 90), -(ry * 90), -(rz * 90));
				
				rx++;
				if(rx == 4) {
					rx = 0;
					ry++;
				}
				
				if(ry == 4) {
					ry = 0;
					rz++;
				}
				
				for(Vec3i v: scanPoints) {
					v.rotate((rx * 90), (ry * 90), (rz * 90));
				}
				
				return instance;
			}
		};
	}
	
}
