package aoc.y2021.d21;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day21 {

	record State(int player1Pos, int player2Pos, int player1score, int player2score, boolean playerfirstTurn) { }
	public static Map<State, long[]> results = new HashMap<>();

	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(21);
		
		int player1StartingC = Integer.parseInt(lines.remove(0).split(" ")[4]);
		int player2StartingC = Integer.parseInt(lines.remove(0).split(" ")[4]);
		
		int player1Starting = player1StartingC;
		int player2Starting = player2StartingC;
		
		int player1Score = 0;
		int player2Score = 0;
		
		int turn = 1;
		boolean player1Turn = true;
		while(player1Score < 1000 && player2Score < 1000) {
			int roll = (turn - 1) * 9 + 6;
			
			if(player1Turn) {
				player1Starting += roll;
				while(player1Starting > 10) player1Starting -= 10;
				player1Score += player1Starting;
			} else {
				player2Starting += roll;
				while(player2Starting > 10) player2Starting -= 10;
				player2Score += player2Starting;
			}
			
			turn++;
			player1Turn = !player1Turn;
		} turn--;
		
						
		Utils.printSolution(Math.min(player1Score, player2Score) * turn * 3);
		long[] allResults = playAllGames(player1StartingC, player2StartingC, 0, 0, true);
		Utils.printSolution(Math.max(allResults[0], allResults[1]));
	}
	
	public static long[] playAllGames(int player1Pos, int player2Pos, int player1score, int player2score, boolean playerfirstTurn) {
		State s = new State(player1Pos, player2Pos, player1score, player2score, playerfirstTurn);
		if(results.containsKey(s)) return results.get(s);
		
		long[] out = new long[2];
		if(player1score >= 21) {
			out[0] = 1;
			return out;
		} else if(player2score >= 21) {
			out[1] = 1;
			return out;
		}
		
		for(int roll1 = 1; roll1 <= 3; roll1++) {
			for(int roll2 = 1; roll2 <= 3; roll2++) {
				for(int roll3 = 1; roll3 <= 3; roll3++) {
					int roll = roll1 + roll2 + roll3;
					
					if(playerfirstTurn) {
						int np = player1Pos + roll;
						while(np > 10) np -= 10;
						long[] res = playAllGames(np, player2Pos, player1score + np, player2score, !playerfirstTurn);
						out[0] += res[0];
						out[1] += res[1];
					} else {
						int np = player2Pos + roll;
						while(np > 10) np -= 10;
						long[] res = playAllGames(player1Pos, np, player1score, player2score + np, !playerfirstTurn);
						out[0] += res[0];
						out[1] += res[1];
					}
				}
			}
		}
		
		results.put(s, out);
		return out;
	}
}