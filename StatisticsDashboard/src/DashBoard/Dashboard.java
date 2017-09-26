package DashBoard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.distribution.NormalDistribution;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Dashboard extends Application {	
	private TextField txtStaD;
	private TextField txtMean;
	private TextField txtNum;
	private ArrayList<Double> randomNumbers;
	
	@Override
	public void start(Stage primaryStage){
	
		//Button:	
		Button btn1 = new Button();
		btn1.setText("Go");
		btn1.setMaxWidth(Double.MAX_VALUE);
		
		btn1.setOnMouseClicked((MouseEvent e) -> {
				SampleData();
				secondStage();
			});
		
		
		// Textfield creation along with the label.	
		txtMean = new TextField();
		Label lbl = new Label("Enter Mean: ");
		txtMean = new TextField();
		setTextProperties(txtMean, "0");
		
		txtStaD = new TextField() ;
		Label lb2 = new Label("Enter Deviation: ");
		txtStaD = new TextField();
		setTextProperties(txtStaD, "10");
		
		txtNum = new TextField() ;
		Label lb3 = new Label("Enter N");	
		txtNum = new TextField();
		setTextProperties(txtNum, "0");
		
		// Vbox creation:
		VBox vb = new VBox();   
		vb.setSpacing(10);
	
		vb.getChildren().add(btn1);
		vb.getChildren().addAll(lbl,txtMean,lb2,txtStaD,lb3,txtNum);  
	
		// Adding VBox to the scene
		Scene scene = new Scene(vb,500, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	private void SampleData(){
		randomNumbers = new ArrayList<Double>();
		NormalDistribution nDist = new NormalDistribution( 
					Double.parseDouble(txtMean.getText()), 
					Double.parseDouble(txtStaD.getText())
				);
		
		int SampleNumber = Integer.parseInt(txtNum.getText());
		
		for(int i = 0; i < SampleNumber; i++ ){
			randomNumbers.add(nDist.sample());
		}
	}
	
	public void secondStage(){

		Stage newStage = new Stage(); 
		ListView listView = new ListView();
		ObservableList<Double> items =FXCollections.observableArrayList(randomNumbers);
		listView.getItems().addAll(randomNumbers);
		
		TextField tf = new TextField();
		Double median = getMedian();
		Double mean = getMean();
		Double max = getMax();
		Double min = getMin();
		Double sd = getStdDeviation(mean);
		Double weightedAverage = getWeightedAvg();
		tf.setText(String.format("Mean: %f  Max: %f  Min:%f  SD:%f Median:%f WeightedAverage:%f", mean, max, min, sd, median, weightedAverage));
		
		VBox vbox = new VBox(tf,listView);
		Scene newScene = new Scene(vbox, 500,400);
		newStage.setScene(newScene);
		newStage.show();
	}
	
	
	private Double getMedian() {
		int j = randomNumbers.size() / 2;
		Collections.sort(randomNumbers);
		return randomNumbers.get(j);
	}
	
	private Double getStdDeviation(Double mean) {
		Double diffOfSquares = getDiffOfSquares(randomNumbers, mean);
		Double sd = Math.sqrt(diffOfSquares/ randomNumbers.size());
		return sd;
	}
	
	
	private double getDiffOfSquares (List<Double> randomNumbers2, double mean){
		double sum = 0;
		
		for(double number: randomNumbers){
			sum += (number - mean)*(number - mean);
		}
		return sum;			
	}
		
	private Double getMin() {
		return Collections.min(randomNumbers);
	}
		
	private Double getMax() {
		return Collections.max(randomNumbers);
	}
	
	private Double getMean() {
		Double sum = 0.0;
		for(Double value: randomNumbers) {
		sum +=value;}
		return sum/ randomNumbers.size();
	}
	
	private Double getWeightedAvg() {
	
		Double sum = 0.0;
		int k = 10;
		for(int i = 0; i < 10; i ++) {
			sum += k * randomNumbers.get(i);
			k--;
		}
		
		double WeightAVG = sum / 10;
		return WeightAVG;
	}
	
	private void setTextProperties(TextField textField, String FinalValue) {
		
		textField.setText(FinalValue);
		textField.setMinWidth(100);
		textField.setAlignment(Pos.BASELINE_LEFT);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
