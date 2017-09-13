package cst8284.assignment1;

/* Rasna Rahman
 * Assignment-3: CST8284
 * References :
 * https://docs.oracle.com/javafx/2/api/javafx/scene/effect/Reflection.html
 * http://stackoverflow.com/questions/21374214/get-toggle-radiobutton-user-value
 * http://stackoverflow.com/questions/32424915/how-to-get-selected-radio-button-from-togglegroup
 * https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html
 * 
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.xml.transform.Result;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Reflection;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


/** 
 * TaskManager class contains methods and properties to do various operations on the list of ToDos and display to the user 
 * @author Rasna Rahman
 * @version 1
 * @since 1
 * @see  javafx.stage.Stage; javafx.stage.FileChooser; javafx.stage.FileChooser.ExtensionFilter;
 * java.io.EOFException; java.lang.NullPointerException;
 */
public class TaskManager extends Application{
	private static ArrayList<ToDo> todoList;
	private static int currentToDoElement;
	private Stage primaryStage;
	private Scene Scene;
	private int priority;
	private Date dueDate;
	private String subject, title;

	private Button firstButton = new Button("|<<");
	private Button prevButton = new Button("<<");
	private Button nextButton = new Button(">>");
	private Button lastButton= new Button(">>|");

	private final int PRIMARY_STAGE_DEFAULT_WIDTH = 900;
	private final int PRIMARY_STAGE_DEFAULT_HEIGHT = 600;
	FileUtils fs = new FileUtils();
	String filePath = fs.getAbsPath();
	FileChooser fileChooser = new FileChooser();

	TextField textTask = new TextField();
	TextField textSubject = new TextField();
	TextField textDate = new TextField();
	RadioButton rb1 = new RadioButton("1");
	RadioButton rb2 = new RadioButton("2");
	RadioButton rb3 = new RadioButton("3");
	ListView<String> listView = null;
	ObservableList<String> observableList = null;

	private static Boolean toDoUpdated = false;

	/**
	 * update property to indicate a todo is updated
	 */
	public static void setToDoUpdated(){
		toDoUpdated = true;
	}

	/** Method to get priority of the current todo
	 * @return int
	 */
	public int getPriority() {
		int priority;

		if(rb1.isSelected()){
			priority = 1;
		}
		else if(rb2.isSelected()){
			priority = 2;
		}
		else{
			priority = 3;
		}

		return priority;
	}

	/** Method to set priority of current todo in the radio button
	 * @param priority prority of the todo
	 */
	public void setPriority(int priority) {
		if(priority == 1){
			rb1.setSelected(true);	
		}
		else if (priority == 2){
			rb2.setSelected(true);		
		}
		else {
			rb3.setSelected(true);	
		}
	}

	
	/** Method returns title from the title text box
	 * @return String 
	 */
	public String getDueDate() {
		return textDate.getText();
	}

	/** Method to set title in the title text box
	 * @param dueDate duedate of the todo
	 */
	public void setDueDate(Date dueDate) {
		textDate.setText(dueDate.toString());
	}

	/** Method to get subject from the subject text box
	 * @return String
	 */
	public String getSubject() {
		return textSubject.getText();
	}

	/** Method to set subject in the subject text box.
	 * @param subject subject of the todo
	 */
	public void setSubject(String subject) {
		textSubject.setText(subject);
	}

	/** Method to set title in the title text box 
	 * @param title title of the todo
	 */
	public void setTitle(String title) {
		textTask.setText(title);
	}

	
	/** returns the index of the current todo element
	 * @return int 
	 */
	public static int getCurrentToDoElement(){
		return currentToDoElement;		
	}
	
	/** set a todo element as the current todo
	 * @param element index of the todo
	 */
	public static void setToDoElement(int element){
		currentToDoElement = element;
	}

	/** returns primary stage
	 * @return Stage
	 */
	public Stage getPrimaryStage(){
		return primaryStage;
	}

	/** sets the primary stage
	 * @param primaryStage primary stage of the todo
	 */
	public void setPrimaryStage(Stage primaryStage ){
		this.primaryStage = primaryStage;
	}

	/** Returns the list of ToDo
	 * @return ArrayList<ToDo>
	 */
	public ArrayList<ToDo> getToDoArrayList(){
		return todoList;
	}

	/** Sets the todo list
	 * @param toDoList list of the todos
	 */
	public void setToDoArrayList(ArrayList<ToDo> toDoList ){
		this.todoList = toDoList;
	}


	/** Returns ObservableList of titles
	 * @return ObservableList<String>
	 */
	private static ObservableList<String> getTitle(){
		ArrayList<String> sAR = new ArrayList<>();
		for(int i = 0; i <todoList.size(); i++){
			sAR.add(todoList.get(i).getTitle());
		}
		
		ObservableList<String> observableList = FXCollections.observableArrayList(sAR);
		return observableList;
	}

	@Override
	public void start(Stage primaryStage) throws ClassNotFoundException, IOException {
		primaryStage.setTitle("To Do List");

		Scene defaultScene = getSplashScene("Click Here");
		defaultScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				ReadNewFile();
			}
		});


		primaryStage.setScene(defaultScene);
		setPrimaryStage(primaryStage);

		displayPrimaryStage();

	}


	/** Returns an animated splash scene
	 * @param defaultText default text to display on splash screen
	 * @return Scene
	 */
	public Scene getSplashScene(String defaultText){
		BorderPane borderPane = new BorderPane();
		Label lblDefault = new Label(defaultText);


		lblDefault.maxHeight(100);
		lblDefault.setMaxWidth(200);
		lblDefault.setFont(Font.font(null, FontWeight.BOLD, 40));;
		lblDefault.setTextFill(Color.DARKORCHID);

		//Effect:  
		Reflection reflection = new Reflection();
		reflection.setFraction(0.7);
		lblDefault.setCache(true);
		lblDefault.setEffect(reflection);

		//FadeTransition:
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000),lblDefault);

		fadeTransition.setFromValue(1.0f);
		fadeTransition.setToValue(0.3f);
		fadeTransition.setCycleCount(1);
		fadeTransition.setAutoReverse(true);

		////ScaleTransition:
		ScaleTransition scaleTransition = new ScaleTransition(
				Duration.millis(2000), lblDefault);
		scaleTransition.setFromX(1);
		scaleTransition.setFromY(1);
		scaleTransition.setToX(2);
		scaleTransition.setToY(2);
		scaleTransition.setCycleCount(1);
		scaleTransition.setAutoReverse(true);

		//rotateTransition:
		RotateTransition rotateTransition = new RotateTransition(
				Duration.millis(2000), lblDefault);
		rotateTransition.setByAngle(180f);
		rotateTransition.setCycleCount(4);
		rotateTransition.setAutoReverse(true);

		SequentialTransition sequentialTransition = new SequentialTransition();		        
		sequentialTransition.getChildren().addAll(rotateTransition,fadeTransition, scaleTransition);

		sequentialTransition.play();

		borderPane.setCenter(lblDefault);
		return new Scene (borderPane);	
	}


	/** Creates a scene of ToDo
	 * @param td todo
	 * @return Scene
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Scene getToDoScene(ToDo td) throws ClassNotFoundException, IOException{
		Pane todoPane = getToDoPane(td);
		return new Scene (todoPane);		
	}

	/** Creates a pane of todo
	 * @param td todo
	 * @return Pane
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Pane getToDoPane(ToDo td) throws ClassNotFoundException, IOException{	
		setTitle(td.getTitle());
		textTask.setPrefWidth(500);	
		textTask.textProperty().addListener((observable, oldTitle, newTitle) -> {
			todoList.get(getCurrentToDoElement()).setTitle(newTitle);	
			setToDoUpdated();
		});

		Label lblTask = new Label("Task");
		lblTask.setPrefWidth(50);

		setSubject(td.getSubject());
		textSubject.setPrefSize(500, 250);
		textSubject.textProperty().addListener((observable, oldSubject, newSubject) -> {
			todoList.get(getCurrentToDoElement()).setSubject(newSubject);	
			setToDoUpdated();
		});

		Label lblSubject = new Label("Subject");
		lblSubject.setPrefWidth(100);

		setDueDate(td.getDueDate());
		textDate.setPrefWidth(500);

		Label lblDate = new Label("Due Date");
		lblDate.setPrefWidth(100);

		Label lblPriority = new Label("Priority");
		lblPriority.setPrefWidth(100);

		//radio Button Create:
		//Radio event reference: http://stackoverflow.com/questions/21374214/get-toggle-radiobutton-user-value
		ToggleGroup toggleGroup = new ToggleGroup();

		rb1.setToggleGroup(toggleGroup);
		rb1.setSelected(true);
		rb2.setToggleGroup(toggleGroup);
		rb3.setToggleGroup(toggleGroup);
		toggleGroup.selectedToggleProperty().addListener((observable, oldPriority, newPriority) -> {
			if (toggleGroup.getSelectedToggle() != null) {
				RadioButton chk = (RadioButton)newPriority.getToggleGroup().getSelectedToggle(); 
				int priority = Integer.parseInt(chk.getText());
				todoList.get(getCurrentToDoElement()).setPriority(priority);
				setToDoUpdated();
			}	
			setToDoUpdated();
		});

		setPriority(td.getPriority());

		HBox hBox4 = new HBox();
		hBox4.setPadding(new Insets(15, 12, 15, 12));
		hBox4.setSpacing(10);
		hBox4.getChildren().addAll(lblPriority);
		hBox4.getChildren().add(rb1);  //radio buttons are added
		hBox4.getChildren().add(rb2);  //radio buttons are added
		hBox4.getChildren().add(rb3);  //radio buttons are added

		//Add controls in grid pane
		GridPane girdPane = new GridPane();
		girdPane.add(lblTask, 0, 0);
		girdPane.add(textTask, 1, 0);
		girdPane.add(lblSubject, 0, 1);
		girdPane.add(textSubject, 1, 1);
		girdPane.add(lblDate, 0, 2);
		girdPane.add(textDate, 1, 2);
		girdPane.add(lblPriority, 0, 3);
		girdPane.add(hBox4, 1, 3);

		girdPane.setVgap(10);
		girdPane.setHgap(50);


		BorderPane borderPane = new BorderPane();

		GridPane girdPaneleft = new GridPane();
		girdPaneleft.setPrefWidth(150);

		GridPane girdPaneright = new GridPane();
		girdPaneright.setPrefWidth(150);

		GridPane girdPaneTop = new GridPane();
		girdPaneTop.setPrefHeight(50);

		// 4 buttons starts here:		
		firstButton.setPrefSize(100, 20);
		firstButton.setOnAction((event) -> {
			int  currentIndex = 0;
			UpdateToDoInTheDisplay(currentIndex, "BACK");
		});

		prevButton.setPrefSize(100, 20);
		prevButton.setOnAction((event) -> {
			int  currentIndex = getCurrentToDoElement() -1;
			UpdateToDoInTheDisplay(currentIndex, "BACK");
		});

		nextButton.setPrefSize(100, 20);
		nextButton.setOnAction((event) -> {
			int  currentIndex = getCurrentToDoElement() +1;
			UpdateToDoInTheDisplay(currentIndex, "FRONT");
		});


		lastButton.setPrefSize(100, 20);
		lastButton.setOnAction((event) -> {
			int  currentIndex = getToDoArrayList().size()-1;
			UpdateToDoInTheDisplay(currentIndex, "FRONT");
		});



		// add menu bar:
		MenuBar menuBar = new MenuBar();

		Menu menu = new Menu("File");
		MenuItem openMenuItem = new MenuItem("Open");
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem addTodoMenuItem = new MenuItem("Add ToDo");
		MenuItem removeTodoMenuItem = new MenuItem("Remove ToDo");
		removeTodoMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
		MenuItem exitMenuItem = new MenuItem("Exit");

		menu.getItems().addAll(openMenuItem, saveMenuItem, addTodoMenuItem, removeTodoMenuItem,
				new SeparatorMenuItem(), exitMenuItem);

		openMenuItem.setOnAction(actionEvent ->
		{
			ReadNewFile();
		});



		saveMenuItem.setOnAction(actionEvent ->
		{
			SaveNewFile();

		});    


		removeTodoMenuItem.setOnAction(actionEvent ->
		{
			int newTodoIndex = getCurrentToDoElement();
			ToDo newtodo = todoList.remove(getCurrentToDoElement());

			System.out.println(newtodo + " is removed from ArrayList");  //test
			System.out.println("Elements in ArrayList :");               //test
			for(int i=0; i < todoList.size(); i++)
				System.out.println(todoList.get(i));


		});



		addTodoMenuItem.setOnAction(actionEvent ->
		{
			int newTodoIndex = getCurrentToDoElement() + 1;
			Date currentDate = new Date();

			ToDo newtodo = new ToDo("", "", 1, currentDate, true, false, false);
			todoList.add(newtodo);

			for(int i = todoList.size() - 1; i > newTodoIndex; i--){
				ToDo tempToDo = todoList.get(i);
				todoList.set(i, todoList.get(i-1));
				todoList.set(i-1, tempToDo);
			}

			EnableAndDisableButton(todoList);

		});

		exitMenuItem.setOnAction(actionEvent ->
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to exit?", ButtonType.YES, ButtonType.NO);
			alert.setTitle("Exit Program");
			alert.setHeaderText(null);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) 
				Platform.exit();
		});


		menuBar.getMenus().add(menu);
		// after edit:
		
		// Add buttons
		
		Button SortByTitle = new Button("SortByTitle");
		SortByTitle.setPrefSize(250,20);
		Button SortBySubject = new Button   ("SortBySubject");
		SortBySubject.setPrefSize(250,20);
		Button SortByDueDate = new Button("SortByDueDate");
		SortByDueDate.setPrefSize(250,20);
		Button SortByPriority = new Button   ("SortByPriority");
		SortByPriority.setPrefSize(250,20);
		Button SortByCompleted = new Button("SortByCompleted");
		SortByCompleted.setPrefSize(250,20);
		Button SortReverse = new Button   ("SortReverse");
		SortReverse.setPrefSize(250,20);
		
		
		observableList = getTitle();
		listView = new ListView<String>();
		listView.setItems(observableList);
				
		SortByTitle.setOnAction((event) -> {
			todoList.sort(new SortByTitle());
			UpdateToDoScene();
			
		});
		
		SortBySubject.setOnAction(e -> {
			todoList.sort(new SortBySubject());
			
			UpdateToDoScene();
		});
		
		SortByDueDate.setOnAction(e -> {
			todoList.sort(new SortByDueDate());
			
			UpdateToDoScene();
		});
		
		SortByPriority.setOnAction(e -> {
			todoList.sort(new SortByPriority());
			
			UpdateToDoScene();
		});
		
     	SortByCompleted.setOnAction(e -> {
     		todoList.sort(new SortByCompleted());
     		
     		UpdateToDoScene();
		});
		
		SortReverse.setOnAction(e -> {			
			Collections.reverse(observableList);
		});
		//here finish

		VBox vboxLeft = new VBox(girdPaneleft);
		VBox vboxRight = new VBox(girdPaneright);

		HBox hboxTop = new HBox(menuBar); //add menu bar here
		hboxTop.setStyle("-fx-background-color: #808080; -fx-padding: 15; -fx-spacing: 10; ");
		HBox hboxCentre = new HBox();
		HBox hboxBottom = new HBox();
		hboxBottom.setAlignment(Pos.CENTER);
		vboxRight.getChildren().addAll(SortByTitle, SortBySubject ,SortByDueDate, SortByPriority, SortByCompleted, SortReverse);
		vboxRight.setStyle(" -fx-padding: 25; -fx-spacing: 10; ");
		
		
		vboxLeft.getChildren().addAll(listView);
		vboxLeft.setMinWidth(100);
		
		hboxCentre.getChildren().addAll(vboxLeft, girdPane, vboxRight);
		hboxCentre.setStyle(" -fx-padding: 25; -fx-spacing: 10; ");
		hboxBottom.getChildren().addAll(firstButton, prevButton, nextButton, lastButton);
		hboxBottom.setStyle("-fx-background-color: #808080; -fx-padding: 15; -fx-spacing: 10; ");
	

		borderPane.setTop(hboxTop);
		borderPane.setCenter(hboxCentre);
		borderPane.setBottom(hboxBottom);

		return borderPane;		
	}

	
	/**
	 * Update todo scene  for any change of event
	 */
	private void UpdateToDoScene(){
 		Scene toDoScence = null;		
 		try {
 			toDoScence = getToDoScene(todoList.get(getCurrentToDoElement()));
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		primaryStage.setScene(toDoScence);
	}

	/**
	 * Saves the todo file
	 */
	private void SaveNewFile(){

		try {
			FileUtils fs = new FileUtils();
			String filePath = fs.getAbsPath();

			FileOutputStream fileOutStream = new FileOutputStream(filePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutStream);

			for(ToDo todo: todoList){
				if(todo != null){
					objectOutputStream.writeObject(todo);
				}
			}
			objectOutputStream.close();
			fileOutStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Reads a new todo file
	 * @return String
	 */
	public String ReadNewFile(){
		toDoUpdated = false;
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Quiz Files", "*.todo"),
				new ExtensionFilter ("All Files", "*.*"));       

		fileChooser.setTitle("Choose ToDo file from the drive");
		File file = fileChooser.showOpenDialog(primaryStage);

		if (FileUtils.fileExists(file)){
			try{

				FileUtils fs = new FileUtils();
				String filePath = fs.getAbsPath();

				todoList = fs.getToDoArray(file.getAbsolutePath());
				fs.setAbsPath(file);
				setToDoArrayList(todoList);
				Scene toDoScence = getToDoScene(todoList.get(getCurrentToDoElement()));
				EnableAndDisableButton(todoList);
				primaryStage.setScene(toDoScence);

			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}

		else {
			Alert alert = new Alert (AlertType.CONFIRMATION);
			alert.setTitle("File does not exist.");
			alert.setContentText("Do you wish to continue (Click 'Cancle') or Click 'OK' to exit" );
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {
				Platform.exit();
			}
			else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
				return ReadNewFile();
			}	 
		}
		return filePath;
	}

	/** Updates the todo in the display when any button pressed to go forward or backward
	 * @param toDoNumber todo number
	 * @param moveDirection direction of the button click
	 */
	public void UpdateToDoInTheDisplay(int toDoNumber, String moveDirection){
		try {
			ArrayList<ToDo> currentToDoArrayList = getToDoArrayList();

			for(int i = toDoNumber; i < currentToDoArrayList.size(); i++){
				if(currentToDoArrayList.get(i).isEmptySet() && i == currentToDoArrayList.size() -1 && moveDirection == "FRONT"){
					toDoNumber--;
				}
				if(currentToDoArrayList.get(i).isEmptySet() && i == 0 && moveDirection == "BACK"){
					toDoNumber++;
				}
				else if(currentToDoArrayList.get(i).isEmptySet() && moveDirection == "FRONT"){
					i++;
				}
				else if(currentToDoArrayList.get(i).isEmptySet() && moveDirection == "BACK"){
					i--;
				}
				else{
					toDoNumber = i;
					break;
				}
			}

			setToDoElement(toDoNumber);

			ToDo currentToDoElement = currentToDoArrayList.get(toDoNumber);
			EnableAndDisableButton(currentToDoArrayList);
			Scene toDoScence = getToDoScene(currentToDoElement);
			primaryStage.setScene(toDoScence);

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}		
		displayPrimaryStage();
	}


	/** Method to enable or disable directional buttons
	 * @param currentToDoList
	 */
	public void EnableAndDisableButton(ArrayList<ToDo> currentToDoList){
		boolean nextIsEmptyToDo = false;
		boolean previousIsEmptyToDo = false;

		int curentToDoIndex = getCurrentToDoElement();

		if(curentToDoIndex < getToDoArrayList().size()-1){
			nextIsEmptyToDo = currentToDoList.get(++curentToDoIndex).isEmptySet();
		}

		if(curentToDoIndex > 0){
			previousIsEmptyToDo = currentToDoList.get(--curentToDoIndex).isEmptySet();
		}

		if(currentToDoList.size() > 1){
			EnableButton(firstButton);
			EnableButton(prevButton);
			EnableButton(lastButton);
			EnableButton(nextButton);
		}

		if((curentToDoIndex == 0) || previousIsEmptyToDo){
			DisableButton(firstButton);
			DisableButton(prevButton);
			EnableButton(lastButton);
			EnableButton(nextButton);
		}

		if((curentToDoIndex == (getToDoArrayList().size()-1)) || nextIsEmptyToDo){
			DisableButton(lastButton);
			DisableButton(nextButton);
			EnableButton(firstButton);
			EnableButton(prevButton);
		}
	}

	public void DisableButton(Button button){
		button.setDisable(true);
	}


	public void EnableButton(Button button){
		button.setDisable(false);
	}

	private void displayPrimaryStage(){
		primaryStage.setWidth(PRIMARY_STAGE_DEFAULT_WIDTH);
		primaryStage.setHeight(PRIMARY_STAGE_DEFAULT_HEIGHT);
		primaryStage.show();
	}


	/** The Main method
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	/** Inner class to sort todo by subject
	 * @author rasna
	 *
	 */
	public class SortBySubject implements Comparator<ToDo> {
		@Override
		public int compare(ToDo td1, ToDo td2) {
			return (td1.getSubject().compareTo(td2.getSubject()));
		}
	}
	
	/** Inner class to sort todo by title
	 * @author rasna
	 *
	 */
	public class SortByTitle implements Comparator<ToDo> {
		@Override
		public int compare(ToDo td1, ToDo td2) {
			return (td1.getTitle().compareTo(td2.getTitle()));
		}
	}
	
	/** Inner class to sort todo by completed
	 * @author rasna
	 *
	 */
	public class SortByCompleted implements Comparator< ToDo> {
		@Override
		public int compare(ToDo td1, ToDo td2) {
			// TODO Auto-generated method stub
			return (td1.isCompleted()==(td2.isCompleted())) ?0 :(td2.isCompleted())?1 :-1;
		}
	}
	
	/** Inner class to sort todo by duedate
	 * @author rasna
	 *
	 */
	public class SortByDueDate implements Comparator< ToDo> {
		@Override
		public int compare(ToDo td1, ToDo td2) {
			return (td1.getDueDate() == td2.getDueDate())?0:td1.getDueDate().before(td2.getDueDate())?-1:1;
		}
	}
	
	/** Inner class to sort todo by priority
	 * @author rasna
	 *
	 */
	public class SortByPriority implements Comparator<ToDo> {
		
		@Override
		public int compare (ToDo td1, ToDo td2){
			 return (td1.getPriority() == td2.getPriority())?0:(td1.getPriority() < td2.getPriority())?-1:1;
		}
	}

}
