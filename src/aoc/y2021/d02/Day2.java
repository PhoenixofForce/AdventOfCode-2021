package aoc.y2021.d02;

import java.util.List;

import aoc.Utils;
import aoc.y2021.Y2021_Utils;

public class Day2 {

	public static void main(String[] args) {
		List<String[]> lines = Y2021_Utils.translateLines(2, l -> l.split(" "));
		
		int x = 0;
		int z = 0;
		
		for(String[] line: lines) {
			String dir = line[0];
			int length = Integer.parseInt(line[1]);
			
			if(dir.equalsIgnoreCase("down")) z += length;
			else if(dir.equalsIgnoreCase("up")) z -= length;
			else x+=length;
		}
		Utils.printSolution(x * z);
		
		x = 0;
		z = 0;
		int aim = 0;
		
		for(String[] line: lines) {
			String dir = line[0];
			int length = Integer.parseInt(line[1]);
			
			if(dir.equalsIgnoreCase("down")) aim += length;
			else if(dir.equalsIgnoreCase("up")) aim -= length;
			else {
				x += length;
				z += aim * length;
			}
		}
		Utils.printSolution(x * z);	
	}
}