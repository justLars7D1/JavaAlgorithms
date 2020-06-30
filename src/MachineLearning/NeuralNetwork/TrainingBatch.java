package MachineLearning.NeuralNetwork;

import Mathematics.LinearAlgebra.Vector;

public class TrainingBatch {

    private final Vector[] xs;
    private final Vector[] ys;

    public TrainingBatch(Vector[] x, Vector[] y) {
        this.xs = x;
        this.ys = y;
    }

    public Vector[] getXs() {
        return xs;
    }

    public Vector[] getYs() {
        return ys;
    }
}
