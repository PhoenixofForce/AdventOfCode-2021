package aoc.y2021.d14;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day14 {

	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(14);
		String start = lines.remove(0); lines.remove(0);
		
		Map<String, String> replacements = lines.stream().map(e -> e.split(" -> ")).collect(Collectors.toMap(e->e[0], e->e[1]));
		Map<String, BigInteger> pairCountMap = new HashMap<>();
		
		for(int i = 0; i < start.length() - 1; i++) {
			String s = start.substring(i, i + 2);
			pairCountMap.put(s, pairCountMap.getOrDefault(s, new BigInteger("0")).add(new BigInteger("1")));
		}
		
		int steps = 40;
		for(int i = 0; i < steps; i++) {
			pairCountMap = step(replacements, pairCountMap);
		}
		
		BigInteger[] result1 = findLeastAndMostChar(pairCountMap);
		Utils.printSolution(result1[1].subtract(result1[0]).divide(new BigInteger("2")));
	}
	
	public static BigInteger[] findLeastAndMostChar(Map<String, BigInteger> pairCount) {
		Map<Character, BigInteger> map = new HashMap<>();
		for(String k: pairCount.keySet()) {
			map.put(k.charAt(0), pairCount.get(k).add(map.getOrDefault(k.charAt(0), new BigInteger("0"))));
			map.put(k.charAt(1), pairCount.get(k).add(map.getOrDefault(k.charAt(1), new BigInteger("0"))));
		}

		
		BigInteger least = new BigInteger("" + Long.MAX_VALUE);
		BigInteger most = new BigInteger("-1");
		
		for(char c: map.keySet()) {
			BigInteger n = map.get(c);
			if(n.compareTo(least) < 0) least = n;
			if(n.compareTo(most) > 0) most = n;
		}
		
		return new BigInteger[]{least, most};
	}

	public static Map<String, BigInteger> step(Map<String, String> replacements, Map<String, BigInteger> pairCountMap){
		Map<String, BigInteger> newCountMap = new HashMap<>();
		
		for(String k: pairCountMap.keySet()) {
			String replacement = replacements.get(k);
			String s = k.charAt(0) + replacement;
			newCountMap.put(s, pairCountMap.get(k).add(newCountMap.getOrDefault(s, new BigInteger("0"))));
			s = replacement + k.charAt(1);
			newCountMap.put(s, pairCountMap.get(k).add(newCountMap.getOrDefault(s, new BigInteger("0"))));
		}
		
		return newCountMap;
	}
}
