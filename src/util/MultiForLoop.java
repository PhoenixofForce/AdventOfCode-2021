package util;

import java.util.Arrays;

public abstract class MultiForLoop {

	public static void main(String[] args) {
		MultiForLoop m = new MultiForLoop(5, 20) {
			public void step(int[] state){
				System.out.println(Arrays.toString(state));
			}
			
			@Override
			public boolean skipThisNumber(int[] state) { 
				return state[0] == 4; 
			}
		};
		m.run();
	}
	
	private int[] state;
	private int[] startValues;
	private ForCheck[] forChecks;
	private Incrementer[] incrementer;
	
	public MultiForLoop(int length, int maxNumber) {
		state = new int[length];
		startValues = new int[length];
		incrementer = new Incrementer[length];
		forChecks = new ForCheck[length];
		
		for(int i = 0; i < length; i++) {
			incrementer[i] = new ConstantNumberIncrementer();
			forChecks[i] = new MaxNumberForCheck(maxNumber);
		}
	}
	
	public MultiForLoop(int length, int maxNumber, int stepSize) {
		state = new int[length];
		startValues = new int[length];
		incrementer = new Incrementer[length];
		forChecks = new ForCheck[length];
		
		for(int i = 0; i < length; i++) {
			incrementer[i] = new ConstantNumberIncrementer(stepSize);
			forChecks[i] = new MaxNumberForCheck(maxNumber);
		}
	}
	
	public MultiForLoop(ForCheck[] forChecks, Incrementer[] incrementers) {
		this(new int[forChecks.length], forChecks, incrementers);
	}
	
	public MultiForLoop(int[] state, ForCheck[] forChecks, Incrementer[] incrementers) {
		this.state = state;
		this.forChecks = forChecks;
		this.incrementer = incrementers;
		
		this.startValues = new int[state.length];

		System.arraycopy(state, 0, startValues, 0, state.length);
	}
	
	public MultiForLoop(int[] state, ForCheck forCheck, Incrementer incre) {
		this.state = state;
		this.startValues = new int[state.length];
		incrementer = new Incrementer[state.length];
		forChecks = new ForCheck[state.length];
		
		for(int i = 0; i < state.length; i++) {
			startValues[i] = state[i];
			incrementer[i] = incre;
			forChecks[i] = forCheck;
		}
	}
	
	public boolean skipThisNumber(int[] state) { return false; }
	public abstract void step(int[] state);
	
	public void run() {
		boolean stop = false;
		while(!stop) {
			if(!skipThisNumber(state)) step(state);
			stop = increase();
		}
	}
	
	private boolean increase() {
		boolean next = true;
		int index = state.length-1;
				
		while(next && index >= 0) {			
			next = false;
			state[index] = incrementer[index].increment(state[index]);
			if(!forChecks[index].check(state[index])) {
				state[index] = startValues[index];
				next = true;
			}
			
			index--;
		}
		
		return next && index < 0;
	}
	
	
	public interface ForCheck {
		boolean check(int i);
	}
	
	public static class MaxNumberForCheck implements ForCheck {
		private int maxNumber;
		public MaxNumberForCheck(int maxNumber) {
			this.maxNumber = maxNumber;
		}
		
		@Override
		public boolean check(int i) {
			return i < maxNumber;
		}
		
	}
	
	public interface Incrementer {
		int increment(int i);
	}
	
	public static class ConstantNumberIncrementer implements Incrementer {

		private int incrementor;
		
		public ConstantNumberIncrementer() {
			this(1);
		}
		
		public ConstantNumberIncrementer(int incrementor) {
			this.incrementor = incrementor;
		}
		
		@Override
		public int increment(int i) {
			return i + incrementor;
		}
		
	}
}