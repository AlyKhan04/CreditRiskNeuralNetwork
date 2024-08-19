import java.util.ArrayList;
import java.util.List;

public class KCrossValidation {
    public static void kFoldSplit(double[][] X, double[] y, int k, NeuralNetwork neuralNetwork, int epochs, double learningRate) {
        int foldSize = X.length / k;

        for (int fold = 0; fold < k; fold++) {
            List<double[]> trainFeatures = new ArrayList<>();
            List<Double> trainLabels = new ArrayList<>();
            List<double[]> testFeatures = new ArrayList<>();
            List<Double> testLabels = new ArrayList<>();

            // Split data into training and testing sets for the current fold
            for (int i = 0; i < X.length; i++) {
                if (i >= fold * foldSize && i < (fold + 1) * foldSize) {
                    testFeatures.add(X[i]);
                    testLabels.add(y[i]);
                } else {
                    trainFeatures.add(X[i]);
                    trainLabels.add(y[i]);
                }
            }

            double[][] trainData = trainFeatures.toArray(new double[0][0]);
            double[] trainTargets = trainLabels.stream().mapToDouble(Double::doubleValue).toArray();

            double[][] testData = testFeatures.toArray(new double[0][0]);
            double[] testTargets = testLabels.stream().mapToDouble(Double::doubleValue).toArray();

            // Train the neural network on the training data
            neuralNetwork.train(trainData, trainTargets, epochs, learningRate);

            // Evaluate the model on the test data
            evaluateModel(neuralNetwork, testData, testTargets);
        }
    }

    // Method to evaluate the model
    public static void evaluateModel(NeuralNetwork neuralNetwork, double[][] testData, double[] testLabels) {
        for (int i = 0; i < testData.length; i++) {
            double prediction = neuralNetwork.predict(testData[i]);
            System.out.println("Prediction: " + prediction + ", Actual: " + testLabels[i]);
        }
    }
}
