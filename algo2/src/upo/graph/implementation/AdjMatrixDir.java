package upo.graph.implementation;

import upo.graph.base.Graph;
import upo.graph.base.VisitForest;
import upo.graph.base.VisitForest.Color;
import upo.graph.base.VisitForest.VisitType;

import java.util.*;
import java.util.stream.Collectors;



public class AdjMatrixDir implements Graph{
	
	int dimensione;
	int[][] matrix;
	int tempoDiVisita = 0;
	int t;

	public AdjMatrixDir(int dimensione) {
		this.dimensione = dimensione;
		matrix = new int[dimensione][dimensione];
	}


	@Override
	public String getVertexLabel(int var1) {
		return null;
	}

	@Override
	public int getVertexIndex(String var1) {
		return Integer.parseInt(var1);
	}


	@Override
	public int addVertex() {
		//
		int newIndex = matrix.length;
		int supMatrix[][] = new int[matrix.length + 1][matrix.length + 1];
		// scorro la matrice
		for (int i = 0; i < matrix.length; ++i) {
			System.arraycopy(matrix[i], 0, supMatrix[i], 0, supMatrix[i].length-1 );
		}
		dimensione++;
		matrix = new int[dimensione][dimensione];
		//scorro la matrice

		for (int i = 0; i < matrix.length; ++i) {
			System.arraycopy(supMatrix[i], 0, supMatrix[i], 0, matrix[i].length-1 );
		}
		return newIndex;
	}


	@Override
	public boolean containsVertex(String indice){
		int indiceVertice = Integer.parseInt(indice);
		// scorro per intero la matrice
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++) if(i == indiceVertice && j == indiceVertice) return true;
		}
		return false;
	}

	@Override
	public void removeVertex(String indice) throws NoSuchElementException {
		int indiceVertice = Integer.parseInt(indice);
		//controllo indice sia maggiore di 0 e che l'indice sia contenuto nella matrice
		if(indiceVertice >= 0 && indiceVertice < matrix.length) {
			//creo una matrice di supporto
			int supMatrix[][] = new int[matrix.length - 1][matrix.length - 1];
			//scorro la matrice
			for(int i = 0; i < matrix.length; i ++){
				for(int j = 0; j < matrix.length; j ++){
					//finche non raggiungo l'indice scelto
					if(i != indiceVertice && j != indiceVertice){
						if((i > indiceVertice) && (j > indiceVertice)) 	supMatrix[i-1][j-1] = matrix[i][j];
						else if(i > indiceVertice) 						supMatrix[i-1][j] 	= matrix[i][j];
							 else if(j > indiceVertice) 				supMatrix[i][j-1] 	= matrix[i][j];
							 	  else 									supMatrix[i][j] 	= matrix[i][j];
					}
				}
			}
			dimensione--;
			matrix = new int[dimensione][dimensione];
			//scorro la matrice
			for (int i = 0; i < matrix.length; ++i) {
				System.arraycopy(supMatrix[i], 0, supMatrix[i], 0, matrix[i].length-1 );
			}
		}
		else throw new NoSuchElementException("Elemento non presente");
	}

	@Override
	public void addEdge(String indiceSorgenteVertice, String indiceDestinatarioVertice) throws IllegalArgumentException {
		int sorgenteVertice = Integer.parseInt(indiceSorgenteVertice);
		int DestinatarioVertice = Integer.parseInt(indiceDestinatarioVertice);
		// controlla che l'indice del vertice sorgente sia contenuto nella matrice o che l'indice non sia minore di 0
		if(sorgenteVertice >= matrix.length || sorgenteVertice < 0) throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		//controlla che l'indice del vercice target sia contenuto nella matrice o che l'indice non sia minore di 0
		if(DestinatarioVertice >= matrix.length || DestinatarioVertice < 0) throw new IllegalArgumentException("Argomento target (secondo argomento) in input non valido");
		//verifica che sia presente quell' arco con la containsEdge
		if(containsEdge(String.valueOf(sorgenteVertice), String.valueOf(DestinatarioVertice)) == false) matrix[sorgenteVertice][DestinatarioVertice] = 1;
	}

	@Override
	public boolean containsEdge(String indiceSorgenteVertice, String indiceDestinatarioVertice) throws IllegalArgumentException {
		int sorgenteVertice = Integer.parseInt(indiceSorgenteVertice);
		int DestinatarioVertice = Integer.parseInt(indiceDestinatarioVertice);
		// controlla che l'indice del vertice sorgente sia contenuto nella matrice o che l'indice non sia minore di 0
		if(sorgenteVertice >= matrix.length || sorgenteVertice < 0) throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		//controlla che l'indice del vercice target sia contenuto nella matrice o che l'indice non sia minore di 0
		if(DestinatarioVertice >= matrix.length || DestinatarioVertice < 0) throw new IllegalArgumentException("Argomento target (secondo argomento) in input non valido");
		// controlla che nella posizione ci sia 1, esso indica la presenza di un arco
		if(matrix[sorgenteVertice][DestinatarioVertice] == 1) return true;
		return false;
	}

	@Override
	public void removeEdge(String indiceSorgenteVertice, String indiceDestinatarioVertice) throws IllegalArgumentException, NoSuchElementException {
		int sorgenteVertice = Integer.parseInt(indiceSorgenteVertice);
		int DestinatarioVertice = Integer.parseInt(indiceDestinatarioVertice);
		if(sorgenteVertice >= matrix.length || sorgenteVertice < 0) throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		if(DestinatarioVertice >= matrix.length || DestinatarioVertice < 0) throw new IllegalArgumentException("Argomento target (secondo argomento) in input non valido");
		if(containsEdge(String.valueOf(sorgenteVertice), String.valueOf(DestinatarioVertice)) == true) matrix[sorgenteVertice][DestinatarioVertice] = 0;
	}

	@Override
	public Set<String> getAdjacent(String indiceSorgenteVertice) throws NoSuchElementException {
		int sorgenteVertice = Integer.parseInt(indiceSorgenteVertice);
		//controllo la presenza del vertice
		if(containsVertex(String.valueOf(sorgenteVertice)) == false) throw new NoSuchElementException("Vertice non presente");
		// creo l'insieme di vertici adiacenti
		Set<Integer> setAdjVertex = new HashSet<>();
		for(int i = 0; i < matrix.length; i++) {
			//scorro solo la matrice nel vertexIndex
			if(i == sorgenteVertice){
				for(int j = 0; j < matrix.length; j++) if(matrix[i][j] == 1) setAdjVertex.add(j);
			}
		}
		Set<String> items = setAdjVertex.stream().map(String::valueOf).collect(Collectors.toSet());
		return items;
	}

	@Override
	public boolean isAdjacent(String indiceSorgenteVertice, String indiceDestinatarioVertice) throws IllegalArgumentException {
		int sorgenteVertice = Integer.parseInt(indiceSorgenteVertice);
		int DestinatarioVertice = Integer.parseInt(indiceDestinatarioVertice);
		if(sorgenteVertice >= matrix.length || sorgenteVertice < 0) throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		if(DestinatarioVertice >= matrix.length || DestinatarioVertice < 0) throw new IllegalArgumentException("Argomento target (secondo argomento) in input non valido");
		//controlla che ci sia un arco
		if(containsEdge(String.valueOf(sorgenteVertice), String.valueOf(DestinatarioVertice)) == true) return true;
		return false;
	}

	@Override
	public int size() {
		//per indicare la size usa la lenght della matrice
		return matrix.length;
	}

	@Override
	public boolean isDirected() {
		return true;
	}

	@Override
	public boolean isCyclic() {
		VisitForest graph = new VisitForest(this, null);
		for(int i = 0; i < this.size(); i++) if(graph.getColor(String.valueOf(i)) == Color.WHITE && VisitaRicCyclic(graph, String.valueOf(i))) return true;
		return false;
	}


	@Override
	public boolean isDAG() {
		// se non è aciclico e diretto allora è un dag
		if(!isCyclic() && isDirected()) return true;
		return false;
	}

	@Override
	public VisitForest getBFSTree(String verticeIniziale) throws UnsupportedOperationException, IllegalArgumentException {
		int verticeInizio = Integer.parseInt(verticeIniziale);
		if(verticeInizio >= matrix.length || verticeInizio < 0) throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		VisitForest visitBFS = new VisitForest(this, VisitType.BFS);
		BFSVisit(visitBFS, verticeIniziale);
		return visitBFS;
	}


	@Override
	public VisitForest getDFSTree(String verticeIniziale) throws UnsupportedOperationException, IllegalArgumentException {
		int verticeInizio = Integer.parseInt(verticeIniziale);
		if(verticeInizio >= matrix.length || verticeInizio < 0) throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		// creo l'oggetto visitDFS
		VisitForest visitDFS = new VisitForest(this, VisitType.DFS);
		// uso vista dfs ricorsiva
		DFSVisitRic(visitDFS, verticeIniziale);
		return visitDFS;
	}


	@Override
	public VisitForest getDFSTOTForest(String verticeIniziale) throws UnsupportedOperationException, IllegalArgumentException {
		int verticeInizio = Integer.parseInt(verticeIniziale);
		if(verticeInizio >= matrix.length || verticeInizio < 0) throw new IllegalArgumentException("Argomento sorgente (primo argomento) in input non valido");
		// creo un oggetto forest
		VisitForest visitForest = new VisitForest(this, VisitType.DFS_TOT);
		// eseguo la DFSVisitaRic()
		DFSVisitRic(visitForest, verticeIniziale);
		// scorro ma 1 riga della matrice
		for(int i = 0; i < matrix.length; i++){
			 //faccio la DFSVisitRic dei soli elementi della forensta bianchi
			 if(visitForest.getColor(String.valueOf(i)) == Color.WHITE) DFSVisitRic(visitForest, String.valueOf(i));
		}
		// ritorno l'oggetto della foresta
		return visitForest;
	}
	
	@Override
	public VisitForest getDFSTOTForest(String[] vertexOrdering)throws UnsupportedOperationException, IllegalArgumentException{
		// TODO su tutto il vertexOrdering per la illegalArgument
		int[] values = Arrays.stream(vertexOrdering).mapToInt(Integer::parseInt).toArray();
		//creo un oggetto forest
		VisitForest visitForest = new VisitForest(this, VisitType.DFS_TOT);
		// scorro l'array di vertici ordinati
		for(int i = 0; i < vertexOrdering.length; i++) {
			//se i vertici sono bianchi allo chiamo la DFSVisitRic()
			if(visitForest.getColor(String.valueOf(values[i])) == Color.WHITE) DFSVisitRic(visitForest, String.valueOf(values[i]));
		}
		return visitForest;
	}

	@Override
	public String[] topologicalSort() throws UnsupportedOperationException {
		t = matrix.length - 1;
		// per l'ordinamento topologico deve essere dag
		if(isDAG()) {
			// creo un array per ordinare con lunghezza uguale alla matrice
			int[] ord = new int[matrix.length];
			String[] stringNums = new String[ord.length];
			VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
			for(int i = 0; i < matrix.length; i++) if(forest.getColor(String.valueOf(i)) == Color.WHITE) DFSTopological(forest, i, ord);
			for(int i = 0; i < ord.length; i++) System.out.println("ORD " +i +": "+ ord[i]);
			int j = 0;
			while (j < ord.length) stringNums[j] = String.valueOf(ord[j++]);
			return stringNums;
		}
		return null;
	}

	@Override
	public Set<Set<String>> stronglyConnectedComponents() throws UnsupportedOperationException {
		
		int[] sortedEndVisit = new int[matrix.length];
		Random random = new Random();
		int randomVertex = random.nextInt(matrix.length - 1);
		System.out.println("StartingVertex: " + randomVertex);
		//visita tutti i vertici dfs
		VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
		DFSVisitRic(forest, String.valueOf(randomVertex));
		for(int i = 0; i < matrix.length; i++) {
			 sortedEndVisit[i] = i;
			 if(forest.getColor(String.valueOf(i)) == Color.WHITE) DFSVisitRic(forest, String.valueOf(i));
		}
		
		//ordinamento
		int varTemporanea;
		for (int k = 0; k < matrix.length; k++){
	           for (int j = k + 1; j < matrix.length; j++){
	               if (forest.getEndTime(String.valueOf(sortedEndVisit[k])) < forest.getEndTime(String.valueOf(sortedEndVisit[j]))){
	                   varTemporanea 		= sortedEndVisit[k];
	                   sortedEndVisit[k] 	= sortedEndVisit[j];
	                   sortedEndVisit[j] 	= varTemporanea;
	               }
	           }
	      }
		for(int n = 0; n < matrix.length; n ++) System.out.println("Ordinato secondo f[] : " + sortedEndVisit[n]);

		//grafo trasposto
		AdjMatrixDir grafoTrasposto = new AdjMatrixDir(matrix.length);
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) if(containsEdge(String.valueOf(i), String.valueOf(j))) grafoTrasposto.addEdge(String.valueOf(j), String.valueOf(i));
		}
		
		Set<Set<Integer>> set = new HashSet<>();
		Set<Set<String>> set2 = new HashSet<>();

		Set<Integer> setAux = new TreeSet<>();
		VisitForest transposedForest = new VisitForest(grafoTrasposto, VisitType.DFS_TOT);
		for(int i = 0; i < sortedEndVisit.length; i++) {
			if(transposedForest.getColor(String.valueOf(sortedEndVisit[i])) == Color.WHITE){
				setAux = grafoTrasposto.getDFSTreeModified(sortedEndVisit[i], transposedForest);
				set2.add(Collections.singleton(setAux.toString()));
				set.add(setAux);
			}
		}
		Object[] setArray= set.toArray();
		System.out.println("SET");
		for(int i = 0; i < set.size(); i++) System.out.println(setArray[i] + " DIM=" + set.size());
		return set2;
	}

	@Override
	public Set<Set<String>> connectedComponents() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Questa operazione non può essere effettuata su grafi diretti");
	}
	private Set<Integer> getDFSTreeModified(int startingVertex, VisitForest transposedForest) {
		Set<Integer> setAux = new TreeSet<>();
		DFSVisitRicModified(transposedForest, startingVertex, setAux);
		return setAux;
	}

	private void DFSVisitRicModified(VisitForest visitDFS, int startingVertex, Set<Integer> set) {
		visitDFS.setColor(String.valueOf(startingVertex), Color.GRAY);
		visitDFS.setStartTime(String.valueOf(startingVertex), tempoDiVisita);
		tempoDiVisita++;
		set.add(startingVertex);
		Object[] elementiAdiacenti = getAdjacent(String.valueOf(startingVertex)).toArray();
		Set<String> listaAdiacenti2= getAdjacent(String.valueOf(startingVertex));
		Set<Integer> setOfInteger = listaAdiacenti2.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet());
		int[] array = setOfInteger.stream().mapToInt(Number::intValue).toArray();
		String[] arrayOfString = new String[listaAdiacenti2.size()];
		int index = 0;
		for (String str : listaAdiacenti2) arrayOfString[index++] = str;
 		for(int i = 0; i < getAdjacent(String.valueOf(startingVertex)).size(); i++){
 			if(visitDFS.getColor(arrayOfString[i]) == Color.WHITE) {
 				visitDFS.setParent(arrayOfString[i], String.valueOf(startingVertex));
 				DFSVisitRicModified(visitDFS, array[i], set);
 			}
 		}
 		visitDFS.setColor(String.valueOf(startingVertex), Color.BLACK);
 		visitDFS.setEndTime(String.valueOf(startingVertex), tempoDiVisita);
		tempoDiVisita++;
	}

	private boolean VisitaRicCyclic(VisitForest graph, String u) {
		graph.setColor(u, Color.GRAY);
		Object[] arrayElementiAdiacenti = getAdjacent(u).toArray();
		for(int i = 0; i < this.getAdjacent(u).size(); i++) {
			if(graph.getColor((String) arrayElementiAdiacenti[i]) == Color.WHITE){
				graph.setParent((String) arrayElementiAdiacenti[i], u);
				if(VisitaRicCyclic(graph, (String) arrayElementiAdiacenti[i])) return true;
			}
			else if(graph.getColor((String) arrayElementiAdiacenti[i]) == Color.GRAY) return true;
		}
		graph.setColor(u, Color.BLACK);
		return false;
	}

	private void DFSVisitRic(VisitForest visitDFS, String verticeInizio){
		//imposto il vertice di partenza grigio
		visitDFS.setColor(verticeInizio, Color.GRAY);
		//imposto il vertice di partenza a 0
		visitDFS.setStartTime(verticeInizio, tempoDiVisita);
		System.out.println("apro il vertice: "+ verticeInizio +" in tempo= "+tempoDiVisita);
		tempoDiVisita++;
		Object[] arrayElementiAdiacenti = getAdjacent(verticeInizio).toArray();
		for(int i = 0; i< getAdjacent(verticeInizio).size(); i++) {
			if(visitDFS.getColor(String.valueOf(arrayElementiAdiacenti[i])) == Color.WHITE) {
				visitDFS.setParent(String.valueOf(arrayElementiAdiacenti[i]), String.valueOf(verticeInizio));
				DFSVisitRic(visitDFS, String.valueOf(arrayElementiAdiacenti[i]));
			}
		}
		visitDFS.setColor(verticeInizio, Color.BLACK);
		System.out.println("chiudo il vertice: "+ verticeInizio +" in tempo= "+tempoDiVisita);
		visitDFS.setEndTime(verticeInizio, tempoDiVisita);
		tempoDiVisita++;
	}

	private void DFSTopological(VisitForest forest, int u, int[] ord) {
		//imposto il vertice u grigio
		forest.setColor(String.valueOf(u), Color.GRAY);
		// imposto il vertice u a 0
		forest.setStartTime(String.valueOf(u), tempoDiVisita);
		System.out.println("apro il vertice: "+ u +" in tempo= "+tempoDiVisita);
		tempoDiVisita++;
		Object[] listaAdiacenti = getAdjacent(String.valueOf(u)).toArray();
		Set<String> listaAdiacenti2= getAdjacent(String.valueOf(u));
		Set<Integer> setOfInteger = listaAdiacenti2.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet());
		int[] array = setOfInteger.stream().mapToInt(Number::intValue).toArray();
		String[] arrayOfString = new String[listaAdiacenti2.size()];
		// Copy elements from set to string array
		// using advanced for loop
		int index = 0;
		for (String str : listaAdiacenti2)
			arrayOfString[index++] = str;
		for(int i = 0; i< getAdjacent(String.valueOf(u)).size(); i++){
			if(forest.getColor(arrayOfString[i]) == Color.WHITE){
				forest.setParent(arrayOfString[i],String.valueOf(u));
				DFSTopological(forest, array[i], ord);
			}
		}
		forest.setColor(String.valueOf(u), Color.BLACK);
		System.out.println("chiudo il vertice: "+ u +" in tempo= "+tempoDiVisita);
		forest.setEndTime(String.valueOf(u), tempoDiVisita);
		tempoDiVisita++;
		ord[t] = u;
		System.out.println(ord[t]);
		t--;
	}

	private void BFSVisit(VisitForest visitBFS, String verticeInizio){
		//creo una coda
		Queue<Integer> coda = new LinkedList<>();
		//imposto il colore del primo vertice grigio
		visitBFS.setColor(verticeInizio, Color.GRAY);
		//imposto la distanza del primo vertice a 0
		visitBFS.setDistance(verticeInizio, 0);
		//aggiungo il primo vertice alla coda
		coda.add(Integer.valueOf(verticeInizio));
		//stampo gli elementi della coda
		System.out.println(coda);
		//scorro la coda finche ci sono elementi
		while(!coda.isEmpty()) {
			// salvo la testa della coda
			int testaCoda = coda.peek();
			// creo un array di vertici adiacenti
			Object[] elementiAdiacenti = getAdjacent(String.valueOf(testaCoda)).toArray();
			Set<String> listaAdiacenti2= getAdjacent(String.valueOf(testaCoda));
			Set<Integer> setOfInteger = listaAdiacenti2.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet());
			int[] array = setOfInteger.stream().mapToInt(Number::intValue).toArray();
			String[] arrayOfString = new String[listaAdiacenti2.size()];
			// Copy elements from set to string array
			// using advanced for loop
			int index = 0;
			for (String str : listaAdiacenti2) arrayOfString[index++] = str;
			System.out.println(testaCoda);
			for(int i = 0; i < this.getAdjacent(String.valueOf(testaCoda)).size(); i++) {
				//se l'elemento dell'array di vertici adiacenti è bianco
				if(visitBFS.getColor(arrayOfString[i]) == Color.WHITE){
					//imposto l'elemento dell'array di colore grigio
					visitBFS.setColor(arrayOfString[i], Color.GRAY);
					//imposto l'elemento di setParent
					visitBFS.setParent(arrayOfString[i], String.valueOf(testaCoda));
					//imposto la distanza degli elementi
					visitBFS.setDistance(arrayOfString[i],visitBFS.getDistance(String.valueOf(testaCoda)) + 1);
					//aggiungo l'elemento dell'array alla coda
					//int element= (int) elementiAdiacenti[i];
					coda.add(array[i]);
				}
			}
			//dopo il ciclo impon
			visitBFS.setColor(String.valueOf(testaCoda), Color.BLACK);
			coda.remove(testaCoda);
		}
		System.out.println(coda.toString());
	}


}
