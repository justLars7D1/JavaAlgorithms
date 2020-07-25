package MachineLearning.KMeans;

import Mathematics.LinearAlgebra.Vector;

public interface Initialization {
    public Vector[] createCentroids(Vector[] xs, int numClusters);
}
