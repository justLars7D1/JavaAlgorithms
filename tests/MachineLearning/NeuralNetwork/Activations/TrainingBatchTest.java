package MachineLearning.NeuralNetwork.Activations;

import MachineLearning.NeuralNetwork.TrainingBatch;
import Mathematics.LinearAlgebra.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingBatchTest {

    @Test
    void testBatchesLength() {
        Vector[] xs = new Vector[15];
        Vector[] ys = new Vector[15];
        for (int i = 0; i < 15; i++) {
            xs[i] = new Vector(i, i, i);
            ys[i] = new Vector(i, i, i);
        }
        TrainingBatch[] batches = TrainingBatch.generateRandomBatches(xs, ys, 10);
        assertEquals(2, batches.length);
    }

    @Test
    void testBatchSizeIndivFull() {
        Vector[] xs = new Vector[15];
        Vector[] ys = new Vector[15];
        for (int i = 0; i < 15; i++) {
            xs[i] = new Vector(i, i, i);
            ys[i] = new Vector(i, i, i);
        }
        TrainingBatch[] batches = TrainingBatch.generateRandomBatches(xs, ys, 10);
        assertEquals(10, batches[0].getSize());
    }

    @Test
    void testBatchSizeIndivPartial() {
        Vector[] xs = new Vector[15];
        Vector[] ys = new Vector[15];
        for (int i = 0; i < 15; i++) {
            xs[i] = new Vector(i, i, i);
            ys[i] = new Vector(i, i, i);
        }
        TrainingBatch[] batches = TrainingBatch.generateRandomBatches(xs, ys, 10);
        assertEquals(5, batches[1].getSize());

        for (int i = 0; i < batches.length; i++) {
            System.out.println("Batch " + i + ": ");
            for (int j = 0; j < batches[i].getSize(); j++) {
                System.out.print("\tX: " + batches[i].getXs()[j]);
                System.out.println(", Y: " + batches[i].getYs()[j]);
            }
        }

    }

}