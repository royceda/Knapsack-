
package knapsack;

import java.util.ArrayList;

import knapsack.heuristic.Item;
import knapsack.heuristic.Solver;



public class Main {

	public static void main(String[] args) {

		System.out.println("heuristic");
		
		
		Item a = new Item(1, 7.0, 13.0);
		Item b = new Item(2, 4.0, 12.0);
		Item c = new Item(3, 3.0, 8.0);
		Item d = new Item(4, 3.0, 10.0);
		
		ArrayList<Item> tmpList = new ArrayList<Item>();
		tmpList.add(a);
		tmpList.add(b);
		tmpList.add(c);
		tmpList.add(d);
		
		
		Solver sol = new Solver(tmpList, 30.0);
		sol.solve();
		
		
	System.out.println("heuristic2\n");
		
		
		Item a = new Item(1, 70.0, 31.0);
		Item b = new Item(2, 20.0, 10.0);
		Item c = new Item(3, 39.0, 20.0);
		Item d = new Item(4, 35.0, 18.0);
		Item e = new Item(4, 7.0,  4.0);
		Item f = new Item(4, 5.0,  3.0);
		Item g = new Item(4, 9.0,  6.0);
		
		
		ArrayList<Item> tmpList = new ArrayList<Item>();
		tmpList.add(a);
		tmpList.add(b);
		tmpList.add(c);
		tmpList.add(d);
		tmpList.add(e);
		tmpList.add(f);
		tmpList.add(g);
		
		
		Solver sol = new Solver(tmpList, 50.0);
		sol.solve();
		
	}

}
