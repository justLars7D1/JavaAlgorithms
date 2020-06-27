package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class ReLu implements ScalarFunction {
    public double evaluate(double x) {
        if (x <= 0) return 0;
        else return x;
    }
}
