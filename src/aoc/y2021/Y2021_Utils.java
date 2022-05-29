package aoc.y2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.List;

import aoc.Utils;
import util.LambdaInterfaces.Translator;
import util.ArrayUtils;

public class Y2021_Utils {

	private static final int YEAR = 2021;
	
	public static String getFileName(int day) {
		return Utils.getFileName(YEAR, day);
	}
	
	public static BufferedReader getReader(int day) throws FileNotFoundException {
		return Utils.getReader(YEAR, day);
	}
	
	public static List<String> getLines(int day) {
		return Utils.getLines(YEAR, day);
	}
	
	public static List<String> getParagraphs(int day) {
		return Utils.getParagraphs(YEAR, day);
	}
	
	public static String getFile(int day) {
		return Utils.getFile(YEAR, day);
	}
	
	public static <T> List<T> translateLines(int day, Translator<T> trans) {
		return Utils.translateLines(YEAR, day, trans);
	}
	
	public static <T> List<T> translateParagraphs(int day, Translator<T> trans) {
		return Utils.translateParagraphs(YEAR, day, trans);
	}
	
	public static <T> T translateFile(int day, Translator<T> trans) {
		return Utils.translateFile(YEAR, day, trans);
	}

	public static int[][] fileAsIntArray(int day) {
		List<String> lines = Y2021_Utils.getLines(day);
		return ArrayUtils.strings2IntArray(lines);
	}

	public static char[][] fileAsCharArray(int day) {
		List<String> lines = Y2021_Utils.getLines(day);
		return ArrayUtils.strings2CharArray(lines);
	}

	public static boolean[][] fileAsBooleanArray(int day, char trueChar) {
		List<String> lines = Y2021_Utils.getLines(day);
		return ArrayUtils.strings2BooleanArray(lines, trueChar);
	}
}