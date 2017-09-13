package cst8284.assignment1;

import java.io.Serializable;
import java.util.Date;

/** 
 * ToDo class contains methods and properties to set or retrieve ToDo information
 * @author Rasna Rahman
 * @version 1
 * @since 1
 */
public class ToDo extends Task implements Serializable {
	/**
	 * Priority of todo
	 */
	private int priority;
	/**
	 * due date of todo
	 */
	private Date dueDate;
	/**
	 * defines if a todo is completed, removed or empty
	 */
	private boolean completed, remove, empty;
	/**
	 * defines the subject and title of todo
	 */
	private String subject, title;
	// setDueDate(dueDate: Date): void

	/**
	 * construcor
	 */
	public ToDo(){}

	/** Constructor
	 * @param subject
	 * @param title
	 * @param priority
	 * @param dueDate
	 * @param completed
	 * @param remove
	 * @param empty
	 */
	public ToDo(String subject, String title, int priority, Date dueDate, boolean completed, boolean remove, boolean empty){
		setSubject(subject); setTitle(title); setPriority(priority); setDueDate(dueDate);
		setCompleted(completed); setRemove(remove); setEmpty(empty);
	}

	/** returns priority
	 * @return int
	 */
	public int getPriority() {
		return priority;
	}

	/** sets priority
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
 
	/** returns due date of ToDO
	 * @return Date
	 */ 
	public Date getDueDate() {
		return dueDate;
	}

	/** sets due date of todo
	 * @param dueDate
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/** returns if a todo is completed
	 * @return boolean
	 */
	public boolean isCompleted() {
		return completed;
	}

	/** sets a todo is completed or not
	 * @param completed
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/** returns if a todo is removed from the list
	 * @return
	 */
	public boolean isRemoveSet() {
		return remove;
	}

	/** sets to remove a todo from the list
	 * @param remove
	 */
	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	/** returns if a todo is empty
	 * @return
	 */
	public boolean isEmptySet() {
		return empty;
	}

	/** sets a todo empty
	 * @param empty
	 */
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}


	/* (non-Javadoc)
	 * @see cst8284.assignment1.Task#getSubject()
	 */
	public String getSubject() {
		return this.subject;
	}

	/* (non-Javadoc)
	 * @see cst8284.assignment1.Task#setSubject(java.lang.String)
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}


	/* (non-Javadoc)
	 * @see cst8284.assignment1.Task#getTitle()
	 */
	public String getTitle() {
		return this.title;
	}

	/* (non-Javadoc)
	 * @see cst8284.assignment1.Task#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see cst8284.assignment1.Task#toString()
	 */
	@Override
	public String toString(){
		return (getDueDate().toString());
	}
}
