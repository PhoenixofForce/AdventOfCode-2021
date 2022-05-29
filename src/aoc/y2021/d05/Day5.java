package aoc.y2021.d05;

import java.util.List;
import java.util.stream.Collectors;

import aoc.Utils;
import aoc.y2021.Y2021_Utils;

public class Day5 {

	public static void main(String[] args) {
		boolean part1 = false;
		
		List<String> lines = Y2021_Utils.getLines(5);
		lines = lines.stream().map(e -> e.replace(",", " ").replace("-> ", "")).collect(Collectors.toList());
		int[][] field = new int[1000][1000];
		
		for(String line: lines) {
			String[] coords = line.split(" ");
			
			int x1 = Integer.parseInt(coords[0]);
			int y1 = Integer.parseInt(coords[1]);
			int x2 = Integer.parseInt(coords[2]);
			int y2 = Integer.parseInt(coords[3]);

			if(!(x1 == x2 || y1 == y2 || (Math.abs(x1-x2) == Math.abs(y1-y2) && !part1))) continue;
			
				int x = x1;
				int y = y1;
				
				while(x != x2 || y != y2) {
					field[x][y] = field[x][y]+1;
					x += Integer.compare(x2, x);
					y += Integer.compare(y2, y);
				}
				field[x][y] = field[x][y]+1;
			
		}
		
		int out = 0;
		for(int x = 0; x < 1000; x++) {
			for(int y = 0; y < 1000; y++) {
				if(field[x][y] > 1) out++;
			}
		}
				
		Utils.printSolution(out);
	}
}