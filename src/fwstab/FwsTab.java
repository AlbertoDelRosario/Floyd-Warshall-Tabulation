/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fwstab;
import java.io.*; 
/**
 *
 * @author usuario
 */
public class FwsTab {
    final static int INF = 99999;//, V = 4;
    private static int V;
    private static boolean debugIn = false;
    private static boolean debugOut = false;
    private static boolean debugTime = false;
    private static long startTime;
static class AllPairShortestPath 
{ 
	

	void floydWarshall(int graph[][]) 
	{ 
                
		int dist[][] = new int[V][V]; 
		int i, j, k; 

		/* Initialize the solution matrix same as input graph matrix. 
		Or we can say the initial values of shortest distances 
		are based on shortest paths considering no intermediate 
		vertex. */
		for (i = 0; i < V; i++) 
			for (j = 0; j < V; j++) 
				dist[i][j] = graph[i][j]; 
                // Debug input//////////////////////////////////////////////////////////
                if (debugIn == true){
                    // Print the input matrix
                    printSolution(dist,"Input vertexs weigth matrix");
                }
                ////////////////////////////////////////////////////////////////////////
		/* Add all vertices one by one to the set of intermediate 
		vertices. 
		---> Before start of an iteration, we have shortest 
			distances between all pairs of vertices such that 
			the shortest distances consider only the vertices in 
			set {0, 1, 2, .. k-1} as intermediate vertices. 
		----> After the end of an iteration, vertex no. k is added 
				to the set of intermediate vertices and the set 
				becomes {0, 1, 2, .. k} */
		for (k = 0; k < V; k++) 
		{ 
			// Pick all vertices as source one by one 
			for (i = 0; i < V; i++) 
			{ 
				// Pick all vertices as destination for the 
				// above picked source 
				for (j = 0; j < V; j++) 
				{ 
					// If vertex k is on the shortest path from 
					// i to j, then update the value of dist[i][j] 
					if (dist[i][k] + dist[k][j] < dist[i][j]) 
						dist[i][j] = dist[i][k] + dist[k][j]; 
				} 
			} 
		} 
                
                double stopTime = System.nanoTime();
                double elapsedTime = (stopTime - startTime)/1000000000;
                
		// Print the shortest distance matrix 
		// Debug output/////////////////////////////////////////////////////////
                if (debugOut == true){
                    // Print the shortest distance matrix 
                    printSolution(dist,"\nThe following matrix shows the shortest "
                                       + "distances between every pair of vertices");
                }
                ////////////////////////////////////////////////////////////////////////
                
                // Debug time//////////////////////////////////////////////////////
                if (debugTime == true){
                    System.out.println("--------------------------------------"
                                        + "\nTime: " + elapsedTime + " seconds");
                }
                ////////////////////////////////////////////////////////////////////////
	} 

	void printSolution(int dist[][], String msg){
        
        // Print all the given matrix's elements and a message
        System.out.println(msg); 
        for (int i=0; i<V; ++i){
            for (int j=0; j<V; ++j){
                if (dist[i][j]==INF){ 
                    System.out.print("INF\t"); 
                }else{
                    System.out.print(dist[i][j]+"\t");
                }
            }
            
            System.out.println(); 
        } 
    }

	// Driver program to test above function 
	
} 

// Contributed by Aakash Hasija 
public static void main (String[] args) throws IOException 
	{ 
                startTime = System.nanoTime();
		/* Let us create the following weighted graph 
		10 
		(0)------->(3) 
		|		 /|\ 
		5 |		 | 
		|		 | 1 
		\|/		 | 
		(1)------->(2) 
		3		 */
		/*int graph[][] = { {0, 5, INF, 10}, 
						{INF, 0, 3, INF}, 
						{INF, INF, 0, 1}, 
						{INF, INF, INF, 0} 
						}; */
                int graph[][] = null;
                // Read arguments and check for switches
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-di")){
                        debugIn = true;
                    }

                    if (args[i].equals("-do")){
                        debugOut = true;
                    }

                    if (args[i].equals("-dt")){
                        debugTime = true;
                    }

                    if (args[i].equals("-f") && i < args.length - 1){
                       graph = FileIO.initializeGraph(args[i+1]);
                       V = FileIO.getNumOfVertices();
                       i++;
                    }
                }
		AllPairShortestPath a = new AllPairShortestPath(); 

		// Print the solution 
                
		a.floydWarshall(graph); 
                
	} 

}
