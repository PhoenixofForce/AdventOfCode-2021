package aoc.y2021.d03;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import aoc.Utils;
import aoc.y2021.Y2021_Utils;

public class Day3 {

	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(3);
		int[] numberOfOnes = getOneCount(lines);
		
		int gamma = 0;
		int eps = 0;
		for(int i = 0; i < numberOfOnes.length; i++) {
			gamma <<= 1;
			eps <<= 1;
			
			if(numberOfOnes[i] > lines.size()/2.0) {
				gamma |= 1;
			} else {
				eps |= 1;
			}
		}
		Utils.printSolution(gamma * eps);

		List<String> lineCOO = new ArrayList<>(lines);
		List<String> lineOO = new ArrayList<>(lines);
		
		int index = 0;
		while(lineCOO.size() > 1) {
			int inCopy = index;
			int[] numberOnes = getOneCount(lineCOO);
			int number = numberOnes[index] >= lineCOO.size() / 2.0? 1: 0;
			lineCOO = lineCOO.stream().filter(s -> s.charAt(inCopy) != (number + "").charAt(0)).collect(Collectors.toList());
			index++;
		}
		
		index = 0;
		while(lineOO.size() > 1) {
			int inCopy = index;
			int[] numberOnes = getOneCount(lineOO);
			int number = numberOnes[index] >= lineOO.size() / 2.0? 1: 0;
			lineOO = lineOO.stream().filter(s -> s.charAt(inCopy) == (number + "").charAt(0)).collect(Collectors.toList());
			index++;
		}
		  
		int coo = Integer.parseInt(lineCOO.get(0), 2);
		int oo = Integer.parseInt(lineOO.get(0), 2);
		
		Utils.printSolution(coo * oo);
		
	}
	
	public static int[] getOneCount(List<String> lines ) {
		int[] numberOfOnes = new int[lines.get(0).length()];
		
		for(String line: lines) {
			for(int i = 0; i < line.length(); i++) {
				if(line.charAt(i) == '1') numberOfOnes[i] = numberOfOnes[i] + 1;
			}
		}  
		
		return numberOfOnes;
	}
}