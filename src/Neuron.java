import java.util.ArrayList;

public class Neuron {

	/**
	 * A List of all the inputs to this Neuron. Each should have their own weight
	 * Default of 0 inputs
	 */
	private ArrayList<Synapse> inputs;
	
	/**
	 * A List of all the outputs originating from this Neuron. Each should have a value.
	 * Default of 1 output with a random weight
	 */
	private ArrayList<Synapse> outputs;
	
	private double threshold = 0.7;
	private double net_input;
	
	/**
	 * Creates a new Neuron with no synapses
	 * When the threshold is reached, this Neuron will become activated
	 * @param threshold
	 */
	public Neuron() {
		this.inputs = new ArrayList<Synapse>();
		this.outputs = new ArrayList<Synapse>();
		this.net_input = 0.0;

		Synapse out = new Synapse(Math.random());
		out.origin = this;
		outputs.add(out);
	}
	
	public Neuron(double threshold) {
		super();
		this.threshold = threshold;
	}
	
	public void setInputs(ArrayList<Synapse> inputs) {
		this.inputs = inputs;
	}
	
	public void addInput(Synapse in) {
		if(in.target == null || in.target != this)
			in.target = this;
		
		this.inputs.add(in);
	}
	
	public ArrayList<Synapse> getInputs() {
		return this.inputs;
	}
	
	public void addOutput(Synapse out) {
		out.origin = this;
		this.outputs.add(out);
		
	}
	
	public ArrayList<Synapse> getOutputs() {
		return this.outputs;
	}
	
	/**
	 * Calculates a new output based on the threshold and inputs
	 * @return
	 */
	public double calcOutput() {

		double out = 0.0;
		for(Synapse in : inputs) {
			out += in.value * in.weight;
		}

		return out;
	}
	
	/**
	 * Gets the error of E = y - t 
	 * y = output
	 * t = expected
	 * 
	 * This error is passed to the sigmoid function, that will then pass it back to the previous layer
	 */
	public double getError(double output) {
		double error = output*(1.0-output)*(threshold-output);
		System.out.println("error: " + error);
		return error;
	}
	
	public double getSigmoidActivation(double input) {
		double activation = 1/(1 + Math.pow(Math.E, -input));
		return activation;
	}
}
