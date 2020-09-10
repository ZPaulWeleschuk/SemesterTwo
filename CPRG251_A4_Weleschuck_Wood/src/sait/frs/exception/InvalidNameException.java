package sait.frs.exception;

import javax.swing.JOptionPane;

public class InvalidNameException extends Exception{
	public InvalidNameException(){
		super("A name must be entered in the name column");
	}

}
