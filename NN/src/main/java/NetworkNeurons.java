import java.util.Arrays;
import java.util.List;

public class NetworkNeurons {
    private static final int NUM_INPUTS = 25;   // Number of features in dataset
    private static final int NUM_HIDDEN_NEURONS = 10; // Number of neurons in the hidden layer (adjustable)
    private static final int NUM_OUTPUTS = 1;  // Number of output neurons

    List<Neuron> inputLayer = Arrays.asList(
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS),
            new Neuron(NUM_INPUTS)
            // 25 instances since there are 25 columns of the dataset
    );

    List<Neuron> hiddenLayer = Arrays.asList(
            new Neuron(NUM_HIDDEN_NEURONS),
            new Neuron(NUM_HIDDEN_NEURONS),
            new Neuron(NUM_HIDDEN_NEURONS),
            new Neuron(NUM_HIDDEN_NEURONS),
            new Neuron(NUM_HIDDEN_NEURONS),
            new Neuron(NUM_HIDDEN_NEURONS),
            new Neuron(NUM_HIDDEN_NEURONS),
            new Neuron(NUM_HIDDEN_NEURONS),
            new Neuron(NUM_HIDDEN_NEURONS),
            new Neuron(NUM_HIDDEN_NEURONS)
            // Initialize neurons for hidden layer
            // Number of neurons in the hidden layer can vary
    );

    List<Neuron> outputLayer = Arrays.asList(
            new Neuron(NUM_OUTPUTS) // Output neuron(s)
    );
}