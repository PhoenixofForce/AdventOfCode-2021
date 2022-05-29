package aoc.y2021.d17;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day17 {

	public static void main(String[] args) {
		String line = Y2021_Utils.getFile(17);
		line = line.replace(",", "");
		String[] parts = line.split(" ");
		
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		for(String s: parts[2].split("=")[1].split("\\.\\.")) {
			if(x1 == 0) x1 = Integer.parseInt(s);
			else x2 = Integer.parseInt(s);
		}
		
		for(String s: parts[3].split("=")[1].split("\\.\\.")) {
			if(y1 == 0) y1 = Integer.parseInt(s);
			else y2 = Integer.parseInt(s);
		}
		
		int out = 0;
		int highest = 0;
		for(int vx = 0; vx < Math.max(x2, x1) + 5; vx++) {
			for(int vy = Math.min(y1, y2)-5; vy < 1000; vy++) {
				if(lands(x1, x2, y1, y2, vx, vy) == 0) {
					out++;
					int localHighest = highestY(x1, x2, y1, y2, vx, vy);
					if(localHighest > highest) highest = localHighest;
				}
			}
		}
		Utils.printSolution(highest);
		Utils.printSolution(out);
	}
	
	public static int lands(int x1, int x2, int y1, int y2, int vx, int vy) {
		if(Math.signum(vx) != Math.signum(x1)) return -1;
		
		int x = 0; 
		int y = 0;
		
		while(y >= Math.min(y1, y2) && !inBounds(x1, x2, y1, y2, x, y)) {
			x += vx;
			y += vy;
									
			if(vx > 0) vx--;
			else if(vx < 0) vx++;
			vy--;
		}
		
		if(inBounds(x1, x2, y1, y2, x, y)) return 0;
		if((x1 < 0 && x > Math.max(x1, x2)) || (x1 > 0 && x < Math.min(x1, x2))) return -1;
		if((x1 < 0 && x < Math.max(x1, x2)) || (x1 > 0 && x > Math.min(x1, x2))) return 1;
		return 1;
	}
	
	public static int highestY(int x1, int x2, int y1, int y2, int vx, int vy) {
		if(Math.signum(vx) != Math.signum(x1)) return -1;
		
		int x = 0; 
		int y = 0;
		
		int hy = y;
		
		while(y >= Math.min(y1, y2) && !inBounds(x1, x2, y1, y2, x, y)) {
			x += vx;
			y += vy;
			
			
			if(y > hy) hy = y;
			
			if(vx > 0) vx--;
			else if(vx < 0) vx++;
			vy--;
		}
		
		return hy;
	}

	public static boolean inBounds(int x1, int x2, int y1, int y2, int x, int y) {
		int sx = Math.min(x1, x2);
		int bx = Math.max(x1, x2);
		int sy = Math.min(y1, y2);
		int by = Math.max(y1, y2);
		
		return (x <= bx && x >= sx && y <= by && y >= sy);
	}
}