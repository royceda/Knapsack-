package DynamicProgram;




public class TestDP {

	
	
	
	
	public static void main(String[] args) {
		
		double[] p = {70, 20, 39, 35, 7, 5, 9};
		double[] w = {31, 10, 20, 18, 4, 3, 6};
		double we  = 50;
		int n = 7;
		
		
		
		
		
		
		DP testDp = new DP(p, w, we, n);
		
		double result = testDp.solve();
		
		System.out.println("result: "+result);
		
		
		
		

	}

}
