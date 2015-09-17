
/**
 * Serves as a parent for input and output
 * Each Synapse is guaranteed to have a weight
 * Value is not required on creation but should pretty much always be present for a calculation
 * Origin and target may also be specified but are not guarenteed at any arbitrary point in time
 * @author Jalal Khan
 * 9/14/15
 */
public class Synapse {
	/**
	 * The weight of the connection between two Neurons
	 */
	public double weight, value;
	
	public Neuron origin, target;
	
	/**
	 * Creates a new Neuron with just a weight and unspecified value, origin and target
	 * @param weight
	 */
	public Synapse(double weight) {
		this.weight = weight;
	}
	
	/**
	 * Creates a new Neuron with both a value and weight, but unspecified origin and target
	 * @param value
	 * @param weight
	 */
	public Synapse(double value, double weight) {
		this.value = value;
		this.weight = weight;
	}
	
	/**
	 * Creates a new Synapse with a value (weight), and specifying its origin and target
	 * @param weight
	 * @param origin
	 * @param target
	 */
	public Synapse(double weight, Neuron origin, Neuron target) {
		this.weight = weight;
		this.origin = origin;
		this.target = target;
	}
	
	public Synapse(double weight, double value, Neuron origin, Neuron target) {
		this.weight = weight;
		this.value = value;
		this.origin = origin;
		this.target = target;
	}
}
