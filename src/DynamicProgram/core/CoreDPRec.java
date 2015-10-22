package DynamicProgram.core;

import knapsack.heuristic.Solver;
import DynamicProgram.DP;

public class CoreDPRec extends DP {

	
	private double[][][] V; 
	
	private int solution[];
	
	public CoreDPRec(double[] pi, double[] wei, double wi, int ni) {
		super(pi, wei, wi, ni);
		V = new double[n][n][(int) (3*w)]; //normalement 2*w  on major juste
		solution = new int[n];
	}
	
	
	

	public CoreDPRec(Solver sol) {
		super(sol);
		V = new double[n][n][(int) (3*w)]; //normalement 2*w  on major juste
		solution = new int[n];
	}




	public int getCriticIndex(){
		double cap = w;
        for (int i = 0; i < this.n; i++) {
            if (we[i] >= cap) {
                return i;
            } else {
                cap -= we[i];
            }
        }
        return 0;
	}
	
	
	
	@Override
	public double solve(){
		
		//init
		int c = getCriticIndex();
		//int a = c;
		//int b = c-1;
		
		double[] variable = new double[4];
		
		for(int a = 0; a<c; a++)
			for(int b = c-1; b<n; b++ )
				for(int d = 0; d < 2*w; d++){
					
					variable[0] = V[a+1][b][d];
					variable[1] =  V[a+1][b][(int) (d+we[a])] - p[a];
					
					
					if(d-we[b] < 0)
						variable[3] = 0;
					else
						variable[3] = V[a][b-1][(int) (d-we[b])];
					if(b-1 < 0)
						variable[2] = 0;
					else
						variable[2] = V[a][b-1][d];
						
						
					V[a][b][d] = max (max(variable[0], variable[1]),
							max(variable[2], variable[3] ));
					
					
					if(V[a][b][d] == V[a+1][b][d])
						solution[a] = 1;
					else if(V[a][b][d] == V[a+1][b][(int) (d+we[a])] - p[a])
						solution[a] = 0;
					else if(V[a][b][d] == V[a][b-1][d])
						solution[b] = 0;
					else if(V[a][b][d] == V[a][b-1][(int) (d-we[b])])
						solution[b] = 1;
				}
		//tester pour extraire une soluce
		
		
		
		return 0.0;
	}
	
	
	public String getSolution(){
		String str = "";
		for(int i = 0; i<n; i++){
			if(solution[i] == 1)
				str += ""+(i+1)+", ";
			else
				str += ""+0+", ";
		}
		return str;
	}
	
	
	
	
}
