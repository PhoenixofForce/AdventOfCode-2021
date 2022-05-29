package util;

import java.util.ArrayList;
import java.util.List;

import util.LambdaInterfaces.GOL_Change;
import util.LambdaInterfaces.CountIf;

public class Automata<T> {
	
	private T[][] state;
	private CountIf<T> countNeighbor;
	private GOL_Change<T> changer;
	boolean countDiagonals, countAroundBorder;
	
	public Automata(T[][] inital, CountIf<T> countNeighbor, GOL_Change<T> changer, boolean countDiagonals, boolean countAroundBorder) {
		this.state = inital;
		this.countNeighbor = countNeighbor;
		this.changer = changer;
		this.countDiagonals = countDiagonals;
		this.countAroundBorder = countAroundBorder;
	}
	
	private List<Boolean> step() {
		List<Boolean> out = new ArrayList<>();
		
		T[][] newState = (T[][]) new Object[state.length][state[0].length];
		for(int x = 0; x < state.length; x++) {
			for(int y = 0; y < state[x].length; y++) {
				
				int count = 0;
				for(int dx = -1; dx <= 1; dx++) {
					for(int dy = -1; dy <= 1; dy++) {
						if(countAroundBorder || (x + dx < state.length && x + dx >= 0 && y + dy >= 0 && y + dy < state[x].length)) {
							if((dx == 0 && dy == 0) || (!countDiagonals && dx != 0 && dy != 0)) continue;
							
							int nx = x + dx;
							nx %= state.length;
							while(nx < 0) nx += state.length;
							
							int ny = y + dy;
							ny %= state[x].length;
							while(ny < 0) ny += state[x].length;
							
							T t = state[nx][ny];
							if(countNeighbor.countIf(t)) {
								count++;
							}
						}
					}
				}
				
				newState[x][y] = changer.change(state[x][y], count);
				if(!newState[x][y].equals(state[x][y])) out.add(true);
				else out.add(false);
			}
		}
		state = newState;
		return out;
	}
	
	public void steps(int s) {
		for(int i = 0; i < s; i++) step();
	}
	
	public long stepsWhileChange() {
		long out = 0;
		boolean change = true;
		while(change) {
			change = step().stream().anyMatch(e -> e);
			out++;
		}
		return out;
	}
	
	public long stepsUntilAllChange() {
		long out = 0;
		boolean change = true;
		while(change) {
			change = !step().stream().allMatch(e -> e);
			out++;
		}
		return out;
	}
	
	public long count() {
		return count((a, b) -> b + 1);
	}
	
	public long count(LambdaInterfaces.Accumulator<T> a) {
		long out = 0;

		for (T[] ts : state) {
			for (T t : ts) {
				if (countNeighbor.countIf(t)) {
					out = a.accumulate(t, out);
				}
			}
		}
		
		return out;
	}
	
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		
		for(T[] ta: state) {
			for(T t: ta) {
				out.append(t.toString());
			}
			out.append("\r\n");
		}
		
		return out.toString();
	}
	
	public String toString(LambdaInterfaces.StringMap<T> sm) {
		StringBuilder out = new StringBuilder();
		
		for(T[] ta: state) {
			for(T t: ta) {
				out.append(sm.toString(t));
			}
			out.append("\r\n");
		}
		
		return out.toString();
	}
}