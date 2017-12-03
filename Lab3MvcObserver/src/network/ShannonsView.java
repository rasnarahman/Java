package network;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShannonsView extends JFrame implements Observer { //, Runnable {
	
	private ShannonJTextFieldPanel shannonJTextFieldPanel;
	private ShannonsJSliderPanel shannonsJSliderPanel;
	private ShannonsController shannonsController = null;
	private ShannonsModel shannonsModelObservale = null;
	private boolean runThread = false;
	
	public ShannonsView () {
		this.SetStartThread();
	}
	
	public JPanel createContentPane() {
		
		shannonJTextFieldPanel = new ShannonJTextFieldPanel(shannonsController);
		shannonsJSliderPanel = new ShannonsJSliderPanel(shannonsController);
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(2, 1));

		jPanel.add(shannonJTextFieldPanel.createPanel());
		jPanel.add(shannonsJSliderPanel.createPanel());
	
		jPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
		jPanel.setBounds(400, 500, 900, 400);
		
		return jPanel;

	}

	@Override
	public void update(Observable observable, Object arg1) { //https://examples.javacodegeeks.com/core-java/util/observer/java-util-observer-example/
		shannonsModelObservale = (ShannonsModel) observable;
		Double newMaximumDataRate = shannonsModelObservale.getMaximumDataRate();
		shannonJTextFieldPanel.updateMDR(newMaximumDataRate);
		shannonsJSliderPanel.updateMDR(newMaximumDataRate);
	}
	
	public void setController(ShannonsController shannonsController) {
		this.shannonsController = shannonsController;
	}

	public void run() {
		//while(runThread) {
			try {
				this.setBounds(400, 500, 1000, 300);
				this.setLocationRelativeTo(null);
				this.setContentPane(this.createContentPane());
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setVisible(true);
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}
	}
	
	public void SetStartThread() {
		runThread = true;
	}
	
	public void SetStopThread() {
		runThread = false;
	}

}
