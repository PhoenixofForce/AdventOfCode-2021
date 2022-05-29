package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ClassUtil {

	public static void main(String[] args) {
		createUtilClass(2016);
	}

	private static void createYear(int yearToAdd) throws IOException {
		File projectFolder = new File("");
		
		File inputFolder = new File(projectFolder.getAbsolutePath() + "/" + yearToAdd + "inputs/");
		if(!inputFolder.exists()) inputFolder.mkdir();
		
		File packetFolder = new File(projectFolder.getAbsoluteFile() + "/src/aoc/y" + yearToAdd);
		if(!packetFolder.exists()) packetFolder.mkdir();
		
		createUtilClass(yearToAdd, projectFolder, packetFolder);
		
		for(int d = 1; d <= 25; d++) {
			createDay(yearToAdd, d);
		}
	}
	
	private static void createDay(int year, int d) throws IOException {
		String dayStr = (d+"").length() == 1? "0" + d: d+"";
		
		File projectFolder = new File("");
		File inputFolder = new File(projectFolder.getAbsolutePath() + "/" + year + "inputs/");
		File packetFolder = new File(projectFolder.getAbsoluteFile() + "/src/aoc/y" + year);
		
		File inputFile = new File(inputFolder.getAbsoluteFile() + "/Day" + dayStr + "_Input");
		if(!inputFile.exists()) inputFile.createNewFile();
		
		File secondPacket = new File(packetFolder.getAbsolutePath() + "/d" + dayStr);
		if(!secondPacket.exists()) secondPacket.mkdir();
		
		File classFile = new File(secondPacket.getAbsoluteFile() + "/Day" + d + ".java");
		if(!classFile.exists()) {
			classFile.createNewFile();
			
			BufferedWriter w = new BufferedWriter(new FileWriter(classFile));
			w.write(String.format("package aoc.y%d.d%s;\r\n\r\n", year, dayStr));
			w.write("import java.io.BufferedReader;\r\n");
			w.write("import java.util.Arrays;\r\n");
			w.write("import java.util.List;\r\n");
			w.write("import java.util.ArrayList;\r\n");
			w.write("import java.util.Map;\r\n");
			w.write("import java.util.HashMap;\r\n");
			w.write("import java.util.stream.Collectors;\r\n");
			w.write(String.format("\r\nimport aoc.y%d.Y%d_Utils;\r\n", year, year));
			w.write("import aoc.Utils;\r\n\r\n");
			w.write(String.format("public class Day%d {\r\n\r\n", d));
			w.write("\tpublic static void main(String[] args) {\r\n");
			w.write(String.format("\t\tList<String> lines = Y%d_Utils.getLines(%d);\r\n", year, d));
			w.write("\t\t\r\n\t}\r\n\r\n}\r\n");
			w.close();
		}
	}
	
	private static void createUtilClass(int yearToAdd) {
		File projectFolder = new File("");
		
		File inputFolder = new File(projectFolder.getAbsolutePath() + "/" + yearToAdd + "inputs/");
		if(!inputFolder.exists()) inputFolder.mkdir();
		
		File packetFolder = new File(projectFolder.getAbsoluteFile() + "/src/aoc/y" + yearToAdd);
		if(!packetFolder.exists()) packetFolder.mkdir();
		
		try {
			createUtilClass(yearToAdd, projectFolder, packetFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void createUtilClass(int yearToAdd, File projectFolder, File packetFolder) throws IOException {
		int year2clone = 2021;
		
		File utilsFile = new File(packetFolder.getAbsoluteFile() + "/Y" + yearToAdd + "_Utils.java");
		utilsFile.createNewFile();
		
		List<String> lines = FileUtils.getLines(projectFolder.getAbsolutePath() + "/src/aoc/y" + year2clone + "/Y" + year2clone + "_utils.java");
		BufferedWriter w = new BufferedWriter(new FileWriter(utilsFile));
		for(String s: lines) w.write(s.replace("2020", yearToAdd + "") + "\r\n");
		w.close();
	}	
}