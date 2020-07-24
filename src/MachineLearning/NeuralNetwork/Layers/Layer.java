package MachineLearning.NeuralNetwork.Layers;

import MachineLearning.NeuralNetwork.Activations.Activation;
import Mathematics.LinearAlgebra.Matrix;
import Mathematics.LinearAlgebra.Vector;

public class Layer {

    private Activation activation;
    private Matrix representation;

    private Vector bias;

    // The following variables are used for training:

    private Matrix gradientSumWeight;
    private Vector gradientSumBias;

    private Vector outputBeforeActivation;
    private Vector outputAfterActivation;

    public Layer(int numInputNeurons, int numOutputNeurons, Activation activation) {
        this.activation = activation;
        representation = new Matrix(numOutputNeurons, numInputNeurons);
        gradientSumWeight = new Matrix(numOutputNeurons, numInputNeurons);
        bias = new Vector(numOutputNeurons);
        gradientSumBias = new Vector(numOutputNeurons);
        setRandomWeights();
    }

    public Vector evaluate(Vector input) {
        Vector result = representation.multiply(input);
        result.add(bias);
        result = activation.evaluate(result);
        return result;
    }

    public Vector evaluateTraining(Vector input) {
        outputBeforeActivation = representation.multiply(input); // This will be the value before the activation is applied
        outputBeforeActivation.add(bias);

        Vector copyOfResult = (Vector) outputBeforeActivation.clone();
        copyOfResult = activation.evaluate(copyOfResult);
        outputAfterActivation = copyOfResult; // This will be the value after the activation is applied

        return outputAfterActivation;
    }

    public void resetGradients() {
        gradientSumWeight = new Matrix(gradientSumWeight.getSize());
        gradientSumBias = new Vector(gradientSumBias.getDimensions());
        outputBeforeActivation = new Vector(outputBeforeActivation.getDimensions());
        outputAfterActivation = new Vector(outputAfterActivation.getDimensions());
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
                representation.set(i, j, -2 + 4*Math.random());
            }
        }
        for (int i = 0; i < bias.getDimensions(); i++) {
            bias.set(i, -2 + 4*Math.random());
        }
    }

    public Matrix getGradientSumWeight() {
        return gradientSumWeight;
    }

    public Vector getGradientSumBias() {
        return gradientSumBias;
    }

    public Vector getOutputBeforeActivation() {
        return outputBeforeActivation;
    }

    public Vector getOutputAfterActivation() {
        return outputAfterActivation;
    }

    public Activation getActivation() {
        return activation;
    }

    public String toString(int id) {
        String res = "---- Layer " + id + ": Dense ----\n";
        res += "Weights: \n";
        res += representation;
        res += "Bias: \n";
        res += bias;
        return res;
    }

    @Override
    public String toString() {
        String res = "---- Dense layer ----\n";
        res += "Weights: \n";
        res += representation;
        res += "Bias: \n";
        res += bias;
        return res;
    }
}
