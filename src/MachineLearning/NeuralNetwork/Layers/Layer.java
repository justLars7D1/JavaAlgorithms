package MachineLearning.NeuralNetwork.Layers;

import MachineLearning.NeuralNetwork.Activations.Activation;
import Mathematics.LinearAlgebra.Matrix;
import Mathematics.LinearAlgebra.Vector;

public class Layer {

    private Activation activation;
    private Matrix representation;

    private Vector bias;

    public Layer(int numInputNeurons, int numOutputNeurons, Activation activation) {
        this.activation = activation;
        representation = new Matrix(numOutputNeurons, numInputNeurons);
        bias = new Vector(numOutputNeurons);
        setRandomWeights();
    }

    public Vector evaluate(Vector input) {
        Vector result = representation.multiply(input);
        result.add(bias);
        result = activation.evaluate(result);
        return result;
    }

    public Vector[] evaluateTraining(Vector input) {
        Vector[] result = new Vector[2];
        result[0] = representation.multiply(input); // This will be the value before the activation is applied
        result[0].add(bias);

        Vector copyOfResult = (Vector) result[0].clone();
        copyOfResult = activation.evaluate(copyOfResult);
        result[1] = copyOfResult; // This will be the value after the activation is applied

        return result;
    }

    public Matrix getRepresentation() {
        return representation;
    }

    public Vector getBias() {
        return bias;
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

    public Activation getActivation() {
        return activation;
    }

    @Override
    public String toString() {
        return "Layer {\n" + representation + bias + "\n}";
    }
}
