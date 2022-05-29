package aoc.y2021.d04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BingoBoard {
	
	public static final int BINGO_SIZE = 5;
	
	private int[][] numbers;
	private boolean[][] wasNumberChoosen;
	
	private boolean won = false;
	
	public BingoBoard(String line) {
		numbers = new int[BINGO_SIZE][BINGO_SIZE];
		wasNumberChoosen = new boolean[BINGO_SIZE][BINGO_SIZE];
		
		line = line.replaceAll(" +", " ");
		List<Integer> boardNumbers = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
		
		for(int i = 0; i < boardNumbers.size(); i++) {
			numbers[i/BINGO_SIZE][i % BINGO_SIZE] = boardNumbers.get(i);
		}
	}
	
	public boolean drawNumber(int num) {
		if(won) return false;
		
		for(int x = 0; x < numbers.length; x++) {
			for(int y = 0; y < numbers.length; y++) {
				if(numbers[x][y] == num) wasNumberChoosen[x][y] = true; 
			}
		}
		
		return checkWin();
	}

	public boolean checkWin() {
		boolean out = false;
		
		for(int x = 0; x < numbers.length; x++) {
			boolean allFive1 = true;
			boolean allFive2 = true;
						
			for(int y = 0; y < numbers.length; y++) {
				if(!wasNumberChoosen[x][y]) allFive1 = false;
				if(!wasNumberChoosen[y][x]) allFive2 = false;
			}
			if(allFive1 || allFive2) out = true;
		}
		
		if(out) won = true;
		return out;
	}
	
	public int getUnmarkedSum( ) {
		int out = 0;
		
		for(int x = 0; x < numbers.length; x++) {
			for(int y = 0; y < numbers.length; y++) {
				if(!wasNumberChoosen[x][y]) out += numbers[x][y];
			}
		}
		
		return out;
	}
}