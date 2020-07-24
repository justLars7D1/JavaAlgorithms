package MachineLearning.NeuralNetwork;

import MachineLearning.NeuralNetwork.Activations.Activation;
import MachineLearning.NeuralNetwork.Layers.Layer;
import MachineLearning.NeuralNetwork.Losses.Loss;
import Mathematics.LinearAlgebra.Matrix;
import Mathematics.LinearAlgebra.Vector;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final double L2REGULARIZATIONFACTOR = 0;

    private final int inputSize;
    protected List<Layer> layers;

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
    public void train(Vector[] xs, Vector[] ys, int batchSize, int epochs) {
        assert xs.length == ys.length;
        //TrainingBatch[] batches =
        for (int i = 0; i < epochs; i++) {

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

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Model Overview\n");
        int idCounter = 0;
        for (Layer l : layers) {
            res.append(l.toString(idCounter++)).append("\n");
        }
        res.append("---------------------");
        return res.toString();
    }
}