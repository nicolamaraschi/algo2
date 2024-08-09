package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upo.graph.base.VisitForest;
import upo.graph.implementation.AdjMatrixDir;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AdjMatrixDirTest {

	AdjMatrixDir graph;

	@BeforeEach
	public void init() {
		graph = new AdjMatrixDir(0);
	}
	
	@Test
	void testAddVertex() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		assertEquals(4, graph.size());
	}

	@Test
	void containsVertexTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		assertTrue(graph.containsVertex("0"));
		assertTrue(graph.containsVertex("1"));
		assertTrue(graph.containsVertex("2"));
		assertTrue(graph.containsVertex("3"));
	}
	
	@Test
	void removeVertexTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		
		graph.removeVertex("3");
		assertEquals(3, graph.size());
		assertTrue(graph.containsVertex("0"));
		assertTrue(graph.containsVertex("1"));
		assertTrue(graph.containsVertex("2"));
		graph.removeVertex("2");
		assertEquals(2, graph.size());
		assertTrue(graph.containsVertex("0"));
		assertTrue(graph.containsVertex("1"));
	}
	
	@Test
	void addEdgeTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("0", "2");
		graph.addEdge("0", "3");

		assertTrue(graph.containsEdge("0", "1"));
		assertFalse(graph.containsEdge("1", "2"));
		assertTrue(graph.containsEdge("0", "2"));
		assertTrue(graph.containsEdge("0", "3"));
		//assertTrue(graph.containsEdge("3", "4"));

	}
	
	@Test
	void containsEdgeTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("0", "2");
		graph.addEdge("0", "3");

		assertTrue(graph.containsEdge("0", "1"));
		assertFalse(graph.containsEdge("1", "2"));
		assertTrue(graph.containsEdge("0", "2"));
		assertFalse(graph.containsEdge("2", "1"));
		assertTrue(graph.containsEdge("0", "3"));

	}
	
	@Test
	void removeEdgeTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("1","2");
		graph.addEdge("2", "3");

		assertTrue(graph.containsEdge("0", "1"));
		assertTrue(graph.containsEdge("1", "2"));
		assertTrue(graph.containsEdge("2", "3"));

		graph.removeEdge("0", "1");

		assertFalse(graph.containsEdge("0", "1"));
		assertTrue(graph.containsEdge("1", "2"));

		graph.removeEdge("1", "2");

		assertFalse(graph.containsEdge("1", "2"));
		assertTrue(graph.containsEdge("2", "3"));

		graph.removeEdge("2", "3");
		assertFalse(graph.containsEdge("2", "3"));


	}

	@Test
	void getAdjacentTest() {
		Set<String> setAdjVertex = new HashSet<>();
		setAdjVertex.add("1");
		setAdjVertex.add("3");
		setAdjVertex.add("2");

		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("0", "3");
		graph.addEdge("0", "2");
		
		assertEquals(setAdjVertex, graph.getAdjacent("0"));
	}
	
	@Test
	void isAdjacentTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("2", "3");

		// ho messo tutti al contrario
		assertFalse(graph.isAdjacent("1", "0"));
		assertFalse(graph.isAdjacent("3", "2"));
		assertTrue(graph.isAdjacent("0", "1"));
		assertTrue(graph.isAdjacent("2", "3"));
	}
	
	@Test
	void sizeTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		assertEquals(6, graph.size());
	}
	
	@Test
	void isCyclicTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("1", "3");
		graph.addEdge("2", "1");
		graph.addEdge("3", "4");
		assertFalse(graph.isCyclic());
		graph.addEdge("3", "5");
		graph.addEdge("5", "6");
		graph.addEdge("5", "2");
		graph.addEdge("4", "0");
		assertTrue(graph.isCyclic());
	}

	@Test
	void isDAGTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("1","3");
		graph.addEdge("1", "2");
		graph.addEdge("2","4");
		graph.addEdge("2", "5");
		graph.addEdge("1", "6");
		assertTrue(graph.isDAG());

		graph.addEdge("5", "3");
		graph.addEdge("3", "2");
		assertFalse(graph.isDAG());
	}

	@Test
	void getBFSTreeTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "2");
		graph.addEdge("1", "3");
		graph.addEdge("0", "2");
		graph.addEdge("2", "3");
		graph.addEdge("3", "0");


		VisitForest visitDFSTest ;
		visitDFSTest = graph.getBFSTree("0");

		double[] expectedDistance={0,1,1,2};

		assertEquals((Double) expectedDistance[0], visitDFSTest.getDistance(String.valueOf(0)));
		assertEquals((Double) expectedDistance[1], visitDFSTest.getDistance(String.valueOf(2)));
		assertEquals((Double) expectedDistance[2], visitDFSTest.getDistance(String.valueOf(2)));
		assertEquals((Double) expectedDistance[3], visitDFSTest.getDistance(String.valueOf(3)));

	}

	@Test
	void getDFSTreeTest() {
		System.out.println("TEST DFS");
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("1", "3");
		graph.addEdge("0", "2");
		graph.addEdge("2", "3");
		graph.addEdge("3", "0");
		
		int[] expectedStartTime = {0, 1, 5, 2};
		int[] expectedEndTime = {7, 4, 6, 3};
		
		VisitForest visitDFSTest;
		visitDFSTest = graph.getDFSTree("0");

		for(int  i = 0; i < graph.size(); i++) {
			assertEquals(expectedStartTime[i], visitDFSTest.getStartTime(String.valueOf(i)));
			assertEquals(expectedEndTime[i], visitDFSTest.getEndTime(String.valueOf(i)));
		}

	}
	
	@Test
	void getDFSTOTForestTest() {
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("2", "3");
	
		System.out.println("\nTEST DFS_TOT");
		VisitForest visitDFSTest ;
		visitDFSTest = graph.getDFSTOTForest("3");
		
		int[] expectedStartTime = {2, 3, 6, 0};
		int[] expectedEndTime = {5, 4, 7, 1};
		
		for(int i = 0; i < graph.size(); i++) {
			assertEquals(expectedStartTime[i], visitDFSTest.getStartTime(String.valueOf(i)));
			assertEquals(expectedEndTime[i], visitDFSTest.getEndTime(String.valueOf(i)));
		}
		System.out.println();
	}

	
	@Test
	void getDFSTOTForestOrder() {
		String[] ary = {"3", "1", "0", "2"};

		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("2", "3");

		System.out.println("\nTEST DFS_TOT_ORDER");
		
		//System.out.println("\nTEST DFS_TOT");
		VisitForest visitDFSTest ;
		visitDFSTest = graph.getDFSTOTForest(ary);
		
		int[] expectedStartTime = {4, 2, 6, 0};
		int[] expectedEndTime = {5, 3, 7, 1};
		
		for(int i = 0; i < graph.size(); i++) {
			assertEquals(expectedStartTime[i], visitDFSTest.getStartTime(String.valueOf(i)));
			assertEquals(expectedEndTime[i], visitDFSTest.getEndTime(String.valueOf(i)));
		}

		boolean ceck = true;
		for(int i = 0; i < graph.size()-1; i++) {
			if(expectedStartTime[Integer.parseInt(ary[i])] > expectedStartTime[Integer.parseInt(ary[i+1])]) ceck = false;
		}
		assertTrue(ceck);

		System.out.println();


	}

	@Test
	void topologicalSortTest() {
		System.out.println("TOPOLOGICAL_SORT");

		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("0", "1");
		graph.addEdge("1", "3");
		graph.addEdge("0", "2");
		graph.addEdge("2", "3");


		String[] stringhe =graph.topologicalSort();
		String[] test={"0","2","1","3"};

		/*
		int[] aux = {0, 2, 1, 3};
		String[] stringNums = new String[aux.length];
		int j = 0;
		while (j < aux.length) {
			stringNums[j] = String.valueOf(aux[j++]);
		}
		*/
		for(int i = 0; i < graph.size(); i++) assertEquals(stringhe[i], test[i]);
		//assertEquals(aux[0], stringNums[0]);
	}
	
	@Test
	void stronglyConnected() {
		System.out.println("\nSTRONGLY CONNECTED SIMPLE");

		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();

		graph.addEdge("1", "0");
		graph.addEdge("0", "4");
		graph.addEdge("4", "0");
		graph.addEdge("5", "4");
		graph.addEdge("5", "1");
		graph.addEdge("0", "5");
		graph.addEdge("4", "7");
		graph.addEdge("5", "7");
		graph.addEdge("2", "3");
		graph.addEdge("3", "2");
		graph.addEdge("2", "6");
		graph.addEdge("6", "2");
		graph.addEdge("6", "8");
		graph.addEdge("8", "7");
		graph.addEdge("8", "9");
		graph.addEdge("9", "8");
		graph.addEdge("2", "1");
		graph.addEdge("6", "5");
		
		Set<Set<String>> set = graph.stronglyConnectedComponents();
		//Set<Set<Integer>> set = graph.stronglyConnectedComponents();
		assertEquals(4, set.size());
	}
}
