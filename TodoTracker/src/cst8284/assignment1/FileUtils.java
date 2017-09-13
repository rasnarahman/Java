package cst8284.assignment1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/** 
 * FileUtils class Contains static methods which perform basic file I/O operations, including checking to see if the file exists, 
 * returning the file name, and so on 
 * @author Rasna Rahman
 * @version 1
 * @see  javafx.stage.Stage; javafx.stage.FileChooser; javafx.stage.FileChooser.ExtensionFilter;
 *  java.io.EOFException; java.lang.NullPointerException; java.io.File;import java.io.FileInputStream;
 *  java.io.FileNotFoundException; java.io.IOException; java.io.ObjectInputStream; java.util.ArrayList;
 * @since 1
 */
public class FileUtils {
	public static String relPath = " ";

	/** Function to get ArrayList of ToDo elements
	 * @param fileName full path the the ToDo file
	 * @return ArrayList
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<ToDo> getToDoArray(String fileName) throws IOException, ClassNotFoundException {
		
		ArrayList<ToDo> todoList = new ArrayList<ToDo>();
		
		FileInputStream fin = new FileInputStream(fileName);
		ObjectInputStream input = new ObjectInputStream(fin);
		Object obj = input.readObject();

		try{
			while(obj != null){
				ToDo todo = (ToDo)obj;
				todoList.add(todo);
				obj = input.readObject();
			}
		} catch (IOException | ClassNotFoundException e) {
			//se.printStackTrace();
		} finally{
			input.close();
		}

		return todoList;
	}


	/** gets the absulate path
	 * @return String
	 */
	public  String getAbsPath() {
		return relPath;
	}

	/** Returns absulate path of a file
	 * @param f full file path
	 * @return String
	 */
	public  String getAbsPath(File f) {
		return f.getAbsolutePath();
	}

	 /** Sets the absulate path of a file
	 * @param f full path of a file
	 */
	public void setAbsPath(File f) { 
		relPath = (fileExists(f))? f.getAbsolutePath():""; 
	}

	/** Method to check if a file exists
	 * @param f full path of a file
	 * @return Boolean
	 */
	public static  Boolean fileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead());
	
	}
}



