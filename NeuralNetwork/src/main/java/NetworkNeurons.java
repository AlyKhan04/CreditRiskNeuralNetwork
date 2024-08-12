import java.util.Arrays;
import java.util.List;

public class NetworkNeurons {
    List<Neuron> neuronNet = Arrays.asList(
            new Neuron(), new Neuron(), new Neuron(), /* input node */
            new Neuron(), new Neuron(),               /* hidden node */
            new Neuron());                           /* Output Node */
}
