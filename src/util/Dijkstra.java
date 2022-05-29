package util;

import java.util.*;
import util.LambdaInterfaces.Neighborfinder;
import util.LambdaInterfaces.Accumulator;
import util.LambdaInterfaces.EndFinder;
import util.LambdaInterfaces.Heuristic;

public class Dijkstra {
	
	public static <T> long findPath(T start, T end, Neighborfinder<T> nf, Accumulator<T> sc) {
		return findPathWithEndAndHeuristik(start, t -> t.equals(end), nf, sc, t -> 0);
	}

	public static <T> long findPathWithEnd(T start, EndFinder<T> ef, Neighborfinder<T> nf, Accumulator<T> sc) {
		return findPathWithEndAndHeuristik(start, ef, nf, sc, t -> 0);
	}

	public static <T> long findPathWithHeuristik(T start, T end, Neighborfinder<T> nf, Accumulator<T> sc, Heuristic<T> h) {
		return findPathWithEndAndHeuristik(start, t -> t.equals(end), nf, sc, h);
	}

	public static <T> long findPathWithEndAndHeuristik(T start, EndFinder<T> ef, Neighborfinder<T> nf, Accumulator<T> sc, Heuristic<T> h) {
		Map<T, Long> distances = new HashMap<>();
		Map<T, T> pre = new HashMap<>();

		distances.put(start, 0L);

		Set<T> closed = new HashSet<>();
		PriorityQueue<T> open = new PriorityQueue<>(Comparator.comparingLong(a -> distances.containsKey(a)? distances.get(a) + h.heuristic(a): Integer.MAX_VALUE));
		distances.put(start, 0L);
		open.add(start);

		int iterations = 0;
		while(!open.isEmpty()) {
			T pos = open.remove();
			long currentScore = distances.get(pos);
			//if(iterations % 10000 == 0) System.out.println("score: " + currentScore + ", open: " + open.size() + ", closed: " + closed.size());

			if(ef.isEnd(pos)) {
				T t = pos;
				while(t != null) {
					t = pre.get(t);
				}

				return distances.get(pos);
			}

			for(T n: nf.getNeighbors(pos)) {
				long newScore = sc.accumulate(n, currentScore);

				if(!closed.contains(n) && newScore < distances.getOrDefault(n, Long.MAX_VALUE)) {
					distances.put(n, newScore);
					pre.put(n, pos);

					open.remove(n);
					open.add(n);
				}
			}

			closed.add(pos);
			iterations++;
		}

		System.err.println("[DIJKSTRA] Didnt find end point");
		return Long.MAX_VALUE;
	}
}