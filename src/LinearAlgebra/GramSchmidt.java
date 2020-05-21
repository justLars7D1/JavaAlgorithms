package LinearAlgebra;

public class GramSchmidt {

    public static Matrix orthogonalise(Matrix m) {
        return orthogonaliseQR(m)[0];
    }

    public static Matrix[] orthogonaliseQR(Matrix m) {

        Matrix[] QR = new Matrix[2];

        Matrix Q = (Matrix) m.clone();
        int[] size = Q.getSize();

        Matrix R = new Matrix(size[1], size[1]);

        Vector[] columnVectors = new Vector[size[1]];

        for (int j = 0; j < size[1]; j++) {
            double[] columnVectorValues = new double[size[0]];
            for (int i = 0; i < size[0]; i++) {
                columnVectorValues[i] = Q.get(i, j);
            }
            columnVectors[j] = new Vector(columnVectorValues);
        }

        for (int j = 0; j < size[1]; j++) {
            Vector curVector = columnVectors[j];

            for (int k = 0; k < j; k++) {
                Vector colVec = columnVectors[k];
                double factor = (columnVectors[j].getDotProduct(colVec));
                R.set(k, j, factor);
                curVector.subtract(colVec.getScaled(factor));
            }

            R.set(j, j, curVector.getMagnitude());
            curVector.normalize();

            for (int i = 0; i < size[0]; i++) {
                Q.set(i, j, curVector.get(i));
            }

        }

        QR[0] = Q;  //This is Q
        QR[1] = R;  //This is R
        return QR;
    }

    public static void main(String[] args) {
        double[][] mData = {{-1, -1, 1}, {1, 3, 3}, {-1, -1, 5}, {1, 3, 7}};
        Matrix m = new Matrix(mData);
        System.out.println(orthogonaliseQR(m)[0]);
    }

}
