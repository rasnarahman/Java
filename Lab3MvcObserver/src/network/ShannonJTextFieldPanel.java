package network;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.ParseConversionEvent;

public class ShannonJTextFieldPanel extends JPanel {

	protected JLabel maxDataLabel;
	private ShannonsController shannonsController;
	JTextField bandwidthTextField, SnrTextField, MdrTextField;
	private ShannonsView shannonsView = null;
	
	
	public ShannonJTextFieldPanel(ShannonsController shannonsController) {
		this.shannonsController=shannonsController;
	}
	
	public void setController() {
		double bandwidth = Double.parseDouble(bandwidthTextField.getText());
		double snr = Double.parseDouble(SnrTextField.getText());
		shannonsController.setBandwidth(bandwidth);
		shannonsController.setSignalToNoise(snr);
	}

	public void updateMDR(double mdr){
		MdrTextField.setText(Double.toString(mdr));
	}
	
	public JPanel createPanel(){
		JPanel greenPanel = new JPanel();
		greenPanel.setLayout(new GridLayout(3, 2,0,2));

	//	greenPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		   //add bandwidthLabel in textbox
	    JLabel bandwidthLabel = new JLabel("Bandwidth(Hz)");
		bandwidthLabel.setBounds(10, 2, 350, 14);
		bandwidthLabel.setBackground(Color.GREEN);
		bandwidthLabel.setOpaque(true);
	
		
		//Textbox1:
		 
		bandwidthTextField=new JTextField();
		bandwidthTextField.setBounds(10, 100, 235, 14);
		bandwidthTextField.setColumns(10);
		bandwidthTextField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	setController();
            }
        });
		
		
		
		//add signalToNoiseLabel in textbox
		JLabel signalToNoiseLabel = new JLabel("SignalToNoise(db)");
		signalToNoiseLabel.setBounds(10, 24,  115, 14);
		signalToNoiseLabel.setBackground(Color.GREEN);
		signalToNoiseLabel.setOpaque(true);
		
		
		//tbox2:
		SnrTextField =new JTextField();
		SnrTextField.setBounds(50, 100, 235, 14);
		SnrTextField.setColumns(10);
		SnrTextField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	setController();
            }
        });
		
	    
		//add maxDataRateLBL 
		JLabel maxDataRateLBL = new JLabel("Maximum Data Rate (kbps):");
		maxDataRateLBL.setBounds(10, 44,  122, 14);
		maxDataRateLBL.setBackground(Color.GREEN);
		maxDataRateLBL.setOpaque(true);	
		
		
		//tbox3:
		MdrTextField =new JTextField();
		SnrTextField.setBounds(100, 100, 350, 14);
		SnrTextField.setColumns(10);
		//MdrTextField.setText("0");

		greenPanel.add(bandwidthLabel);
		greenPanel.add(bandwidthTextField);
		greenPanel.add(signalToNoiseLabel);
		greenPanel.add(SnrTextField);
		greenPanel.add(maxDataRateLBL);
		greenPanel.add(MdrTextField);
		
		
		
		return greenPanel;
	}
}
