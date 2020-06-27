package MachineLearning.NeuralNetwork.Losses;

import Mathematics.LinearAlgebra.Vector;

public class MSE implements Loss {
    @Override
    public Vector evaluate(Vector yActual, Vector yPred) {
        return yActual.getSubtracted(yPred).getExponentScaled(2);
    }
}
