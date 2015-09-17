import java.util.ArrayList;

/**
 * Creates a back propogation neural net.
 * Instantiates one layer.
 * Feedforwards through every neuron
 * Sets the learning rate
 * Passes input through all layers
 * Outputs are calculated
 * @author Jalal Khan
 *
 */
public class BackpropNet {
	
	private ArrayList<Layer> layers;
	public static final double LEARNING_RATE = 0.25;

	public BackpropNet() {
		
		//2 inputs by default, weights will be ignored for calculations
		Synapse input1 = new Synapse(1.0, 1.0);
		Synapse input2 = new Synapse(1.0, 1.0);
		
		Neuron input = new Neuron();
		input.addInput(input1);
		input.addInput(input2);
		ArrayList<Neuron> inputs = new ArrayList<Neuron>();
		inputs.add(input);
		
		Layer inputLayer = new Layer(inputs);
		Layer hiddenLayer = new Layer(2);
		Layer outputLayer = new Layer(1);
		
		inputLayer.setPreviousLayer(null);
		inputLayer.setNextLayer(hiddenLayer);
		hiddenLayer.setNextLayer(outputLayer);
		outputLayer.setNextLayer(null);
		
		inputLayer.feedForward();
	}
	
	public static void main(String args[]) {
		new BackpropNet();
	}
}
