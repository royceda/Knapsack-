package ForwardDP;

import DynamicProgram.core.CoreDP;

public class testForward {

	public static void main(String[] args) {
	
		int n = 7;
		double weight = 50.0;
		double[] p = {70, 20, 39, 35, 7, 5, 9};
		double[] w = {31, 10, 20, 18, 4, 3, 6};
		
		
		ForwardDP test = new ForwardDP(p, w, weight, n);
		System.out.println("OK");
		
		String lb = test.solve();
		System.out.println("lb: "+lb);
		
	}

}
