import java.util.Random;

public class Neuron {
    private double[] weights;
    private double bias;

    //A representation of a neuron. This initializes weights array based on input size then initializes bias with a random value.
    //Parameters: input size.
    public Neuron(int inputSize) {
        weights = new double[inputSize];
        bias = Math.random();

        for (int i = 0; i < inputSize; i++) {
            weights[i] = Math.random();
        }
    }

    // Method to calculate the neuron's output.
    // Z holds the weighted sum, the for loop calculates the weighted sum, then bias is added and the result is passed through the activation function.
    //Activation function is sigmoid
    // Parameters: The inputs of the neuron
    public double activate(double[] inputs) {
        double z = 0.0;
        for (int i = 0; i < inputs.length-1; i++) {
            z += inputs[i] * weights[i];
        }
        z += bias;  // Add bias to the weighted sum

        return sigmoid(z);  // Pass the result through the activation function
    }

    // Sigmoid activation function. Passes the weighted sum through the sigmoid formula.
    //Parameters: Z
    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    // Getters and setters
    public double[] getWeights() {
        return weights;
    }
    public double getBias() {
        return bias;
    }
    public void setBias(double bias) {
        this.bias = bias;
    }
}

