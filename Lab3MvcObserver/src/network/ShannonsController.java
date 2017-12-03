package network;

import java.util.Observer;

public interface ShannonsController {
	
	public void AddObserver(Observer o);
	
	public void setBandwidth(double bandwidth);
	
	public void setSignalToNoise(double signalToNoise);
}
