package MachineLearning.NeuralNetwork.Activations;

import Mathematics.LinearAlgebra.Vector;

public interface Activation {

    Vector evaluate(Vector x);
    Vector evalDerivative(Vector x);

}
