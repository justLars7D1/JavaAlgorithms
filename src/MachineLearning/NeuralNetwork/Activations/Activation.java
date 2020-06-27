package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public interface Activation extends ScalarFunction {

    double evalDerivative(double x);

}
