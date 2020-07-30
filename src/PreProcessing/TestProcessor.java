package PreProcessing;

import Mathematics.LinearAlgebra.Vector;

import java.util.Arrays;
import java.util.Random;

public class TestProcessor {
    public static void main(String[] args) {

        Vector[] data = new Vector[10];
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = new Vector(1);
            data[i].set(0, -5 + r.nextInt(10));
        }

        System.out.println("Dataset: " + Arrays.toString(data));

        DataPreProcessor processor = new DataPreProcessor(data);

        Vector[] scaledData = processor.standardize(data);
        Vector[] unscaledData = processor.unstandardize(scaledData);

        System.out.println("Mean: " + processor.getMean());
        System.out.println("Mean: " + processor.getStDev());
        System.out.println("After Feature Scaling: " + Arrays.toString(scaledData));
        System.out.println("After Feature Unscaling: " + Arrays.toString(unscaledData));

    }
}
