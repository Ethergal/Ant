package net.galach;

public class Tuple<T1, T2> {
	public T1 x;
	public T2 y;
	
	public Tuple(T1 x, T2 y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x +", " + y + ") ";
	}
}