package aoc.y2021.d16;

import aoc.y2021.Y2021_Utils;
import aoc.Utils;

public class Day16 {

	public static String in;
	
	public static void main(String[] args) {
		in = Y2021_Utils.getFile(16);
		in = toBinary(in);

		Packet start = parse();
		Utils.printSolution(start.getVersionNumber());
		Utils.printSolution(start.getValue());
	}
	
	
	public static Packet parse() {
		int version = Integer.parseInt(in.substring(0, 3), 2);
		in = in.substring(3);
		
		int typeID = Integer.parseInt(in.substring(0, 3), 2);
		in = in.substring(3);
				
		Packet out = new Packet(version, typeID);
		
		if(typeID == 4) {
			String val = "";
			
			String start = in.substring(0, 5);
			in = in.substring(5);
			
			while(start.charAt(0) == '1') {
				val += start.substring(1);
				
				start = in.substring(0, 5);
				in = in.substring(5);
			}
			val += start.substring(1);
			
			out.setLiteral(val);
		} else {
			char lengthTypeID = in.substring(0, 1).charAt(0);
			in = in.substring(1);
			
			if(lengthTypeID == '0') {
				int totalLength = Integer.parseInt(in.substring(0, 15), 2);
				in = in.substring(15);
				
				int startLength = in.length();
				while(Math.abs(startLength - in.length()) < totalLength) {
					out.addChildren(parse());
				}
			} else {
				int totalPackages = Integer.parseInt(in.substring(0, 11), 2);
				in = in.substring(11);
				
				for(int i = 0; i < totalPackages; i++) {
					out.addChildren(parse());
				}
			}
		}
		
		return out;
	}

	public static String toBinary(String in) {
		String out = "";
		for(char c: in.toCharArray()) {
			if(c == '0') out += "0000";
			if(c == '1') out += "0001";
			if(c == '2') out += "0010";
			if(c == '3') out += "0011";
			if(c == '4') out += "0100";
			if(c == '5') out += "0101";
			if(c == '6') out += "0110";
			if(c == '7') out += "0111";
			if(c == '8') out += "1000";
			if(c == '9') out += "1001";
			if(c == 'A') out += "1010";
			if(c == 'B') out += "1011";
			if(c == 'C') out += "1100";
			if(c == 'D') out += "1101";
			if(c == 'E') out += "1110";
			if(c == 'F') out += "1111";
		}
		return out;
	}
	
}
