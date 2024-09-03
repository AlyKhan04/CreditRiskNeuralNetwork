import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Runner {
    //Main function: Passes the filename, then stores the data, splits the data into features by calling CSV loader, then calls K cross validation to eval model.
    public static void main(String[] args) {
        // Loads the data from the CSV file
        String filename = "/Users/alykhan/Desktop/CreditRiskModel/Input/standardized_data.csv";
        double[][] data = CSVLoader.loadData(filename);

        // Splits the data into features (X) and labels (y)
        int targetColumnIndex = 5;
        CSVLoader.DataSet dataset = CSVLoader.splitFeaturesAndLabels(data, targetColumnIndex);

        double[][] X = dataset.features;
        double[] y = dataset.labels;

        // Initializes the number of input, hidden, and output neurons
        NeuralNetwork neuralNetwork = new NeuralNetwork(new int[]{25, 15, 1});

        // Performs K-Fold Cross Validation
        int k = 5; // Number of folds
        int epochs = 25;
        double learningRate = 0.05;
        KCrossValidation.kFoldSplit(X, y, k, neuralNetwork, epochs, learningRate);
    }
}
