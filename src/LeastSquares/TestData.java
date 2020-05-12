package LeastSquares;

import LeastSquares.function.Function;
import LeastSquares.function.LinearEquation;

import java.util.Arrays;

public class TestData {

    public static void main(String[] args) {

        final double[] xs = {0, 1, 2, 3, 4, 5};
        final double[] ys = {-1, 4, 3, 0, 2, 3};

        Function[] funcs = getFunctions();

        Basis basis = new Basis(funcs);
        Function weight = funcs[0];

        LeastSquares leastSquares = LeastSquares.getInstance();
        double[] c = leastSquares.computeCoefficients(basis, weight, xs, ys);

        LinearEquation equation = new LinearEquation(basis, c);

        System.out.println(equation);

        System.out.println(Arrays.toString(equation.evaluate(xs)));
        System.out.println(String.format("RMSE: %f", equation.calculateRMSE(xs, ys)));

    }

    private static Function[] getFunctions() {
        return new Function[] {
                new Function("1") {
            @Override
            public double evaluate(double... X) {
                return 1;
            }
        },
                new Function("x") {
            @Override
            public double evaluate(double... X) {
                return X[0];
            }
        },
                new Function("x^2") {
            @Override
            public double evaluate(double... X) {
                return Math.pow(X[0], 2);
            }
        },
                new Function("x^3") {
            @Override
            public double evaluate(double... X) {
                return Math.pow(X[0], 3);
            }
        },
                new Function("x^4") {
            @Override
            public double evaluate(double... X) {
                return Math.pow(X[0], 4);
            }
        },
                new Function("x^5") {
            @Override
            public double evaluate(double... X) {
                return Math.pow(X[0], 5);
            }
        },
        };
    }

}
