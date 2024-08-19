import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public static double[][] loadData(String filename) {
        List<double[]> dataList = new ArrayList<>();

        // Create CSVReader using CSVReaderBuilder
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).withSkipLines(1).build()) {
            String[] nextLine;

            // Iterate through lines in the CSV
            while (true) {
                try {
                    nextLine = reader.readNext();
                } catch (CsvValidationException e) {
                    e.printStackTrace();
                    continue; // Skip invalid line
                }
                if (nextLine == null) {
                    break;
                }
                double[] data = new double[nextLine.length];
                for (int i = 0; i < nextLine.length; i++) {
                    data[i] = Double.parseDouble(nextLine[i]);
                }
                dataList.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle IO exceptions
        }

        return dataList.toArray(new double[0][0]);
    }

    // Separate features and labels
    public static DataSet splitFeaturesAndLabels(double[][] data, int targetColumnIndex) {
        int numFeatures = data[0].length - 1;
        double[][] X = new double[data.length][numFeatures]; // Features
        double[] y = new double[data.length]; // Labels (target)

        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, X[i], 0, targetColumnIndex); // Copy features
            System.arraycopy(data[i], targetColumnIndex + 1, X[i], targetColumnIndex, numFeatures - targetColumnIndex); // Skip target column

            y[i] = data[i][targetColumnIndex]; // Target (label)
        }

        return new DataSet(X, y);
    }

    public static class DataSet {
        public double[][] features;
        public double[] labels;

        public DataSet(double[][] features, double[] labels) {
            this.features = features;
            this.labels = labels;
        }
    }
}

