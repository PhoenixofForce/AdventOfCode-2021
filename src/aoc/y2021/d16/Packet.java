package aoc.y2021.d16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Packet {

	private int version, typeID;
	private BigInteger literal;
	private List<Packet> children;
	public Packet(int version, int typeID) {
		this.version = version;
		this.typeID = typeID; 
		
		this.children = new ArrayList<>();
	}
	
	public void setLiteral(String l) {
		this.literal = new BigInteger(l, 2);
	}
	
	public void addChildren(Packet p) {
		children.add(p);
	}
	
	public long getVersionNumber() {
		long out = version;
		for(Packet c: children) out += c.getVersionNumber();
		return out;
	}
	
	public BigInteger getValue() {
		if(typeID == 0) {
			BigInteger out = BigInteger.ZERO;
			for(Packet c: children) out = out.add(c.getValue());
			return out;
		}
		else if(typeID == 1) {
			BigInteger out = BigInteger.ONE;
			for(Packet c: children) out = out.multiply(c.getValue());
			return out;
		}
		else if(typeID == 2) {
			BigInteger out = children.get(0).getValue();
			for(Packet c: children) {
				BigInteger v = c.getValue();
				if(v.compareTo(out) < 0) out = v;
			}
			return out;
		}
		else if(typeID == 3) {
			BigInteger out = children.get(0).getValue();
			for(Packet c: children) {
				BigInteger v = c.getValue();
				if(v.compareTo(out) > 0) out = v;
			}
			return out;
		}
		else if(typeID == 4) return literal;
		else if(typeID == 5) {
			return (children.get(0).getValue().compareTo(children.get(1).getValue()) > 0? BigInteger.ONE: BigInteger.ZERO);
		}
		else if(typeID == 6) {
			return (children.get(0).getValue().compareTo(children.get(1).getValue()) < 0? BigInteger.ONE: BigInteger.ZERO);
		}
		else if(typeID == 7) {
			return (children.get(0).getValue().compareTo(children.get(1).getValue()) == 0? BigInteger.ONE: BigInteger.ZERO);
		}
		return BigInteger.ZERO;
	}
	
}
