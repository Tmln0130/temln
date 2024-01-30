package lab7;
import dataStructures.ArrayLinearList; 

public class Dijkistra {
	public static void main(String[]args) {
		
		graph thegraph = new graph(5);
		thegraph.addEdge(0,1,6);
		thegraph.addEdge(0,3,2);
		thegraph.addEdge(1,3,2);
		thegraph.addEdge(3,4,1);
		thegraph.addEdge(0,1,6);
		
	}
}
class graph {
	int[][]g;
	ArrayLinearList visited = new ArrayLinearList();
	ArrayLinearList unvisited = new ArrayLinearLIst();
	ArrayLinearList table = new ArrayLinearList();
	
	public graph(int size)
	{
		g = new 
	int [size][size];
	for{int i=0; j<g.length; i++)
		for (int j =0;j<g.length;j++)
			g[i][j] = 0;
	}
	}
			
}
