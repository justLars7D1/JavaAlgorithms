package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class LeakyReLu implements ScalarFunction {
    public double evaluate(double x) {
        if (x <= 0) return 0.01*x;
        else return x;
    }
}
