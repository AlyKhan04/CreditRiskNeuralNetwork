import java.util.*;

public class KCrossValidation {

    //Splits the data into k folds, then trains the neural network on said factors then evaluates the models.
    //Parameters: The X data, The Y variable, the created NeuralNetwork, the number of folds, the number of epochs and the learning rate
    public static void kFoldSplit(double[][] X, double[] y, int k, NeuralNetwork neuralNetwork, int epochs, double learningRate) {
        int foldSize = (X.length + k - 1) / k;

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
            //randomUndersample(trainFeatures,trainLabels);
            randomOversample(trainFeatures, trainLabels);
            // Train the neural network on the training data
            neuralNetwork.train(trainData, trainTargets, epochs, learningRate);

            // Evaluate the model on the test data
            evaluateModel(neuralNetwork, testData, testTargets);

            // Plot results for this fold
            neuralNetwork.plotLossAndAccuracy(fold + 1);
        }
    }

    //Class implements oversampling
    //Randomly duplicates positive samples due to the class imbalance
    //Parameters: List of features and list of labels
    private static void randomOversample(List<double[]> features, List<Double> labels) {
        int positiveCount = 0;
        int negativeCount = 0;

        for (double label : labels) {
            if (label == 1.0) positiveCount++;
            else negativeCount++;
        }

        int maxCount = Math.max(positiveCount, negativeCount);
        Random random = new Random();

        while (positiveCount < maxCount || negativeCount < maxCount) {
            for (int i = 0; i < labels.size(); i++) {
                double label = labels.get(i);
                if (label == 1.0 && positiveCount < maxCount) {
                    features.add(features.get(i));
                    labels.add(label);
                    positiveCount++;
                } else if (label == 0.0 && negativeCount < maxCount) {
                    features.add(features.get(i));
                    labels.add(label);
                    negativeCount++;
                }
                if (positiveCount == maxCount && negativeCount == maxCount) break;
            }
        }
    }


    // Method to evaluate the model. Updates the various parts of a confusion matrix.
    //Parameters: NeuralNetwork, TestData and the testlabels
    public static void evaluateModel(NeuralNetwork neuralNetwork, double[][] testData, double[] testLabels) {
        int tp = 0, tn = 0, fp = 0, fn = 0;  // Variables to store counts for TP, TN, FP, FN

        for (int i = 0; i < testData.length; i++) {
            double rawPrediction = neuralNetwork.predict(testData[i]);
            int prediction = (rawPrediction >= 0.5) ? 1 : 0;  // Threshold for binary classification
            int actual = (int) testLabels[i];

            // Updates TP, TN, FP, FN based on the prediction and actual values
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

        // Calculates accuracy, precision, recall, and F1 score
        double accuracy = (double) (tp + tn) / (tp + tn + fp + fn);
        double precision = (tp + fp > 0) ? (double) tp / (tp + fp) : 0;
        double recall = (tp + fn > 0) ? (double) tp / (tp + fn) : 0;
        double f1Score = (precision + recall > 0) ? 2 * (precision * recall) / (precision + recall) : 0;

        // Prints the results
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