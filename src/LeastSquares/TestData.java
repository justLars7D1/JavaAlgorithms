package LeastSquares;

import LeastSquares.function.Function;
import LeastSquares.function.LinearEquation;

import java.util.Arrays;

public class TestData {

    public static void main(String[] args) {

        final double[] xs = {0, 1, 2, 3, 4, 5};
        final double[] ys = {-1, 4, 3, 0, 2, 3};

        Function one = new Function("1") {
            @Override
            public double evaluate(double... X) {
                return 1;
            }
        };

        Function x = new Function("x") {
            @Override
            public double evaluate(double... X) {
                return X[0];
            }
        };

        Basis basis = new Basis(one, x);
        Function weight = one;

        LeastSquares leastSquares = LeastSquares.getInstance();
        double[] c = leastSquares.computeCoefficients(basis, weight, xs, ys);

        LinearEquation equation = new LinearEquation(basis, c);

        System.out.println(equation);

        System.out.println(Arrays.toString(equation.evaluate(xs)));

    }

}
