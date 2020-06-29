package MachineLearning.NeuralNetwork.Activations;

import Mathematics.LinearAlgebra.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivationTest {

    @Test
    void evaluateReLuZero() {
        Activation relu = new ReLu();
        Vector in = new Vector(1);
        assertEquals(0, relu.evaluate(in).get(0));
    }

    @Test
    void evaluateReLuPositive() {
        Activation relu = new ReLu();
        Vector in = new Vector(1); in.set(0, 1);
        assertEquals(1, relu.evaluate(in).get(0));
    }

    @Test
    void evaluateReLuNegative() {
        Activation relu = new ReLu();
        Vector in = new Vector(1); in.set(0, -1);
        assertEquals(0, relu.evaluate(in).get(0));
    }

}