package knapsack.BranchAndBound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import knapsack.heuristic.Item;
import knapsack.heuristic.Solver;

public class BranchNBound {
	
	private Solver problem;
	private Node root;
	
	private HashMap<Integer, Item> map; //map of item <number, item>
	private HashMap<Integer, Node> mapNode; //map of node <number, Node>
	
	private ArrayList<Integer> n1; //liste des indices déjà fixé à 1
	private ArrayList<Integer> n0; //liste des indices déjà fixé à 0
	private ArrayList<Integer> f; // liste des indices encore libres
	private ArrayList<Integer> visited;//liste des noeuds traité en 0 et 1;
	
	private int n; //size
	private int index = 0;	
	
	private Double u; //Z+UB 
	private Double z; //z
	private Double zBest; //optimal Z
	private Double w; //optimal w
	
	public BranchNBound(ArrayList<Item> list, Double weight){
		problem = new Solver(list, weight);
		map = new HashMap<Integer, Item>();
		//remplir la map
		for(Iterator<Item> ite = list.iterator(); ite.hasNext();){
			Item tmp = ite.next();
			map.put(tmp.getNumber(), tmp);
		}
		
		n = list.size();
		n0 = new ArrayList<Integer>();
		n1 = new ArrayList<Integer>();
		root = new Node(index,1);
		index++;
		w = weight;
		visited = new ArrayList<Integer>();
		mapNode = new HashMap<Integer, Node>();
	}
	
	
	public void procedure(){
		
		//on initialise le probleme
		
		n0 = new ArrayList<Integer>();
		n1 = new ArrayList<Integer>();
		f = new ArrayList<Integer>();
		
		for(int i = 0; i<n; i++){
			f.add(i);
			i++;
		}
		
		
		//borne inferieur
		problem.solve();
		//z = problem.getLb();
		z = 0.0;
		
		//borne superieur
		zBest = 102.0;
		u = 102.0;
		
		//procedure du premier noeud
		//on initialise root
		root.setW(w - map.get(1).getWeight());
		
		//let's ride!!!!!
		depthFirst();
	}
	
	
	public void depthFirst(){
		
		Node tmp = traitementDeNoeud(problem.getWeightMax(), root);
		System.out.println("Noeud: "+tmp.getNumber()+" Objet: "+tmp.getItem()+" Z: "+tmp.getZ()+" W: "+tmp.getW());
		depthFirstRec(tmp);
	}
	
	
	
	private void depthFirstRec(Node node){
				
		//sondage du noeud
		if(node.getZ() >  u || node.getW() > problem.getWeightMax()){
			//on sonde
			//backtracking
			
			//l = max j parmis N1 et on remonte au noeud xl = 0
			
			int l = maxOfList(n1);
			
			if(visited.contains(l)){
				System.out.println("la recurssion a fini. Le mode Hero !!!!!!! ^_^");
				return;
			}else{
				System.out.println("on a visté "+l);
				visited.add(l);
			}
					
			//maj
			z = z - map.get(l).getValue();
			w = w + node.getW();
			
			Node tmp = mapNode.get(l);
			depthFirstRec(tmp);
			
		}else{
			Node tmp = traitementDeNoeud(problem.getWeightMax(), node);
			System.out.println("Noeud: "+tmp.getNumber()+" Objet: "+tmp.getItem()+" Z: "+tmp.getZ()+" W: "+tmp.getW());
			depthFirstRec(tmp);
		}
			
	}
	
	
	private int maxOfList(ArrayList<Integer> list){
		
		int max = 0;
		for(Iterator<Integer> ite = list.iterator(); ite.hasNext();){
			int tmp = ite.next();
			if(max < tmp){
				max = tmp;
			}
		}
		return max;
	}
	
	

	public Node traitementDeNoeud(Double we, Node root){
	
		if(root.getW() > we){
			//on ne  prend pas
			//new node en 0; modifie le noeud root pr le vrai noeud
			root.setN0(new Node(index, root.getItem()));
			index++;
			
			f.remove(root.getNumber());
			n0.add(root.getNumber());
					
			//pas de modif
			return root.getN0();
			
		}else if (root.getW() < we){
			//on prend
			//new node en 1; modifie le noeud root pr le vrai noeud
			if(map.get(root.getItem()+1) != null){
				root.setN1(new Node(index, root.getItem()+1));
				index++;
				
				f.remove(root.getNumber());
				n1.add(root.getNumber());
				
				w = w - map.get(root.getItem()).getWeight(); //pb a 4
				z = z + map.get(root.getItem()).getValue();
				if(z > zBest){
					zBest = z;
				}
				
				root.getN1().setW(w);
				root.getN1().setZ(z);
				
				return root.getN1();
			}
			return root;
		}
		return null;
	}
	
	
	
	
	
	//Getters and Setters
	public Solver getProblem() {
		return problem;
	}
	public void setProblem(Solver problem) {
		this.problem = problem;
	}
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public HashMap<Integer, Item> getMap() {
		return map;
	}
	public void setMap(HashMap<Integer, Item> map) {
		this.map = map;
	}
	public ArrayList<Integer> getN1() {
		return n1;
	}
	public void setN1(ArrayList<Integer> n1) {
		this.n1 = n1;
	}
	public ArrayList<Integer> getN0() {
		return n0;
	}
	public void setN0(ArrayList<Integer> n0) {
		this.n0 = n0;
	}
	public ArrayList<Integer> getF() {
		return f;
	}
	public void setF(ArrayList<Integer> f) {
		this.f = f;
	}
	public Double getU() {
		return u;
	}
	public void setU(Double u) {
		this.u = u;
	}
	public Double getZ() {
		return z;
	}
	public void setZ(Double z) {
		this.z = z;
	}
	
	

}
