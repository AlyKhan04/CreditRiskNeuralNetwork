public class Layer {
    private Neuron[] neurons;  // Array to hold the neurons in this layer

    // Constructor to initialize the neurons in the layer
    public Layer(int numNeurons, int inputSize) {
        neurons = new Neuron[numNeurons];  // Initialize the array of neurons

        // Create each neuron in the layer
        for (int i = 0; i < numNeurons; i++) {
            neurons[i] = new Neuron(inputSize);  // Each neuron has the same number of inputs
        }
    }

    // Method to perform a forward pass through the layer
    public double[] forward(double[] inputs) {
        double[] outputs = new double[neurons.length];  // Array to hold the output of each neuron

        // Pass the inputs through each neuron
        for (int i = 0; i < neurons.length; i++) {
            outputs[i] = neurons[i].activate(inputs);  // Activate each neuron and store the output
        }

        return outputs; // Returns the outputs of the layer
    }

    // Getter for neurons array
    public Neuron[] getNeurons() {
        return neurons;
    }
}
