package DynamicProgram.core;

public class State {

	private int a;
	private int b;
	private double w;
	private double p;
	private State s; //dernier etat
	
	
	public State(int a, int b, double w, double p, State s){
		this.a = a;
		this.b = b;
		this.w = w;
		this.p = p;
		this.s = s;
	}
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public double getW() {
		return w;
	}
	public void setW(double w) {
		this.w = w;
	}
	public double getP() {
		return p;
	}
	public void setP(double p) {
		this.p = p;
	}
	
	
}
