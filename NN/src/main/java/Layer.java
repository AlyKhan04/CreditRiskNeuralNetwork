public class Layer {
    private Neuron[] neurons;  // Array to hold the neurons in this layer

    // Constructor to initialize the neurons in the layer. Creates each neuron in the for loop and ensures each neuron has same number of inputs.
    //Parameters: The number of neurons in the layer and the input-size.
    public Layer(int numNeurons, int inputSize) {
        neurons = new Neuron[numNeurons];

        for (int i = 0; i < numNeurons; i++) {
            neurons[i] = new Neuron(inputSize);
        }
    }

    // Method to perform a forward pass through the layer. outputs holds the output each neuron gives.
    // Function returns the outputs of the neurons.
    public double[] forward(double[] inputs) {
        double[] outputs = new double[neurons.length];  // Array to hold the output of each neuron

        // Pass the inputs through each neuron
        for (int i = 0; i < neurons.length; i++) {
            outputs[i] = neurons[i].activate(inputs);
        }

        return outputs;
    }

    // Getter for neurons array
    public Neuron[] getNeurons() {
        return neurons;
    }
}