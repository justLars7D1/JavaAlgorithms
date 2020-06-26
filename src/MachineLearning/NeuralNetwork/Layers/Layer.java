package MachineLearning.NeuralNetwork.Layers;

import MachineLearning.NeuralNetwork.Activations.ActivationFunctions;
import MachineLearning.NeuralNetwork.Neurons.Neuron;

public class Layer {

    protected Neuron[] neurons;

    public Layer(int num_neurons, ActivationFunctions activation_function, String type) {
        if (type.toLowerCase().equals("input")) {
            neurons = new Neuron[num_neurons+1];
            for(int i = 0; i < num_neurons+1; i++) neurons[i] = new Neuron();
            neurons[neurons.length-1].value = 1;
        } else if (type.toLowerCase().equals("hidden")) {
            neurons = new Neuron[num_neurons+1];
            for(int i = 0; i < num_neurons; i++) neurons[i] = new Neuron(activation_function);
            neurons[neurons.length-1] = new Neuron();
            neurons[neurons.length-1].value = 1;
        } else if (type.toLowerCase().equals("output")) {
            neurons = new Neuron[num_neurons];
            for(int i = 0; i < num_neurons; i++) neurons[i] = new Neuron(activation_function);
        }
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public void setNeurons(Neuron[] neurons) {
        this.neurons = neurons;
    }

}
