package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class Sin implements ScalarFunction {
    public double evaluate(double x) {
        return Math.sin(x);
    }
}
