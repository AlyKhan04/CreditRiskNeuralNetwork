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
    public double predict(double[] input) {
        double[] output = input;  // Initialize the input as the output for the first layer

        // Pass the input through each layer
        for (Layer layer : layers) {
            output = layer.forward(output);  // Forward the output from one layer as input to the next
        }

        return output[0];  // Return the first value of the final output (assuming single output neuron)
    }

    // Method to train the network using backpropagation and gradient descent
    public void train(double[][] X, double[] y, int epochs, double learningRate) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < X.length; i++) {
                double predicted = predict(X[i]);  // Single output

                // Calculate the error for the output neuron (single output)
                double outputError = predicted * (1 - predicted) * (predicted - y[i]);  // Only one error value

                double[] errors = new double[layers.length];
                errors[layers.length - 1] = outputError;

                // Backpropagate the error through the network
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

                // Update the weights and biases using gradient descent
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
        }
    }
}