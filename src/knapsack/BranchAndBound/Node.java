package knapsack.BranchAndBound;

public class Node {
	private Integer number;
	private Double z; //valeur de z a cet instant
	private Double w; //capacité restante
	
	private Node n0; // xi = 0
	private Node n1; // xi = 1
	
	
	
	public Node(int index){
		number = index;
	}
	
	
	
	
	
	//Getters and Setters
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getW() {
		return w;
	}
	public void setW(Double w) {
		this.w = w;
	}
	public Double getZ() {
		return z;
	}
	public void setZ(Double z) {
		this.z = z;
	}
	public Node getN0() {
		return n0;
	}
	public void setN0(Node n0) {
		this.n0 = n0;
	}
	public Node getN1() {
		return n1;
	}
	public void setN1(Node n1) {
		this.n1 = n1;
	}

}
