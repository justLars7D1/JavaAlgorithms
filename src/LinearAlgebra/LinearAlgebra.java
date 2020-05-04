package LinearAlgebra;

public class LinearAlgebra {

    public static Vector solveSLE(Matrix m, Vector v) {
        double[][] A = m.getGrid();
        double[] b = v.getCoordinates();
        double[] x = GaussianElimination.lsolve(A, b);
        return new Vector(x);
    }

}
