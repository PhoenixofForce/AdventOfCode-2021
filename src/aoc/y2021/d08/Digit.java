package aoc.y2021.d08;

import java.util.Arrays;

public class Digit {

	//0 1  2 3 4  5 6 7  8  9     1 7 4 2?3?5? 0?6?9? 8
	//6 2! 5 5 4! 5 6 3! 7! 6  => 2 3 4 5 5 5  6 6 6  7
	public static String[] DEFAULT_NUMBERS = {"abcefg", "cf", "acdeg", "bcdf", "abdfg", "acf", "abcdfg", "abcdfg"};
	
	private String[] watchedNumber;
	private String[] outNumbers;
	
	private String[] translations;
	
	public Digit(String line) {
		String[] parts = line.split("\\|");
		watchedNumber = parts[0].trim().split(" ");
		outNumbers = parts[1].trim().split(" ");
		Arrays.sort(watchedNumber, (a, b) -> Integer.compare(a.length(), b.length()));
	}
	
	public int p1_get1478() {
		int out = 0;
		
		for(String s: outNumbers) {
			if(s.length() == 2 || s.length() == 4 || s.length() == 7 || s.length() == 3) out++;
		}
		return out;
	}
	
	public void solve() {
		String com1 = watchedNumber[0];
		String com4 = watchedNumber[2];
		String com7 = watchedNumber[1];
		String com8 = watchedNumber[9];

		String mapCF = com1;
		String mapAEG = cut(com8, com4);
		String mapEG = cut(mapAEG, com7);
		
		String com2 = null;
		for(int i = 3; i < 6; i++) {
			String s = watchedNumber[i];
			if(contains(s, mapEG)) com2 = s;
		}
		
		String mapBF = cut(com8, com2);
		
		String com5 = null;
		for(int i = 3; i < 6; i++) {
			String s = watchedNumber[i];
			if(contains(s, mapBF)) com5 = s;
		}
		
		String com3 = null;
		for(int i = 3; i < 6; i++) {
			String s = watchedNumber[i];
			if(!s.equals(com2) && !s.equals(com5)) com3 = s;
		}
		
		String com0 = null;
		for(int i = 6; i < 9; i++) {
			String s = watchedNumber[i];
			if(contains(s, mapEG) && contains(s, mapCF)) com0 = s;
		}
		
		String com9 = null;
		for(int i = 6; i < 9; i++) {
			String s = watchedNumber[i];
			if(!com0.equals(s) && contains(s, mapCF)) com9 = s;
		}


		String com6 = null;
		for(int i = 6; i < 9; i++) {
			String s = watchedNumber[i];
			if(!com0.equals(s) && !com9.equals(s)) com6 = s;
		}
		
		translations = new String[]{com0, com1, com2, com3, com4, com5, com6, com7, com8, com9};
	}
	
	public int p2() {
		String out = "0";
		for(int j = 0; j < outNumbers.length; j++) {
			String s = outNumbers[j];
			
			for(int i = 0; i < translations.length; i++) {
				if(equals(s, translations[i])) out += "" + i;
			}
		}
		
		return Integer.parseInt(out);
	}
	
	private boolean equals(String a, String b) {
		return contains(a, b) && a.length() == b.length();
	}
	
	private boolean contains(String a, String b) {
		String intersection = intersect(a, b);
		return  intersection.length() == b.length();
	}
	
	private String intersect(String a, String b) {
		String out = "";
		
		for(char c: a.toCharArray()) {
			if(b.contains(c + "")) out += c;
		}
		
		return out;
	}
	
	private String cut(String a, String b) {
		String out = "";
		
		for(char c: a.toCharArray()) {
			if(!b.contains(c + "")) out += c;
		}
		
		return out;
	}
}
