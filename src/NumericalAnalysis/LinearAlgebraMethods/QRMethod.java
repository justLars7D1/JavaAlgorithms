package NumericalAnalysis.LinearAlgebraMethods;

import Mathematics.LinearAlgebra.Matrix;

public class QRMethod {

    private static Matrix[] calculateQR(Matrix m) {
        return GramSchmidt.orthogonaliseQR(m);
    }

    private static Matrix computeEigenvectors(Matrix[] qHistory) {
        Matrix eigenvectorMatrix = (Matrix) qHistory[0].clone();
        for (int i = 1; i < qHistory.length; i++) {
            eigenvectorMatrix = eigenvectorMatrix.multiply(qHistory[i]);
        }
        return eigenvectorMatrix;
    }

    public static Matrix[] QRMethod(Matrix m, int numIterations) {
        Matrix[] qHistory = new Matrix[numIterations];

        Matrix eigenvalueMatrix = (Matrix) m.clone();
        for (int i = 0; i < numIterations; i++) {
            Matrix[] QR = calculateQR(eigenvalueMatrix);
            qHistory[i] = QR[0];

            eigenvalueMatrix = QR[1].multiply(QR[0]);
        }

        Matrix eigenvectorMatrix = computeEigenvectors(qHistory);

        return new Matrix[] {eigenvalueMatrix, eigenvectorMatrix};
    }

    public static void main(String[] args) {
        double[][] mData = {{4.6, 0.7, 0.0, 0.0},
                            {0.7, 3.5, 0.8, 0.0},
                            {0.0, 0.8, 1.8, 0.3},
                            {0.0, 0.0, 0.3, 1.3}};
        Matrix m = new Matrix(mData);

        for (int i = 1; i <= 10000000; i *= 2) {
            System.out.print("#Iterations: " + i + ", runtime: ");
            long start = System.nanoTime();
            QRMethod(m, i);
            long end = System.nanoTime();
            System.out.println((end-start)/Math.pow(10, 9) + "s");
        }

    }

}
