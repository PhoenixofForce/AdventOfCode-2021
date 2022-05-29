package aoc.y2021.d20;

import java.util.List;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;
import util.ArrayUtils;

public class Day20 {

	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(20);
		String enhancer = lines.remove(0); lines.remove(0);
		
		boolean[][] map = ArrayUtils.strings2BooleanArray(lines, '#');
		
		boolean infinity = false;
		for(int i = 0; i < 50; i++) {
			map = enhance(map, enhancer, infinity);
			infinity = !infinity;
			if(i == 1) Utils.printSolution(count(map));
		}
		
		Utils.printSolution(count(map));
		
	}
	
	public static boolean[][] enhance(boolean[][] map, String enhancer, boolean infinity) {
		boolean[][] out = new boolean[map.length + 2][map[0].length + 2];
		
		for(int x = 0; x < out.length; x++) {
			for(int y = 0; y < out[0].length; y++) {
				
				String currentVal = "";
				for(int dx = -1; dx <= 1; dx++) {
					for(int dy = -1; dy <= 1; dy++) {
						int nx = x + dx - 1;
						int ny = y + dy - 1;
						if((nx < map.length && nx >= 0 && ny >= 0 && ny < map[0].length)) {
							
							
							
							boolean t = map[nx][ny];
							currentVal += t? "1": "0";
						} else currentVal += infinity? "1": "0";
					}
				}
				
				boolean newValue = enhancer.charAt(Integer.parseInt(currentVal, 2)) == '#';
				out[x][y] = newValue;
			}
		}
		
		return out;
	}

	public static long count(boolean[][] state) {
		long out = 0;
		
		for(int x = 0; x < state.length; x++) {
			for(int y = 0; y < state[x].length; y++) {
				if(state[x][y]) {
					out += 1;
				}
			}
		}
		
		return out;
	}
	
}
