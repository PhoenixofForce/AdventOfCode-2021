package aoc.y2021.d15;

import java.util.List;
import java.util.ArrayList;

import aoc.y2021.Y2021_Utils;
import util.Dijkstra;
import aoc.Utils;

public class Day15 {

	record Position(int x, int y){}

	public static void main(String[] args) {
		int[][] riskMap = Y2021_Utils.fileAsIntArray(15);
				
		Utils.printSolution(findPath(riskMap));
		Utils.printSolution(findPath(getPart2Map(riskMap)));
	}

	public static int[][] getPart2Map(int[][] in) {
		int[][] out = new int[in.length * 5][in[0].length * 5];
		
		for(int dx = 0; dx < 5; dx++) {
			for(int dy = 0; dy < 5; dy++) {
				for(int y = 0; y < in.length; y++) {
					for(int x = 0; x < in[0].length; x++) {
						int diff = dx + dy;
						int risk = (in[y][x] + diff);
						if(risk >= 10) risk -= 9;
						
						int tY = dy * in.length + y;
						int tX = dx * in[0].length + x;
						out[tY][tX] = risk;
					}
				}
			}
		}
		
		return out;
	}
	
	public static double findPath(int[][] riskLevel) {
		Position start = new Position(0, 0);
		Position end = new Position(riskLevel[0].length - 1, riskLevel.length - 1);
		
		return Dijkstra.findPath(start, end, a -> {
			List<Position> out = new ArrayList<>();
			for(int dx = -1; dx <= 1; dx++) {
				for(int dy = -1; dy <= 1; dy++) {
					if((dy == 0 && dx == 0) || (dx != 0 && dy != 0)) continue;
					if(a.x + dx >= 0 && a.x + dx < riskLevel[0].length && a.y + dy >= 0 && a.y + dy < riskLevel.length) {
						Position newPos = new Position(a.x + dx, a.y + dy);
						out.add(newPos);
						
					}
				}
			}
			return out;
		}, (a, b) -> b + riskLevel[a.y][a.x]);
	}
}
