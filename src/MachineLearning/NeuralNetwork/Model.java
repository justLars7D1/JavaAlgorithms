package MachineLearning.NeuralNetwork;

import MachineLearning.NeuralNetwork.Activations.Activation;
import MachineLearning.NeuralNetwork.Layers.Layer;
import MachineLearning.NeuralNetwork.Losses.Loss;
import Mathematics.LinearAlgebra.Matrix;
import Mathematics.LinearAlgebra.Vector;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private final int inputSize;
    private List<Layer> layers;

    private final double learningRate;
    private final Loss lossFunction;

    public Model(int inputSize, double learningRate, Loss lossFunction) {
        assert (inputSize > 0) && (learningRate > 0) && (lossFunction != null);
        this.inputSize = inputSize;
        this.learningRate = learningRate;
        this.lossFunction = lossFunction;
        this.layers = new ArrayList<>();
    }

    //TODO: Add batch size later for better tuning
    public void train(Vector[] xs, Vector[] ys, int numIterations) {
        assert xs.length == ys.length;
        System.out.println(this + "\n");
        for (int i = 0; i < numIterations; i++) {
            for (int j = 0; j < xs.length; j++) {
                List<Vector[]> resultingData = evaluateForTraining(xs[j]);
                backPropagate(resultingData, ys[j], xs[j]);
            }
            System.out.println(this + "\n");
        }
    }

    public List<Vector> evaluate(List<Vector> input) {
        List<Vector> results = new ArrayList<>();
        for (Vector v: input) {
            results.add(evaluate(v));
        }
        return results;
    }

    public Vector evaluate(Vector input) {
        assert input.getDimensions() == inputSize;
        Vector currentOutput = (Vector) input.clone();
        for (Layer l: layers) {
            currentOutput = l.evaluate(currentOutput);
        }
        return currentOutput;
    }

    public void addLayer(int numNeurons, Activation activation) {
        int lastOutputSize = (layers.size() > 0) ? layers.get(layers.size()-1).getOutputSize() : inputSize;
        layers.add(new Layer(lastOutputSize, numNeurons, activation));
    }

    private void backPropagate(List<Vector[]> intermediateData, Vector yActual, Vector x) {
        Layer lastLayer = layers.get(layers.size()-1);
        Vector[] lastData = intermediateData.get(layers.size()-1);

        // The error gradient for the last layer. We use this to update the weights
        Vector error = lossFunction.evalDerivative(lastData[1], yActual);
        error.multiply(lastLayer.getActivation().evalDerivative(lastData[0]));
        System.out.println("Output:" + lastData[1]);
        System.out.println("Actual: " + yActual);
        System.out.println(lastData[0]);
        System.out.println(lastData[1]);

        //TODO: Fix black box bug (error lots of 0)

        Vector nextLayerError = (Vector) error.clone();
        Matrix nextLayerWeights = (Matrix) lastLayer.getRepresentation().clone();

        int previousLayerIndex = layers.size()-2;
        if (previousLayerIndex >= 0) updateWeights(lastLayer, error, intermediateData.get(previousLayerIndex)[1]);
        else updateWeights(lastLayer, error, x);

        for (int i = layers.size()-2; i >= 0; i--) {
            Layer currentLayer = layers.get(i);
            Vector[] correspondingData = intermediateData.get(i);

            Matrix transposedWeights = nextLayerWeights.getTransposed();

            Vector currentError = transposedWeights.multiply(nextLayerError);
            currentError.multiply(currentLayer.getActivation().evalDerivative(correspondingData[0]));

            nextLayerError = (Vector) currentError.clone();
            nextLayerWeights = (Matrix) currentLayer.getRepresentation().clone();

            previousLayerIndex = i - 1;
            if (previousLayerIndex >= 0) updateWeights(currentLayer, currentError, intermediateData.get(previousLayerIndex)[1]);
            else updateWeights(currentLayer, currentError, x);

        }

    }

    private void updateWeights(Layer layer, Vector error, Vector previousOutput) {
        Matrix weights = layer.getRepresentation();
        Vector bias = layer.getBias();
        int[] dims = weights.getSize();

        for (int i = 0; i < dims[0]; i++) {
            for (int j = 0; j < dims[1]; j++) {
                double newValue = weights.get(i, j) - learningRate * error.get(i) * previousOutput.get(j);
                weights.set(i, j, newValue);
            }
            double newBiasValue = bias.get(i) - learningRate * error.get(i);
            bias.set(i, newBiasValue);
        }

    }

    private List<Vector[]> evaluateForTraining(Vector input) {
        assert input.getDimensions() == inputSize;
        List<Vector[]> currentOutput = new ArrayList<>();
        Vector currentInput = (Vector) input.clone();

        for (Layer l: layers) {
            Vector[] layerData = l.evaluateTraining(currentInput);
            currentOutput.add(layerData);
            currentInput = (Vector) layerData[1].clone();
        }

        return currentOutput;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Layer l : layers) {
            res.append(l).append("\n");
        }
        return res.toString();
    }
}
