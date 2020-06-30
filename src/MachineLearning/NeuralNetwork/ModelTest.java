package MachineLearning.NeuralNetwork;

import MachineLearning.NeuralNetwork.Activations.Sigmoid;
import MachineLearning.NeuralNetwork.Layers.Layer;
import MachineLearning.NeuralNetwork.Losses.Loss;
import MachineLearning.NeuralNetwork.Losses.MSE;
import Mathematics.LinearAlgebra.Vector;

import java.util.List;

public class ModelTest extends Model {

    public ModelTest(int inputSize, double learningRate, Loss lossFunction) {
        super(inputSize, learningRate, lossFunction);
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public static void main(String[] args) {
        ModelTest m = new ModelTest(2, 0.5, new MSE());
        m.addLayer(2, new Sigmoid());
        m.addLayer(2, new Sigmoid());
        List<Layer> layers = m.getLayers();
        layers.get(0).getRepresentation().set(0,0,.15);
        layers.get(0).getRepresentation().set(0,1,.20);
        layers.get(0).getRepresentation().set(1,0,.25);
        layers.get(0).getRepresentation().set(1, 1,.30);
        layers.get(0).getBias().set(0, .35);
        layers.get(0).getBias().set(1, .35);
        layers.get(1).getRepresentation().set(0,0,.40);
        layers.get(1).getRepresentation().set(0,1,.45);
        layers.get(1).getRepresentation().set(1,0,.50);
        layers.get(1).getRepresentation().set(1, 1,.55);
        layers.get(1).getBias().set(0, .60);
        layers.get(1).getBias().set(1, .60);

        Vector[] xs = {new Vector(0.05, 0.10)};
        Vector[] ys = {new Vector(0.01, 0.99)};

        System.out.println(m.evaluate(xs[0]).getSubtracted(ys[0]).getExponentScaled(2));

        m.train(xs, ys, 10000);

        System.out.println(layers.get(0).getRepresentation());
        System.out.println(layers.get(1).getRepresentation());

        System.out.println(m.evaluate(xs[0]).getSubtracted(ys[0]).getExponentScaled(2));

    }

}
