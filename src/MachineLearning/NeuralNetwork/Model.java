//package MachineLearning.NeuralNetwork;
//
//import MachineLearning.NeuralNetwork.Activations.Activation;
//import MachineLearning.NeuralNetwork.Layers.Layer;
//import MachineLearning.NeuralNetwork.Losses.Loss;
//import Mathematics.LinearAlgebra.Matrix;
//import Mathematics.LinearAlgebra.Vector;
//
//import java.util.*;
//
//public class Model {
//
//    private final int inputSize;
//    protected List<Layer> layers;
//
//    private final double learningRate;
//    private final Loss lossFunction;
//
//    public Model(int inputSize, double learningRate, Loss lossFunction) {
//        assert (inputSize > 0) && (learningRate > 0) && (lossFunction != null);
//        this.inputSize = inputSize;
//        this.learningRate = learningRate;
//        this.lossFunction = lossFunction;
//        this.layers = new ArrayList<>();
//    }
//
//    //TODO: Add batch size later for better tuning
//    public void train(Vector[] xs, Vector[] ys, int epochs, int batchSize) {
//        assert xs.length == ys.length;
//        TrainingBatch[] batches = createBatches(xs, ys, batchSize);
//        for (int i = 0; i < epochs; i++) {
//            for (int j = 0; j < batches.length; j++) {
//                List<List<Vector[]>> resultingData = evaluateForTraining(batches[j]);
//                backPropagate(resultingData, batches[j]);
//            }
//        }
//    }
//
//    public List<Vector> evaluate(List<Vector> input) {
//        List<Vector> results = new ArrayList<>();
//        for (Vector v: input) {
//            results.add(evaluate(v));
//        }
//        return results;
//    }
//
//    public Vector evaluate(Vector input) {
//        assert input.getDimensions() == inputSize;
//        Vector currentOutput = (Vector) input.clone();
//        for (Layer l: layers) {
//            currentOutput = l.evaluate(currentOutput);
//        }
//        return currentOutput;
//    }
//
//    public void addLayer(int numNeurons, Activation activation) {
//        int lastOutputSize = (layers.size() > 0) ? layers.get(layers.size()-1).getOutputSize() : inputSize;
//        layers.add(new Layer(lastOutputSize, numNeurons, activation));
//    }
//
//    private TrainingBatch[] createBatches(Vector[] xs, Vector[] ys, int batchSize) {
//        Random rand = new Random();
//
//        int numBatches = (int) Math.ceil(xs.length / (double) batchSize);
//        TrainingBatch[] batches = new TrainingBatch[numBatches];
//
//        List<Integer> samplesLeft = new ArrayList<>();
//        for (int i = 0; i < xs.length; i++) samplesLeft.add(i);
//
//        for (int batchNumber = 0; batchNumber < numBatches; batchNumber++) {
//            int sizeOfBatch = Math.min(batchSize, samplesLeft.size());
//            Vector[] xsBatch = new Vector[sizeOfBatch];
//            Vector[] ysBatch = new Vector[sizeOfBatch];
//
//            for (int i = 0; i < batchSize && samplesLeft.size() > 0; i++) {
//                int randomIndex = rand.nextInt(samplesLeft.size());
//                int sampleID = samplesLeft.get(randomIndex);
//                xsBatch[i] = xs[sampleID];
//                ysBatch[i] = ys[sampleID];
//                samplesLeft.remove(randomIndex);
//            }
//
//            batches[batchNumber] = new TrainingBatch(xsBatch, ysBatch);
//        }
//        return batches;
//    }
//
//    private void backPropagate(List<List<Vector[]>> intermediateData, TrainingBatch batch) {
//        Vector[] xs = batch.getXs();
//        Vector[] ys = batch.getYs();
//
//        Layer lastLayer = layers.get(layers.size()-1);
//        //TODO: Finish logic of SGD with batches
//        Vector[] lastData = intermediateData.get(layers.size()-1);
//
//        // The error gradient for the last layer. We use this to update the weights
//        Vector error = lossFunction.evalDerivative(lastData[1], yActual);
//        error.multiply(lastLayer.getActivation().evalDerivative(lastData[0]));
//
//        Vector nextLayerError = (Vector) error.clone();
//        Matrix nextLayerWeights = (Matrix) lastLayer.getRepresentation().clone();
//
//        int previousLayerIndex = layers.size()-2;
//        if (previousLayerIndex >= 0) updateWeights(lastLayer, error, intermediateData.get(previousLayerIndex)[1]);
//        else updateWeights(lastLayer, error, x);
//
//        for (int i = layers.size()-2; i >= 0; i--) {
//            Layer currentLayer = layers.get(i);
//            Vector[] correspondingData = intermediateData.get(i);
//
//            Matrix transposedWeights = nextLayerWeights.getTransposed();
//
//            Vector currentError = transposedWeights.multiply(nextLayerError);
//            currentError.multiply(currentLayer.getActivation().evalDerivative(correspondingData[0]));
//
//            nextLayerError = (Vector) currentError.clone();
//            nextLayerWeights = (Matrix) currentLayer.getRepresentation().clone();
//
//            previousLayerIndex = i - 1;
//            if (previousLayerIndex >= 0) updateWeights(currentLayer, currentError, intermediateData.get(previousLayerIndex)[1]);
//            else updateWeights(currentLayer, currentError, x);
//
//        }
//
//    }
//
//    private void updateWeights(Layer layer, Vector error, Vector previousOutput) {
//        Matrix weights = layer.getRepresentation();
//        Vector bias = layer.getBias();
//        int[] dims = weights.getSize();
//
//        for (int i = 0; i < dims[0]; i++) {
//            for (int j = 0; j < dims[1]; j++) {
//                double newValue = weights.get(i, j) - learningRate * error.get(i) * previousOutput.get(j);
//                weights.set(i, j, newValue);
//            }
//            double newBiasValue = bias.get(i) - learningRate * error.get(i);
//            bias.set(i, newBiasValue);
//        }
//
//    }
//
//    private List<List<Vector[]>> evaluateForTraining(TrainingBatch inputBatch) {
//        List<List<Vector[]>> currentOutput = new ArrayList<>();
//        Vector[] xs = inputBatch.getXs();
//
//        for (Vector x : xs) {
//            List<Vector[]> tempOutput = new ArrayList<>();
//            Vector currentInput = (Vector) x.clone();
//
//            for (Layer l : layers) {
//                Vector[] layerData = l.evaluateTraining(currentInput);
//                tempOutput.add(layerData);
//                currentInput = (Vector) layerData[1].clone();
//            }
//            currentOutput.add(tempOutput);
//        }
//
//        return currentOutput;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder res = new StringBuilder("Model Overview\n");
//        int idCounter = 0;
//        for (Layer l : layers) {
//            res.append(l.toString(idCounter++)).append("\n");
//        }
//        res.append("---------------------");
//        return res.toString();
//    }
//}
