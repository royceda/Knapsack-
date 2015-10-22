package DynamicProgram.core;

import DynamicProgram.DP;

public class TestCore {

	public static void main(String[] args) {
	/*
		int n = 7;
		double weight = 50.0;
		double[] p = {70, 20, 39, 35, 7, 5, 9};
		double[] w = {31, 10, 20, 18, 4, 3, 6};
		
		
		CoreDP test = new CoreDP(p, w, weight, n);
		System.out.println("OK");
		
		double lb = test.Solve();
		System.out.println("lb: "+lb);
	 */
		
		
		double[] p = {70, 20, 39, 35, 7, 5, 9};
		double[] w = {31, 10, 20, 18, 4, 3, 6};
		double we  = 50;
		int n = 7;
		
		
		
		
		
		
		CoreDPRec testDp = new CoreDPRec(p, w, we, n);
		
		double result = testDp.solve();
		
		System.out.println("result: "+result+"\n"+testDp.getSolution());
		
		
		
		
		
		
		
	}

}
