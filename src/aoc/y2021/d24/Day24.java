package aoc.y2021.d24;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day24 {

	record State(int i, int wi, int z) {}
	
	static Set<State> states = new HashSet<>();
	
	public static void main(String[] args) {
		List<String> lines = Y2021_Utils.getLines(24);
		List<List<Integer>> snippets = getSnippets(lines);

		part1(snippets);
		states = new HashSet<>();
		part2(snippets);
	}

	public static void part1(List<List<Integer>> snippets) {
		outer:
		for(int w0 = 9; w0 >= 1; w0--) {
			int z0  = run(snippets.get(0), w0, 0);
			{
				State s = new State(0, w0, z0);
				if(states.contains(s)) continue;
				states.add(s);
			}
			for(int w1 = 9; w1 >= 1; w1--) {
				int z1  = run(snippets.get(1), w1, z0);
				{
					State s = new State(1, w1, z1);
					if(states.contains(s)) continue;
					states.add(s);
				}
				for(int w2 = 9; w2 >= 1; w2--) {
					int z2  = run(snippets.get(2), w2, z1);
					{
						State s = new State(2, w2, z2);
						if(states.contains(s)) continue;
						states.add(s);
					}
					for(int w3 = 9; w3 >= 1; w3--) {
						int z3  = run(snippets.get(3), w3, z2);
						{
							State s = new State(3, w3, z3);
							if(states.contains(s)) continue;
							states.add(s);
						}
						for(int w4 = 9; w4 >= 1; w4--) {
							int z4  = run(snippets.get(4), w4, z3);
							{
								State s = new State(4, w4, z4);
								if(states.contains(s)) continue;
								states.add(s);
							}
							for(int w5 = 9; w5 >= 1; w5--) {
								int z5  = run(snippets.get(5), w5, z4);
								{
									State s = new State(5, w5, z5);
									if(states.contains(s)) continue;
									states.add(s);
								}
								for(int w6 = 9; w6 >= 1; w6--) {
									int z6  = run(snippets.get(6), w6, z5);
									{
										State s = new State(6, w6, z6);
										if(states.contains(s)) continue;
										states.add(s);
									}
									for(int w7 = 9; w7 >= 1; w7--) {
										int z7  = run(snippets.get(7), w7, z6);
										{
											State s = new State(7, w7, z7);
											if(states.contains(s)) continue;
											states.add(s);
										}
										for(int w8 = 9; w8 >= 1; w8--) {
											int z8  = run(snippets.get(8), w8, z7);
											{
												State s = new State(8, w8, z8);
												if(states.contains(s)) continue;
												states.add(s);
											}
											for(int w9 = 9; w9 >= 1; w9--) {
												int z9  = run(snippets.get(9), w9, z8);
												{
													State s = new State(9, w9, z9);
													if(states.contains(s)) continue;
													states.add(s);
												}
												for(int w10 = 9; w10 >= 1; w10--) {
													int z10  = run(snippets.get(10), w10, z9);
													{
														State s = new State(10, w10, z10);
														if(states.contains(s)) continue;
														states.add(s);
													}
													for(int w11 = 9; w11 >= 1; w11--) {
														int z11  = run(snippets.get(11), w11, z10);
														{
															State s = new State(11, w11, z11);
															if(states.contains(s)) continue;
															states.add(s);
														}
														for(int w12 = 9; w12 >= 1; w12--) {
															int z12  = run(snippets.get(12), w12, z11);
															{
																State s = new State(12, w12, z12);
																if(states.contains(s)) continue;
																states.add(s);
															}
															for(int w13 = 9; w13 >= 1; w13--) {
																int z13  = run(snippets.get(13), w13, z12);
																if(z13 == 0) {
																	Utils.printSolution(w0 + "" + w1 + "" + w2 + "" + w3 + "" + w4 + "" + w5 + "" + w6 + "" + w7 + "" + w8 + "" + w9 + "" + w10 + "" + w11 + "" + w12 + "" + w13);
																	break outer;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static void part2(List<List<Integer>> snippets) {
		outer:
			for(int w0 = 1; w0 <= 9; w0++) {
				int z0  = run(snippets.get(0), w0, 0);
				{
					State s = new State(0, w0, z0);
					if(states.contains(s)) continue;
					states.add(s);
				}
				for(int w1 = 1; w1 <= 9; w1++) {
					int z1  = run(snippets.get(1), w1, z0);
					{
						State s = new State(1, w1, z1);
						if(states.contains(s)) continue;
						states.add(s);
					}
					for(int w2 = 1; w2 <= 9; w2++) {
						int z2  = run(snippets.get(2), w2, z1);
						{
							State s = new State(2, w2, z2);
							if(states.contains(s)) continue;
							states.add(s);
						}
						for(int w3 = 1; w3 <= 9; w3++) {
							int z3  = run(snippets.get(3), w3, z2);
							{
								State s = new State(3, w3, z3);
								if(states.contains(s)) continue;
								states.add(s);
							}
							for(int w4 = 1; w4 <= 9; w4++) {
								int z4  = run(snippets.get(4), w4, z3);
								{
									State s = new State(4, w4, z4);
									if(states.contains(s)) continue;
									states.add(s);
								}
								for(int w5 = 1; w5 <= 9; w5++) {
									int z5  = run(snippets.get(5), w5, z4);
									{
										State s = new State(5, w5, z5);
										if(states.contains(s)) continue;
										states.add(s);
									}
									for(int w6 = 1; w6 <= 9; w6++) {
										int z6  = run(snippets.get(6), w6, z5);
										{
											State s = new State(6, w6, z6);
											if(states.contains(s)) continue;
											states.add(s);
										}
										for(int w7 = 1; w7 <= 9; w7++) {
											int z7  = run(snippets.get(7), w7, z6);
											{
												State s = new State(7, w7, z7);
												if(states.contains(s)) continue;
												states.add(s);
											}
											for(int w8 = 1; w8 <= 9; w8++) {
												int z8  = run(snippets.get(8), w8, z7);
												{
													State s = new State(8, w8, z8);
													if(states.contains(s)) continue;
													states.add(s);
												}
												for(int w9 = 1; w9 <= 9; w9++) {
													int z9  = run(snippets.get(9), w9, z8);
													{
														State s = new State(9, w9, z9);
														if(states.contains(s)) continue;
														states.add(s);
													}
													for(int w10 = 1; w10 <= 9; w10++) {
														int z10  = run(snippets.get(10), w10, z9);
														{
															State s = new State(10, w10, z10);
															if(states.contains(s)) continue;
															states.add(s);
														}
														for(int w11 = 1; w11 <= 9; w11++) {
															int z11  = run(snippets.get(11), w11, z10);
															{
																State s = new State(11, w11, z11);
																if(states.contains(s)) continue;
																states.add(s);
															}
															for(int w12 = 1; w12 <= 9; w12++) {
																int z12  = run(snippets.get(12), w12, z11);
																{
																	State s = new State(12, w12, z12);
																	if(states.contains(s)) continue;
																	states.add(s);
																}
																for(int w13 = 1; w13 <= 9; w13++) {
																	int z13  = run(snippets.get(13), w13, z12);
																	if(z13 == 0) {
																		Utils.printSolution(w0 + "" + w1 + "" + w2 + "" + w3 + "" + w4 + "" + w5 + "" + w6 + "" + w7 + "" + w8 + "" + w9 + "" + w10 + "" + w11 + "" + w12 + "" + w13);
																		break outer;
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
	}
	
	public static List<List<Integer>> getSnippets(List<String> lines) {
		List<List<Integer>> snippets = new ArrayList<>();
		
		for(int i = 0; i < 14; i++) {
			List<Integer> current = new ArrayList<>();
			int startIndex = i * 18;
			current.add(Integer.parseInt(lines.get(startIndex + 4).split(" ")[2]));
			current.add(Integer.parseInt(lines.get(startIndex + 5).split(" ")[2]));
			current.add(Integer.parseInt(lines.get(startIndex + 15).split(" ")[2]));
			
			snippets.add(current);
		}


		return snippets;
	}

	public static int run(List<Integer> values, int w, int z) {
		int x = (z % 26) + values.get(1) == w? 0: 1;
		z /= values.get(0);
		z *= 25 * x + 1;
		z += (w + values.get(2)) * x;
		
		return z;
	}
}