package upo.graph.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Classe che rappresenta una foresta di visita (o albero di visita, per determinati tipi di visite).
 * Deve essere usata per rappresentare l'output di una visita.
 * Questa classe non deve essere modificata. Se trovate errori contattate il docente.
 * 
 * @author Luca Piovesan
 *
 */
public class VisitForest {
	private final Graph graph;
	private Color[] vertexColor;
	private Double[] distance;
	private Integer[] parent;
	private int[] startTime;
	private int[] endTime;
	public enum Color {WHITE, GRAY, BLACK}
	public final VisitType visitType;
	public enum VisitType {BFS, DFS, DFS_TOT}

	/*---------------------------------------------------*/


	public VisitForest(Graph graph, VisitType visitType) {
		this.graph = graph;
		this.visitType = visitType;
		this.initialize();
	}
	
	private void initialize() {
		vertexColor = new Color[graph.size()];
		Arrays.fill(vertexColor, Color.WHITE);
		parent = new Integer[graph.size()];
		Arrays.fill(parent, null);
		distance = new Double[graph.size()];
		Arrays.fill(distance, null);
		startTime = new int[graph.size()];
		Arrays.fill(startTime, -1);
		endTime = new int[graph.size()];
		Arrays.fill(endTime, -1);
	}

	public Set<String> getRoots() {
		Set<String> res = new HashSet();

		for(int i = 0; i < this.parent.length; ++i) {
			if (this.parent[i] == null) {
				res.add(this.graph.getVertexLabel(i));
				//devo sistemare la getVertexLabel
			}
		}
		return res;
	}

	public Color getColor(String vertex) throws NoSuchElementException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else {
			return this.vertexColor[this.graph.getVertexIndex(vertex)];
		}
	}

	public void setColor(String vertex, Color color) throws NoSuchElementException, IllegalArgumentException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else if (this.vertexColor[this.graph.getVertexIndex(vertex)].compareTo(color) > 0) {
			throw new IllegalArgumentException("Il colore di un vertice non puo' passare da GRAY a WHITE o da BLACK a GRAY o WHITE");
		} else {
			this.vertexColor[this.graph.getVertexIndex(vertex)] = color;
		}
	}

	public String getPartent(String vertex) throws NoSuchElementException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else {
			return this.graph.getVertexLabel(this.parent[this.graph.getVertexIndex(vertex)]);
		}
	}

	public void setParent(String vertex, String parent) throws NoSuchElementException, IllegalArgumentException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else if (this.graph.getVertexIndex(parent) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + parent + " non appartiene al grafo");
		} else {
			this.parent[this.graph.getVertexIndex(vertex)] = this.graph.getVertexIndex(parent);
		}
	}

	public Double getDistance(String vertex) throws NoSuchElementException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else {
			return this.distance[this.graph.getVertexIndex(vertex)];
		}
	}

	public void setDistance(String vertex, double distance) throws NoSuchElementException, IllegalArgumentException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else {
			this.distance[this.graph.getVertexIndex(vertex)] = distance;
		}
	}

	public int getStartTime(String vertex) throws NoSuchElementException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else {
			return this.startTime[this.graph.getVertexIndex(vertex)];
		}
	}

	public void setStartTime(String vertex, int startTime) throws NoSuchElementException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else {
			this.startTime[this.graph.getVertexIndex(vertex)] = startTime;
		}
	}

	public int getEndTime(String vertex) throws NoSuchElementException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else {
			return this.endTime[this.graph.getVertexIndex(vertex)];
		}
	}

	public void setEndTime(String vertex, int endTime) throws NoSuchElementException {
		if (this.graph.getVertexIndex(vertex) >= this.graph.size()) {
			throw new NoSuchElementException("Il vertice di indice " + vertex + " non appartiene al grafo");
		} else {
			this.endTime[this.graph.getVertexIndex(vertex)] = endTime;
		}
	}
}
