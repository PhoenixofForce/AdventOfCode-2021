package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	//>--| FILES >--<\\
	
	public static String getFile(String fileName) {
		return getFile(new File(fileName));
	}
	
	public static String getFile(File f) {
		try {
			return getFile(new BufferedReader(new FileReader(f)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static String getFile(BufferedReader r) {
		StringBuilder lines = new StringBuilder();
		
		try {
			String line = r.readLine();
			while(line != null) {
				
				lines.append(line);
				line = r.readLine();
			}
			
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lines.toString();
	}
	
	//>--| PARAGRAPHS >--<\\
	
	public static List<String> getParagraphs(String fileName) {
		return getParagraphs(new File(fileName));
	}
	
	public static List<String> getParagraphs(File f) {
		try {
			return getParagraphs(new BufferedReader(new FileReader(f)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	public static List<String> getParagraphs(BufferedReader r) {
		List<String> lines = new ArrayList<>();
		
		try {
			String line = r.readLine();
			StringBuilder current = new StringBuilder();
			while(line != null) {
				if(line.length() == 0) {
					lines.add(current.toString().trim());
					current = new StringBuilder();
					
					line = r.readLine();
					continue;
				}
				
				current.append(line).append(" ");
				line = r.readLine();
			}
			
			if(current.length() > 0) lines.add(current.toString());
			
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lines;
	}
	
	//>--| LINES >--<\\
	
	public static List<String> getLines(String fileName) {
		return getLines(new File(fileName));
	}
	
	public static List<String> getLines(File f) {
		try {
			return getLines(new BufferedReader(new FileReader(f)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	public static List<String> getLines(BufferedReader r) {
		List<String> lines = new ArrayList<String>();
		
		try {
			String line = r.readLine();
			while(line != null) {
				
				lines.add(line);
				line = r.readLine();
			}
			
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lines;
	}
	
	public static void write2file(File f, String s) {
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			w.write(s);
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
