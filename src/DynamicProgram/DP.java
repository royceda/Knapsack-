package DynamicProgram;

import java.util.Iterator;

import knapsack.heuristic.Item;
import knapsack.heuristic.Solver;

public class DP {

	protected double[] p;
	protected double[] we;
	protected double   w;
	protected int      n;
	
	
	private double[][] z;
	private int lambda;
	private double max;
	
	public DP(double[] pi, double[] wei, double wi, int ni){
		this.p  = pi;
		this.we = wei;
		this.w  = wi;
		this.n  = ni;	
	}
	
	
	public DP(Solver pb){
		
		int k = 0;
		
		n  = pb.getItems().size();
		p  = new double[n];
		we = new double[n];
		w  = pb.getWeightMax();
		
		
		for(Iterator<Item> ite = pb.getItems().iterator(); ite.hasNext(); ){
			Item tmp = ite.next();		
			p[k]  = tmp.getValue();
			we[k] = tmp.getWeight();
			k++;
		}
		
	}
	
	protected double max(Double a, Double b){
		if(a > b){
			return a;
		}else{
			return b;
		}
	}
	
	
	private double max(double[][] z){
		double a = 0;
		for(int i = 0; i<w+1; i++){
			for(int j = 0; j<n; j++){
				if(a <= z[i][j]){
					a = z[i][j];
				}
			}
		}
		return a;
	}
	
	
	public double solve(){
		
		//initialisation			
		int ligne = (int) w+1; 
		z = new double[ligne][n];
		
		
		//premiere colonne
		for(int i = 0; i<ligne; i++){
			if(i >= we[0]){
				z[i][0] = p[0];
			}
		}
			
		//recurrence bouclée
		for(int i=1; i<ligne; i++){
			for(int r = 1; r<n ; r++){
				if(i-we[r] >= 0){
					int l = (int) (i-we[r]);
					z[i][r] = max(z[i][r-1], p[r] + z[l][r-1]);
				}else{
					z[i][r] = z[i][r-1];
					//z[i][r] = max(z[i][r-1], p[r] + 0);
				}
			}
		}
		
		
		for(int i = 0; i<ligne; i++){
			for(int j = 0; j<n; j++){
				System.out.print(" "+z[i][j]);
			}
			System.out.println("");System.out.println("");
		}
		
		//choix de la solution	
		lambda = ligne-1;
		
		max =  max(z);
		return max;
	}
	
	
	
	// a debug
	public String getSolution(){
		
		String response = "Solution DP: \n Z="+max+"\nX: ";
		
		
		
		if(p[0] > z[lambda][0]){
			response += " 1";			
		}else
			response +=" 0";
		
		for(int k=1; k<n; k++){	
			if(z[(int) (lambda-we[k])][k-1] + p[k] > z[lambda][k-1]){
				int tmp = k+1;
				response += ","+tmp+" ";			
			}else
				response += ",0 ";		
		}
		
		
		return response;
	}
	
	
}

