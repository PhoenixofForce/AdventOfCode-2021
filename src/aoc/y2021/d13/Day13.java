package aoc.y2021.d13;

import java.util.List;
import java.util.ArrayList;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;
import util.ArrayUtils;

public class Day13 {

	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(13);
		boolean[][] paper = toArray(lines);
			
		boolean out = false;
		for(String line: lines) {
			String direction = line.split(" ")[2].split("=")[0];
			int val = Integer.parseInt(line.split(" ")[2].split("=")[1]);
			
			if(direction.charAt(0) == 'y') paper = foldAtY(paper, val);
			if(direction.charAt(0) == 'x') paper = foldAtX(paper, val);
			
			if(!out) {
				Utils.printSolution(count(paper));
				out = true;
			}
		}
		
		Utils.printSolution("");
		for(boolean[] b: paper) {
			for (boolean b1 : b) System.out.print(b1 ? '#' : '.');
			System.out.println();
		}
	}
	
	public static boolean[][] toArray(List<String> lines) {
		List<int[]> pos = new ArrayList<>();
		
		int maxX = 0;
		int maxY = 0;
		
		int index = 0; 
		while(lines.get(index).length() > 0) {
			String line = lines.remove(0);
			String[] parts = line.split(",");
			int x = Integer.parseInt(parts[0]);
			int y = Integer.parseInt(parts[1]);
			
			pos.add(new int[]{x, y});
			if(x > maxX) maxX = x;
			if(y > maxY) maxY = y;
		}
		lines.remove(0);
	
		boolean[][] out = new boolean[maxY+1][maxX+1];
		for(int[] p: pos) out[p[1]][p[0]] = true;
		return out;
	}
	
	public static boolean[][] foldAtY(boolean[][] in, int yFold) {
		boolean[][] out = new boolean[yFold][in[0].length];
		for(int y = 0; y < in.length; y++) {
			for(int x = 0; x < in[0].length; x++) {
				if(y < yFold) {
					out[y][x] = in[y][x];
				}
				else {
					if(in[y][x]) out[in.length - y -1][x] = in[y][x];
				}
			}
		}
		return out;
	}

	public static boolean[][] foldAtX(boolean[][] in, int xFold) {
		boolean[][] out = new boolean[in.length][xFold];
		for(int x = 0; x < in[0].length; x++) {
			for(int y = 0; y < in.length; y++) {
				if(x < xFold) {
					out[y][x] = in[y][x];
				}
				else {
					if(in[y][x]) out[y][in[0].length - x -1] = in[y][x];
				}
			}
		}
		return out;
	}
	
	public static int count(boolean[][] in) {
		return ArrayUtils.countIf(in);
	}
}