
package knapsack;

import java.util.ArrayList;

import knapsack.heuristic.Item;
import knapsack.heuristic.Solver;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("hello world");
		
		
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
		

		
		
	}

}
