package knapsack.BranchAndBound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import knapsack.heuristic.Item;
import knapsack.heuristic.Solver;

public class BranchNBound {
	
	private Solver problem;
	private Node   root;
	
	private HashMap<Integer, Item> map; //map of item <number, item>
	private HashMap<Integer, Node> mapNode; //map of node <number, Node>
	private HashMap<Integer, Node> mapItem; //pour un item son dernier node associé
	
	
	private ArrayList<String> n1; //liste des indices déjà fixé à 1
	private ArrayList<String> n0; //liste des indices déjà fixé à 0
	private ArrayList<String> f; // liste des indices encore libres
	private ArrayList<Integer> visited;//liste des noeuds traité en 0 et 1;
	
	private int n; //size
	private int index = 0;	//index de noeud
	
	private Double u; //Z+UB 
	private Double z; //z
	private Double zBest; //optimal Z
	private Double w; //optimal w barre
	
	
	
	
	public BranchNBound(ArrayList<Item> list, Double weight){
		problem = new Solver(list, weight);
		
		
		//remplir la map
		map = new HashMap<Integer, Item>();
		for(Iterator<Item> ite = list.iterator(); ite.hasNext();){
			Item tmp = ite.next();
			map.put(tmp.getNumber(), tmp);
		}
		
		n = list.size();
		n0 = new ArrayList<String>();
		n1 = new ArrayList<String>();
		root = new Node(index++,1);
		f  = new ArrayList<String>();
		
		w = weight;
		visited = new ArrayList<Integer>();
		mapItem = new HashMap<Integer, Node>();
		mapNode = new HashMap<Integer, Node>();
	}
	
	
	public void procedure(){
		
		//on initialise le probleme
		
		f.clear();
		n0.clear();
		n1.clear();
		
		
		for(int i = 1; i<=n; i++){
			Item a = map.get(i);
			String str = Integer.toString(map.get(i).getNumber());
			System.out.println(str);
			f.add( str); //eviter les pb lors de la suppression
		}
		
		
		//borne inferieur
		problem.solve();
		//z = problem.getLb();
		z = 0.0;
		
		//borne superieur
		zBest = 102.0;
		u = 0 + 102.0;
		
		//procedure du premier noeud
		//on initialise le noeud root
		root.setW(w - map.get(1).getWeight());
		
		//let's ride!!!!!
		depthFirst();
	}
	
	
	public void depthFirst(){
		
		Node tmp = traitementDeNoeud(problem.getWeightMax(), root);
		mapNode.put(tmp.getNumber(), tmp);
		System.out.println("Noeud: "+tmp.getNumber()+" Objet: "+tmp.getItem()+" Z: "+tmp.getZ()+" W: "+tmp.getW());
		depthFirstRec(tmp);
	}
	
	
	
	private void depthFirstRec(Node node){
				
		//sondage du noeud
		Node tmp = traitementDeNoeud(problem.getWeightMax(), node);
		
		if(tmp == null){
			System.out.println("FIN");
			return;
		}else{
			mapNode.put(tmp.getNumber(), tmp);
			System.out.println("Noeud: "+tmp.getNumber()+" Objet: "+tmp.getItem()+" Z: "+tmp.getZ()+" W: "+tmp.getW());
			depthFirstRec(tmp);
		}
	}
	
	/**
	 * return le numero du noeud du plus grand item (sens index)
	 * @param list
	 * @return
	 */
	private int maxOfList(ArrayList<String> list){	
		int max = 0;
		for(Iterator<String> ite = list.iterator(); ite.hasNext();){
			String tmp = ite.next();
			if(tmp != null){
				int a = Integer.parseInt(tmp);
				if(max < a){
					max = a;
				}
			}
		}
		
		Node tmp = mapItem.get(max);
		return tmp.getNumber();
	}
	
	

	public Node traitementDeNoeud(Double we, Node root){
	
		//peut etre mettre la condition d'arret ici ???
		if(map.get(root.getItem()).getWeight() > w){ //pb f devrais etre vide un moment donné
			
			if(root.getZ() >  u || f.isEmpty()){			
				//si c'est le dernier ou UB <= inc
				return backtrack(root);				
			}else{
				
				//on ne  prend pas
				//new node en 0; modifie le noeud root pr le vrai noeud			
				if(map.get(root.getItem()+1) == null){
					//dernier item
					root.setN0(new Node(index, root.getItem()));
					index++;
				}else{
					root.setN0(new Node(index, root.getItem()+1));
					index++;
				}
				
				String number = Integer.toString(map.get(root.getItem()).getNumber()); 
				f.remove(number);
				n0.add(root.getItem().toString());
				
							
				//a modif
				root.getN0().setW(root.getW());
				root.getN0().setZ(root.getZ());
				
				//pas de modif
				return root.getN0();
			}
			
		}else if (map.get(root.getItem()).getWeight() < w){
			//on prend
			//new node en 1; modifie le noeud root pr le vrai noeud
			
			
			if(map.get(root.getItem()+1) == null){
				root.setN1(new Node(index, root.getItem()+1));
				index++;
			}else{
				root.setN1(new Node(index, root.getItem()+1));
				index++;
			}
			
			f.remove(Integer.toString(map.get(root.getItem()).getNumber()));
			n1.add(root.getItem().toString());
			if(mapItem.get(root.getItem()) == null)
				mapItem.put(root.getItem(), root);
			
			w = w - map.get(root.getItem()).getWeight(); //pb a 4
			z = z + map.get(root.getItem()).getValue();
			
			if(z > zBest){
				zBest = z;
			}
			
			root.getN1().setW(w);
			root.getN1().setZ(z);
			
			return root.getN1();
		}
		return null;
	}

	
	
	
	private Node backtrack(Node root){
		//on sonde
		//backtracking
		
		//l = max j parmis N1 et on remonte au noeud xl = 0
		
		int l = maxOfList(n1);
				
		if(visited.contains(l)){
			System.out.println("la recurssion a fini. Le mode Hero !!!!!!! ^_^");	
			System.out.println("Noeud: "+root.getNumber()+" Objet: "+root.getItem()+" Z: "+root.getZ()+" W: "+root.getW());
			
			return null;
			//depthFirstRec(root);
		}else{
			System.out.println("on a visté "+l);
			visited.add(l);
		}
				
		
		
		//maj	
		Node tmp = mapNode.get(l);
		freeNodes(tmp.getItem(), root.getItem());
		
		tmp.setN0(new Node(index, tmp.getItem())); //penser au test et backtracker au pire
		index++;
		
		//f.remove(Integer.toString(map.get(tmp.getItem()).getNumber()));
		
		u = z;
		
		w = tmp.getW();
		z = tmp.getZ();
		
		tmp.getN0().setW(w);
		tmp.getN0().setZ(z);
		
		//depthFirstRec(tmp.getN0());
		
		return tmp.getN0();
	}
	
	
	
	private void freeNodes(int i, int j){
		
		for(int k = i; k<j; k++){
			Item tmp = map.get(k);
			if(n1.contains(Integer.toString(tmp.getNumber()))){
				n1.remove(Integer.toString(tmp.getNumber()));
				mapItem.remove(tmp.getNumber());
			}else if(n0.contains(Integer.toString(tmp.getNumber())))
				n0.remove(Integer.toString(tmp.getNumber()));
			
			String number = Integer.toString(map.get(k).getNumber());
			f.add(number);
			
			Node node = mapNode.get(k);
		}
	}
	
	public Double upperBound(){
		return 0.0;
	}
	
	public Double lowerBound(){
		return 0.0;
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
	public ArrayList<String> getN1() {
		return n1;
	}
	public void setN1(ArrayList<String> n1) {
		this.n1 = n1;
	}
	public ArrayList<String> getN0() {
		return n0;
	}
	public void setN0(ArrayList<String> n0) {
		this.n0 = n0;
	}
	public ArrayList<String> getF() {
		return f;
	}
	public void setF(ArrayList<String> f) {
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