package LinearAlgebra;

public class GramSchmidt {

    public static Matrix orthogonalise(Matrix m) {

        Matrix orthogonalisedM = (Matrix) m.clone();
        int[] size = orthogonalisedM.getSize();

        Vector[] columnVectors = new Vector[size[1]];

        for (int j = 0; j < size[1]; j++) {
            double[] columnVectorValues = new double[size[0]];
            for (int i = 0; i < size[0]; i++) {
                columnVectorValues[i] = orthogonalisedM.get(i, j);
            }
            columnVectors[j] = new Vector(columnVectorValues);
        }

        for (int j = 0; j < size[1]; j++) {
            Vector curVector = columnVectors[j];

            for (int k = 0; k < j; k++) {
                Vector colVec = columnVectors[k];
                double factor = (columnVectors[j].getDotProduct(colVec)) / (colVec.getDotProduct(colVec));
                curVector.subtract(colVec.getScaled(factor));
            }

            Vector normalizedVec = curVector.getNormalized();

            for (int i = 0; i < size[0]; i++) {
                orthogonalisedM.set(i, j, normalizedVec.get(i));
            }

        }

        return orthogonalisedM;
    }

    public static void main(String[] args) {
        double[][] mData = {{2, -1, 1}, {-1, 3, -2}, {1, 2, 3}};
        Matrix m = new Matrix(mData);
        System.out.println(orthogonalise(m));
    }

}
