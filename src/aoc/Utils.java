package aoc;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import util.FileUtils;
import util.LambdaInterfaces;

public class Utils {
	
	private static long start;
	private static long firstFinish = 0L;
	
	public static String getFileName(int year, int day) {
		start = System.currentTimeMillis();
		String dayStr = (day+"").length() == 1? "0" + day: day+"";
		
		System.out.printf("%s.12.%d\r\n", dayStr, year);
		return String.format("%dinputs/Day%s_Input", year, dayStr);
	}
	
	public static BufferedReader getReader(int year, int day) throws FileNotFoundException {
		File f = new File(getFileName(year, day));
		if(!f.exists() || f.length() == 0) {
			String s = getInputFromSite(year, day);
			FileUtils.write2file(f, s);
		}
		
		start = System.currentTimeMillis();
		return new BufferedReader(new FileReader(f));
	}
	
	public static String getFile(int year, int day) {
		String out = "";
		try {
			out =  FileUtils.getFile(getReader(year, day));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		start = System.currentTimeMillis();
		return out;
	}
	
	public static List<String> getLines(int year, int day) {
		List<String> out = new ArrayList<>();
		try {
			out = FileUtils.getLines(getReader(year, day));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		start = System.currentTimeMillis();
		return out;
	}
	
	
	public static List<String> getParagraphs(int year, int day) {
		List<String> out = new ArrayList<>();
		try {
			out = FileUtils.getParagraphs(getReader(year, day));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		start = System.currentTimeMillis();
		return out;
	}
	
	public static <T> List<T> translateLines(int year, int day, LambdaInterfaces.Translator<T> trans) {
		return getLines(year, day).stream().map(trans::translate).collect(Collectors.toList());
	}
	
	public static <T> List<T> translateParagraphs(int year, int day, LambdaInterfaces.Translator<T> trans) {
		return getParagraphs(year, day).stream().map(trans::translate).collect(Collectors.toList());
	}
	
	public static <T> T translateFile(int year, int day, LambdaInterfaces.Translator<T> trans) {
		return trans.translate(getFile(year, day));
	}
	
	public static void printSolution(Object o) {
		if(firstFinish == 0L) {
			System.out.println("Solution 1 (" + (System.currentTimeMillis() - start) + "ms, total: " + (System.currentTimeMillis() - start) + "ms)");
			firstFinish = System.currentTimeMillis();
		} else {
			System.out.println("Solution 2 (" + (System.currentTimeMillis() - firstFinish) + "ms, total: " + (System.currentTimeMillis() - start) + "ms)");
		}
		
		String out = o.toString();
		System.out.print(out);
		copy2clipboard(out);
	}
	
	public static void printSolution(String s, Object... o) {
		printSolution(String.format(s, o));
	}
	
	private static void copy2clipboard(String theString) {
		StringSelection selection = new StringSelection(theString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}
	
	
	 private static String getInputFromSite(int year, int day) {
		System.out.println("Fetching Input from Site...");
		File sessionFile = new File(".session");
		if(!sessionFile.exists()) {
			System.err.println("SESSION FILE DOES NOT EXIST");
			return "";
		}
		String sessionID = FileUtils.getFile(sessionFile);
		if(sessionID.length() == 0) {
			System.err.println("SESSION ID INVALID");
			return "";
		}
		
		StringBuilder s = new StringBuilder();
		
		try {
			URL u = new URL("https://adventofcode.com/" + year + "/day/" + day + "/input");
			HttpsURLConnection c = (HttpsURLConnection) u.openConnection();
			
			c.setRequestMethod("POST");
			c.setConnectTimeout(5000);
			c.setReadTimeout(5000);
			
			c.setRequestProperty("Cookie", "session=" + sessionID);
			
			int status = c.getResponseCode();
			BufferedReader r;
			if(status >= 300) {
				r = new BufferedReader(new InputStreamReader(c.getErrorStream()));
				String line = r.readLine();
				while(line != null) {
					System.err.print(line);
					line = r.readLine();
				}
			} else {
				r = new BufferedReader(new InputStreamReader(c.getInputStream()));
				String line = r.readLine();
				while(line != null) {
					s.append(line).append("\r\n");
					line = r.readLine();
				}
			}

			r.close();
			c.disconnect();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return s.toString();
	}
}