//package MachineLearning.PCA;
//
//import Mathematics.LinearAlgebra.Matrix;
//import Mathematics.LinearAlgebra.Vector;
//import NumericalAnalysis.LinearAlgebraMethods.QRMethod;
//
//public class PCA {
//
//    private Matrix eigenvectorMatrix;
//
//    public PCA(Vector[] dps) {
//        Matrix covarianceMatrix = computeCovarianceMatrix(dps);
//        System.out.println(covarianceMatrix);
//
//        Matrix[] eigenVecVals = QRMethod.QRMethod(covarianceMatrix, 2);
//        eigenvectorMatrix = eigenVecVals[1];
//    }
//
//    private static Matrix computeCovarianceMatrix(Vector[] dps) {
//        int dims = dps[0].getDimensions();
//        Matrix covarianceMatrix = new Matrix(dims, dims);
//
//        for (Vector dp: dps) {
//            Matrix dpMatrix = new Matrix(dp);
//            Matrix res = dpMatrix.multiply(dpMatrix.getTransposed());
//            covarianceMatrix.add(res);
//        }
//        covarianceMatrix.scale(1./dps.length);
//
//        return covarianceMatrix;
//    }
//
//}
