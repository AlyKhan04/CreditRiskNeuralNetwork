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
}