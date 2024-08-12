import java.util.Random;

public class Neuron {
    private double[] weights;  // Array to hold the weights of the neuron
    private double bias;       // Bias term

    // Constructor to initialize weights and bias
    public Neuron(int inputSize) {
        weights = new double[inputSize];  // Initializes weights array based on input size
        bias = Math.random();  // Initializes bias with a random value

        // Initializes each weight with a random value
        for (int i = 0; i < inputSize; i++) {
            weights[i] = Math.random();  // If need be can reduce range
        }
    }

    // Method to calculate the neuron's output
    public double activate(double[] inputs) {
        double z = 0.0;  // Variable to hold the weighted sum
        for (int i = 0; i < inputs.length; i++) {
            z += inputs[i] * weights[i];  // Calculate weighted sum
        }
        z += bias;  // Add bias to the weighted sum

        return sigmoid(z);  // Pass the result through the activation function
    }

    // Sigmoid activation function
    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));  // Calculate sigmoid
    }

    // Getters and setters for weights and bias
    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
