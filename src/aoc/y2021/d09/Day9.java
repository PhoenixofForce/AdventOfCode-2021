package aoc.y2021.d09;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day9 {

	public static void main(String[] args) {
		int[][] heightmap = Y2021_Utils.fileAsIntArray(9);
		List<int[]> lowPoints = new ArrayList<>();

		int out = 0;
		
		for(int x = 0; x < heightmap.length; x++) {
			for(int y = 0; y < heightmap[x].length; y++) {
				int heightHere = heightmap[x][y];
				
				int heightAbove = 9;
				int heightLeft = 9;
				int heightRight = 9;
				int heightBelow = 9;
				
				if(x > 0) heightLeft = heightmap[x-1][y];
				if(x < heightmap.length-1) heightRight= heightmap[x+1][y];
				if(y > 0) heightAbove = heightmap[x][y-1];
				if(y < heightmap[x].length-1) heightBelow = heightmap[x][y+1];
				
				
				if(heightHere < heightAbove && heightHere < heightBelow && heightHere < heightLeft && heightHere < heightRight) {
					lowPoints.add(new int[]{x, y});
					out += heightHere+1;
				}
			}
		}
		
		Utils.printSolution(out);
		
		List<List<int[]>> basinList = new ArrayList<>();
		List<int[]> allBasinContent = new ArrayList<>();
		
		for(int[] pos: lowPoints) {
			if(contains(allBasinContent, pos)) continue;
			
			List<int[]> basinHere = findBasin(heightmap, pos[0], pos[1]);
			allBasinContent.addAll(basinHere);
			basinList.add(basinHere);
		}
		
		basinList = basinList.stream().sorted(Comparator.comparingInt(List::size)).collect(Collectors.toList());
		out = basinList.get(basinList.size()-1).size() * basinList.get(basinList.size()-2).size() * basinList.get(basinList.size()-3).size();
		Utils.printSolution(out);
	}
	
	public static List<int[]> findBasin(int[][] heightmap, int x, int y) {
		if(heightmap[x][y] == 9) return new ArrayList<>();
		Stack<int[]> stack = new Stack<>();
		List<int[]> out = new ArrayList<>();
		
		stack.add(new int[]{x, y});
		while(!stack.isEmpty()) {
			int[] pos = stack.pop();
			out.add(pos);
			
			if(pos[0] > 0) {
				int[] newPos = new int[]{pos[0]-1, pos[1]};
				if(heightmap[newPos[0]][newPos[1]] != 9) {
					if(!(contains(stack, newPos) || contains(out, newPos))) {
						stack.push(newPos);
					}
				}
			}
			if(pos[0] < heightmap.length-1) {
				int[] newPos = new int[]{pos[0]+1, pos[1]};
				if(heightmap[newPos[0]][newPos[1]] != 9) {
					if(!(contains(stack, newPos) || contains(out, newPos))) {
						stack.push(newPos);
					}
				}
			}
			if(pos[1] > 0)  {
				int[] newPos = new int[]{pos[0], pos[1]-1};
				if(heightmap[newPos[0]][newPos[1]] != 9) {
					if(!(contains(stack, newPos) || contains(out, newPos))) {
						stack.push(newPos);
					}
				}
			}
			if(pos[1] < heightmap[pos[0]].length-1) {
				int[] newPos = new int[]{pos[0], pos[1]+1};
				if(heightmap[newPos[0]][newPos[1]] != 9) {
					if(!(contains(stack, newPos) || contains(out, newPos))) {
						stack.push(newPos);
					}
				}
			}
		}
		
		return out;
	}
	
	public static boolean contains(List<int[]> list, int[] element) {
		return list.stream().anyMatch(e-> e[0] == element[0] && e[1] == element[1]);
	}
}