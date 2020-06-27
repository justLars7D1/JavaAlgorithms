package MachineLearning.NeuralNetwork.Layers;

import Mathematics.Functions.ScalarFunction;
import Mathematics.LinearAlgebra.Matrix;
import Mathematics.LinearAlgebra.Vector;

public class Layer {

    private ScalarFunction activation;
    private Matrix representation;

    private Vector bias;

    public Layer(int numInputNeurons, int numOutputNeurons, ScalarFunction activation) {
        this.activation = activation;
        representation = new Matrix(numOutputNeurons, numInputNeurons);
        setRandomWeights();
        System.out.println(representation);
    }

    public Vector evaluate(Vector input) {
        Vector result = representation.multiply(input);
        result.add(bias);

        //TODO: Improve by changing activation function to vector-valued functions
        for (int i = 0; i < result.getDimensions(); i++) {
            double afterActivation = activation.evaluate(result.get(i));
            result.set(i, afterActivation);
        }
        return result;
    }

    public Vector[] evaluateTraining(Vector input) {
        Vector[] result = new Vector[2];
        result[0] = representation.multiply(input); // This will be the value before the activation is applied
        result[0].add(bias);

        //TODO: Improve by changing activation function to vector-valued functions
        Vector copyOfResult = (Vector) result[0].clone();
        for (int i = 0; i < copyOfResult.getDimensions(); i++) {
            double afterActivation = activation.evaluate(copyOfResult.get(i));
            copyOfResult.set(i, afterActivation);
        }

        result[1] = copyOfResult; // This will be the value after the activation is applied

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
        for (int i = 0; i < bias.getDimensions(); i++) {
            bias.set(i, -1 + 2*Math.random());
        }
    }

}
