package aoc.y2021.d18;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Snailfish {

	private Snailfish father;
	private List<Snailfish> children;
	private int depth, value;
	
	public Snailfish(String f) {
		children = new ArrayList<>();
		depth = 0;
		Stack<Snailfish> s = new Stack<>();
		s.push(this);
		
		for(int i = 1; i < f.length(); i++) {
			char c = f.charAt(i);
			if(c == '[') {
				s.push(new Snailfish(s.size()));
			}
			else if(c == ']') {
				Snailfish last = s.pop();
				if(s.size() > 0) s.peek().addFish(last);
			}
			else {
				s.peek().addFish(new Snailfish(s.size(), Character.getNumericValue(c)));
			}
		}
	}
	
	public Snailfish(int depth, int v) {
		children = new ArrayList<>();
		this.depth = depth;
		this.value = v;
	}
	
	public Snailfish(int d) {
		children = new ArrayList<>();
		this.depth = d;
	}
	
	public void addFish(Snailfish s) {
		children.add(s);
		s.father = this;
	}
	
	public void increaseDepth() {
		depth++;
		for(Snailfish c: children) c.increaseDepth();
	}
	
	public boolean explode() {
		boolean out = false;
		
		for(int i = 0; i < children.size(); i++) {
			Snailfish c = children.get(i);
			if(c.depth == 4 && c.children.size() > 0) {
				 int first = c.children.get(0).value;
				 int second = c.children.get(1).value;
				 
				 c.children = new ArrayList<>();
				 
				 Snailfish prevS = null;
				 for(int j = i-1; j >= 0; j--) {
					 prevS = children.get(j).findPrevfish();
					 if(prevS != null) break;
				 }
				 if(prevS == null) prevS = father.findPrevfish(this);
				 
				 Snailfish nextS = null;
				 for(int j = i+1; j < children.size(); j++) {
					 nextS = children.get(j).findNextfish();
					 if(nextS != null) break;
				 }
				 if(nextS == null) nextS = father.findNextfish(this);

				 if(prevS != null) prevS.value += first;
				 if(nextS != null) nextS.value += second;
				 
				 out = true;
			}
			if(out) break;
		}
		
		if(out) return out;
		
		for(Snailfish c: children) {
			out = c.explode();
			if(out) break;
		}
		
		return out;
	}
	
	private Snailfish findNextfish(Snailfish current) {
		int start = children.indexOf(current);
		
		for(int i = start+1; i < children.size(); i++) {
			Snailfish out = children.get(i).findNextfish();
			if(out != null) return out;
		}
		if(father != null) return father.findNextfish(this);
		return null;
	}
	
	private Snailfish findNextfish() {
		if(this.children.size() == 0) return this;
		Snailfish out = null;
		for(int i = 0; i < children.size(); i++) {
			Snailfish s = children.get(i);
			out = s.findNextfish();
			if(out != null) break;
		}
		return out;
	}
	
	private Snailfish findPrevfish(Snailfish current) {
		int start = children.indexOf(current);
		
		for(int i = start-1; i >= 0; i--) {
			Snailfish out = children.get(i).findPrevfish();
			if(out != null) return out;
		}
		if(father != null) return father.findPrevfish(this);
		return null;
	}
	
	private Snailfish findPrevfish() {
		if(this.children.size() == 0) return this;
		Snailfish out = null;
		for(int i = children.size() - 1; i >= 0; i--) {
			Snailfish s = children.get(i);
			out = s.findPrevfish();
			if(out != null) break;
		}
		return out;
	}
	
	public boolean split() {
		if(value >= 10) {
			int first = (int) Math.floor(value / 2.0);
			int second = (int) Math.ceil(value / 2.0);
			this.value = 0;
			
			Snailfish firstFish = new Snailfish(depth + 1, first);
			Snailfish secondFish = new Snailfish(depth + 1, second);
			addFish(firstFish);
			addFish(secondFish);
			
			return true;
		}
		
		boolean out = false;
		for(Snailfish s: children) {
			out = s.split();
			if(out) break;
		}
		
		return out;
	}
	
	public long getSum() {
		if(children.size() == 0) return value;
		return 3 * children.get(0).getSum() + 2 * children.get(1).getSum();
	}
	
	@Override
	public String toString() {
		if(children.size() == 0) return "" + value;
		String out = "[";
		for(Snailfish c: children) {
			out = out + c.toString() + ",";
		}
		out += "]";
		out = out.replace(",]", "]");
		return out;
	}
	
	@Override
	public Snailfish clone() {
		Snailfish out = new Snailfish(depth, value);
		for(Snailfish c: children) out.addFish(c.clone());
		return out;
	}
	
	public static Snailfish add(Snailfish a, Snailfish b) {
		Snailfish out = new Snailfish(0);
		
		a.increaseDepth();
		b.increaseDepth();
		
		out.addFish(a);
		out.addFish(b);
		
		return out;
	}
	
	@Override 
	public boolean equals(Object o2) {
		return o2 == this;
	}
}
