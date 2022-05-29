package aoc.y2021.d25;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;
import util.ArrayUtils;

public class Day25 {

	record Step(boolean b, char[][] map) {}

	public static void main(String[] args) {
		char[][] map = Y2021_Utils.fileAsCharArray(25);

		int i = 0;
		Step s = new Step(true, map);
		while(s.b) {
			s = step(map);
			map = s.map;
			i++;
		}
		Utils.printSolution(i);
	}

	public static String toString(char[][] map) {
		return ArrayUtils.toString(map);
	}

	public static Step step(char[][] map) {
		char[][] out = new char[map.length][map[0].length];
		boolean change = false;

		for(int x = 0; x < map.length; x++) {
			System.arraycopy(map[x], 0, out[x], 0, map[0].length);
		}

		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y < map[0].length; y++) {
				char current = map[x][y];
				char next = map[x][(y+1) % map[0].length];
				if(current == '>') {
					if(next == '.') {
						out[x][(y+1) % map[0].length] = '>';
						out[x][y] = '.';
						change = true;
					} else {
						out[x][y] = '>';
					}
				}
			}
		}

		char[][] copy = new char[map.length][map[0].length];

		for(int x = 0; x < map.length; x++) {
			System.arraycopy(out[x], 0, copy[x], 0, map[0].length);
		}

		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y < map[0].length; y++) {
				char current = map[x][y];
				char next = copy[(x+1)%map.length][y];
				if(current == 'v') {
					if (next == '.') {
						out[(x + 1) % map.length][y] = 'v';
						out[x][y] = '.';
						change = true;
					} else {
						out[x][y] = 'v';
					}
				}
			}
		}

		return new Step(change, out);
	}
}