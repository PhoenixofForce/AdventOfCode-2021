package aoc.y2021.d19;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day19 {

	public static void main(String[] args) {
		List<Scanner> scanner = getScanner();
		
		List<Scanner> closed = new ArrayList<>();
		{
			Scanner start = scanner.remove(0);
			start.setPosition(new Vec3i());
			closed.add(start);
		}
		
		while(scanner.size() > 0) {
			System.out.println(scanner.size() + " " + closed.size());
			
			toBreak:
			for(int i = 0; i < scanner.size(); i++) {
				Scanner currentScanner = scanner.get(i);
				
				for(int j = 0; j < closed.size(); j++) {
					Scanner save = closed.get(j);
					
					Iterator<Scanner> currentIt= currentScanner.getOrientationIterator();
					while(currentIt.hasNext()) {
						if(currentScanner.matches(save)) {
							scanner.remove(i);
							closed.add(currentScanner);
							break toBreak;
						}
						
						currentIt.next();
					}
				}
			}
		}
		
		List<Vec3i> allPosition = new ArrayList<>();
		for(Scanner s: closed) {
			for(Vec3i v: s.getScanPoints()) {
				if(!allPosition.contains(v)) allPosition.add(v);
			}
		}
		Utils.printSolution(allPosition.size());
		
		long maxDistance = 0;
		for(int i = 1; i < closed.size(); i++) {
			Scanner first = closed.get(i);
			for(int j = 0; j < i; j++) {
				Scanner second = closed.get(j);
				long distance = second.getPosition().manhattan(first.getPosition());
				if(distance > maxDistance) maxDistance = distance;
			}
		}
		Utils.printSolution(maxDistance);
	}
	
	public static List<Scanner> getScanner() {
		List<Scanner> out = new ArrayList<>();
		List<String> lines = Y2021_Utils.getLines(19);
		
		Scanner current = null;
		for(String s: lines) {
			if(s.startsWith("--- scanner")) {
				if(current != null) out.add(current);
				current = new Scanner();
 			}
 			else {
 				current.addPoint(s);
 			}
		}
		if(current != null) out.add(current);
		
		return out;
	}
}