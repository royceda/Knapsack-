package knapsack.heuristic;

public class Item {
	
	private Integer number;
	private Double weight;
	private Double value;
	private Double ratio;
	
	
	public Item(Integer n, Double v, Double w){
		number = n;
		weight = w;
		value  = v;
		ratio = (v/w);
	}
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}


	public Double getRatio() {
		return ratio;
	}


	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
}
