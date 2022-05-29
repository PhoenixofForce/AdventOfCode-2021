package aoc.y2021.d18;

import java.util.List;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day18 {

	public static void main(String[] args) {
		List<Snailfish> lines = Y2021_Utils.translateLines(18, Snailfish::new);
				
		long max = 0;
		for(int i = 0; i < lines.size(); i++) {
			for(int j = 0; j < lines.size(); j++) {
				if(i == j) continue;
				Snailfish first = lines.get(i).clone();
				Snailfish second = lines.get(j).clone();
				
				Snailfish s = add(first, second);
				long out = s.getSum();
				if(out > max) max = out;
			}
		}
		
		while(lines.size() > 1) {
			Snailfish first = lines.remove(0);
			Snailfish second = lines.remove(0);
			
			Snailfish s = add(first, second);
			lines.add(0, s);
		}
		
		Snailfish lastFish = lines.get(0);
		Utils.printSolution(lastFish.getSum());
		Utils.printSolution(max);
	}

	public static Snailfish add(Snailfish a, Snailfish b) {
		Snailfish s = Snailfish.add(a, b);
		boolean action = true;
		while(action) {
			action = s.explode();
			if(action) continue;
			if(s.split()) action = true;
		}
		return s;
	}
}