import java.util.ArrayList;

/**
 * Creates a single Neural layer with a default of one neuron with a threshold
 * of 1.0 Since we will be using backprop for now, this will also contain a
 * feedforward method
 * 
 * @author Jalal Khan 9/14/15
 */
public class Layer {

	private ArrayList<Neuron> neurons;
	private Layer next, previous;
	private int iteration = 0;

	/**
	 * Default constructor. Adds one Neuron to this Layer with a threshold of
	 * 1.0
	 */
	public Layer() {
		this.neurons = new ArrayList<Neuron>();
		this.iteration = 0;
	}

	/**
	 * Creates a new layer with a given number of neurons. Each of these neurons
	 * is given a random threshold
	 * 
	 * @param numNeurons
	 */
	public Layer(int numNeurons) {
		super();
		neurons = new ArrayList<Neuron>(numNeurons);
		for (int i = 0; i < numNeurons; i++) {
			Neuron n = new Neuron();
			neurons.add(n);
		}
	}

	/**
	 * Creates a new Layer with a particular list of Neurons
	 * 
	 * @param neurons
	 */
	public Layer(ArrayList<Neuron> neurons) {
		super();
		this.neurons = neurons;
	}

	/**
	 * Similar to a node in a linked list, the next layer should be identified
	 * from this layer The last layer should have a null next layer
	 * 
	 * @param next
	 */
	public void setNextLayer(Layer next) {

		this.next = next;
		if (next != null) {
			next.setPreviousLayer(this);
			this.setInterLayerConnections(next);
		}
	}

	/**
	 * Similar to a node in a linked list, the previous layer will be set when
	 * the next layer is set It should only be called for the input layer
	 * 
	 * @param prev
	 */
	public void setPreviousLayer(Layer prev) {
		this.previous = prev;
	}

	/**
	 * Creates default synapses between this layer and the next. Sets a synapse
	 * between every neuron
	 * 
	 * @param next
	 */
	private void setInterLayerConnections(Layer next) {
		if (this.previous == null) {
			for (Neuron n : neurons) {
				for (Synapse input : n.getInputs()) {
					n.addOutput(input);
				}
			}
		} else {
			for (Neuron thisNeuron : neurons) {
				int count = 0;
				for (Neuron nextNeuron : next.neurons) {
					Synapse s = new Synapse(Math.random(), thisNeuron.getOutputs().get(count++).value, thisNeuron,
							nextNeuron);
					thisNeuron.addOutput(s);
					nextNeuron.addInput(s);
				}
			}
		}
	}

	/**
	 * Gets a list of all the Neurons in this layer
	 * 
	 * @return
	 */
	public ArrayList<Neuron> getNeurons() {
		return this.neurons;
	}

	/**
	 * Convenience method to get the number of neurons in this layer
	 * 
	 * @return
	 */
	public int size() {
		return this.neurons.size();
	}

	/**
	 * The initial phase of the backprop model. Instantiates each neuron's
	 * weight in this layer to a random amount Tells the next layer to do the
	 * same until end is reached If this is the first iteration, it initializes
	 * all output synapses for this layer to a random weight
	 */
	public void feedForward() {
		if (this.next != null) {
			if (this.previous != null) {
				for (Neuron n : neurons) {
					for (Synapse in : n.getInputs()) {
						for (Synapse out : n.getOutputs()) {
							out.value += in.value * in.weight;
							System.out.println("feedforward: " + out.value + ", " + out.weight);
						}
					}

				}
			} else {
				for (Neuron n : neurons) {
					for (Synapse s : n.getInputs()) {
						System.out.println("input: " + s.value + ", " + s.weight);
					}
				}
			}
			this.next.feedForward();
		} else {
			System.out.println("Starting backprop");
			backprop();
		}
	}

	/**
	 * Do the actual backprop! Find error of end result using sigmoid function
	 * and expected result Tell all synapses to adjust weights appropriately
	 * until expected is reached
	 */
	public void backprop() {
		if (this.previous != null) {
			for (Neuron n : neurons) {
				for (Synapse s : n.getInputs()) {
					s.weight += n.getError(n.calcOutput());
					System.out.println("backprop: " + s.value + ", " + s.weight);
				}
			}

			this.previous.backprop();
		} else {
			System.out.println("iteration done");
		}
	}
}
