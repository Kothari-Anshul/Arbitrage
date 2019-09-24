import java.util.HashSet;
import java.math.*;
public class Arbitrage {
	private String map[];
	private final int V;
	private WeightedDigraph G;
	private boolean hasNegativeCycle;
	private Edge[] edgeTo;
	private double[] distTo;
	private int v_in_cycle;
	public Arbitrage(double[] rates,String map[],int V){
		this.V = V;
		this.map = map;
		edgeTo = new Edge[V];
		distTo = new double[V];
		
		
		G = new WeightedDigraph(V);
		initialize_graph(rates);
		
		//initialize distTo array
		for(int v=0; v<V; v++){
			distTo[v] = Double.MAX_VALUE;
		}
		distTo[0] = 0;
		// Bellman ford Algorithm for negative cycle detection
		Queue<Integer> q = new Queue<Integer>();
		// initialize the set with all the verties
		for(int i=0 ; i<V;i++){
			q.enqueue(i);
		}
		for(int i=0; i<V; i++){
			Queue<Integer> nq = new Queue<Integer>();
			while(q.isEmpty() == false){
				int v = q.dequeue();
				for(Edge e: G.adj(v)){
					relax(e,nq);
				}
			}
			if(i == V-1 && nq.isEmpty() == false){
				hasNegativeCycle = true;
				v_in_cycle = nq.dequeue();
				break;
			}
			q = nq;
			
		}
	}
	public void relax( Edge e,Queue<Integer> q){
		int v = e.from();
		int w = e.to();
		if(dist[v] != Double.MAX_VALUE && distTo[w] > distTo[v] + e.weight()){
			q.enqueue(w);
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}
	
	private void initialize_graph(double[] rates){
		int k = 0;
		for(int i=0; i<V; i++){
			for(int j=0; j<V; j++){
				if(i != j){
					G.addEdge(new Edge(i,j,-Math.log(rates[k++])));
				}
			}
		}
	}
	public boolean hasNegativeCycle(){
		return hasNegativeCycle;
	}
	public String get_negative_cycle(){
		StringBuilder str = new StringBuilder();
		int v = edgeTo[v_in_cycle].from();
		str.append(convert_toString(edgeTo[v_in_cycle]));
		for(int i = v; i != v_in_cycle; i = edgeTo[i].from()){
			str.append(convert_toString(edgeTo[i]));
		}
		return str.toString();
		
	}
	public String convert_toString(Edge e){
		int v = e.from();
		int w = e.to();
		return map[v] + " ---> " + map[w] + "\n";
	}
	public static void main(String a[]){
		double rates[] = {10,3,2,-8,10,4};
		String map[] = {"USD","EUR","INR"};
		Arbitrage arbitrage = new Arbitrage(rates,map,3);
		if(arbitrage.hasNegativeCycle()){
			System.out.println(arbitrage.get_negative_cycle());
		}
		
		
	}

	

}
