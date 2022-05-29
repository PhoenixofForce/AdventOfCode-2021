package aoc.y2021.d22;

public record Cube(int x1, int y1, int z1, int x2, int y2, int z2, boolean state) {

	public Cube intersect(Cube c, boolean on) {
        if (x1 > c.x2 || x2 < c.x1 || y1 > c.y2 ||
                y2 < c.y1 || z1 > c.z2 || z2 < c.z1) return null;
        
        return new Cube(
                Math.max(x1, c.x1), Math.max(y1, c.y1), Math.max(z1, c.z1),
                Math.min(x2, c.x2), Math.min(y2, c.y2), Math.min(z2, c.z2), on);
    }
	
	public long size() {
		return (x2 - x1 + 1L) * (y2 - y1 + 1L) * (z2 - z1 + 1L) * (state ? 1 : -1);
	}
}