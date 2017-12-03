package network;

import java.util.Observable;

/*	Package for class placement	*/
/** This class is the model of ShannonsTheorem Class. The class has the method to handle maximum rate calculations and show the result 
 * through ShannonsTheorem Class is the model in the MVC design pattern, inherits from Observable.
 * @author Rasna Rahman
 * @version 1.0.0 November 30, 2017
 * 
 */


public class ShannonsModel extends Observable {
	
	private double bandwidth;	
	private double signalToNoise;
	
	
	public ShannonsModel(){
	}

	
	public double getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(double bandwidth) {
		this.bandwidth = bandwidth;
		this.setChanged();
		notifyObservers();
	}

	public double getSignalToNoise() {
		return signalToNoise;
	}

	public void setSignalToNoise(double signalToNoise) {
		this.signalToNoise = signalToNoise;
		this.setChanged();
		notifyObservers();
	}

	public double getMaximumDataRate()
	{
		return getMaximumDataRate(bandwidth, signalToNoise); 
	}
	
	
	private static double getMaximumDataRate( double bandwidth, double signalToNoise)
	{	
			return ((bandwidth * (Math.log(1+ signalToNoise))/ Math.log(2))); // use the formula for valid input
	}

	
	/**
	 * The toString method is used to convert this class input and output value to a meaningful string.
	 * @return	the value of bandwidth, signal to Noise ratio and the maximum data rate
	 */
	
	public String toString()	{
		return	String.format("%.2f",getMaximumDataRate());
	}

	

}
