package NumericalAnalysis.DifferentialEquations;

import Mathematics.Functions.ODEFunction;
import Mathematics.LinearAlgebra.Vector;

public class Test {

    public static void main(String[] args) {

        ODEFunction ex = (t, y) -> {
            Vector v = new Vector(1);
            v.set(0, Math.exp(t));
            return v;
        };

        Vector oneD = new Vector(1);
        oneD.set(0, 1);

        double tStart = 0;
        double result = Math.E, tRes = 1;

        double stepSize = 1;
        double prev = 1;
        for (int i = 1; i < 10e7; i *= 10) {
            ClassicalRK4 RK4 = new ClassicalRK4(stepSize/i);
            Vector val = RK4.solve(ex, tStart, tRes, oneD);
            double error = Math.abs(val.get(0) - result);
            System.out.println("Step size: " + stepSize/i);
            System.out.println("Res: " + val);
            System.out.println("Absolute error: " + error);
            System.out.println("Relative to previous: " + String.format("%.1f", error/prev) + "x");
            System.out.println();
            prev = error;
        }


    }

}
