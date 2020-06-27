package MachineLearning.NeuralNetwork.Losses;

import Mathematics.LinearAlgebra.Vector;

public interface Loss {

    Vector evaluate(Vector yPred, Vector yActual);

}
