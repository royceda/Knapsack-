package DynamicProgram.core;

import java.util.ArrayList;
import java.util.Iterator;

import knapsack.heuristic.Solver;
import DynamicProgram.DP;

public class CoreDP extends DP{

	protected ArrayList<State> list;
	protected double[] w;
	protected double[] p;
	
	protected double W;
	protected int n;
	
	/*public CoreDP(){
		list = new ArrayList<State>();
	}*/
	
	public CoreDP(double[] p, double[] w, double W, int n){
		this.p = p;
		this.w = w;
		this.W = W;
		this.n = n;
		this.list = new ArrayList<State>();
	}
	
	
	public CoreDP(Solver sol) {
		super(sol);
		this.list = new ArrayList<State>();
	}


	/**
	 * get the c
	 * @return
	 */
	public int getCriticIndex(){
		double cap = W;
        for (int i = 0; i < n; i++) {
            if (w[i] >= cap) {
                return i;
            } else {
                cap -= w[i];
            }
        }
        return 0;
	}
	
	public double sumW(int n){
	    int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += w[i];
        }
        return sum;
	}
	
	public double sumP(int n){
		 int sum = 0;
		 for (int i = 0; i < n; i++) {
			 sum += p[i];
		 }
		 return sum;
	}
	
	
	public double getLB(ArrayList<State> list){
		
		//max {p / (a,b,w,p) dans list et w <= W} 
	    double lb = -1;
        try{ //!!!! JRE1.8
        	/*lb = list.stream() 
        			.filter(s -> s.getW() <= W)
        			.mapToInt(s -> s.getP())
        			.max()
        			.getAsInt();*/
        	double pmax = 0;
        	for(Iterator<State> ite = list.iterator(); ite.hasNext(); ){
        		State tmp = ite.next();
        		
        		if(tmp.getP() >= pmax){
        			pmax = tmp.getP();
        		}
        	}
        	
        	lb = pmax;
        } catch(Exception e){}
        
        return lb;
	}
	
	
	  public double relaxation(int idxFirstItem, int idxSecondItem, int idxThirdItem, int idxFourthItem, double maxCapacity) {
		  double solution = 0;
		  double currentCapacity = maxCapacity;
		  for (int i = idxFirstItem; i < idxSecondItem; i++) {
			  if ( w[i] >= currentCapacity) {
				  //we take what we can and lets go
	                solution += p[i] * currentCapacity / w[i];
	                currentCapacity = 0;
	                break;
	            } else {
	                solution += p[i];
	                currentCapacity -= w[i];
	            }
	        }
	        for (int i = idxThirdItem; i < idxFourthItem && currentCapacity != 0; i++) {
	            if (w[i]>= currentCapacity) {
	                //we take what we can and lets go
	                solution +=  p[i] * currentCapacity / w[i];
	                break;
	            } else {
	                solution += p[i];
	                currentCapacity -= w[i];
	            }
	        }
	        return solution;
	    }
	  
	  
	
	  private boolean stateIsDominated(State testedItem, State initialItem) {
	        return (initialItem.getW() < testedItem.getW() && initialItem.getP() >= testedItem.getP()) ||
	        		(initialItem.getW() == testedItem.getW() && initialItem.getP() > testedItem.getP());
	    }
	
	public double getUB(State item){		
		double ub = item.getP() - sumP(item.getA()) + 
				relaxation(0, item.getA(), item.getB()+1, n, W - item.getW() + sumW(item.getA()));	
		return ub;
	}
	
	public void eliminate(ArrayList<State> list, double lb){
	    	
		for(Iterator<State> ite = list.iterator(); ite.hasNext();){
        	
            State tmp = ite.next();
            if (getUB(tmp) < lb) {
                ite.remove();
            } else {
            	for(Iterator<State> ite1 = list.iterator(); ite1.hasNext();){
            		State tmp1 = ite1.next();	
            		if (stateIsDominated(tmp, tmp1)) {
            			ite1.remove();
            			break;
            		}
            	}
            }
        }	
	}
	
	
	
	
	public double Solve(){
		
		n = 7; //nb variable a ne pas laisser
		W = 12.2;
		
		//initialization
		int c = getCriticIndex();
		int a = c;
		int b = c-1;
	
		double lb = sumP(c);
		
		list.add(new State(a, b, sumW(c), sumP(c), null));
		
		ArrayList<State> list1 = new ArrayList<State>();
		
		//Recursion
		while(a > 0 || b < n-1){
			if(b < n){
				
				for(Iterator<State> ite = list.iterator(); ite.hasNext();){
					State tmp = ite.next();
					list1.add(new State(tmp.getA(), tmp.getB()+1, tmp.getW(), tmp.getP(), tmp));
					//solution[b+1] = 0;
					double w2 = tmp.getW() - sumW(tmp.getA());
					
					if (w2 + w[tmp.getB()+1] <= W){					
						list1.add(new State(tmp.getA(), tmp.getB()+1, w2 + w[tmp.getB()+1], tmp.getP() + p[tmp.getB()+1], tmp));
						//solution[b+1] = 1;
					}	
				}
				
				lb = getLB(list1);
				eliminate(list1, lb);
				//penser a modifier solution durant eliminate
				list.clear();
				list.addAll(list1);
				b++;
			}else if(a > 0){
				
				for(Iterator<State> ite = list.iterator(); ite.hasNext();){
					State tmp = ite.next();
					list1.add(new State(tmp.getA()-1, tmp.getB(), tmp.getW(), tmp.getP(), tmp));
					//solution[a-1] = 1;
					list1.add(new State(a, b+1, sumW(tmp.getA()) - w[tmp.getA()-1], sumP(tmp.getA()) - p[tmp.getA()-1], tmp));
					//solution[a-1] = 0;
				}
				
				lb = getLB(list1);
				eliminate(list1, lb);
				//penser a modifier solution durant eliminate
				list.clear();
				list.addAll(list1);
				a--;	
			}	
		}
		
		System.out.println("Taille de list1 : "+list1.size());
		return lb;
	}
	
	   public String getSolution() {
		   String solution = "";
		   int a = -1;
		   int b = -1;
	          for(Iterator<State> ite = list.iterator(); ite.hasNext();){
	        	State tmp = ite.next();
	        	
	        	a = tmp.getA();
	        	b = tmp.getB();
	        	
	        	if(p[a] == tmp.getP() && w[a] == tmp.getW())
	        		solution += ", "+(a+1);
	        	else
	        		solution += ", "+0;
	        	if(p[b] != tmp.getP() && w[b] != tmp.getW())
	        		solution += ", "+(b+1);
	        	else
	        		solution += ", "+0;
	        	
	          }
	       
		   return solution;    
	}
}
