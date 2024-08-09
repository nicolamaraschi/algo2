package upo.graph.implementation;

import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Implementazione mediante <strong>matrice di adiacenza</strong> di un grafo <strong>orientato pesato</strong>.
 * 
 * @author Nome Cognome Matricola
 *
 */
public class
AdjMatrixDirWeight implements WeightedGraph{

	private int dimensione;
	private int[][] matrix;
	private ArrayList<String> vertici;

	private VisitForest visitForest;


	public AdjMatrixDirWeight(int dimensione) {
		this.dimensione = dimensione;
		this.vertici= new ArrayList<>(dimensione);
		matrix = new int[dimensione][dimensione];
	}

	public AdjMatrixDirWeight() {
		matrix = new int[dimensione][dimensione];
	}

	@Override
	public String getVertexLabel(int var1) {
		return null;
	}

	@Override
	public int getVertexIndex(String var1) {
		return 0;
	}

	@Override
	public int addVertex() {

		return 0;
	}

	@Override
	public boolean containsVertex(String index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeVertex(String index) throws NoSuchElementException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(String sourceVertexIndex, String targetVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsEdge(String sourceVertexIndex, String targetVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeEdge(String sourceVertexIndex, String targetVertexIndex)
			throws IllegalArgumentException, NoSuchElementException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> getAdjacent(String vertexIndex) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdjacent(String targetVertexIndex, String sourceVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCyclic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDAG() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VisitForest getBFSTree(String startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitForest getDFSTree(String startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitForest getDFSTOTForest(String startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitForest getDFSTOTForest(String[] vertexOrdering)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] topologicalSort() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Set<String>> stronglyConnectedComponents() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Set<String>> connectedComponents() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEdgeWeight(String sourceVertexIndex, String targetVertexIndex)
			throws IllegalArgumentException, NoSuchElementException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEdgeWeight(String sourceVertexIndex, String targetVertexIndex, double weight)
			throws IllegalArgumentException, NoSuchElementException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WeightedGraph getBellmanFordShortestPaths(String verticePartenza)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeightedGraph getDijkstraShortestPaths(String startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeightedGraph getPrimMST(String startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeightedGraph getKruskalMST() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeightedGraph getFloydWarshallShortestPaths() throws UnsupportedOperationException {

		WeightedGraph weightedGraph;
		weightedGraph= (WeightedGraph) new AdjMatrixDir(matrix.length);

		int i, j, k;

		for (i = 0; i < matrix.length; i++)
			for (j = 0; j < matrix.length; j++) matrix[i][j] = matrix[i][j];

		// Adding vertices individually
		for (k = 0; k < matrix.length; k++) {
			for (i = 0; i < matrix.length; i++) {
				for (j = 0; j < matrix.length; j++) {
					if (matrix[i][j] > matrix[i][k] + matrix[k][j]) matrix[i][j] = matrix[i][k] + matrix[k][j];
				}
			}
		}
		//printMatrix(matrix);

		return weightedGraph;

	}

}
