package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class Sigmoid implements ScalarFunction {
    public double evaluate(double x) {
        return 1/(1+Math.exp(-x));
    }
}
