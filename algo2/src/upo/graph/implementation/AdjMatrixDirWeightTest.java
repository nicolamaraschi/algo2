package upo.graph.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjMatrixDirWeightTest {
    AdjMatrixDirWeight graph;
    AdjMatrixDirWeight adjMatrixDirWeight;

    @BeforeEach
    public void init() {
        graph = new AdjMatrixDirWeight(5);
    }

    @Test
    void getFloydWarshallShortestPaths() {

        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
    }
}