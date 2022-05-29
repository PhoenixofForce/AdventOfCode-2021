package aoc.y2021.d11;

import java.util.List;
import java.util.ArrayList;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;
import util.ArrayUtils;

public class Day11 {

	public static void main(String[] args) {
		int[][] octopussy = Y2021_Utils.fileAsIntArray(11);
		
		int out = 0;
		int steps = 100;
		for(int i = 0; i < steps; i++) {
			out += step(octopussy);
		}
		Utils.printSolution(out);

		octopussy = Y2021_Utils.fileAsIntArray(11);
		
		out = 1;
		while(!step2(octopussy)) out++;
		Utils.printSolution(out);
	}
	
	public static void print(int[][] octopussy) {
		System.out.println(ArrayUtils.toString(octopussy));
	}
	
	public static int step(int[][] octopussy) {
		int out = 0;
		boolean[][] hasFlashed = new boolean[octopussy.length][octopussy[0].length];
		
		for(int x = 0; x < octopussy.length; x++) {
			for(int y = 0; y < octopussy[x].length; y++) {
				octopussy[x][y] = octopussy[x][y] + 1;
			}
		}
		List<int[]> flashes = new ArrayList<>();

		boolean noFlash = false;
		while(!noFlash) {
			noFlash = true;
			
			for(int x = 0; x < octopussy.length; x++) {
				for(int y = 0; y < octopussy[x].length; y++) {
					if(octopussy[x][y] > 9 && !hasFlashed[x][y]) {
						flashes.add(new int[]{x, y});
						out += 1;
						noFlash = false;
						
						hasFlashed[x][y] = true;
						
						for(int dx = -1; dx <= 1; dx++) {
							for(int dy = -1; dy <= 1; dy++) {
								int nx = x + dx;
								int ny = y + dy;
								
								if(nx >= 0 && nx < octopussy.length && ny >= 0 && ny < octopussy[x].length) {
									octopussy[nx][ny] = octopussy[nx][ny] + 1;
								}
							}
						}
					}
				}
			}
		}
		
		for(int[] pos: flashes) {
			octopussy[pos[0]][pos[1]] = 0;
		}
		
		return out;
	}
	
	public static boolean step2(int[][] octopussy) {
		boolean[][] hasFlashed = new boolean[octopussy.length][octopussy[0].length];
		
		for(int x = 0; x < octopussy.length; x++) {
			for(int y = 0; y < octopussy[x].length; y++) {
				octopussy[x][y] = octopussy[x][y] + 1;
			}
		}
		List<int[]> flashes = new ArrayList<>();

		boolean noFlash = false;
		while(!noFlash) {
			noFlash = true;
			
			for(int x = 0; x < octopussy.length; x++) {
				for(int y = 0; y < octopussy[x].length; y++) {
					if(octopussy[x][y] > 9 && !hasFlashed[x][y]) {
						flashes.add(new int[]{x, y});
						noFlash = false;
						
						hasFlashed[x][y] = true;
						
						for(int dx = -1; dx <= 1; dx++) {
							for(int dy = -1; dy <= 1; dy++) {
								int nx = x + dx;
								int ny = y + dy;
								
								if(nx >= 0 && nx < octopussy.length && ny >= 0 && ny < octopussy[x].length) {
									octopussy[nx][ny] = octopussy[nx][ny] + 1;
								}
							}
						}
					}
				}
			}
		}
		
		for(int[] pos: flashes) {
			octopussy[pos[0]][pos[1]] = 0;
		}

		return flashes.size() == octopussy.length * octopussy[0].length;
	}
}