package knapsack.BranchAndBound;

import java.util.ArrayList;
import java.util.Collections;

import knapsack.heuristic.Item;
import knapsack.heuristic.Solver;

public class testBranchAndBound {

	public static void main(String[] args) {
		
		ArrayList<Item> tmpList = new ArrayList<Item>();
		
		Item a = new Item(1, 70.0, 31.0);
		Item b = new Item(2, 20.0, 10.0);
		Item c = new Item(3, 39.0, 20.0);
		Item d = new Item(4, 35.0, 18.0);
		Item e = new Item(4, 7.0,  4.0);
		Item f = new Item(5, 5.0,  3.0);
		Item g = new Item(6, 9.0,  6.0);
			
			
		tmpList = new ArrayList<Item>();
		tmpList.add(a);
		tmpList.add(b);
		tmpList.add(c);
		tmpList.add(d);
		tmpList.add(e);
		tmpList.add(f);
		tmpList.add(g);
			
			
		Solver sol = new Solver(tmpList, 50.0);
		
		Collections.sort(tmpList);
		
		
		BranchAndBound2 bb = new BranchAndBound2(sol);
		
		System.out.println("INIT: OK");
		//System.out.println(tmpList.toString());
		
		bb.solve();
		
		System.out.println(bb.getBest().toString());	
	}

}
