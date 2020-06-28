package MachineLearning.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class TrainingData {

    private final List<Double> errors = new ArrayList<>();

    public void addError(double err) {
        errors.add(err);
    }

    public List<Double> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "TrainingData{\n" +
                "errors=" + errors +
                "\n}";
    }
}
