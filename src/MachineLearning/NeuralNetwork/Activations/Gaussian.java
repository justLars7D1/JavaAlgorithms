package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class Gaussian implements ScalarFunction {
    public double evaluate(double x) {
        return Math.exp(-1*x*x);
    }
}
