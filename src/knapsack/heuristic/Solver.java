package knapsack.heuristic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Solver {

	private ArrayList<Item> items;
	private Double weightMax;
	private Double lb;
	
	private ArrayList<Item> result;
	private double max;

	public Solver(){
		result = new ArrayList<Item>();
		items = new ArrayList<Item>();
		max = 0.0;
	}
	
	public Solver(List<Item> list, Double w){
		items = new ArrayList<Item>();
		items.addAll(list);
		weightMax = w;
		
		result = new ArrayList<Item>();
		max = 0.0;
	}
	
	
	public int getSize(){
		return items.size();
	}
	
	
	public void addItem(Item it){
		items.add(it);
	}
	
	public void removeItem(Item it){
		items.remove(it);
	}
	
	
	public double solve(){
		
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
		
		double sum = 0.0;
		//ArrayList<Item> result = new ArrayList<Item>();
		
		for (Item p : items) {
			//System.out.println(p.getNumber() + "\t" + p.getRatio());
			if(sum + p.getWeight() <= this.weightMax){
				result.add(p);
				sum += p.getWeight();
			}
		}
		
		setLb(sum);
		//System.out.println("Total: "+sum);
		/*for(Item p : result){
			System.out.println(p.getNumber() + "\t" + p.getWeight());			
		}*/
		
		max = sum;
		return sum;
	}

	
	public String getSolution(){
		String response = "Solution: z = "+ max+"\n";
		
		for(Iterator<Item> ite = result.iterator(); ite.hasNext();){
			Item tmp = ite.next();
			response += Integer.toString(tmp.getNumber())+", ";
		}
		return response;
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
