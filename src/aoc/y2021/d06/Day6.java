package aoc.y2021.d06;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import aoc.Utils;
import aoc.y2021.Y2021_Utils;

public class Day6 {

	public static void main(String[] args) {
		List<Integer> lines = Arrays.stream(Y2021_Utils.getFile(6).split(",")).map(Integer::parseInt).collect(Collectors.toList());
		
		int maxDays = 256 ;
		int daysToReproduce = 7;
		int cooldowndays = 2;
		
		long[] fishes = new long[daysToReproduce + cooldowndays];
		
		for(int i: lines) fishes[i]++;
		
		for(int d = 0; d < maxDays; d++) {
			if(d == 80) Utils.printSolution(Arrays.stream(fishes).sum());
			long toAdd =  fishes[0];

			System.arraycopy(fishes, 1, fishes, 0, fishes.length - 1);
			
			fishes[daysToReproduce + cooldowndays - 1] = toAdd;
			fishes[daysToReproduce - 1] += toAdd;
		}
		
		Utils.printSolution(Arrays.stream(fishes).sum());
	}
}
