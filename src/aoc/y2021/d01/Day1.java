package aoc.y2021.d01;

import java.util.List;
import java.util.ArrayList;

import aoc.Utils;
import aoc.y2021.Y2021_Utils;

public class Day1 {

	public static void main(String[] args) {
		List<Integer> depths = Y2021_Utils.translateLines(1, Integer::parseInt);
		List<Integer> changes = new ArrayList<>();
		
		for(int i = 1; i < depths.size(); i++) {
			int pre = depths.get(i-1);
			int cur = depths.get(i);
			
			changes.add((int) Math.signum(cur-pre));
		}
		Utils.printSolution((int) changes.stream().filter(i -> i > 0).count());

		changes = new ArrayList<>();
		for(int i = 2; i < depths.size(); i++) {
			if(i + 1 >= depths.size()) break;
			
			int pre = depths.get(i-2) + depths.get(i-1) + depths.get(i);
			int cur = depths.get(i-1) + depths.get(i) + depths.get(i+1);
			
			changes.add((int) Math.signum(cur-pre));
		}
		Utils.printSolution((int) changes.stream().filter(i -> i > 0).count());
	}
}