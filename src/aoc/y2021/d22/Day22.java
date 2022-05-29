package aoc.y2021.d22;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day22 {

	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(22);
		List<Cube> cubes = new ArrayList<>();
		Map<Cube, Boolean> state = new HashMap<>();
		
		for(String s: lines) {
			boolean turnState = s.split(" ")[0].equals("on");
			String[] coordinateStrings = s.split(" ")[1].replaceAll("[xyz=]", "").split(",");
			
			int xf, xt, yf, yt, zf, zt;
			
			String[] xCoords = coordinateStrings[0].split("\\.\\.");
			xf = Integer.parseInt(xCoords[0]);
			xt = Integer.parseInt(xCoords[1]);
			
			String[] yCoords = coordinateStrings[1].split("\\.\\.");
			yf = Integer.parseInt(yCoords[0]);
			yt = Integer.parseInt(yCoords[1]);
			
			String[] zCoords = coordinateStrings[2].split("\\.\\.");
			zf = Integer.parseInt(zCoords[0]);
			zt = Integer.parseInt(zCoords[1]);
						
			cubes.add(new Cube(xf, yf, zf, xt, yt, zt, turnState));
			
			if(xf >= -50 && xt <= 50 && yf >= -50 && yt <= 50 && zf >= -50 && zt <= 50) {
				for(int x = xf; x <= xt; x++) {
					for(int y = yf; y <= yt; y++) {
						for(int z = zf; z <= zt; z++) {
							state.put(new Cube(x, y, z, x, y, z, false), turnState);
						}
					}
				}
			}		
		}
		
		Utils.printSolution(state.values().stream().filter(e -> e).count());
		
		List<Cube> placed = new ArrayList<>();
        for (Cube c : cubes) {
            List<Cube> todo = new ArrayList<>();
            if (c.state()) todo.add(c);
            for (Cube p : placed) {
                Cube inter = p.intersect(c, !p.state());
                if(inter != null) todo.add(inter);
            }
            placed.addAll(todo);
        }
		
        long out = 0;
        for(Cube c: placed) {
        	out += c.size();
        }
        
		Utils.printSolution(out);
	}
}