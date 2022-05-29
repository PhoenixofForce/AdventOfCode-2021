package aoc.y2021.d12;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day12 {

	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(12);
		Map<String, List<String>> paths = new HashMap<>();
		
		for(String s: lines) {
			String from = s.split("-")[0];
			String to = s.split("-")[1];
			
			if(!paths.containsKey(from)) paths.put(from, new ArrayList<>());
			paths.get(from).add(to);
			
			if(!paths.containsKey(to)) paths.put(to, new ArrayList<>());
			paths.get(to).add(from);
		}
		Utils.printSolution(getPaths(paths, false));
		Utils.printSolution(getPaths(paths, true));
		
	}
	
	public static int getPaths(Map<String, List<String>> paths, boolean part2) {
		String start = "start";
		return getPaths(paths, new ArrayList<>(), start, part2);
	}
	
	public static int getPaths(Map<String, List<String>> paths, List<String> takens, String currentPoint, boolean part2) {
		if(currentPoint.equals("end")) return 1;
		if((currentPoint.charAt(0) + "").matches("[a-z]")) takens.add(currentPoint);
		int out = 0;
		
		for(String points: paths.get(currentPoint)) {
			if(points.equals("start")) continue;
			
			if(!(takens.contains(points) && (containsDouble(takens) || !part2))) {
				out += getPaths(paths, takens, points, part2);
			}
		}
		
		if((currentPoint.charAt(0) + "").matches("[a-z]")) takens.remove(currentPoint);
		return out;
	}

	public static boolean containsDouble(List<String> list) {
		for(String s: list) {
			if(s.equals("start") || s.equals("end")) continue;
			int count = 0;
			for(String s2: list) if(s2.equals(s)) count ++;
			if(count == 2) return true;
		}
		return false;
	}
}