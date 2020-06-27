package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class BinaryStep implements ScalarFunction {
    public double evaluate(double x) {
        if (x <= 0) return 0;
        else return 1;
    }
}
