package LinearAlgebra;

import java.util.Arrays;

public class LUDecomposition {

    public static Matrix[] decompose(Matrix A) {
        // The first element is L and the second one is U
        Matrix[] result = new Matrix[2];

        Matrix L = (Matrix) A.clone();
        int[] size = L.getSize();

        Matrix U = new Matrix(size);
        for (int i = 0; i < size[0]; i++) U.set(i, i, 1);

        for (int i = 0; i < size[0]; i++) {
            for (int j = i + 1; j < size[0]; j++) {

                double factor = L.get(j, i) / L.get(i, i);
                for (int k = 0; k < size[1]; k++) {
                    double newValue = L.get(j, k) - (factor * L.get(i, k));
                    L.set(j, k, newValue);
                }

                U.set(j, i, factor);

            }
        }

        // We have U = L and L = U (wrong variable names)
        result[0] = U;
        result[1] = L;
        return result;
    }

    public static Vector sleSolve(Matrix L, Matrix U, Vector b) {
        double[] y = GaussianElimination.lsolve(L.getGrid(), b.getCoordinates());
        double[] x = GaussianElimination.lsolve(U.getGrid(), y);
        return new Vector(x);
    }

    public static void main(String[] args) {
        double[][] test = {{3, -7, -2, 2}, {-3, 5, 1, 0}, {6, -4, 2, -5}, {-9, 5, -5, 6}};
        Matrix[] LU = decompose(new Matrix(test));

        Vector b = new Vector(-9, 5, 7, -19);
        System.out.println(sleSolve(LU[0], LU[1], b));
    }

}
