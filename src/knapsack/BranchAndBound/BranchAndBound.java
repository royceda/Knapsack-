package knapsack.BranchAndBound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import knapsack.heuristic.Item;
import knapsack.heuristic.Solver;

public class BranchAndBound {

	private Solver problem;
	private int size;
	
	
	private ArrayList<Item> items;
	private int       i   ;
	private int       n;
	private double    c   = 0.0; //remained weight
	private double    z   = 0.0; //value gained
	private double    ub  = Double.POSITIVE_INFINITY;
	private double    inc = 0.0; 
	private double    lb  = 0.0;
	private boolean[] x;
	private boolean[] x1; //x* in the algorithm
	
	private double[] p; //tableau des valeurs
	private double[] w; //tableua des poids
	
	private Config best; //garde la meileur conf
	
	
	private String response ;
	
	public BranchAndBound(){}
	
	
	public BranchAndBound(Solver pb){
		this.problem = pb;
		this.size = pb.getSize();
		this.x = new boolean[size];
		this.x1 = new boolean[size];
		this.p = new double[size];
		this.w = new double[size];
		
		this.c = pb.getWeightMax();
		
		best = new Config();
		
		response = "";
		
		int k = 0;
		for(Iterator<Item> ite = pb.getItems().iterator(); ite.hasNext(); ){
			Item tmp = ite.next();
			
			p[k] = tmp.getValue();
			w[k] = tmp.getWeight();
			k++;
		}
	}
	
	
	
	
	
	/**
	 * ressort une liste des items trié par pi/wi
	 * @return
	 */
	public ArrayList<Item> sort(){
		
		ArrayList<Item> items = problem.getItems();
		Collections.sort(items);
		return items;
	}
	
	/**
	 * Compute for the calculus of Upper Bound (A ameliorer)
	 * @param i
	 * @param n
	 * @param c
	 * @return
	 */
	public double PL(int j, int n, double c){
		double ps = 0.0;
		double ws = 0.0;
		for(int k = j; k<n; k++){
			if(ws + w[k] <= c){
				ps += p[k];
				ws += w[k];
			}
			else
				break;		
		}
		return ps;
	}
	
	
	/**
	 * initialize the problem
	 */
	public void init(){		
		problem.solve();
		lb = problem.getLb();
		items = sort();
		c = problem.getWeightMax();
		z = 0;
		i = 0;
		
		n = size-1;
		for(int k = 0; k<size; size++){
			x[k] = false;
			x1[k] = false;
		}
	}
	
	
	public double solve(){
		//initialization
		init();
		size = n+1;
		response += "Begin\n";
		return procedure();
	}
	
	
	
	
	/**
	 * Launch the Branch and Bound execution
	 */
	public double procedure(){	
		size=n+1;
		response += "i = "+i+" z = "+z+" c = "+c+"\n";
		//Compute UB
		ub = z + PL(i, size, c);
		if(ub <= inc){
			return backtrack();
		}else{
			return plunge();
		}
	}

	


	public double plunge(){
		
		while(i <= n && c >= w[i]){
			x[i] = true;
			z = z + p[i];
			c = c - w[i];
			i++;
			response += "Plunge: i = "+i+" z = "+z+" c = "+c+"\n";
		}
		response += "Plunge: i = "+i+" z = "+z+" c = "+c+"\n";
		return fixationZero();
	}

	
	public double fixationZero(){
		if(i < n+1){
			response += ""+i+" fixé à 0: i = "+i+" z = "+z+" c = "+c+"\n";
			x[i] = false;
			i++;
			return procedure();
		}
		response += "i = "+i+" z = "+z+" c = "+c+"\n";
		return updateLB();
	}
	
	public double updateLB(){
		if(i == size && z > lb){
			lb = z;
			setBest(new Config(z, c, x));
			x1 = x;
			response += "Une bonne config: i = "+i+" z = "+z+" c = "+c+"\n";
		}
		return backtrack();
	}
	
	
	public double backtrack(){
		response += "BackTrack:\n";
		if(i == n){
			x[n] = false;
			z = z - p[n];
			c = c + w[n];
			i--;
		}
		
		if(i == n+1)
			i--;
		
		while(x[i] == false){
			i--;
			if(i == -1){
				response += "FIN\n";
				return z;
			}
		}
		
		x[i] = false;
		z = z - p[i];
		c = c + w[i];
		i++;
		response += "à "+i+"\n";
		return procedure();
	}
	

	//getters and setters
	public Solver getProblem() {
		return problem;
	}
	public void setProblem(Solver problem) {
		this.problem = problem;
	}


	public Config getBest() {
		return best;
	}


	public void setBest(Config best) {
		this.best = best;
	}
	
	
	public String  getResponse(){
		return response;
	}
	
}
