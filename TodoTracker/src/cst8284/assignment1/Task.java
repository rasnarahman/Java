package cst8284.assignment1;

import java.util.Date;


/** 
 * Task class Contains static methods which perform various operations on a task like getting subject, title etc
 * @author Rasna Rahman
 * @version 1
 * @since 1
 */
public abstract class Task {
	/**
	 * Date
	 */
	private Date dateCreated;
	
	/**
	 * constructor
	 */
	public Task(){setDateCreated();}
	
	/** returns subject of todo
	 * @return String
	 */
	public abstract String getSubject();	
	/** sets subject
	 * @param subject
	 */
	public abstract void setSubject(String subject);
	
	/** returns title of todo
	 * @return String
	 */
	public abstract String getTitle();	
	/** sets title of todo
	 * @param title
	 */
	public abstract void setTitle(String title);
	
	/** returns date creation of todo
	 * @return Date
	 */
	public Date getDateCreated(){return dateCreated;}
	/**
	 * sets date creation of todo
	 */
	private void setDateCreated(){this.dateCreated = new Date();}

	@Override
	public String toString(){
		return (getDateCreated().toString());}
	
}