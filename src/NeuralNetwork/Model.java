package NeuralNetwork;

import NeuralNetwork.Activations.ActivationFunctions;
import NeuralNetwork.Layers.Layer;

import java.util.ArrayList;

public class Model {

    private ArrayList<Layer> layers;
    private ArrayList<double[][]> weights;

    //private Optimizer optimizer;
    //private Loss loss;

    public void add(int num_neurons, ActivationFunctions activation, String type) {
        layers.add(new Layer(num_neurons, activation, type));

        if(layers.size() > 1) {
            if(type == "output") {
                double[][] tmp_weights = new double[num_neurons][layers.get(layers.size()-2).getNeurons().length];
                for(int i = 0; i < tmp_weights[0].length; i++) {
                    for(int j = 0; j < tmp_weights.length; j++) {
                        tmp_weights[i][j] = Math.random();
                    }
                }
            } else {
                double[][] tmp_weights = new double[num_neurons][layers.get(layers.size()-2).getNeurons().length];
                for(int i = 0; i < tmp_weights[0].length; i++) {
                    for(int j = 0; j < tmp_weights.length; j++) {
                        tmp_weights[i][j] = Math.random();
                    }
                }
            }
        }
    }

}
