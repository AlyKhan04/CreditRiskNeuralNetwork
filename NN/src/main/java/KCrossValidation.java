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
        int tp = 0, tn = 0, fp = 0, fn = 0;  // Variables to store counts for TP, TN, FP, FN

        for (int i = 0; i < testData.length; i++) {
            double rawPrediction = neuralNetwork.predict(testData[i]);
            int prediction = (rawPrediction >= 0.5) ? 1 : 0;  // Threshold for binary classification
            int actual = (int) testLabels[i];

            // Update TP, TN, FP, FN based on the prediction and actual values
            if (prediction == 1 && actual == 1) {
                tp++;
            } else if (prediction == 0 && actual == 0) {
                tn++;
            } else if (prediction == 1 && actual == 0) {
                fp++;
            } else if (prediction == 0 && actual == 1) {
                fn++;
            }
        }

        // Calculate accuracy, precision, recall, and F1 score
        double accuracy = (double) (tp + tn) / (tp + tn + fp + fn);
        double precision = (tp + fp > 0) ? (double) tp / (tp + fp) : 0;
        double recall = (tp + fn > 0) ? (double) tp / (tp + fn) : 0;
        double f1Score = (precision + recall > 0) ? 2 * (precision * recall) / (precision + recall) : 0;

        // Print the results
        System.out.println("Accuracy: " + accuracy);
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
        System.out.println("F1 Score: " + f1Score);
        System.out.println("True Positives (TP): " + tp);
        System.out.println("True Negatives (TN): " + tn);
        System.out.println("False Positives (FP): " + fp);
        System.out.println("False Negatives (FN): " + fn);
    }
}
