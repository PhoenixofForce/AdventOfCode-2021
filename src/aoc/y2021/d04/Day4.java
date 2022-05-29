package aoc.y2021.d04;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import aoc.Utils;
import aoc.y2021.Y2021_Utils;

public class Day4 {

	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getParagraphs(4);
		
		List<Integer> randomNumbers = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
		List<BingoBoard> boards = new ArrayList<>();
		
		for(int i = 1; i < lines.size(); i++) boards.add(new BingoBoard(lines.get(i)));
		
		int winner = 0;
		for(int i = 0; i < randomNumbers.size(); i++) {
			int drawn = randomNumbers.get(i);
			
			for(BingoBoard b: boards) {
				if(b.drawNumber(drawn)) {
					if(winner == 0 || winner == boards.size()-1) {
						int out = b.getUnmarkedSum() * drawn;
						Utils.printSolution(out);
					}
					winner++;
				}
			}
			
			if(winner == boards.size()) break;
		}
	}
}