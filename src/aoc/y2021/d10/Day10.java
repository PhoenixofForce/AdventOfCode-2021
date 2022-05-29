package aoc.y2021.d10;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day10 {

	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(10);
		List<String> toRemove = new ArrayList<>();
		
		int out = 0;
		
		for(String line: lines) {
			char c = readLine1(line);
			if(c == '>') out += 25137;
			if(c == '}') out += 1197;
			if(c == ']') out += 57;
			if(c == ')') out += 3;
			
			if(c != '0') toRemove.add(line);
		}
		
		Utils.printSolution(out);
		lines.removeAll(toRemove);
		List<Long> out2 = lines.stream().map(Day10::readLine2).sorted(Long::compareTo).collect(Collectors.toList());
		Utils.printSolution(out2.get(out2.size()/2));
	}
	
	public static char readLine1(String line) {
		Stack<Character> queue = new Stack<>();
		
		for(char c: line.toCharArray()) {
			if(c == '(' || c == '{' || c == '<' || c == '[') queue.push(c);
			else {
				char opening = queue.pop();
				
				if(c != getOther(opening)) {
					return c;
				}
			}
		}
		
		return '0';
	}
	
	public static long readLine2(String line) {
		Stack<Character> queue = new Stack<>();
		
		for(char c: line.toCharArray()) {
			if(c == '(' || c == '{' || c == '<' || c == '[') queue.push(c);
			else {
				queue.pop();
			}
		}
		
		long out = 0;
		while(queue.size() > 0) {
			char c = queue.pop();
			
			out *= 5;
			if(c == '(') out += 1;
			if(c == '[') out += 2;
			if(c == '{') out += 3;
			if(c == '<') out += 4;
		}
		
		return out;
	}
	
	public static char getOther(char c) {
		if(c == '(') return ')';
		if(c == '<') return '>';
		if(c == '[') return ']';
		if(c == '{') return '}';
		return '0';
	}
}