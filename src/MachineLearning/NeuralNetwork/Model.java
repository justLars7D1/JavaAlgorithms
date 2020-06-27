package MachineLearning.NeuralNetwork;

import MachineLearning.NeuralNetwork.Layers.Layer;
import Mathematics.Functions.ScalarFunction;
import Mathematics.LinearAlgebra.Vector;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private final int inputSize;
    private List<Layer> layers;

    public Model(int inputSize) {
        assert inputSize > 0;
        this.inputSize = inputSize;
        this.layers = new ArrayList<>();
    }

    public Vector evaluate(Vector input) {
        assert input.getDimensions() == inputSize;
        Vector currentOutput = (Vector) input.clone();
        for (Layer l: layers) {
            currentOutput = l.evaluate(currentOutput);
        }
        return currentOutput;
    }

    public void addLayer(int numNeurons, ScalarFunction activation) {
        int lastOutputSize = (layers.size() > 0) ? layers.get(layers.size()-1).getOutputSize() : inputSize;
        layers.add(new Layer(lastOutputSize, numNeurons, activation));
    }

}
