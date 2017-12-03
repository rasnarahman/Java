package network;

import java.util.Observer;

public class ShannonsTheorem implements ShannonsController {
	
	private ShannonsModel shannonsModel = null;
	private static ShannonsView shannonsView = null;
	
	public ShannonsTheorem () {
		setModel(new ShannonsModel());	
	}
	
	private void setModel(ShannonsModel shannonsModel) {
		this.shannonsModel = shannonsModel;
	}

	public void AddObserver(Observer newObserver) {
		shannonsModel.addObserver(newObserver);
	}
	
	public double getBandwidth(){
		return shannonsModel.getBandwidth();
	}

	public void setBandwidth(double bandwidth) {
		shannonsModel.setBandwidth(bandwidth);
	}
	
	public double getSignalToNoise() {
		return shannonsModel.getSignalToNoise();
	}
	
	public void setSignalToNoise(double snr) {
		shannonsModel.setSignalToNoise(snr);
		
	}
	
	public double getMaximumDataRate(){
		return shannonsModel.getMaximumDataRate();
	}
	
	
	public static void main (String args[]){

		ShannonsController shannonsTheoremController = new ShannonsTheorem();	
		shannonsView = new ShannonsView();
		shannonsView.setController(shannonsTheoremController);
		shannonsTheoremController.AddObserver((Observer)shannonsView);
		shannonsView.run();
		//Thread thread = new Thread((Runnable) (Observer)shannonsView);	
		//thread.start();
	}
}
