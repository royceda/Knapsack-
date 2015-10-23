package ForwardDP;


import java.util.ArrayList;
import java.util.Iterator;

import DynamicProgram.core.CoreDP;
import DynamicProgram.core.State;
import knapsack.heuristic.Item;

public class ForwardDP extends CoreDP {


	public ForwardDP(double[] p, double[] w, double weight, int n) {
		super(p, w, weight, n);
		list = new ArrayList<State>();
	}

	
	public String solve(){
		//init
		list.add(new State(0,0,0.0,0.0,null));
		double lb = 0.0;
		
		//boucle
		for(int k = 0; k< n; k++){
			ArrayList<State> list1 = new ArrayList<State>();
			for(Iterator<State> ite = list.iterator(); ite.hasNext(); ){
				State tmp = ite.next();
				
				if(tmp.getW() + w[k] <= W){
					list1.add(new State(0,0,tmp.getP()+p[k] ,tmp.getW() + w[k], null)); //devrait y avoir un "-" aux weights
				}
			}
			lb = getLB(list1);
			
			eliminate(list1, lb);
			
			list.addAll(list1);
			
		}
		
		
		
		return Integer.toString(list.size());
	}
}


