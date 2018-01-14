
public class Edge {
	private final int v;
	private final int w;
	private final double weight;
	
	public Edge(int v, int w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	public double weight(){
		return weight;
	}
	public int to(){
		return w;
	}
	public int from(){
		return v;
	}
	public String toString(){
		return  String.valueOf(v) + "-->" + String.valueOf(w) + "\n";
	}
}
