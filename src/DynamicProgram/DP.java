package DynamicProgram;

public class DP {

	private double[] p;
	private double[] we;
	private double w;
	private int n;
	 
	public DP(double[] pi, double[] wei, double wi, int ni){
		this.p = pi;
		this.we = wei;
		this.w = wi;
		this.n = ni;	
	}
	
	
	private Double max(Double a, Double b){
		if(a > b){
			return a;
		}else{
			return b;
		}
	}
	
	private double max(double[][] z){
		double a = 0;
		for(int i = 0; i<w; i++){
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
		int ligne = (int) w; 
		double[][] z = new double[ligne][n];
		
		
		//premiere colonne
		for(int i = 0; i<ligne; i++){
			if(i >= we[0]){
				z[i][0] = p[0];
			}
		}
			
		//recurrence bouclée
		for(int i=1; i<w; i++){
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
		return max(z);
	}
}

