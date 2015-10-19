package knapsack.BranchAndBound;

public class Config {

	private double z;
	private double c;
	private boolean[] x;
	
	
	public Config(){}
	
	
	public Config(double z, double c, boolean[] x){
		this.z = z;
		this.c = c;
		this.x = x.clone();
	}
	
	@Override
	public String toString(){
		String str = "";
		
		str += "Z = "+z+"\n";
		str += "C = "+c+"\n";
		str += "On prend: \n";
		
		for(int i = 0; i < x.length ; i++){
			if(x[i] == true){
				int l = i + 1;
				str += ","+l;
			}else
				str += ",0";
		}
		
	
		return str;
	}
	
}
