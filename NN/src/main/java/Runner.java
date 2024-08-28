import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Runner {
    public static void main(String[] args) {
        // Load the data from the CSV file
        String filename = "/Users/alykhan/Desktop/CreditRiskModel/Input/standardized_data.csv";
        double[][] data = CSVLoader.loadData(filename);

        // Split the data into features (X) and labels (y)
        int targetColumnIndex = 5;
        CSVLoader.DataSet dataset = CSVLoader.splitFeaturesAndLabels(data, targetColumnIndex);

        double[][] X = dataset.features;
        double[] y = dataset.labels;

        // Initializes the number of input, hidden, and output neurons
        NeuralNetwork neuralNetwork = new NeuralNetwork(new int[]{25, 10, 1});

        // Perform K-Fold Cross Validation
        int k = 5; // Number of folds
        int epochs = 100;
        double learningRate = 0.01;
        //Need to increase accuracy
        KCrossValidation.kFoldSplit(X, y, k, neuralNetwork, epochs, learningRate);
    }
}
