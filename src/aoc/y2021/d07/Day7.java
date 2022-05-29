package aoc.y2021.d07;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day7 {

	public static void main(String[] args) {
		List<Integer> lines = Arrays.stream(Y2021_Utils.getFile(7).split(",")).map(Integer::parseInt).sorted(Integer::compare).collect(Collectors.toList());
		
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		for(int i = lines.get(0); i < lines.get(lines.size() - 1); i++) {
			int sum1 = 0;
			int sum2 = 0;
			
			for(int j = 0; j < lines.size(); j++) {
				int diff = (Math.abs(i - lines.get(j)));
				sum1 += diff;
				sum2 += sumAll(diff);
			}
			
			if(sum1 < min1) min1 = sum1;
			if(sum2 < min2) min2 = sum2;
		}
		
		Utils.printSolution(min1);
		Utils.printSolution(min2);
	}
	
	public static int sumAll(int n) {
		return (n * (n+1)) / 2;
	}

}
