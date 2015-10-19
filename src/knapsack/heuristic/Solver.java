package knapsack.heuristic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solver {

	private ArrayList<Item> items;
	private Double weightMax;
	private Double lb;
	
	
	
	public Solver(){
		items = new ArrayList<Item>();
	}
	
	public Solver(List<Item> list, Double w){
		items = new ArrayList<Item>();
		items.addAll(list);
		weightMax = w;
	}
	
	
	
	public void addItem(Item it){
		items.add(it);
	}
	
	public void removeItem(Item it){
		items.remove(it);
	}
	
	
	public ArrayList<Item> solve(){
		
		Collections.sort(items, new Comparator<Item>() {	
			@Override
			public int compare(Item i1, Item i2){
				
				if(i1.getRatio() - i2.getRatio() > 0)
					return -1;
				else if(i1.getRatio() - i2.getRatio() < 0)
					return 1;
				else
					return 0;
			}	
		});
		
		Double sum = 0.0;
		ArrayList<Item> result = new ArrayList<Item>();
		
		for (Item p : items) {
			System.out.println(p.getNumber() + "\t" + p.getRatio());
			if(sum + p.getWeight() <= this.weightMax){
				result.add(p);
				sum += p.getWeight();
			}
		}
		
		setLb(sum);
		System.out.println("Total: "+sum);
		for(Item p : result){
			System.out.println(p.getNumber() + "\t" + p.getWeight());			
		}
		
		return result;
	}

	
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public Double getLb() {
		return lb;
	}

	public void setLb(Double lb) {
		this.lb = lb;
	}
	public Double getWeightMax() {
		return this.weightMax;
	}

	public void setWeightMax(Double lb) {
		this.weightMax = lb;
	}
}
