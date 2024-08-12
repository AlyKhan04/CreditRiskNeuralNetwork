public class NeuralNetwork {
    private Layer[] layers;  // Array to hold the layers in the network

    // Constructor to initialize the layers
    public NeuralNetwork(int[] layerSizes) {
        layers = new Layer[layerSizes.length - 1];  // Initialize the array of layers

        // Create each layer
        for (int i = 0; i < layerSizes.length - 1; i++) {
            layers[i] = new Layer(layerSizes[i + 1], layerSizes[i]);  // Each layer connects to the previous layer
        }
    }

    // Method to perform a forward pass through the network
    public double[] predict(double[] input) {
        double[] output = input;  // Initialize the input as the output for the first layer

        // Pass the input through each layer
        for (Layer layer : layers) {
            output = layer.forward(output);  // Forward the output from one layer as input to the next
        }

        return output;  // Return the final output
    }

    // Method to train the network using backpropagation and gradient descent
    public void train(double[][] X, double[][] y, int epochs, double learningRate) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < X.length; i++) {
                double[] predicted = predict(X[i]);

                // Calculate the error for the output layer
                double[] outputError = new double[predicted.length];
                for (int j = 0; j < predicted.length; j++) {
                    outputError[j] = predicted[j] * (1 - predicted[j]) * (predicted[j] - y[i][j]);
                }

                double[][] errors = new double[layers.length][];
                errors[layers.length - 1] = outputError;

                // Backpropagate the error through the network
                for (int j = layers.length - 2; j >= 0; j--) {
                    errors[j] = new double[layers[j].getNeurons().length];
                    for (int k = 0; k < layers[j].getNeurons().length; k++) {
                        double error = 0.0;
                        for (int l = 0; l < layers[j + 1].getNeurons().length; l++) {
                            error += errors[j + 1][l] * layers[j + 1].getNeurons()[l].getWeights()[k];
                        }
                        errors[j][k] = error * layers[j].getNeurons()[k].activate(X[i]) * (1 - layers[j].getNeurons()[k].activate(X[i]));
                    }
                }

                // Update the weights and biases using gradient descent
                for (int j = 0; j < layers.length; j++) {
                    double[] inputs = (j == 0) ? X[i] : layers[j - 1].forward(X[i]);
                    for (int k = 0; k < layers[j].getNeurons().length; k++) {
                        Neuron neuron = layers[j].getNeurons()[k];
                        for (int l = 0; l < neuron.getWeights().length; l++) {
                            neuron.getWeights()[l] -= learningRate * errors[j][k] * inputs[l];
                        }
                        neuron.setBias(neuron.getBias() - learningRate * errors[j][k]);
                    }
                }
            }
        }
    }
}
