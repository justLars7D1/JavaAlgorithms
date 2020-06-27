package MachineLearning.NeuralNetwork;

import MachineLearning.NeuralNetwork.Activations.ReLu;
import Mathematics.LinearAlgebra.Vector;

public class Test {

    public static void main(String[] args) {
        Model m = new Model(3);
        m.addLayer(2, new ReLu());

        Vector input = new Vector(1, 1, 1);
        System.out.println(m.evaluate(input));

    }

}
