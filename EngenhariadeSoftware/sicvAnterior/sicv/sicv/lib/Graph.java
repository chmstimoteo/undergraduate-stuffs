package sicv.lib;


public class Graph {
 
	
	private int nvertices;
	private Edge edges[][];
    private int degree[];
    private int nedges;
    private int parent[];
    private final int MAXINT = 1000000;
    private final int MAXV = 100;
    private final int MAXDEGREE = 10;
    private int distance[];
    private int lastStart;
    
    public Graph(int n){
    	this.edges = new Edge[MAXV+1][MAXDEGREE+1];
    	this.degree = new int[MAXV +1];
    	this.parent = new int[MAXV];
    	this.distance = new int[MAXV] ;
    	this.nvertices = n;
    	this.lastStart = -1;
    	
    }
    
    
    public void insertEdge(int x,int y,int weight,boolean directed)
    {
    	if (this.degree[x] > this.MAXDEGREE) {
			throw new RuntimeException("Warning:insertion("+ x + ","+ y + ")exceeds max degree\n");
		}
    	
    	this.edges[x][this.degree[x]] = new Edge(y,weight);
    	this.degree[x]++;
    	
    	if (directed == false) {
			insertEdge(y, x,weight, true);
		}else{
			this.nedges++;
		}
    	
    	
        	
    }
    
    
     
	public int getDistance(int source,int destination) {
	
		if (source != lastStart) {
			this.dijkstra(source);
			lastStart = source;
		}
		 
		return this.distance[destination];
	
	}
	
	
	 
	private void dijkstra(int start) {
	
	  int i;
	  boolean intree[] = new boolean[MAXV];
	  int v;
	  int w;
	  int weight;
	  int dist;
	  
	  for(i=1;i<=nvertices;i++){
		intree[i] = false;
		distance[i] = MAXINT;
	    parent[i] = -1;
	  }
	  
	  distance[start] = 0;
	  v = start;
	  
	
	  
	  while(intree[v] == false){
	  
	        intree[v] = true;
	        
	        for(i=0;i<degree[v];i++){
	        	w = edges[v][i].v;
	        	weight = edges[v][i].weight;
	        	if (distance[w] > (distance[v] + weight)) {
					distance[w] = distance[v] + weight;
					parent[w] = v;
				}
	        }
	  
	  
	  
	  
	  v = 1;
	  dist = MAXINT;
	  for(i=2;i<=nvertices;i++)
		  if (intree[i] == false && (dist > distance[i])) {
			dist = distance[i];
			v = i;
		  }
	  
	  } 

		  
	}
	 
	
	private class Edge {

		public Edge(int v,int weight) {
			this.v = v;
			this.weight = weight;
		}
	    int v;
	    int weight;
		
	  }
	
	
	
	 
}
 
