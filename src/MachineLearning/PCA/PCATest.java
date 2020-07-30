//package MachineLearning.PCA;
//
//import Mathematics.LinearAlgebra.Vector;
//import PreProcessing.DataPreProcessor;
//
//import java.util.Arrays;
//
//public class PCATest {
//    public static void main(String[] args) {
//
//        Vector[] data = new Vector[2];
//        for (int i = 0; i < data.length; i++) {
//            data[i] = new Vector(i+1, i+1);
//        }
//        DataPreProcessor processor = new DataPreProcessor(data);
//
//        Vector[] scaledData = processor.standardize(data);
//
//        System.out.println(Arrays.toString(scaledData));
//
//        PCA pca = new PCA(scaledData);
//        System.out.println();
//
//    }
//}
