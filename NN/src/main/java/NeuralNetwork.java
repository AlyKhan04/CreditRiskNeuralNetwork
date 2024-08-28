import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private Layer[] layers; // Array to hold the layers in the network
    private List<Double> losses = new ArrayList<>();
    private List<Double> accuracies = new ArrayList<>();

    public NeuralNetwork(int[] layerSizes) {
        layers = new Layer[layerSizes.length - 1];  // Initializes an array of layers

        // Creates each layer
        for (int i = 0; i < layerSizes.length - 1; i++) {
            layers[i] = new Layer(layerSizes[i + 1], layerSizes[i]);  // Each layer connects to the previous layer
        }
    }

    // Method to perform a forward pass through the network
    public double predict(double[] input) {
        double[] output = input;  // Initializes the input as the output for the first layer

        // Pass the input through each layer
        for (Layer layer : layers) {
            output = layer.forward(output);  // Forward the output from one layer as input to the next
        }

        return output[0];  // Returns the final output
    }

    // Method to train the network using backpropagation and gradient descent
    public void train(double[][] X, double[] y, int epochs, double learningRate) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            double totalLoss = 0.0;
            int correctPredictions = 0;

            for (int i = 0; i < X.length; i++) {
                double predicted = predict(X[i]);

                // Calculate the error for the output neuron
                double outputError = (predicted - y[i]) * predicted * (1 - predicted);
                totalLoss += Math.pow(predicted - y[i], 2);

                // Accuracy calculation
                if ((predicted >= 0.5 && y[i] == 1.0) || (predicted < 0.5 && y[i] == 0.0)) {
                    correctPredictions++;
                }

                double[] errors = new double[layers.length];
                errors[layers.length - 1] = outputError;

                // Backpropagate the error
                for (int j = layers.length - 2; j >= 0; j--) {
                    errors[j] = 0.0;
                    for (int k = 0; k < layers[j].getNeurons().length; k++) {
                        double error = 0.0;
                        for (int l = 0; l < layers[j + 1].getNeurons().length; l++) {
                            error += errors[j + 1] * layers[j + 1].getNeurons()[l].getWeights()[k];
                        }
                        errors[j] = error * layers[j].getNeurons()[k].activate(X[i]) * (1 - layers[j].getNeurons()[k].activate(X[i]));
                    }
                }

                // Update the weights and biases
                for (int j = 0; j < layers.length; j++) {
                    double[] inputs = (j == 0) ? X[i] : layers[j - 1].forward(X[i]);
                    for (int k = 0; k < layers[j].getNeurons().length; k++) {
                        Neuron neuron = layers[j].getNeurons()[k];
                        for (int l = 0; l < neuron.getWeights().length; l++) {
                            neuron.getWeights()[l] -= learningRate * errors[j] * inputs[l];
                        }
                        neuron.setBias(neuron.getBias() - learningRate * errors[j]);
                    }
                }
            }

            // Track loss and accuracy for this epoch
            double averageLoss = totalLoss / X.length;
            double accuracy = (double) correctPredictions / X.length;
            losses.add(averageLoss);
            accuracies.add(accuracy);

            System.out.println("Epoch " + (epoch + 1) + " - Loss: " + averageLoss + ", Accuracy: " + accuracy);
        }

        // Plot the loss and accuracy
        plotLossAndAccuracy();
    }
    private void plotLossAndAccuracy() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < losses.size(); i++) {
            dataset.addValue(losses.get(i), "Loss", Integer.toString(i + 1));
            dataset.addValue(accuracies.get(i), "Accuracy", Integer.toString(i + 1));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Training Metrics",
                "Epoch",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);    // Loss in red
        renderer.setSeriesPaint(1, Color.BLUE);   // Accuracy in blue
        plot.setRenderer(renderer);

        JFrame frame = new JFrame("Training Metrics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
