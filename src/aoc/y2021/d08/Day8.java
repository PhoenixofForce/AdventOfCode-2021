package aoc.y2021.d08;

import java.util.List;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day8 {

	public static void main(String[] args) {
		List<Digit> lines = Y2021_Utils.translateLines(8, Digit::new);
	
		int out = 0;
		for(Digit d: lines) out += d.p1_get1478();
		
		Utils.printSolution(out);
		
		for(Digit d: lines) d.solve();
		
		out = 0;
		for(Digit d: lines) {
			out += d.p2();
		}
		Utils.printSolution(out);
	}
}