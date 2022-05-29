package util;

import java.util.Collection;

public class LambdaInterfaces {

	public interface CountIf<T> {
		boolean countIf(T t);
	}

	public interface GOL_Change<T> {
		T change(T t, int count);
	}

	public interface Accumulator<T> {
		long accumulate(T t, long sum);
	}

	public interface Translator<T> {
		T translate(String s);
	}

	public interface StringMap<T> {
		String toString(T t);
	}

	public interface Neighborfinder<T> {
		Collection<T> getNeighbors(T t);
	}

	public interface Heuristic<T> {
		int heuristic(T t);
	}

	public interface EndFinder<T> {
		boolean isEnd(T t);
	}

	public interface CoordinateParser<T> {
		T parse(int x, int y);
	}

	public interface Cloner<T> {
		T clone(T t);
	}
}