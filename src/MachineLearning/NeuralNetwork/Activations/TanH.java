package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class TanH implements ScalarFunction {
    public double evaluate(double x) {
        return Math.tanh(x);
    }
}
