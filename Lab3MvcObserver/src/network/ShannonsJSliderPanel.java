package network;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ShannonsJSliderPanel extends JPanel {
	JSlider bandwidthSlider, signaltonoiseSlider, maxDataRateSlider; 
	private ShannonsController shannonsController;

	public ShannonsJSliderPanel(ShannonsController shannonsController) {
		this.shannonsController = shannonsController;
	}
	
	public void setController() {
		double bandwidth = bandwidthSlider.getValue();
		double snr = signaltonoiseSlider.getValue();
		shannonsController.setBandwidth(bandwidth);
		shannonsController.setSignalToNoise(snr);
	}
	
	public void updateMDR(double mdr){
		Integer mdrInt = (int)mdr;
		maxDataRateSlider.setValue(mdrInt);;
	}
	
	public JPanel createPanel(){
		
		JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridLayout(3, 2,0,1));
		sliderPanel.setBounds(10,151,1000,161);
		sliderPanel.setBackground(Color.ORANGE);
		sliderPanel.setOpaque(true);
		
		JLabel bandwidthlabel = new JLabel ("Bandwidth (Hz)");
		bandwidthlabel.setBounds(10, 16, 116, 14);
	

		// add label SignalToNoise on panel
		JLabel SignalToNoiselabel = new JLabel ("SignalToNoise(db)");
		SignalToNoiselabel.setBounds(10, 68, 120, 14);
		
		
		//add label maxDataRate on panel
		JLabel maxDataRateLBL_1 = new JLabel ("Maximum Data Rate(kbps)");
		maxDataRateLBL_1.setBounds(10, 105, 116, 14);
		
		
		// add bandwidth slider
		bandwidthSlider = new JSlider();
		bandwidthSlider.setMaximum (9000);
		bandwidthSlider.setMinorTickSpacing(50);
		bandwidthSlider.setBounds(130, 11, 235, 43);
		bandwidthSlider.setMajorTickSpacing(1000);
		bandwidthSlider.setPaintTicks(true);
		bandwidthSlider.setPaintLabels(true);
		bandwidthSlider.setBackground(Color.ORANGE);
		bandwidthSlider.setOpaque(true);
		bandwidthSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				setController();
			}
		});


		// add signaltonoiseSlider slider
		signaltonoiseSlider = new JSlider();
		signaltonoiseSlider.setMaximum (3000);
		signaltonoiseSlider.setMinorTickSpacing(50);
		signaltonoiseSlider.setMajorTickSpacing(300);
		signaltonoiseSlider.setPaintTicks(true);
		signaltonoiseSlider.setPaintLabels(true);
		signaltonoiseSlider.setBounds(130, 65, 235, 43);
		signaltonoiseSlider.setBackground(Color.ORANGE);
		signaltonoiseSlider.setOpaque(true);
		signaltonoiseSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				setController();
			}
		});
		

		//add maxDataRateLBL in sliderPanel				
		maxDataRateSlider = new JSlider();
		maxDataRateSlider.setBounds(130, 115,800, 45);
		maxDataRateSlider.setMinorTickSpacing(1000);
		maxDataRateSlider.setMajorTickSpacing(1000);
		maxDataRateSlider.setMaximum (9000);
		maxDataRateSlider.setPaintTicks(true);
		maxDataRateSlider.setPaintLabels(true);
		maxDataRateSlider.setBackground(Color.ORANGE);
		maxDataRateSlider.setOpaque(true);
		
		sliderPanel.add(bandwidthlabel);
		sliderPanel.add(bandwidthSlider);
		sliderPanel.add(SignalToNoiselabel);
		sliderPanel.add(signaltonoiseSlider);
		sliderPanel.add(maxDataRateLBL_1);
		sliderPanel.add(maxDataRateSlider);
			
		return sliderPanel;
		
	}

}
