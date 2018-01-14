import java.util.HashSet;

public class WeightedDigraph {
	private final int V;
	private HashSet<Edge> a[];
	public WeightedDigraph(int V){
		this.V = V;
		a = (HashSet<Edge>[])(new HashSet[V]);
		for(int v=0; v<V;v++){
			a[v] = new HashSet<Edge>();
		}
	}
	public void addEdge(Edge e){
		int v = e.from();
		a[v].add(e);
	}
	public Iterable<Edge> adj(int v){
		return a[v];
	}
}
