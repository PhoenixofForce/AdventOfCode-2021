package aoc.y2021.d23;

import java.util.List;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;
import util.ArrayUtils;
import util.Dijkstra;

public class Day23 {
	
	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(23);

		boolean part2 = true;
		if(part2) {
			lines.add(3, "  #D#B#A#C#");
			lines.add(3, "  #D#C#B#A#");
		}

		char[][] map = new char[lines.size()][lines.get(0).length()];
		for(int i = 0; i < lines.size(); i++) {
			String s = lines.get(i);

			for(int j = 0; j < lines.get(0).length(); j++) {
				if(j >= s.length()) map[i][j] = ' ';
				else map[i][j] = lines.get(i).charAt(j);
			}
		}

		System.out.println(ArrayUtils.toString(map));

		State start = new State(map, 0);
		long out = Dijkstra.findPathWithEndAndHeuristik(start, State::isEnd, State::getStates, (a, b) -> a.getScore(), State::getHeuristik);
		Utils.printSolution(out);
	}

}
