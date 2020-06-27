package MachineLearning.NeuralNetwork.Layers;

import Mathematics.Functions.ScalarFunction;
import Mathematics.LinearAlgebra.Matrix;
import Mathematics.LinearAlgebra.Vector;

public class Layer {

    private ScalarFunction activation;
    private Matrix representation;

    private double bias;

    public Layer(int numInputNeurons, int numOutputNeurons, ScalarFunction activation) {
        this.activation = activation;
        representation = new Matrix(numOutputNeurons, numInputNeurons);
        setRandomWeights();
        System.out.println(representation);
    }

    public Vector evaluate(Vector input) {
        Vector result = representation.multiply(input);
        //TODO: Improve by changing activation function to vector-valued functions
        for (int i = 0; i < result.getDimensions(); i++) {
            double afterActivation = activation.evaluate(result.get(i) + bias);
            result.set(i, afterActivation);
        }
        return result;
    }

    public int getOutputSize() {
        return representation.getSize()[0];
    }

    private void setRandomWeights() {
        int[] size = representation.getSize();
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                representation.set(i, j, -1 + 2*Math.random());
            }
        }
        bias = -1 + 2*Math.random();
    }

}
